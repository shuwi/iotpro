package cn.iot.ipro.controller;

import cn.iot.ipro.config.ResultBean;
import cn.iot.ipro.entity.RoomApplication;
import cn.iot.ipro.service.IRoomApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/roomapply/")
public class RoomApplicationController {
    private IRoomApplicationService roomApplicationService;

    @Autowired
    public void setRoomApplicationService(IRoomApplicationService roomApplicationService) {
        this.roomApplicationService = roomApplicationService;
    }

    @RequestMapping(value = "new")
    @ResponseBody
    public ResultBean add(@RequestBody @Valid RoomApplication roomApplication, BindingResult results) {
        if (results.hasErrors()) {
            return ResultBean.error(-2, Objects.requireNonNull(results.getFieldError()).getDefaultMessage());
        }
        roomApplication.setCreated(new Date());
        roomApplication.setState(0);
        roomApplicationService.addRoomApplication(roomApplication);
        return ResultBean.success();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "list")
    @ResponseBody
    public ResponseEntity getList(@RequestParam("type") int type, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return ResponseEntity.ok(roomApplicationService.getList(type, pageable));
    }
}
