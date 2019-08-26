package cn.iot.ipro.service;

import cn.iot.ipro.entity.InviteCode;

public interface IInviteCodeService {
    void addCode(InviteCode inviteCode);

    InviteCode getCodeByCodeValue(String code);
}
