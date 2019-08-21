package cn.iot.ipro.service;

import cn.iot.ipro.entity.RoomApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRoomApplicationService {
    void addRoomApplication(RoomApplication roomApplication);

    Page<RoomApplication> getList(Integer type, Pageable pageable);
}
