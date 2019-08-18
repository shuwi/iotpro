package cn.iot.ipro.service;

import cn.iot.ipro.entity.UserEntity;

public interface IUserService {
    Long addUser(UserEntity userEntity);
    boolean userExisted(UserEntity userEntity);
}
