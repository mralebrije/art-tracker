package com.sduran;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJSONDoc
@ComponentScan(basePackages = "com.sduran")
@EntityScan(basePackages = { "com.sduran.model" })
@EnableJpaRepositories(basePackages = { "com.sduran.api.repository" })
public class ArtTrackerApplication{

	public static void main(String[] args) {
		SpringApplication.run(ArtTrackerApplication.class, args);
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(3600); // refresh cache once per hour
		return messageSource;
	}

	private static final Logger LOG = LoggerFactory.getLogger(ArtTrackerApplication.class);
}
