package Elcin;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class _US107_Student_Group {
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
    public void _US107_Confirmation_Remove_Student() {

        String StudentId="6678ab7b66bab932bcdc3a3a";

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .get("/school-service/api/student-group/"+StudentId+"?page=0&size=10");

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void _US107_Delete_Student_from_StudentGroup() {
        String studentId = "6678ab7b66bab932bcdc3a3a";
        List<String> studentIdsToRemove = Arrays.asList("658ef7edcacea97f2a0cb05f");

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(studentIdsToRemove)
                .post("/school-service/api/student-group/" + studentId + "/remove-students");

        // API'nin döndüğü hata kodunu ve mesajını kontrol etme
        int statusCode = response.getStatusCode();
        if (statusCode != 200) {
            System.out.println("Response status code: " + statusCode);
            System.out.println("Response body: " + response.getBody().asString());
        }

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }



}
