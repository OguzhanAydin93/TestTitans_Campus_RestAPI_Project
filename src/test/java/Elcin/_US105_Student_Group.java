package Elcin;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class _US105_Student_Group {
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
    public void _US105_Student_Group_Test() {

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("name", null);
        jsonBody.put("description", null);
        jsonBody.put("publicGroup", null);
        jsonBody.put("showToStudent", null);
        jsonBody.put("schoolId", "6576fd8f8af7ce488ac69b89");

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .post("/school-service/api/student-group/search");

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }
    @Test
    public void _US105_Add_Student_Group_Test() {

        String name="Elcin";
        int randomInt = new Random().nextInt(100000);

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", null);
        jsonBody.put("name",name+" "+randomInt);
        jsonBody.put("description", null);
        jsonBody.put("showToStudent", true);
        jsonBody.put("publicGroup", true);
        jsonBody.put("schoolId", "6576fd8f8af7ce488ac69b89");

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();
        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .post("/school-service/api/student-group/search");

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());

    }
    @Test
    public void _US105_Update_Student_Group_Test() {

        String name="Elcin";
        int randomInt = new Random().nextInt(100000);

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", "6678a38166bab932bcdc3a38");
        jsonBody.put("name",name+" "+randomInt);
        jsonBody.put("description", null);
        jsonBody.put("showToStudent", true);
        jsonBody.put("publicGroup", true);
        jsonBody.put("schoolId", "6576fd8f8af7ce488ac69b89");

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .put("/school-service/api/student-group");

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void _US105_Delete_Student_Group_Test() {

        String customId="6678a38166bab932bcdc3a38";

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .delete("/school-service/api/student-group/"+customId);

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void _US105_Failed_Delete_Student_Group_Test() {

        String customId="wrongID";

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .delete("/school-service/api/student-group/"+customId);

       // response.then().statusCode(400);
        System.out.println(response.getBody().asString());

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("invalid objectId"),
                "Response body should contain 'invalid objectId' message");
    }
}
