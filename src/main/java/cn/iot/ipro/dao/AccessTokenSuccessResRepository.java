package cn.iot.ipro.dao;

import cn.iot.ipro.entity.AccessTokenSuccessRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenSuccessResRepository extends JpaRepository<AccessTokenSuccessRes, Integer> {
    AccessTokenSuccessRes findFirstByAppId(String appId);
}
