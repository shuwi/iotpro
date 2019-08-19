package cn.iot.ipro.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionControllerConfig {
    private static final Logger log = LoggerFactory.getLogger(ExceptionControllerConfig.class);

    @ExceptionHandler
    public ResultBean unknownException(Exception e) {
        log.error("unknownException："+ e.getMessage());
        return ResultBean.error(-1, e.getMessage());
    }

    @ExceptionHandler
    public ResultBean authenticationException(AuthenticationException e){
        log.error("authenticationException："+ e.getMessage());
        return ResultBean.error(-2, e.getMessage());
    }
}
