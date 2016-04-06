package com.webuhee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class ReactFileDialogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactFileDialogApplication.class, args);
	}
}
