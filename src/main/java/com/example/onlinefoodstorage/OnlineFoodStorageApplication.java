package com.example.onlinefoodstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class OnlineFoodStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineFoodStorageApplication.class, args);
    }

}
