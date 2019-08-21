package cn.iot.ipro.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class QrCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank(message = "用户ID不能为空")
    @NotNull(message = "用户ID不能为空")
    private String userId;
    @Column
    @NotBlank(message = "区域ID不能为空")
    @NotNull(message = "用户ID不能为空")
    private String viewId;
    @Column
    private String begin;
    @Column
    @NotNull(message = "有效结束时间不能为空")
    private String end;
}
