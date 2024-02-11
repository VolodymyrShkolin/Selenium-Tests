package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@DisplayName("API tests")
public class ApiTests {

    @BeforeAll
    public static void setBaseUrl(){
        RestAssured.baseURI = "https://api.spacexdata.com/v4";
    }

    @DisplayName("Check CEO")
    @Test
    public void checkCeo(){
        given().get("/company")
                .then().log().body()
                .body("ceo", equalTo("Elon Musk"));
    }

    @DisplayName("Check there are four company links")
    @Test
    public void checkFoundLinks(){
        LinkedHashMap<String, String> links = given().get("company")
                .then().log().body()
                .extract().body().jsonPath().get("links");
        Assertions.assertEquals(4, links.size());
    }

    @DisplayName("Check there are thirty employees")
    @Test
    public void allCrewMemebers(){
        ArrayList members = given().get("crew")
                .then().log().body()
                .extract().body().as(ArrayList.class);
        Assertions.assertEquals(30, members.size());
    }

    @DisplayName("Check there are twenty nine ships")
    @Test
    public void allCompanyShips(){
        List<String> ships = given().get("/ships")
                .then().log().body()
                .extract().body().jsonPath().get("legacy_id");
        Assertions.assertEquals(29, ships.size());
    }

}
