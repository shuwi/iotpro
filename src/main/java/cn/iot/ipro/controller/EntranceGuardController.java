package cn.iot.ipro.controller;

import cn.iot.ipro.entity.*;
import cn.iot.ipro.model.IsConnect;
import cn.iot.ipro.model.IsConnectResponse;
import cn.iot.ipro.service.ICheckCodeResponseService;
import cn.iot.ipro.service.IEntranceGuardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/eg/")
public class EntranceGuardController {
    private IEntranceGuardService entranceGuardService;
    private ICheckCodeResponseService checkCodeResponseService;

    @Autowired
    public void setEntranceGuardService(IEntranceGuardService entranceGuardService) {
        this.entranceGuardService = entranceGuardService;
    }

    @Autowired
    public void setCheckCodeResponseService(ICheckCodeResponseService checkCodeResponseService) {
        this.checkCodeResponseService = checkCodeResponseService;
    }

    /**
     * 数据上传接口
     *
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

        if (entranceGuardService.addEntranceGuard(entranceGuardEntity) > 1)
            checkCodeResponse.setStatus(1);
        else
            checkCodeResponse.setStatus(0);
        checkCodeResponseService.addCheckCodeResponse(checkCodeResponse);
        return checkCodeResponse;
    }

    /**
     * 设备通讯心跳接口 ，包括校时
     *
     * @param params 示例 paramaters={"ViewId":"D2","UID":"1001"," UKey":"3F698DAC58","SN":"1701000110"}
     * @return IsConnectResponse
     * @throws IOException
     */
    @PostMapping("IsConnect")
    public IsConnectResponse isConnect(@RequestParam("paramaters") String params) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        IsConnect isConnect = objectMapper.readValue(params, IsConnect.class);
        System.out.println(isConnect.getSN());
        IsConnectResponse isConnectResponse = new IsConnectResponse();
        isConnectResponse.setDateTime(new Date());
        return isConnectResponse;
    }

}
