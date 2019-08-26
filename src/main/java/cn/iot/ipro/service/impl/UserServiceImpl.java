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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        if (inviteCodeService.getCodeByCodeValue(user.getInviteCode()) == null)
            return ResultBean.error(-2, "邀请码错误或者不存在");

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
}
