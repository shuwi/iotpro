package cn.iot.ipro.model;

import lombok.Data;

@Data
public class ApplyRequest {
    private String applyType;
    private int state;
    private int pageNo;
    private int pageSize;
}
