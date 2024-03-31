package com.example.onlinefoodstorage.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "redis.cache")
@Data
public class CacheConfigProperties {
   private String port;
   private String host;
   private Map<String,String> caches;
}
