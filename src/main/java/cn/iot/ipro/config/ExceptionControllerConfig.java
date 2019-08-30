package cn.iot.ipro.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@ResponseBody
public class ExceptionControllerConfig {
    private static final Logger log = LoggerFactory.getLogger(ExceptionControllerConfig.class);

    @ExceptionHandler
    public ResultBean unknownException(Exception e) {
        e.printStackTrace();
        log.error("unknownException：" + e.getMessage());
        return ResultBean.error(-1, "系统异常，请联系管理员");
    }

    @ExceptionHandler
    public ResultBean authenticationException(AuthenticationException e) {
        log.warn("authenticationException：" + e.getMessage());
        return ResultBean.error(-2, "账户权限验证未通过");
    }

    @ExceptionHandler
    public ResultBean constraintViolationException(ConstraintViolationException e) {
        log.warn("constraintViolationException：" + e.getMessage());
        return ResultBean.error(-3, "数据校验错误，请核实输入数据");
    }
}
