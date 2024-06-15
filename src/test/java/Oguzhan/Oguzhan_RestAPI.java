package Oguzhan;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class Oguzhan_RestAPI {


    String url = "https://test.mersys.io";
    RequestSpecification reqSpec;
    Faker random = new Faker();
    String randomName = "";
    String randomNumber = "";
    String countryID = "";

    @BeforeClass
    public void setup(){

        baseURI = "https://www.themoviedb.org";

        reqSpec = new RequestSpecBuilder()
                .addHeader("Cookie", "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZENoYW5nZSI6ZmFsc2UsInVzZXJfbmFtZSI6InR1cmtleXRzIiwic2NvcGUiOlsib3BlbmlkIl0sImF0aSI6InpPWk02VklmU2hXRjlDV1k1YnAwekJ3NUF3ayIsImV4cCI6MTcxODk4NzM0MSwiaWF0IjoxNzE4MzgyNTQxLCJhdXRob3JpdGllcyI6WyJST0xFX0VWRVJZT05FIiwiUk9MRV9URUNITk9fVEVTVCJdLCJqdGkiOiJRY050bmVja0pQNjV3Z0FQZHIzaDBpZG5IRU0iLCJjbGllbnRfaWQiOiJ3ZWJfYXBwIiwidXNlcm5hbWUiOiJ0dXJrZXl0cyJ9.aGPgSwqciR83yv_uwAKmXrHohUUx4okLTX7oS7mnT2zBB7O3xBuzJFYs0VwWUBNerFWdDJY9fxwRTd3B7mi2cg7SxjdtgooDrXDmagqQZ7yK33NyvasJDsJdiQfTNvihm5tbQNiIyDQaCxqBSNswW8bovffo4iAqWLhpwiYWwEnZ6IRnhMvaJGCW8tPeQqUjKT21jO_YnrkhnDB73wtlCJIN0CHYZzZiTSB0UKxOwWJxftFgKBbCi1vJzVuUDhM3xBdduSK_U6kayHFIwT3Sw4lOkUB_-c9Mh0P_JeNZcvFqoZ9P_qL-e4e4bshvnuf7V5uFZbczasvoJXzB-RznUw")
                .setContentType(ContentType.JSON)
                .build();

    }


    @Test
    public void us_001Negatif() {

        Map<String, String> loginBody = new HashMap<>();

        loginBody.put("username", "turkeyt");
        loginBody.put("password", "TechnoStudy123");
        loginBody.put("rememberMe", "true");


        Cookies gelenCoockies =
                given()
                        .body(loginBody)
                        .contentType(ContentType.JSON)

                        .when()
                        .post(url + "/auth/login")

                        .then()
                        .log().body()
                        .statusCode(401)
                        .extract().response().getDetailedCookies();
        reqSpec = new RequestSpecBuilder()
                .addCookies(gelenCoockies)
                .setContentType(ContentType.JSON)
                .build()

        ;

    }

    @Test(dependsOnMethods = "us_001Negatif")
    public void us_001() {

        Map<String, String> loginBody = new HashMap<>();

        loginBody.put("username", "turkeyts");
        loginBody.put("password", "TechnoStudy123");
        loginBody.put("rememberMe", "true");



                given()
                        .body(loginBody)
                        .contentType(ContentType.JSON)

                        .when()
                        .post(url + "/auth/login")

                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract().response().getDetailedCookies()
                
                
                ;
    }

    @Test(dependsOnMethods = "us_001")
    public void us_002() {

        countryID =

        randomName = random.name().fullName();
        randomNumber = random.phoneNumber().phoneNumber();


        Map<String, Object> countryListBody = new HashMap<>();

        countryListBody.put("id", "null");
        countryListBody.put("name", randomName);
        countryListBody.put("code", randomNumber);
        countryListBody.put("translateName", "null");
        countryListBody.put("hasState", true);

                given()
                        .spec(reqSpec)
                        .body(countryListBody)
                        .contentType(ContentType.JSON)

                        .when()
                        .post(url + "/school-service/api/countries")

                        .then()
                        .log().body()
                        .statusCode(201)

        ;

        System.out.println("countryID = " + countryID);

    }

    @Test(dependsOnMethods = "us_001")
    public void us_101_ADD_POST() {

        randomName = random.country().name();
        randomNumber = random.name().fullName();

        Map<String, Object> statesListBody = new HashMap<>();

        statesListBody.put("id", "null");
        statesListBody.put("name", randomName);
        statesListBody.put("code", randomNumber);
        statesListBody.put("translateName", "null");
        statesListBody.put("hasState", true);


        given()
                .spec(reqSpec)
                .body(statesListBody)
                .contentType(ContentType.JSON)

                .when()
                .post(url + "/school-service/api/states")

                .then()
                .log().body()
                .statusCode(201)

        ;

    }


}
