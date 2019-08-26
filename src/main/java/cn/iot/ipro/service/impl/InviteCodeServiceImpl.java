package cn.iot.ipro.service.impl;

import cn.iot.ipro.dao.InviteCodeRepository;
import cn.iot.ipro.entity.InviteCode;
import cn.iot.ipro.service.IInviteCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class InviteCodeServiceImpl implements IInviteCodeService {
    private InviteCodeRepository inviteCodeRepository;

    @Autowired
    public void setInviteCodeRepository(InviteCodeRepository inviteCodeRepository) {
        this.inviteCodeRepository = inviteCodeRepository;
    }

    @Override
    public void addCode(InviteCode inviteCode) {
        inviteCodeRepository.save(inviteCode);
    }

    @Override
    public InviteCode getCodeByCodeValue(String code) {
        return inviteCodeRepository.getByCodeVal(code);
    }
}
