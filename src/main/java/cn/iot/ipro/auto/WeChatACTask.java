package cn.iot.ipro.auto;

import cn.hutool.http.HttpUtil;
import cn.iot.ipro.config.WeChatAccConfig;
import cn.iot.ipro.entity.AccessTokenSuccessRes;
import cn.iot.ipro.service.IAccessTokenSuccessResService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@EnableScheduling
public class WeChatACTask {
    private static final Logger logger = LoggerFactory.getLogger(WeChatACTask.class);
    private WeChatAccConfig weChatAccConfig;
    private IAccessTokenSuccessResService accessTokenSuccessResService;

    @Autowired
    public void setWeChatAccConfig(WeChatAccConfig weChatAccConfig) {
        this.weChatAccConfig = weChatAccConfig;
    }

    @Autowired
    public void setAccessTokenSuccessResService(IAccessTokenSuccessResService accessTokenSuccessResService) {
        this.accessTokenSuccessResService = accessTokenSuccessResService;
    }

    /**
     * 每小时更新一次access_token
     */
    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void getAccessToken() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("grant_type", "client_credential");
        paramMap.put("appid", weChatAccConfig.getAppid());
        paramMap.put("secret", weChatAccConfig.getSecret());
        String res = HttpUtil.get(weChatAccConfig.getUrl(), paramMap);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            AccessTokenSuccessRes accessTokenSuccessRes = objectMapper.readValue(res, AccessTokenSuccessRes.class);
            accessTokenSuccessRes.setAppId(weChatAccConfig.getAppid());
            accessTokenSuccessResService.addAccessTokenSuccessRes(accessTokenSuccessRes);
            System.out.println(accessTokenSuccessRes.getAccessToken());
            System.out.println(accessTokenSuccessRes.getExpiresIn());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
}
