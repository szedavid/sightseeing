package com.szedavid.sightseeing;

import com.szedavid.sightseeing.service.UserService;
import com.szedavid.sightseeing.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableFeignClients
public class SightseeingApplication {
	// todo del
	@Autowired
	RoleService roleService;
	// todo del
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SightseeingApplication.class, args);
	}

	// todo with Liquibase
	@EventListener(ApplicationReadyEvent.class)
	public void createDemoUsers() {
		roleService.init();
		userService.fillWithDemoUsers();
	}
}
