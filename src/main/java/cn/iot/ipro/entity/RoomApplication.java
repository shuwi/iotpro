package cn.iot.ipro.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class RoomApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank(message = "用户ID不能为空")
    @NotNull(message = "用户ID不能为空")
    private String userId;
    @Column
    @NotBlank(message = "用户openId不能为空")
    @NotNull(message = "用户openId为空")
    private String openId;
    @Column
    @NotBlank(message = "预约类型不能为空")
    @NotNull(message = "预约类型为空")
    private String applyType;
    @Column
    @NotBlank(message = "用户姓名不能为空")
    @NotNull(message = "用户姓名为空")
    private String userName;
    @Column
    @NotBlank(message = "用户联系电话不能为空")
    @NotNull(message = "用户联系电话为空")
    private String userPhone;
    @Column
    @NotNull(message = "开始时间不能为空")
    private Date begin;
    @Column
    @NotNull(message = "结束时间不能为空")
    private Date end;
    @Column
    private String applyDes;
    @Column
    private String forbiddenReason;
    @Column
    private Date created;
    @Column
    private Date modified;
    @Column
    private String modifiedBy;
    @Column
    private int state;
}
