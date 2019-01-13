package base;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import properties.AppProperties;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

public abstract class BaseTest {
    protected Faker faker = new Faker();
    protected SoftAssert softAssert = new SoftAssert();
    private AppProperties appProperties = ConfigFactory.create(AppProperties.class);

    @BeforeClass
    protected void beforeClass() {
        Configuration.startMaximized = true;
        baseUrl = appProperties.hostUri();
    }

    @BeforeMethod
    protected void beforeMethod() {
        open("");
    }
}