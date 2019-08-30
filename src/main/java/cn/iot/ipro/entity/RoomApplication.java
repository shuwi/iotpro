package cn.iot.ipro.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
    @JsonProperty("userId")
    private String userId;
    @Column
    @JsonProperty("openId")
    private String openId;
    @Column
    @NotBlank(message = "预约类型不能为空")
    @NotNull(message = "预约类型为空")
    @JsonProperty("applyType")
    private String applyType;
    @Column
    @NotBlank(message = "用户姓名不能为空")
    @NotNull(message = "用户姓名为空")
    @JsonProperty("userName")
    private String userName;
    @Column
    @NotBlank(message = "用户联系电话不能为空")
    @NotNull(message = "用户联系电话为空")
    @JsonProperty("userPhone")
    private String userPhone;
    @Column
    @NotBlank(message = "预约单位不能为空")
    @NotNull(message = "预约单位为空")
    @JsonProperty("userOrg")
    private String userOrg;
    @Column
    @JsonProperty("guestTo")
    private String guestTo;//被访问人
    @Column
    @JsonProperty("applyDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date applyDate;
    @Column
    @JsonProperty("fromTo")
    private String fromTo;
    @Column
    @JsonProperty("applyDes")
    private String applyDes;
    @Column
    @JsonProperty("forbiddenReason")
    private String forbiddenReason;
    @Column
    private Date created;
    @Column
    private Date modified;
    @Column
    private String modifiedBy;
    @Column
    private int state;//审核状态
    @Column
    private int applyNum;//预约人数
    @Column
    private String room;
    @Column(nullable = true, columnDefinition = "decimal(11,2)")
    public BigDecimal price;
}
