package cn.iot.ipro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class IproApplication {
    public static void main(String[] args) {
        SpringApplication.run(IproApplication.class, args);
    }

}
