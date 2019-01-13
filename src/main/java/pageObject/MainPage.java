package pageObject;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Data
public class MainPage {
    private SelenideElement
            loginBtn = $(By.xpath(".//*[@id='login']")),
            registerBtn = $(By.xpath(".//*[@id='register']")),
            logoutBtn = $(By.xpath(".//*[@id='logout']")),
            cartBtn = $(By.xpath(".//*[@id='basket-overview']")),
            accountTab = $(By.xpath(".//*[@id='tabAccount']")),
            catalogueTab = $(By.xpath(".//*[@id='tabCatalogue']")),
            userInfoPanel = $(By.xpath(".//*[@id='howdy']/a"));

    public LoginPopUp clickLogin() {
        loginBtn.shouldBe(visible).click();
        return new LoginPopUp();
    }

    public RegisterPopUp clickRegister() {
        registerBtn.shouldBe(visible).click();
        return new RegisterPopUp();
    }

    public MainPage clickLogout() {
        logoutBtn.shouldBe(visible).click();
        return this;
    }

    public CataloguePage clickCatalogueTab() {
        catalogueTab.isDisplayed();
        catalogueTab.click();
        return new CataloguePage();
    }

    @Data
    public static class LoginPopUp {
        private SelenideElement
                wrongCredentialsAlert = $(By.xpath(".//*[@class='alert alert-danger']")),
                userNameInput = $(By.xpath(".//input[@id='username-modal']")),
                passwordInput = $(By.xpath(".//input[@id='password-modal']")),
                loginBtn = $(By.xpath(".//button[@class='btn btn-primary' and normalize-space(text())='Log in']"));

        public LoginPopUp fillUserName(String userName) {
            userNameInput.shouldBe(visible).val(userName);
            return this;
        }

        public LoginPopUp fillPassword(String password) {
            passwordInput.shouldBe(visible).val(password);
            return this;
        }

        public LoginPopUp clickLoginButton() {
            loginBtn.shouldBe(visible).click();
            return this;
        }
    }

    @Data
    public static class RegisterPopUp {
        private SelenideElement
                existedCredentialsAlert = $(By.xpath(".//*[@class='alert alert-danger']")),
                userNameInput = $(By.xpath(".//input[@id='register-username-modal']")),
                firstNameInput = $(By.xpath(".//input[@id='register-first-modal']")),
                lastNameInput = $(By.xpath(".//input[@id='register-last-modal']")),
                emailInput = $(By.xpath(".//input[@id='register-email-modal']")),
                passwordInput = $(By.xpath(".//input[@id='register-password-modal']")),
                closePopUpBtn = $(By.xpath(".//button[@class='close']")),
                registerBtn = $(By.xpath(".//button[@class='btn btn-primary' and normalize-space(text())='Register']"));

        public RegisterPopUp fillUserName(String userName) {
            userNameInput.shouldBe(visible).val(userName);
            return this;
        }

        public RegisterPopUp fillFirstName(String firstName) {
            firstNameInput.shouldBe(visible).val(firstName);
            return this;
        }

        public RegisterPopUp fillLastName(String lastName) {
            lastNameInput.shouldBe(visible).val(lastName);
            return this;
        }

        public RegisterPopUp fillEmail(String email) {
            emailInput.shouldBe(visible).val(email);
            return this;
        }

        public RegisterPopUp fillPassword(String password) {
            passwordInput.shouldBe(visible).val(password);
            return this;
        }

        public RegisterPopUp clickRegisterButton() {
            registerBtn.shouldBe(visible).click();
            return this;
        }
    }
}
