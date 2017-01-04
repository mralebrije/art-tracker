package com.sduran.api.web;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.DecoderConfig;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.config.ObjectMapperConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	private static final String DEFAULT_BASE_URI = "http://localhost:8080";
	private static final String DEFAULT_BASE_PATH = "/loggap";
	
	private String baseURI;
	private String basePath;

	
	@BeforeClass(alwaysRun = true)
	@Parameters({ "baseURI", "basePath" })
	public void initializeParameters(
			@Optional String baseURI, 
			@Optional String basePath) {
		
		this.baseURI = baseURI;
		this.basePath = basePath;
		
		if(StringUtils.isBlank(this.baseURI)){
			this.baseURI = DEFAULT_BASE_URI;
			this.basePath = DEFAULT_BASE_PATH;
			System.out.println("Using default configuration...");
		}
		
		RestAssured.baseURI = this.baseURI;
		RestAssured.basePath = this.basePath;
		
		ObjectMapperConfig config = new ObjectMapperConfig();
		RestAssured.config = new RestAssuredConfig()
								.decoderConfig(
										new DecoderConfig("UTF-8")
								)
								.encoderConfig(
										new EncoderConfig("UTF-8", "UTF-8").appendDefaultContentCharsetToContentTypeIfUndefined(false)
								);
	}
	
	public RequestSpecification getSuiteSpecification(){
		return RestAssured.given()
				.log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.header("Content-Type", MediaType.APPLICATION_JSON_VALUE);
	}
	
	public RequestSpecification getMultipartSuiteSpecification(){
		return RestAssured.given()
				.log().ifValidationFails()
				.contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
				.header("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
	}

}
