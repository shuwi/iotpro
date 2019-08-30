package cn.iot.ipro.service.impl;

import cn.iot.ipro.dao.RoomApplicationRepository;
import cn.iot.ipro.entity.RoomApplication;
import cn.iot.ipro.service.IRoomApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoomApplicationServiceImpl implements IRoomApplicationService {
    private RoomApplicationRepository roomApplicationRepository;

    @Autowired
    public void setRoomApplicationRepository(RoomApplicationRepository roomApplicationRepository) {
        this.roomApplicationRepository = roomApplicationRepository;
    }

    @Override
    public RoomApplication getRoomApplicationByID(long id) {
        return roomApplicationRepository.getOne(id);
    }

    @Override
    public void addRoomApplication(RoomApplication roomApplication) {
        roomApplicationRepository.save(roomApplication);
    }

    @Override
    public Page<RoomApplication> getList(Integer type, String applyType, Pageable pageable) {
        return roomApplicationRepository.findAllByStateAndApplyTypeLikeOrderByIdDesc(type, "%" + applyType + "%", pageable);
    }
}
