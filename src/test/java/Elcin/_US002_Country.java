package Elcin;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class _US002_Country {

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

        loginResponse.then().statusCode(200); // Oturum açmanın başarılı olduğunu doğrulayın

        bearerToken = loginResponse.jsonPath().getString("access_token");
    }

    @Test
    public void _US002_Country_Test() {

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("code", "AL");
        jsonBody.put("hasState", true);
        jsonBody.put("id", "63919f4e6d489849480e9c6c");
        jsonBody.put("name", "Albania");
        jsonBody.put("translateName", null);

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .put("/school-service/api/countries");

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }
}
