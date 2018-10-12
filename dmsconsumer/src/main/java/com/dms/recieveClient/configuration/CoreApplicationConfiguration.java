package com.dms.recieveClient.configuration;

import com.dms.recieveClient.config.JPAConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({ "com.dms.recieveClient.configuration", "com.dms.recieveClient.service", "com.dms.recieveClient.common"/*, "com.emxcel.dms.core.business.modules.auth"*/})
@PropertySources({ @PropertySource("classpath:email.properties"), @PropertySource("classpath:messages.properties") })
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableAsync
@Import({ DBConfig.class, EhCacheConfiguration.class, JPAConfig.class, TxAdviceInterceptor.class, EmailConfig.class })
public class CoreApplicationConfiguration {

}