package cn.iot.ipro.controller;

import cn.iot.ipro.entity.CheckCodeResponse;
import cn.iot.ipro.entity.EntranceGuardEntity;
import cn.iot.ipro.entity.IsConnect;
import cn.iot.ipro.entity.IsConnectResponse;
import cn.iot.ipro.service.ICheckCodeResponseService;
import cn.iot.ipro.service.IEntranceGuardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/eg/")
public class EntranceGuardController {
    @Autowired
    private IEntranceGuardService entranceGuardService;
    @Autowired
    private ICheckCodeResponseService checkCodeResponseService;

    /**
     * 数据上传接口
     * @param params
     * @return
     * @throws IOException
     */
    @PostMapping("CheckCode")
    public CheckCodeResponse checkCode(@RequestParam("paramaters") String params) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        EntranceGuardEntity entranceGuardEntity = objectMapper.readValue(params, EntranceGuardEntity.class);
        CheckCodeResponse checkCodeResponse = new CheckCodeResponse();
        checkCodeResponse.setUID(entranceGuardEntity.getUID());

        if(entranceGuardService.addEntranceGuard(entranceGuardEntity) > 1)
            checkCodeResponse.setStatus(1);
        else
            checkCodeResponse.setStatus(0);
        checkCodeResponseService.addCheckCodeResponse(checkCodeResponse);
        return checkCodeResponse;
    }

    /**
     * 设备通讯心跳接口 ，包括校时
     * @param params 示例 paramaters={"ViewId":"D2","UID":"1001"," UKey":"3F698DAC58","SN":"1701000110"}
     * @return IsConnectResponse
     * @throws IOException
     */
    @PostMapping("IsConnect")
    public IsConnectResponse isConnect(@RequestParam("paramaters") String params) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        IsConnect isConnect = objectMapper.readValue(params, IsConnect.class);
        System.out.println(isConnect.getSN());
        IsConnectResponse isConnectResponse = new IsConnectResponse();
        isConnectResponse.setDateTime(new Date());
        return isConnectResponse;
    }
}
