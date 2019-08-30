package cn.iot.ipro.model;

import lombok.Data;

@Data
public class UserDto {
    private String nickname;
    private String username;
    private String password;
    private String newpassword;
    private String inviteCode;
    private String thumbnail;
}
