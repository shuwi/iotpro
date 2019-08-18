package cn.iot.ipro.service.impl;

import cn.iot.ipro.dao.UserRepository;
import cn.iot.ipro.entity.UserEntity;
import cn.iot.ipro.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.transaction.Transactional;
import java.util.Date;

@Transactional
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public Long addUser(UserEntity userEntity) {
        userEntity.setCreateTime(new Date());
        userEntity.setPassWord(DigestUtils.md5DigestAsHex(userEntity.getPassWord().getBytes()));
        userRepository.save(userEntity);
        Long id = userEntity.getId();
        return id;
    }
    @Override
    public boolean userExisted(UserEntity userEntity){
        return userRepository.findByUserName(userEntity.getUserName()) == null;
    }

}