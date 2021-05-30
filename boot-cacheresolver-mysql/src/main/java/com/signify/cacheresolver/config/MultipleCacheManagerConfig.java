package com.signify.cacheresolver.config;

import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class MultipleCacheManagerConfig extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("books");
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }
    
    Caffeine < Object, Object > caffeineCacheBuilder() {
	  return Caffeine.newBuilder()
	   .initialCapacity(100)
	   .maximumSize(500)
	   .expireAfterAccess(10, TimeUnit.MINUTES)
	   .weakKeys()
	   .recordStats();
	 }

    @Bean
    public CacheManager alternateCacheManager() {
        return new ConcurrentMapCacheManager("books_id", "books_title");
    }

    @Bean
    public CacheResolver cacheResolver() {
        return new MultipleCacheResolverConfig(alternateCacheManager(), cacheManager());
    }
}
