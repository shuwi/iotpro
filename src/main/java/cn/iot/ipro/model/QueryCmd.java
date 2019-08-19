package cn.iot.ipro.model;

import lombok.Data;

@Data
public class QueryCmd {
    private String ViewId;
    private String UID;
    private String UKey;
    private String SN;
    private String TamperAlarm;
    private String DoorMagnetic;
}
