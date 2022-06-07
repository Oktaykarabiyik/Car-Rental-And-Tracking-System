package com.project.webapp;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.project.webapp.services.UserService;//DatabaseInitializer bunu tabloya kullanıcı insert etmek için WebappApplication classının yanına koy

//kullanıcı tablosunda password encrypted insert edilmesi gerekiyor
@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent>{

	private final UserService userService;

    public DatabaseInitializer(UserService userService) {
        this.userService = userService;
    }
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
	userService.createUser(2L, "ismail", "deneme");
	userService.createUser(3L, "oktay2", "123456");
		//userService.createUser(3L, "nisan", "123");
	}

}
