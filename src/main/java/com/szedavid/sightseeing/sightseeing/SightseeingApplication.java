package com.szedavid.sightseeing.sightseeing;

import com.szedavid.sightseeing.sightseeing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SightseeingApplication {
	// todo del
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SightseeingApplication.class, args);
	}

	// todo másképp?
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		userService.fillWithDemoUsers();
	}
}
