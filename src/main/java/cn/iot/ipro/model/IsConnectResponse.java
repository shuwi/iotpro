package cn.iot.ipro.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class IsConnectResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date DateTime;
}
