package cn.iot.ipro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class InviteCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(nullable = false, columnDefinition = "varchar(100) comment '邀请码值'")
    private String codeVal;
    @Column(nullable = false, columnDefinition = "datetime comment '创建时间'")
    @JsonIgnore
    private Date createdDate;
}
