package com.jaadla.namlogapi.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@org.springframework.cache.annotation.CacheConfig(cacheNames = "messages")
@EnableCaching
public class CacheConfig {

}

