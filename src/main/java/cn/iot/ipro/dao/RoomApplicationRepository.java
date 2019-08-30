package cn.iot.ipro.dao;

import cn.iot.ipro.entity.RoomApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomApplicationRepository extends JpaRepository<RoomApplication, Long> {
    Page<RoomApplication> findAllByStateAndApplyTypeLikeOrderByIdDesc(int state, String applyType, Pageable pageable);
}
