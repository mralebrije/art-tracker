package com.sduran.api.web;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.sduran.ArtTrackerApplication;
import com.sduran.api.repository.MuseumRepository;
import com.sduran.api.web.builder.MuseumBuilder;
import com.sduran.api.web.request.MuseumRequest;
import com.sduran.model.Museum;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ArtTrackerApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class MuseumControllerIT extends BaseTest {
    private static final String MUSEUMS_RESOURCE = "art-tracker/museum";
    private static final String NAME_FIELD  = "name";
    private static final String ZERO_ITEM_NAME = "Zero museum";
    private static final String FIRST_ITEM_NAME = "First museum";
    private static final String SECOND_ITEM_NAME = "Second museum";
    private static final String THIRD_ITEM_NAME = "Third museum";

    private static final MuseumRequest FIRST_ITEM = new MuseumBuilder()
            .name(FIRST_ITEM_NAME)
            .address("address 1")
            .councilDistrict(1)
            .neighborhood("neighborhood 1")
            .policeDistrict("policeDistrict 1")
            .zipCode("zipCode 1")
            .build();
    private static final MuseumRequest SECOND_ITEM = new MuseumBuilder()
            .name(SECOND_ITEM_NAME)
            .address("address 2")
            .councilDistrict(2)
            .neighborhood("neighborhood 2")
            .policeDistrict("policeDistrict 2")
            .zipCode("zipCode 2")
            .build();
    private static final MuseumRequest THIRD_ITEM = new MuseumBuilder()
            .name(THIRD_ITEM_NAME)
            .address("address 3")
            .councilDistrict(3)
            .neighborhood("neighborhood 3")
            .policeDistrict("policeDistrict 3")
            .zipCode("zipCode 3")
            .build();

    private static final Museum MUSEUM_ITEM = new Museum(ZERO_ITEM_NAME, "address 0", "neghborhood 0", 0, "policeDistrict 0", "zipCode 0");

    private String museumId;


    @Autowired
    private MuseumRepository repository;
    @Value("${local.server.port}")
    private int serverPort;
    private MuseumRequest firstItem;
    private MuseumRequest secondItem;

    @Before
    public void setUp() {
        repository.deleteAll();
        repository.save(MUSEUM_ITEM);
        RestAssured.port = serverPort;
    }

    @Test
    public void getItemsShouldReturnBothItems() {

        Response response = getSuiteSpecification()
                .given()
                .with()
                .when()
                .get(MUSEUMS_RESOURCE);

        response.then()
                .assertThat()
                .body("total", Matchers.equalTo(1))
                .statusCode(HttpStatus.SC_OK);

        System.out.println("Museum list: " + response.jsonPath().get("museums"));
    }

    @Test
    public void successCreateMuseum() {

        Response response = getSuiteSpecification()
                .given()
                .with()
                .body(THIRD_ITEM)
                .when()
                .post(MUSEUMS_RESOURCE);

        response.then()
                .log().ifValidationFails()
                .assertThat()
                .body("id", Matchers.not(Matchers.isEmptyString()))
                .statusCode(HttpStatus.SC_OK);

        museumId = response.jsonPath().getString("id");
        System.out.println("Museum ID: " + museumId);
    }
}