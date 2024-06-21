package Elcin;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class _US001_Login {
    @BeforeClass
    public void setup() {
        // Rest Assured ayarları
        RestAssured.baseURI = "https://test.mersys.io";
    }

    @Test
    public void validLoginTest() {
        // İstek gövdesi
        String requestBody = "{ \"username\": \"turkeyts\", \"password\": \"TechnoStudy123\" }";

        // POST isteği gönderme
        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                        .when()
                        .post("/auth/login")
                        .then()
                        .statusCode(200) //
                        .extract().response();
        System.out.println(response.asString());
    }
}
