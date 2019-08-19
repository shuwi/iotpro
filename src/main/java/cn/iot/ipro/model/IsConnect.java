package cn.iot.ipro.model;

import lombok.Data;

@Data
public class IsConnect {
    /**
     * 区域 ID
     */
    private String ViewId;
    /**
     * 设备 ID
     */
    private String UID;
    /**
     * 授权 KEY
     */
    private String Ukey;
    /**
     * 设备序列号
     */
    private String SN;
}
