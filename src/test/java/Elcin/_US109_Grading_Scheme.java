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

public class _US109_Grading_Scheme {
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
    public void _US109_Get_Granding_Scheme() {

        String schoolID="6576fd8f8af7ce488ac69b89";

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .get("/school-service/api/grading-schemes/school/"+schoolID+"/search");

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void _US109_Add_Granding_Scheme() {

        String name="Elcin";
        int randomInt = new Random().nextInt(100000);

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", null);
        jsonBody.put("name",name+" "+randomInt);
        jsonBody.put("active", true);
        jsonBody.put("type", "POINT");
        jsonBody.put("enablePoint", false);
        jsonBody.put("minPointToPass", 0);
        jsonBody.put("gradeRanges", new ArrayList<>());
        jsonBody.put("schoolId", "6576fd8f8af7ce488ac69b89");

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .post("/school-service/api/grading-schemes");

        response.then().statusCode(201);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void _US109_Update_Granding_Scheme() {

        String name="Elcin";
        int randomInt = new Random().nextInt(100000);

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", "667432ef568dd227fd5015be");
        jsonBody.put("name",name+" "+randomInt);
        jsonBody.put("active", true);
        jsonBody.put("type", "POINT");
        jsonBody.put("enablePoint", false);
        jsonBody.put("minPointToPass", 0);
        jsonBody.put("gradeRanges", new ArrayList<>());
        jsonBody.put("schoolId", "6576fd8f8af7ce488ac69b89");

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .put("/school-service/api/grading-schemes");

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }
    @Test
    public void _US109_Delete_Grading_Scheme() {
        String schemeId = "6679d330d7a6685984fbd913";
        String schoolId = "6576fd8f8af7ce488ac69b89";

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("schoolId", schoolId);

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .queryParam("schoolId", schoolId)
                .body(jsonBody)
                .delete("/school-service/api/grading-schemes/" + schemeId);

        int statusCode = response.getStatusCode();
        if (statusCode != 200) {
            System.out.println("Response status code: " + statusCode);
            System.out.println("Response body: " + response.getBody().asString());
        }

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }




}
