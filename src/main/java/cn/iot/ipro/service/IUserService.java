package cn.iot.ipro.service;

import cn.iot.ipro.config.ResultBean;
import cn.iot.ipro.entity.User;
import cn.iot.ipro.model.UserDto;

import java.util.List;
import java.util.Map;

public interface IUserService {
    ResultBean save(UserDto user);

    ResultBean userInfoUpdate(UserDto user);

    ResultBean getUserThumbnail(UserDto user);

    List<User> findAll();

    void delete(long id);

    User findOne(String username);

    User findById(Long id);
}
