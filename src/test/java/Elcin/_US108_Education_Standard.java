package Elcin;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class _US108_Education_Standard {
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
    public void _US108_Get_Education_Standard() {

        String schoolID="6576fd8f8af7ce488ac69b89";

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .get("/school-service/api/education-standard/school/"+schoolID);

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void _US108_Add_Education_Standard() {

        String name="Elcin";
        int randomInt = new Random().nextInt(100000);

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", null);
        jsonBody.put("name",name+" "+randomInt);
        jsonBody.put("description", "deneme");
        jsonBody.put("schoolId", "6576fd8f8af7ce488ac69b89");

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .post("/school-service/api/education-standard");

        response.then().statusCode(201);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void _US108_Update_Education_Standard() {

        String name="Elcin";
        int randomInt = new Random().nextInt(100000);

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", "6678b90d66bab932bcdc3a44");
        jsonBody.put("name",name+" "+randomInt);
        jsonBody.put("description", "deneme");
        jsonBody.put("schoolId", "6576fd8f8af7ce488ac69b89");

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .put("/school-service/api/education-standard");

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void _US108_Delete_Education_Standard() {

        String standardId="6678ba1f66bab932bcdc3a45";

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .delete("/school-service/api/education-standard/"+standardId);

        response.then().statusCode(204);
        System.out.println(response.getBody().asString());
    }
    @Test
    public void _US108_Failed_Delete_Education_Standard() {

        String standardId="wrongID";

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .delete("/school-service/api/education-standard/"+standardId);

        response.then().statusCode(400);
        System.out.println(response.getBody().asString());
    }
}

