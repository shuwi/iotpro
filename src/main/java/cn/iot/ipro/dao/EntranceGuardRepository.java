package cn.iot.ipro.dao;

import cn.iot.ipro.entity.EntranceGuardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntranceGuardRepository extends JpaRepository<EntranceGuardEntity, Long> {
}
