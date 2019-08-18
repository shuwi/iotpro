package cn.iot.ipro.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class EntranceGuardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @JsonProperty("CodeVal")
    @Column(nullable = false, columnDefinition = "varchar(100) comment '数据值，可为二维码值，IC 卡卡号，身份证序号等'")
    private String CodeVal;
    @Column(nullable = false, columnDefinition = "varchar(100) comment '数据类型，Q 二维码；C IC卡；I 身份证；B 按钮事件；K 密码输入（密码为完整数字）'")
    @JsonProperty("CodeType")
    private String CodeType;
    @Column(nullable = false, columnDefinition = "datetime comment '日期时间，yyyy-MM-dd HH:mm:ss据值'")
    @JsonProperty("BrushTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date BrushTime;
    @Column(columnDefinition = "varchar(100) comment '所属区域 ID，可设置'")
    @JsonProperty("ViewId")
    private String ViewId;
    @Column(columnDefinition = "varchar(100) comment '设备ID'")
    @JsonProperty("UID")
    private String UID;
    @Column(columnDefinition = "varchar(100) comment '授权KEY，用于与服务器校验'")
    @JsonProperty("UKey")
    private String UKey;
    @Column(nullable = false, columnDefinition = "varchar(2) comment '是否在线数据，1 为在线验证实时数据，0 为离线脱机数据'")
    @JsonProperty("IsOnline")
    private String IsOnline;
    @JsonProperty("Property")
    @Column(columnDefinition = "varchar(2) comment '脱机数据合法卡性，1 为合法，非 1 为非法;仅针对离线脱机数据有效'")
    private String Property;
    @JsonProperty("SN")
    @Column(nullable = false, columnDefinition = "datetime comment '唯一的设备序列号'")
    private String SN;
    @JsonIgnore
    @Column(nullable = false, columnDefinition = "datetime comment '设备交互时间'")
    private Date PostDate;
}
