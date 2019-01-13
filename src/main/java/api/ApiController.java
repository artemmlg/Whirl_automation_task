package api;

import api.registration.RegistrationRequestModel;
import api.registration.RegistrationResponseModel;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.hamcrest.Matchers;
import properties.AppProperties;

import static io.restassured.RestAssured.given;

public class ApiController {
    private RequestSpecification requestSpecification;
    private RegistrationRequestModel registrationRequestModel;

    public ApiController(RegistrationRequestModel registrationRequestModel) {
        this.registrationRequestModel = registrationRequestModel;

        AppProperties appProperties = ConfigFactory.create(AppProperties.class);
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(appProperties.hostUri())
                .addHeader("Authorization", "Basic cG9zdG1hbjpwb3N0bWFu")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL).build();

        RestAssured.defaultParser = Parser.JSON;
        RestAssured.unregisterParser("text/plain");
    }

    public RegistrationResponseModel registerNewUserViaAPI() {
        return given(requestSpecification)
                .body(registrationRequestModel)
                .expect().statusCode(200)
                .when()
                .post("/register")
                .as(RegistrationResponseModel.class);
    }

    public void registerExistedUserViaAPI() {
        given(requestSpecification)
                .body(registrationRequestModel)
                .expect().statusCode(500).contentType(ContentType.TEXT).content(Matchers.empty())
                .when()
                .post("/register")
                .as(RegistrationResponseModel.class);
    }

    public void loginWithRegisteredUserViaAPI(String userName, String password) {
        given(requestSpecification)
                .auth().basic(userName, password)
                .expect().statusCode(200)
                .when().get("/login");
    }
}
