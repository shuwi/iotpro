package cn.iot.ipro.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull(message = "用户名不能为空")
    private String userName;
    @Column
    @NotNull(message = "密码不能为空")
    private String passWord;
    @Column
    private String role;
    @Column
    private Date createTime;
}
