package pageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;

@Data
public class CataloguePage {
    private ElementsCollection addToCartButtons = $$(By.xpath(".//a[@class='btn btn-primary']"));
    private SelenideElement itemsInCart = $(By.xpath(".//*[@id='numItemsInCart']"));

    public CataloguePage clickAddToCart() {
        addToCartButtons.get(((int) (Math.random() * 5))).shouldBe(visible).click();
        sleep(500);
        return this;
    }

    public CartPage clickOnItemsInCartPanel() {
        itemsInCart.shouldBe(visible).click();
        return new CartPage();
    }
}
