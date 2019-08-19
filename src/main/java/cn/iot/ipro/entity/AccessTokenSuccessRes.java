package cn.iot.ipro.entity;

import lombok.Data;

@Data
public class AccessTokenSuccessRes {
    private String access_token;
    private String expires_in;
}
