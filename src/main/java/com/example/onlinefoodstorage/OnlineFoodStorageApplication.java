package com.example.onlinefoodstorage;

import com.example.onlinefoodstorage.config.CacheConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(value = {CacheConfigurationProperties.class})
public class OnlineFoodStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineFoodStorageApplication.class, args);
    }

}
