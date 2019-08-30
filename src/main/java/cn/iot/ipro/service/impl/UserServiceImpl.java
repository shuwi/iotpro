package cn.iot.ipro.service.impl;

import cn.iot.ipro.config.ResultBean;
import cn.iot.ipro.dao.RoleRepository;
import cn.iot.ipro.dao.UserRepository;
import cn.iot.ipro.entity.Role;
import cn.iot.ipro.entity.User;
import cn.iot.ipro.model.UserDto;
import cn.iot.ipro.service.IInviteCodeService;
import cn.iot.ipro.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserDetailsService, IUserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bcryptEncoder;

    private RoleRepository roleRepository;

    private IInviteCodeService inviteCodeService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setBcryptEncoder(BCryptPasswordEncoder bcryptEncoder) {
        this.bcryptEncoder = bcryptEncoder;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setInviteCodeService(IInviteCodeService inviteCodeService) {
        this.inviteCodeService = inviteCodeService;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            //authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findOne(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public ResultBean save(UserDto user) {

        if (findOne(user.getUsername()) != null)
            return ResultBean.error(-3, "该用户已存在");

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setInviteCode(user.getInviteCode());
        Role role = roleRepository.findByName("USER");
        if (null == role) {
            role = new Role();
            role.setName("USER");
            role.setDescription("normal");
            roleRepository.save(role);
        }
        newUser.setRoles(Arrays.asList(role));
        return ResultBean.success(userRepository.save(newUser));
    }

    @Override
    public ResultBean userInfoUpdate(UserDto user) {

        User u = findOne(user.getUsername());
        if (u == null)
            return ResultBean.error(-1, "用户不存在");

        if (!bcryptEncoder.matches(user.getPassword(), u.getPassword()))
            return ResultBean.error(-2, "原始密码不正确");

        if (!StringUtils.isEmpty(user.getNickname()))
            u.setNickname(user.getNickname());
        if (!StringUtils.isEmpty(user.getThumbnail()))
            u.setThumbnail(user.getThumbnail());
        if (!StringUtils.isEmpty(user.getNewpassword()))
            u.setPassword(bcryptEncoder.encode(user.getNewpassword()));
        return ResultBean.success(userRepository.save(u));
    }

    @Override
    public ResultBean getUserThumbnail(UserDto user) {

        User u = findOne(user.getUsername());
        if (u == null)
            return ResultBean.error(-1, "用户不存在");

        if (StringUtils.isEmpty(u.getThumbnail())) {
            try {
                ClassPathResource classPathResource = new ClassPathResource("icon-avatar.png");
                InputStream inputStream = classPathResource.getInputStream();
                byte[] data = new byte[inputStream.available()];
                inputStream.read(data);
                inputStream.close();
                BASE64Encoder encoder = new BASE64Encoder();
                u.setThumbnail(encoder.encode(data));
                // 通过base64来转化图片
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResultBean.success(u.getThumbnail());
    }
}
