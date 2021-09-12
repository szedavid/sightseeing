package com.szedavid.sightseeing;

import com.szedavid.sightseeing.service.RoleService;
import com.szedavid.sightseeing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableFeignClients
public class SightseeingApplication {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SightseeingApplication.class, args);
    }

    // In a real project I would use Liquibase
    @EventListener(ApplicationReadyEvent.class)
    public void createDemoUsers() {
        roleService.initForDemo();
        userService.initForDemo();
    }
}
