package cn.iot.ipro.controller;

import cn.iot.ipro.config.ResultBean;
import cn.iot.ipro.entity.UserEntity;
import cn.iot.ipro.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("addUser")
    public ResultBean addUser(@RequestBody @Valid UserEntity userEntity, BindingResult results) {
        if (results.hasErrors())
            return ResultBean.error(500, results.getFieldError().getDefaultMessage());
        Long id = userService.addUser(userEntity);
        if (id > 0)
            return ResultBean.success();
        else
            return ResultBean.error(500, "user add error!");
    }
}
