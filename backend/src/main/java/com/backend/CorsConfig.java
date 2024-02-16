package com.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
	  
	@Bean
	public CorsFilter CorsFilter() {
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       CorsConfiguration config = new CorsConfiguration();
       config.setAllowCredentials(true);
       config.addAllowedOriginPattern("*"); // Use allowedOriginPatterns instead of allowedOrigins
       config.addAllowedHeader("*");
       config.addAllowedMethod("OPTIONS");
       config.addAllowedMethod("GET");
       config.addAllowedMethod("POST");
       config.addAllowedMethod("PUT");
       config.addAllowedMethod("DELETE");
       source.registerCorsConfiguration("/**", config);
       return new CorsFilter(source);

       
	}

}
