package cn.iot.ipro.controller;

import cn.iot.ipro.config.TokenProvider;
import cn.iot.ipro.dao.UserRepository;
import cn.iot.ipro.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private TokenProvider jwtTokenUtil;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtTokenUtil(TokenProvider jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        Map<String, Object> res = new HashMap<>();
        res.put("token", token);
        res.put("user", userRepository.findByUsername(loginUser.getUsername()));
        return ResponseEntity.ok(res);
    }

    @RequestMapping(value = "/")
    public ResponseEntity index() {
        return ResponseEntity.ok("世界和平！");
    }

}
