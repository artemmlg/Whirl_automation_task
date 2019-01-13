package gui;

import base.BaseTest;
import com.codeborne.selenide.CollectionCondition;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.CartPage;
import pageObject.CataloguePage;
import pageObject.MainPage;
import pageObject.MainPage.LoginPopUp;

import static com.codeborne.selenide.Condition.visible;

public class WhirlTestSuite extends BaseTest {
    private MainPage mainPage = new MainPage();
    private MainPage.LoginPopUp loginPopUp = new MainPage.LoginPopUp();
    private MainPage.RegisterPopUp registerPopUp = new MainPage.RegisterPopUp();
    private CataloguePage cataloguePage = new CataloguePage();
    private CartPage cartPage = new CartPage();
    private final String userName = faker.pokemon().name();
    private final String firstName = faker.name().firstName();
    private final String lastName = faker.name().lastName();
    private final String email = faker.internet().safeEmailAddress();
    private final String password = faker.code().imei();

    @BeforeMethod
    private void before() {
        if (mainPage.getLogoutBtn().exists())
            mainPage.clickLogout();
    }

    @Test (description = "without sleep, to reproduce a bug")
    public void testLoginWithNonExistedUser() {
        mainPage.clickLogin();
        loginPopUp.fillUserName("Lola_Koka_Kola")
                .fillPassword(String.valueOf(System.currentTimeMillis()))
                .clickLoginButton();
        Assert.assertEquals(loginPopUp.getWrongCredentialsAlert().shouldBe(visible).getText(), "Invalid login credentials.");
    }

    @Test
    public void testNewUserRegistration() {
        mainPage.clickRegister();
        registerPopUp
                .fillUserName(userName)
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillEmail(email)
                .fillPassword(password)
                .clickRegisterButton();
        softAssert.assertTrue(mainPage.getAccountTab().shouldBe(visible).exists());
        softAssert.assertTrue(mainPage.getLogoutBtn().shouldBe(visible).exists());
        softAssert.assertEquals(mainPage.getUserInfoPanel().shouldBe(visible).getText(), "Logged in as " + firstName + " " + lastName);
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "testNewUserRegistration")
    public void testAlreadyExistedUserRegistration() {
        mainPage.clickRegister();
        registerPopUp
                .fillUserName(userName)
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillEmail(email)
                .fillPassword(password);
        Assert.assertEquals(registerPopUp.getExistedCredentialsAlert().shouldBe(visible).getText(), "There was a problem with your registration: Internal Server Error");
    }

    @Test
    public void testLoginAfterRegistration() {
        final String userName = faker.harryPotter().character();
        final String firstName = faker.name().firstName();
        final String lastName = faker.name().lastName();
        final String email = faker.internet().safeEmailAddress();
        final String password = faker.code().imei();
        mainPage.clickRegister();
        new MainPage.RegisterPopUp()
                .fillUserName(userName)
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillEmail(email)
                .fillPassword(password)
                .clickRegisterButton();
        softAssert.assertTrue(mainPage.getAccountTab().shouldBe(visible).exists());
        softAssert.assertTrue(mainPage.getLogoutBtn().shouldBe(visible).exists());
        softAssert.assertEquals(mainPage.getUserInfoPanel().shouldBe(visible).getText(), "Logged in as " + firstName + " " + lastName);
        mainPage.clickLogout()
                .clickLogin();
        new LoginPopUp()
                .fillUserName(userName)
                .fillPassword(password)
                .clickLoginButton();
        softAssert.assertTrue(mainPage.getAccountTab().shouldBe(visible).exists());
        softAssert.assertTrue(mainPage.getLogoutBtn().shouldBe(visible).exists());
        softAssert.assertEquals(mainPage.getUserInfoPanel().shouldBe(visible).getText(), "Logged in as " + firstName + " " + lastName);
        softAssert.assertAll();
    }

    @Test
    public void testAddItemToCartAsRegisteredUser() {
        final String userName = faker.gameOfThrones().character();
        final String firstName = faker.name().firstName();
        final String lastName = faker.name().lastName();
        final String email = faker.internet().safeEmailAddress();
        final String password = faker.code().imei();
        mainPage.clickRegister()
                .fillUserName(userName)
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillEmail(email)
                .fillPassword(password)
                .clickRegisterButton();
        mainPage.clickCatalogueTab();
        cataloguePage.clickAddToCart();
        softAssert.assertEquals(cataloguePage.getItemsInCart().getText(), "1 item(s) in cart");
        cataloguePage.clickOnItemsInCartPanel();
        cartPage.getListOfItemsInShoppingCart().shouldHave(CollectionCondition.size(1));
        cartPage.clickCatalogueTab();
        cataloguePage.clickAddToCart();
        softAssert.assertEquals(cataloguePage.getItemsInCart().getText(), "2 item(s) in cart");
        cataloguePage.clickOnItemsInCartPanel();
        cartPage.getListOfItemsInShoppingCart().shouldHave(CollectionCondition.size(2));
        softAssert.assertAll();
    }

    @Test
    public void testAddItemToCartAsUnregisteredUser(){
        softAssert.assertTrue(mainPage.getLoginBtn().shouldBe(visible).exists());
        mainPage.clickCatalogueTab();
        cataloguePage.clickAddToCart();
        softAssert.assertEquals(cataloguePage.getItemsInCart().getText(), "1 item(s) in cart");
        cataloguePage.clickOnItemsInCartPanel();
        cartPage.getListOfItemsInShoppingCart().shouldHave(CollectionCondition.size(1));
        cartPage.clickCatalogueTab();
        cataloguePage.clickAddToCart();
        softAssert.assertEquals(cataloguePage.getItemsInCart().getText(), "2 item(s) in cart");
        cataloguePage.clickOnItemsInCartPanel();
        cartPage.getListOfItemsInShoppingCart().shouldHave(CollectionCondition.size(2));
        softAssert.assertAll();
    }
}
