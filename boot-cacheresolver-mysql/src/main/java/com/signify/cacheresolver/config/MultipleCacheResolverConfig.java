package com.signify.cacheresolver.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;

public class MultipleCacheResolverConfig implements CacheResolver {

	private final CacheManager simpleCacheManager;
    private final CacheManager caffeineCacheManager;    
    private static final String BOOK_CACHE = "books";    
    private static final String BOOK_ID_CACHE = "books_id";
    private static final String BOOK_TITLE_CACHE = "books_title";
    
    public MultipleCacheResolverConfig(CacheManager simpleCacheManager,CacheManager caffeineCacheManager) {
        this.simpleCacheManager = simpleCacheManager;
        this.caffeineCacheManager=caffeineCacheManager;
    }

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        Collection<Cache> caches = new ArrayList<Cache>();
        if ("findById".equals(context.getMethod().getName())) {
            caches.add(simpleCacheManager.getCache(BOOK_ID_CACHE));
        } else if("findByTitle".equals(context.getMethod().getName())) {
            caches.add(simpleCacheManager.getCache(BOOK_TITLE_CACHE));
        } else {
        	caches.add(caffeineCacheManager.getCache(BOOK_CACHE));
        }
        return caches;
    }

}
