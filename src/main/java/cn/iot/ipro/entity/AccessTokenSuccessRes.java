package cn.iot.ipro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class AccessTokenSuccessRes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    @JsonIgnore
    @Column
    private String appId;
    @JsonProperty("access_token")
    @Column(nullable = false, columnDefinition = "varchar(255) comment 'token'")
    private String accessToken;
    @JsonProperty("expires_in")
    @Column(nullable = false, columnDefinition = "int(100) comment '有效时间'")
    private int expiresIn;
    @JsonIgnore
    @Column
    private Date created;
    @JsonIgnore
    @Column
    private Date modified;
}
