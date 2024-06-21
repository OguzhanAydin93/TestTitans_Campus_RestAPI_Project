package Elcin;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class _US101_States {

    private String bearerToken;

    @BeforeMethod
    public void setup() {
        baseURI = "https://test.mersys.io";

        // Oturum açma isteği
        String loginRequestBody = "{ \"username\": \"turkeyts\", \"password\": \"TechnoStudy123\" }";

        Response loginResponse = given()
                .contentType(ContentType.JSON)
                .body(loginRequestBody)
                .post("/auth/login");

        loginResponse.then().statusCode(200);

        bearerToken = loginResponse.jsonPath().getString("access_token");
    }

    @Test
    public void _US101_States_Create_Test() {

        String randomCity = RandomStringUtils.randomAlphabetic(10);

        String environmentCountryID = "63919f4e6d489849480e9c6c";

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", null);
        jsonBody.put("name", randomCity);
        jsonBody.put("shortName", "elcin123456");

        Map<String, Object> country = new HashMap<>();
        country.put("id", environmentCountryID);
        jsonBody.put("country", country);

        jsonBody.put("translateName", new String[]{});

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .post("/school-service/api/states");

        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody().asString());

        response.then().statusCode(201);
    }

    @Test
    public void _US101_States_Update_Test() {

        String randomCity = RandomStringUtils.randomAlphabetic(10);
        String randomShortName = RandomStringUtils.randomAlphabetic(10);

        String environmentCountryID = "63919f4e6d489849480e9c6c";

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", environmentCountryID);
        jsonBody.put("name", randomCity);
        jsonBody.put("shortName", randomShortName);

        Map<String, Object> country = new HashMap<>();
        country.put("id", environmentCountryID);
        jsonBody.put("country", country);

        jsonBody.put("translateName", new String[]{});

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .put("/school-service/api/states");

        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody().asString());

        response.then().statusCode(200);
    }

    @Test
    public void _US101_States_Delete_Test() {

        String randomCity = RandomStringUtils.randomAlphabetic(10);
        String randomShortName = RandomStringUtils.randomAlphabetic(10);

        String environmentCountryID = "63919f4e6d489849480e9c6c";

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", environmentCountryID);
        jsonBody.put("name", randomCity);
        jsonBody.put("shortName", randomShortName);

        Map<String, Object> country = new HashMap<>();
        country.put("id", environmentCountryID);
        jsonBody.put("country", country);

        jsonBody.put("translateName", new String[]{});

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .delete("/school-service/api/states/"+environmentCountryID);

        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody().asString());

        response.then().statusCode(200);
    }
}
