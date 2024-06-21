package Elcin;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class _US002_Country {

    @Test
    public void _US002_Country_Test(){

        baseURI = "https://test.mersys.io";

        String bearerToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZENoYW5nZSI6ZmFsc2UsInVzZXJfbmFtZSI6InR1cmtleXRzIiwic2NvcGUiOlsib3BlbmlkIl0sImV4cCI6MTcxODk4NzU3MiwiaWF0IjoxNzE4OTg3MjcyLCJhdXRob3JpdGllcyI6WyJST0xFX0VWRVJZT05FIiwiUk9MRV9URUNITk9fVEVTVCJdLCJqdGkiOiJkbGJUSXY2SXR5bVg2MXRPWVlWdmhTM00yYW8iLCJjbGllbnRfaWQiOiJ3ZWJfYXBwIiwidXNlcm5hbWUiOiJ0dXJrZXl0cyJ9.Tn1xlxOQXctANGESekEWSmF_HiwUwFzZbBjNcg9M_CYMFQBj1l61jvhGVaRTH3cKYdyjtQWMAcXzO6nHZ3TC8ZweLzStgefR_5-nEyT7Cr0LZty4JXVEwM7VUNuRYrGee0lhW5FHXAMJQ1CZETiJBkJFPHenvIEYeHjCBAjj2fYg3Mf4IFcgNW62pAYfirgK5NKQJ7eKDLDrLR1JkfRaSYRyTed_zG1OvWOzBIoVqOQxicf--B9gBazAJq_0TrwWDHSrOmFejjZ3JzgP34MKFDCSEuXXX4FNIHs11QBFXazQhFCVBmsXSJTD71I3RM3TDc25-5EgEvZkx9iGAXudXA";

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("code", "AL");
        jsonBody.put("hasState", true);
        jsonBody.put("id", "63919f4e6d489849480e9c6c");
        jsonBody.put("name", "Albania");
        jsonBody.put("translateName", null);

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();

        Response response = given()
                .spec(reqSpec)
                .body(jsonBody)
                .put("/school-service/api/countries");

        response.then().statusCode(200);
        System.out.println(response.getBody().asString());
    }
}
