package com.sandesh.springDataJpaHospitalManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.util.TimeZone;

@SpringBootApplication

public class SpringDataJpaHospitalManagementApplication {


    public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kathmandu"));
        SpringApplication.run(SpringDataJpaHospitalManagementApplication.class, args);
        System.out.println("Access via this url: http://localhost:8080/api/v1/swagger-ui/index.html");
    }
}
