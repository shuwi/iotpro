package cn.iot.ipro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class CheckCodeResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @JsonProperty("UID")
    @Column(nullable = false, columnDefinition = "varchar(100) comment '数据值，可为二维码值，IC 卡卡号，身份证序号等'")
    private String UID;
    @JsonProperty("Status")
    @Column(nullable = false, columnDefinition = "varchar(100) comment '返回状态'")
    private int Status;
    @JsonProperty("StatusDesc")
    @Column(columnDefinition = "varchar(100) comment '状态信息描述'")
    private String StatusDesc;
    @JsonProperty("Relay1Time")
    @Column(columnDefinition = "varchar(100) comment '继电器1动作时间'")
    private int Relay1Time;
    @JsonProperty("BeepType")
    @Column(columnDefinition = "varchar(100) comment '蜂鸣器类型'")
    private int BeepType;
    @JsonProperty("BeepTime")
    @Column(columnDefinition = "varchar(100) comment '蜂鸣器时间'")
    private int BeepTime;
    @JsonProperty("MsgCode")
    @Column(columnDefinition = "varchar(100) comment '显示信息代码'")
    private String MsgCode;
    @JsonIgnore
    @Column(columnDefinition = "varchar(100) comment 'CheckCode返回时间'")
    private Date ResDate;
}
