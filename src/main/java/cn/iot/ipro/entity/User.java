package cn.iot.ipro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    @Column
    private String inviteCode;
    @Column
    private String username;
    @Column
    private String nickname;
    @JsonIgnore
    @Column(columnDefinition = "longtext comment '用户头像'")
    private String thumbnail;
    @Column
    @JsonIgnore
    private String password;
    @Column
    @JsonIgnore
    private long salary;
    @Column
    @JsonIgnore
    private int age;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")})
    private List<Role> roles;

}
