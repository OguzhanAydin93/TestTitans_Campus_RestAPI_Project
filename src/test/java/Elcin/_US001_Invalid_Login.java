package Elcin;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class _US001_Invalid_Login {
    @Test
    public void _02_Invalid_Login_Test() {
        baseURI = "https://test.mersys.io";

        // Kullanıcı adı, şifre ve token bilgilerini içeren bir HashMap oluşturma
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("username", "wrongUsernama");
        userInfo.put("password", "wrongPassword");
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBody(userInfo)
                .build();

        given()
                .spec(reqSpec)
                .post("/auth/login")
                .then()
                .statusCode(401);
    }
}
