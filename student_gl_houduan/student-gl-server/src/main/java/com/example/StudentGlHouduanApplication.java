package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;


@ServletComponentScan
@SpringBootApplication
public class StudentGlHouduanApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentGlHouduanApplication.class, args);
    }

}
