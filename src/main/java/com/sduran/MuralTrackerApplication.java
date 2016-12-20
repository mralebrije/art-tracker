package com.sduran;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJSONDoc
public class MuralTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuralTrackerApplication.class, args);
	}

	private static final Logger LOG = LoggerFactory.getLogger(MuralTrackerApplication.class);
}
