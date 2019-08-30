package cn.iot.ipro.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PayFor {
    @NotNull(message = "购物数量不能为空")
    private int num;
    @NotBlank(message = "商品不能为空")
    private Long materialId;
    @NotBlank(message = "购物人不能为空")
    private String createdBy;
    @NotBlank(message = "购物人姓名不能为空")
    private String userName;
    @NotBlank(message = "联系电话不能为空")
    private String userPhone;
    @NotBlank(message = "点单房间不能为空")
    private String roomType;
}
