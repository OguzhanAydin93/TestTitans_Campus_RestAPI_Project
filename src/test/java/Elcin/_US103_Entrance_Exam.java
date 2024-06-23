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

public class _US103_Entrance_Exam {

    private String bearerToken;

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
    public void _US103_Exam_Create_Test() {

        String randomName = "Biology";
        int randomInt = new Random().nextInt(100000);

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", null);
        jsonBody.put("name", randomName + " " + randomInt);
        jsonBody.put("translateName", new ArrayList<>());
        jsonBody.put("school", "6576fd8f8af7ce488ac69b89");

        Map<String, Object> gradeLevel = new HashMap<>();
        gradeLevel.put("id", "654ce6e88f4d46269de3bb69");
        jsonBody.put("gradeLevel", gradeLevel);

        jsonBody.put("academicPeriod", "6577022e8af7ce488ac69b96");
        jsonBody.put("active", true);
        jsonBody.put("description", "");
        jsonBody.put("note", "");
        jsonBody.put("agreementText", "");
        jsonBody.put("sendSMS", false);
        jsonBody.put("sms", "");
        jsonBody.put("sendEmailEnabled", false);

        Map<String, Object> emailMessage = new HashMap<>();
        emailMessage.put("subject", "");
        emailMessage.put("content", "");
        jsonBody.put("emailMessage", emailMessage);

        jsonBody.put("registrationStartDate", null);
        jsonBody.put("registrationEndDate", null);
        jsonBody.put("paid", false);
        jsonBody.put("price", 0);
        jsonBody.put("bankAccount", null);
        jsonBody.put("sendEmailToRegistrar", false);
        jsonBody.put("registrarEmails", new ArrayList<>());
        jsonBody.put("showDescFirst", false);
        jsonBody.put("showNoteFirst", false);
        jsonBody.put("noteEnabled", false);
        jsonBody.put("descEnabled", false);
        jsonBody.put("agreementEnabled", false);
        jsonBody.put("documents", new ArrayList<>());

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .put("/school-service/api/exams");

        // Hata mesajını kontrol etmek için
        if (response.statusCode() != 200) {
            System.out.println("Response status code: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody().asString());
        }

        response.then().statusCode(200);
    }

    @Test
    public void _US103_Failed_Exam_Create_Test() {

        String randomName = "Biology";
        int randomInt = new Random().nextInt(100000);

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", null);
        jsonBody.put("name", randomName + " " + randomInt);
        jsonBody.put("translateName", new ArrayList<>());
        jsonBody.put("school", "6576fd8f8af7ce488ac69b89");

        Map<String, Object> gradeLevel = new HashMap<>();
        gradeLevel.put("id", "654ce6e88f4d46269de3bb69");
        jsonBody.put("gradeLevel", null);

        jsonBody.put("academicPeriod", "6577022e8af7ce488ac69b96");
        jsonBody.put("active", true);
        jsonBody.put("description", "");
        jsonBody.put("note", "");
        jsonBody.put("agreementText", "");
        jsonBody.put("sendSMS", false);
        jsonBody.put("sms", "");
        jsonBody.put("sendEmailEnabled", false);

        Map<String, Object> emailMessage = new HashMap<>();
        emailMessage.put("subject", "");
        emailMessage.put("content", "");
        jsonBody.put("emailMessage", emailMessage);

        jsonBody.put("registrationStartDate", null);
        jsonBody.put("registrationEndDate", null);
        jsonBody.put("paid", false);
        jsonBody.put("price", 0);
        jsonBody.put("bankAccount", null);
        jsonBody.put("sendEmailToRegistrar", false);
        jsonBody.put("registrarEmails", new ArrayList<>());
        jsonBody.put("showDescFirst", false);
        jsonBody.put("showNoteFirst", false);
        jsonBody.put("noteEnabled", false);
        jsonBody.put("descEnabled", false);
        jsonBody.put("agreementEnabled", false);
        jsonBody.put("documents", new ArrayList<>());

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .put("/school-service/api/exams");

        if (response.statusCode() != 400) {
            System.out.println("Response status code: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody().asString());
        }
        response.then().statusCode(400);
    }

    @Test
    public void _US103_Exam_Update_Test() {

        String examId = "66788b6166bab932bcdc3a32";
        String updatedName = "Updated Biology";
        int randomInt = new Random().nextInt(100000);

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", examId);
        jsonBody.put("name", updatedName + " " + randomInt);
        jsonBody.put("translateName", new ArrayList<>());
        jsonBody.put("school", "6576fd8f8af7ce488ac69b89");

        Map<String, Object> gradeLevel = new HashMap<>();
        gradeLevel.put("id", "654ce6e88f4d46269de3bb69");
        jsonBody.put("gradeLevel", gradeLevel);

        jsonBody.put("academicPeriod", "6577022e8af7ce488ac69b96");
        jsonBody.put("active", true);
        jsonBody.put("description", "");
        jsonBody.put("note", "");
        jsonBody.put("agreementText", "");
        jsonBody.put("sendSMS", false);
        jsonBody.put("sms", "");
        jsonBody.put("sendEmailEnabled", false);

        Map<String, Object> emailMessage = new HashMap<>();
        emailMessage.put("subject", "");
        emailMessage.put("content", "");
        jsonBody.put("emailMessage", emailMessage);

        jsonBody.put("registrationStartDate", null);
        jsonBody.put("registrationEndDate", null);
        jsonBody.put("paid", false);
        jsonBody.put("price", 0);
        jsonBody.put("bankAccount", null);
        jsonBody.put("sendEmailToRegistrar", false);
        jsonBody.put("registrarEmails", new ArrayList<>());
        jsonBody.put("showDescFirst", false);
        jsonBody.put("showNoteFirst", false);
        jsonBody.put("noteEnabled", false);
        jsonBody.put("descEnabled", false);
        jsonBody.put("agreementEnabled", false);
        jsonBody.put("documents", new ArrayList<>());

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .put("/school-service/api/exams");

        // Hata mesajını kontrol etmek için
        if (response.statusCode() != 200) {
            System.out.println("Response status code: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody().asString());
        }
        response.then().statusCode(200);
    }
    @Test
    public void _US103_Exam_Update_Failed_Test() {

        String nonExistentExamId = "nonexistentExamId";

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", nonExistentExamId);
        jsonBody.put("name", "Updated Exam Name");
        jsonBody.put("school", "6576fd8f8af7ce488ac69b89");

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .put("/school-service/api/exams/");

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        boolean validStatusCode = statusCode == 400 || statusCode == 401 || statusCode == 402 || statusCode == 404;
        boolean containsErrorMessage = responseBody.contains("Exam doesn't exist");

        System.out.println("Response status code: " + statusCode);
        System.out.println("Response body: " + responseBody);
    }

    @Test
    public void _US103_Exam_Delete_Test() {

        String ExamId = "66788b6166bab932bcdc3a32";

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .delete("/school-service/api/exams/"+ExamId);

        if (response.statusCode() != 204) {
            System.out.println("Response status code: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody().asString());
        }
        response.then().statusCode(204);

    }

    @Test
    public void _US103_Exam_Failed_Delete_Test() {

        String ExamId = "WrongID";

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .delete("/school-service/api/exams/"+ExamId);

        if (response.statusCode() != 400) {
            System.out.println("Response status code: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody().asString());
        }
        response.then().statusCode(400);

    }
}
