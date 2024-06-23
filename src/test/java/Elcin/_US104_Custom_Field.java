package Elcin;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class _US104_Custom_Field {

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
    public void _US104_Custom_Field_Test() {

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("schoolId", "66754da8358b9b451550a6a8");

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .post("/school-service/api/custom-field-groups/search");

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }
    @Test
    public void _US104_Add_Custom_Field_Test() {

        String name = "Elcin";
        int randomInt = new Random().nextInt(100000);

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", null);
        jsonBody.put("name", name + " " + randomInt);
        jsonBody.put("columnSize", "1");
        jsonBody.put("orderNo", randomInt);
        jsonBody.put("translateName", new ArrayList<>());
        jsonBody.put("schoolId", "6576fd8f8af7ce488ac69b89");

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .post("/school-service/api/custom-field-groups");

        response.then().statusCode(201);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void _US104_Update_Custom_Field_Test() {

        String name = "Elcin";
        int randomInt = new Random().nextInt(100000);

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", "66754da8358b9b451550a6a8");
        jsonBody.put("name", name + " " + randomInt);
        jsonBody.put("columnSize", "1");
        jsonBody.put("orderNo", randomInt);
        jsonBody.put("translateName", new ArrayList<>());
        jsonBody.put("schoolId", "6576fd8f8af7ce488ac69b89");

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .put("/school-service/api/custom-field-groups");

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void _US104_Failed_Update_Custom_Field_Test() {

        String name = "Elcin";
        int randomInt = new Random().nextInt(100000);

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", null);
        jsonBody.put("name", name + " " + randomInt);
        jsonBody.put("columnSize", "1");
        jsonBody.put("orderNo", 369);
        jsonBody.put("translateName", new ArrayList<>());
        jsonBody.put("schoolId", "6576fd8f8af7ce488ac69b89");

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .post("/school-service/api/custom-field-groups");

        response.then().statusCode(400);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void _US104_Failed_Delete_Custom_Field_Test() {
        String customID = "wrongID";

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .delete("/school-service/api/custom-field-groups/" + customID);

       // response.then().statusCode(400);
        System.out.println(response.getBody().asString());

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("invalid objectId"),
                "Response body should contain 'invalid objectId' message");
    }
}

