package Elcin;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class _US110_Incident_Type {
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
    public void _US110_Incident_Type_Test() {

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("schoolId", "6576fd8f8af7ce488ac69b89");

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .post("school-service/api/incident-type/search");

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }
        @Test
        public void _US110_Add_Incident_Type () {
            String name = "elcin";
            int randomInt = new Random().nextInt(100000);

            Map<String, Object> jsonBody = new HashMap<>();
            jsonBody.put("id", null);
            jsonBody.put("name", name + " " + randomInt);
            jsonBody.put("active", true);
            jsonBody.put("schoolId", "6576fd8f8af7ce488ac69b89");
            jsonBody.put("minPoint", 11);
            jsonBody.put("maxPoint", 11);
            jsonBody.put("academicBased", false);

            List<String> permissions = new ArrayList<>();
            permissions.add("ROLE_ADMIN");
            jsonBody.put("permissions", permissions);

            RequestSpecification reqSpec = new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .addHeader("Authorization", "Bearer " + bearerToken)
                    .build();

            Response response = given()
                    .spec(reqSpec)
                    .body(jsonBody)
                    .post("school-service/api/incident-type");

            response.then().statusCode(201);
            System.out.println(response.getBody().asString());
        }

    @Test
    public void _US110_Update_Incident_Type() {
        String incidentTypeId = "666881a244a9e3277bf144ec"; // Geçerli bir incidentTypeId kullanın

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("id", incidentTypeId);
        jsonBody.put("name", "Associate Degree in Criminology9"); // Güncellenecek isim
        jsonBody.put("active", true);
        jsonBody.put("schoolId", "6576fd8f8af7ce488ac69b89");
        jsonBody.put("minPoint", 0);
        jsonBody.put("maxPoint", 100);
        jsonBody.put("academicBased", false);
        jsonBody.put("permissions", new ArrayList<>());

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .put("/school-service/api/incident-type");

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }
    @Test
    public void _US110_Delete_Incident_Type() {
        String incidentTypeId = "666881a244a9e3277bf144ec";

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .when()
                .delete("/school-service/api/incident-type/" + incidentTypeId);

        response.then().statusCode(200);
        System.out.println("Incident Type successfully deleted");
    }
    @Test
    public void _US110_Delete_Incident_Type_Failed() {
        String incidentTypeId = "invalidIncidentTypeId";

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .when()
                .delete("/school-service/api/incident-type/" + incidentTypeId);

        response.then().statusCode(400);
        System.out.println("Incident Type deletion failed");
        System.out.println(response.getBody().asString());
    }
    }


