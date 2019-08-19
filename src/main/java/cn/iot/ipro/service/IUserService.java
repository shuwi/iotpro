package cn.iot.ipro.service;

import cn.iot.ipro.entity.User;
import cn.iot.ipro.model.UserDto;

import java.util.List;

public interface IUserService {
    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}
