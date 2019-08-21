package cn.iot.ipro.service.impl;

import cn.iot.ipro.dao.CheckCodeResponseRepository;
import cn.iot.ipro.entity.CheckCodeResponse;
import cn.iot.ipro.service.ICheckCodeResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class CheckCodeResponseServiceImpl implements ICheckCodeResponseService {
    private CheckCodeResponseRepository checkCodeResponseRepository;

    @Autowired
    public void setCheckCodeResponseRepository(CheckCodeResponseRepository checkCodeResponseRepository) {
        this.checkCodeResponseRepository = checkCodeResponseRepository;
    }

    @Override
    public long addCheckCodeResponse(CheckCodeResponse checkCodeResponse) {
        checkCodeResponse.setResDate(new Date());
        checkCodeResponseRepository.save(checkCodeResponse);
        return checkCodeResponse.getId();
    }
}
