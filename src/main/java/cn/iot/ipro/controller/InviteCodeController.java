package cn.iot.ipro.controller;

import cn.iot.ipro.config.ResultBean;
import cn.iot.ipro.entity.InviteCode;
import cn.iot.ipro.service.IInviteCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 邀请码控制器
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/invite/")
public class InviteCodeController {
    private IInviteCodeService inviteCodeService;

    @Autowired
    public void setInviteCodeService(IInviteCodeService inviteCodeService) {
        this.inviteCodeService = inviteCodeService;
    }

    @RequestMapping(value = "check")
    @ResponseBody
    public ResultBean check(@RequestBody InviteCode ic) {
        InviteCode inviteCode = inviteCodeService.getCodeByCodeValue(ic.getCodeVal());
        if (inviteCode == null)
            return ResultBean.error(-1, "邀请码有误");
        return ResultBean.success(inviteCode);
    }
}
