package api;

import api.registration.RegistrationRequestModel;
import api.registration.RegistrationResponseModel;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

@JsonIgnoreType
public class WhirlApiTestSuite {
    private Faker faker = new Faker();
    private final String userName = faker.pokemon().name();
    private final String password = faker.code().ean13();
    private final String emailAddress = faker.internet().emailAddress();
    private RegistrationRequestModel registrationRequestModel = new RegistrationRequestModel(userName, password, emailAddress);

    @Test
    public void testRegisterNewUserViaApi() {
        RegistrationResponseModel registrationResponseModel = new ApiController(registrationRequestModel).registerNewUserViaAPI();
        Assert.assertNotNull(registrationResponseModel.getId(), "ID field is empty");
    }

    @Test(enabled = true)
    public void testLoginWithExistedUserViaAPI() {
        new ApiController(registrationRequestModel).loginWithRegisteredUserViaAPI("user", "password");
    }

    @Test(enabled = false, dependsOnMethods = "testRegisterNewUserViaApi")
    public void testRegisterExistedUserViaApi() {
        new ApiController(registrationRequestModel).registerExistedUserViaAPI();
    }
}
