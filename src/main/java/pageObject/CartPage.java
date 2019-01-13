package pageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Data
public class CartPage {
    private SelenideElement catalogueTab = $(By.xpath(".//*[@id='tabCatalogue']"));
    private ElementsCollection listOfItemsInShoppingCart = $$(By.xpath(".//tbody[@id='cart-list']"));

    public CataloguePage clickCatalogueTab() {
        catalogueTab.isDisplayed();
        catalogueTab.click();
        return new CataloguePage();
    }
}
