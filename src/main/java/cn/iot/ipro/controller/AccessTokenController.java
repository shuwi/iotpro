package cn.iot.ipro.controller;

import cn.iot.ipro.dao.AccessTokenSuccessResRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/access")
public class AccessTokenController {
    private AccessTokenSuccessResRepository accessTokenSuccessResRepository;

    @Autowired
    public void setAccessTokenSuccessResRepository(AccessTokenSuccessResRepository accessTokenSuccessResRepository) {
        this.accessTokenSuccessResRepository = accessTokenSuccessResRepository;
    }

    @RequestMapping(value = "/gettoken", method = RequestMethod.POST)
    public ResponseEntity checkCode(@RequestParam("appid") String appid) {
        return ResponseEntity.ok(accessTokenSuccessResRepository.findFirstByAppId(appid));
    }

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public ResponseEntity demo() {
        return ResponseEntity.ok("OK");
    }
}
