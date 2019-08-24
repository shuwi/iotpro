package cn.iot.ipro.dao;

import cn.iot.ipro.entity.CheckCodeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckCodeResponseRepository extends JpaRepository<CheckCodeResponse, Long> {
}
