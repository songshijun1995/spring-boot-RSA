package com.rsa;

import com.rsa.annotation.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSecurity
public class RsaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RsaApplication.class, args);
    }

}
