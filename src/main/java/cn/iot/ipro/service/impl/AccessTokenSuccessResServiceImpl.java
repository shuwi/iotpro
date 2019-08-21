package cn.iot.ipro.service.impl;

import cn.iot.ipro.dao.AccessTokenSuccessResRepository;
import cn.iot.ipro.entity.AccessTokenSuccessRes;
import cn.iot.ipro.service.IAccessTokenSuccessResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class AccessTokenSuccessResServiceImpl implements IAccessTokenSuccessResService {
    private AccessTokenSuccessResRepository accessTokenSuccessResRepository;

    @Autowired
    public void setAccessTokenSuccessResRepository(AccessTokenSuccessResRepository accessTokenSuccessResRepository) {
        this.accessTokenSuccessResRepository = accessTokenSuccessResRepository;
    }

    @Override
    public void addAccessTokenSuccessRes(AccessTokenSuccessRes accessTokenSuccessRes) {
        AccessTokenSuccessRes accessTokenSuccessRes1 = accessTokenSuccessResRepository.findFirstByAppId(accessTokenSuccessRes.getAppId());
        if (null == accessTokenSuccessRes1) {
            accessTokenSuccessRes.setCreated(new Date());
            accessTokenSuccessResRepository.save(accessTokenSuccessRes);
        } else {
            accessTokenSuccessRes1.setModified(new Date());
            accessTokenSuccessRes1.setExpiresIn(accessTokenSuccessRes.getExpiresIn());
            accessTokenSuccessRes1.setAccessToken(accessTokenSuccessRes.getAccessToken());
            accessTokenSuccessResRepository.save(accessTokenSuccessRes1);
        }
    }
}
