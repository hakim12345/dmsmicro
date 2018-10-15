package com.dms.recieveClient.configuration;

import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class EhCacheConfiguration {

	@Bean
	public EhCacheCacheManager ehCacheCacheManager() {
		return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("spring/ehcache.xml"));
		ehCacheManagerFactoryBean.setShared(true);
		return ehCacheManagerFactoryBean;
	}

	@Bean(name = "serviceCache")
	public Cache serviceCache() {
		return ehCacheCacheManager().getCache("com.dms.recieveClient.OBJECT_CACHE");
	}

}