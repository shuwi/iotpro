package cn.iot.ipro.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccConfig {
    private String url;
    private String appid;
    private String secret;
}
