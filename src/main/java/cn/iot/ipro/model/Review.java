package cn.iot.ipro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class Review {
    @NotNull(message = "预约单主键不能为空")
    @JsonProperty("applyId")
    private int applyId;
    @NotNull(message = "是否通过不能为空")
    private int agreed;
    @NotNull(message = "审核人用户名不能为空")
    @JsonProperty("viewedBy")
    private String viewedBy;
    private String reason;
    private String room;
    private BigDecimal price;
}
