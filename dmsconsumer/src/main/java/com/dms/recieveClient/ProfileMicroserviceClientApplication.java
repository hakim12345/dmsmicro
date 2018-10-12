package com.dms.recieveClient;

import com.dms.recieveClient.urlRepository.ProfileRepository;
import com.dms.recieveClient.urlRepository.impl.RemoteMailRepository;
import com.dms.recieveClient.urlRepository.impl.RemoteProfileRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class ProfileMicroserviceClientApplication {
	
	public static final String PROFILES_SERVICE_URL = "http://PROFILES-MICROSERVICE-PRODUCER";

	public static final String MAIL_SERVICE_URL = "http://MAIL-MICROSERVICE-PRODUCER";


	public static void main(String[] args) {
		SpringApplication.run(ProfileMicroserviceClientApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ProfileRepository profileRepository(){
		return new RemoteProfileRepository(PROFILES_SERVICE_URL);
	}

	@Bean
	public RemoteMailRepository remoteMailRepository(){
		return new RemoteMailRepository(MAIL_SERVICE_URL);
	}

}