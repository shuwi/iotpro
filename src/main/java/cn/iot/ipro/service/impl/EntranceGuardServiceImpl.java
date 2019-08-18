package cn.iot.ipro.service.impl;

import cn.iot.ipro.dao.EntranceGuardRepository;
import cn.iot.ipro.entity.EntranceGuardEntity;
import cn.iot.ipro.service.IEntranceGuardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class EntranceGuardServiceImpl implements IEntranceGuardService {
    @Autowired
    private EntranceGuardRepository entranceGuardRepository;
    @Override
    public Long addEntranceGuard(EntranceGuardEntity entranceGuardEntity) {
        entranceGuardEntity.setPostDate(new Date());
        entranceGuardRepository.save(entranceGuardEntity);
        Long id = entranceGuardEntity.getId();
        return id;
    }
}
