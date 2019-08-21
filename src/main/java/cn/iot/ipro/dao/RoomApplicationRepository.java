package cn.iot.ipro.dao;

import cn.iot.ipro.entity.RoomApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomApplicationRepository extends JpaRepository<RoomApplication, Long> {
    Page<RoomApplication> findAllByState(int state, Pageable pageable);
}
