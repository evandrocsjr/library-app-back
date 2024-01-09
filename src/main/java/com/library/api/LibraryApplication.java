package com.library.api;

import com.library.api.domain.demo.DemoDataBaseService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Biblioteca API", version = "1.0"))
public class LibraryApplication {

    public static void main(String[] args) {
        ApplicationContext application = SpringApplication.run(LibraryApplication.class, args);
        application.getBean(DemoDataBaseService.class).createUserAndBooks();
    }

}
