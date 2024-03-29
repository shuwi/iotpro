package cn.iot.ipro.controller;

import cn.iot.ipro.config.ResultBean;
import cn.iot.ipro.entity.RoomApplication;
import cn.iot.ipro.model.ApplyRequest;
import cn.iot.ipro.model.Review;
import cn.iot.ipro.service.IRoomApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/apply/")
public class RoomApplicationController {
    private IRoomApplicationService roomApplicationService;

    @Autowired
    public void setRoomApplicationService(IRoomApplicationService roomApplicationService) {
        this.roomApplicationService = roomApplicationService;
    }

    @RequestMapping(value = "new")
    @ResponseBody
    public ResultBean add(@RequestBody @Valid RoomApplication roomApplication, BindingResult results) {
        if (results.hasErrors())
            return ResultBean.error(-2, Objects.requireNonNull(results.getFieldError()).getDefaultMessage());

        roomApplication.setCreated(new Date());
        roomApplication.setState(0);
        roomApplicationService.addRoomApplication(roomApplication);
        return ResultBean.success();
    }

    @RequestMapping(value = "wechatnew")
    @ResponseBody
    public ResultBean addNew(@RequestBody @Valid RoomApplication roomApplication, BindingResult results) {
        if (results.hasErrors())
            return ResultBean.error(-2, Objects.requireNonNull(results.getFieldError()).getDefaultMessage());

        roomApplication.setCreated(new Date());
        roomApplication.setState(0);
        roomApplicationService.addRoomApplication(roomApplication);
        return ResultBean.success();
    }

    @RequestMapping(value = "list")
    public ResponseEntity getList(@RequestBody ApplyRequest params) {
        Pageable pageable = PageRequest.of(params.getPageNo(), params.getPageSize());
        return ResponseEntity.ok(roomApplicationService.getList(params.getState(), params.getApplyType(), pageable));
    }

    @RequestMapping(value = "review")
    public ResultBean review(@RequestBody @Valid Review review, BindingResult results) {
        if (results.hasErrors())
            return ResultBean.error(-2, Objects.requireNonNull(results.getFieldError()).getDefaultMessage());
        RoomApplication roomApplication = roomApplicationService.getRoomApplicationByID(review.getApplyId());
        if (null == roomApplication)
            return ResultBean.error(-1, "预约单不存在");

        roomApplication.setState(review.getAgreed());
        roomApplication.setForbiddenReason(review.getReason());
        roomApplication.setModifiedBy(review.getViewedBy());
        roomApplication.setModified(new Date());
        roomApplication.setRoom(review.getRoom());
        roomApplication.setPrice(review.getPrice());
        roomApplicationService.addRoomApplication(roomApplication);
        return ResultBean.success();
    }

}
