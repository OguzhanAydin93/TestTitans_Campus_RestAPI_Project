package Elcin;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class _US102_Cities {

    private String bearerToken;
    private String cityId;

    @BeforeMethod
    public void setup() {

        baseURI = "https://test.mersys.io";

        String loginRequestBody = "{ \"username\": \"turkeyts\", \"password\": \"TechnoStudy123\" }";

        Response loginResponse = given()
                .contentType(ContentType.JSON)
                .body(loginRequestBody)
                .post("/auth/login");

        loginResponse.then().statusCode(200);

        bearerToken = loginResponse.jsonPath().getString("access_token");
    }

    @Test
    public void _US101_Cities_Create_Test() {
        // Yeni bir şehir oluşturma işlemi
        String randomCity = "Istanbul";
        int randomInt = new Random().nextInt(100000);
        String environmentCountryID = "63919f4e6d489849480e9c6c";

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", null);
        jsonBody.put("name", randomCity + " " + randomInt);
        Map<String, Object> country = new HashMap<>();
        country.put("id", environmentCountryID);
        jsonBody.put("country", country);
        jsonBody.put("state", null);
        jsonBody.put("translateName", new ArrayList<>());

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .post("/school-service/api/cities");

        // Hata mesajını kontrol etmek için
        if (response.statusCode() != 201) {
            System.out.println("Response status code: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody().asString());
        }

        response.then().statusCode(201);

        cityId = response.jsonPath().getString("id");
        System.out.println("Created city ID: " + cityId);
    }

    @Test(dependsOnMethods = "_US101_Cities_Create_Test")
    public void _US101_Cities_Update_Test() {
        // Oluşturulan şehrin güncellenmesi işlemi
        if (cityId == null) {
            throw new RuntimeException("City ID is null. Cannot proceed with update test.");
        }

        String updatedCityName = "Updated City Name";

        Map<String, Object> updateBody = new HashMap<>();
        updateBody.put("id", cityId); // Güncellenecek şehrin ID'si
        updateBody.put("name", updatedCityName); // Güncellenmiş şehir adı
        Map<String, Object> country = new HashMap<>();
        country.put("id", "63919f4e6d489849480e9c6c"); // Aynı ülke ID'sini kullan
        updateBody.put("country", country);
        updateBody.put("state", null);
        updateBody.put("translateName", new ArrayList<>());

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response updateResponse = given()
                .spec(reqSpec)
                .body(updateBody)
                .get("/school-service/api/cities/" + cityId);

        // Hata mesajını kontrol etmek için
        if (updateResponse.statusCode() != 200) {
            System.out.println("Response status code: " + updateResponse.getStatusCode());
            System.out.println("Response body: " + updateResponse.getBody().asString());
        }

        updateResponse.then().statusCode(200);
    }
    @Test(dependsOnMethods = "_US101_Cities_Create_Test")
    public void _US102_Cities_Delete_Test() {
        if (cityId == null) {
            throw new RuntimeException("City ID is null. Cannot proceed with delete test.");
        }

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .delete("/school-service/api/cities/" + cityId);

        if (response.statusCode() != 200) {
            System.out.println("Response status code: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody().asString());
        }

        response.then().statusCode(200);

        System.out.println("Deleted city ID: " + cityId);
    }

}
