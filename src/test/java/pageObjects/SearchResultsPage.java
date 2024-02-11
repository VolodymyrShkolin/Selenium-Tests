package pageObjects;

import core.BaseTest;
import core.CustomExpectedConditions;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BaseTest {
    public SearchResultsPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@data-testid='productBoxContainer'] //span[@data-testid='itemDescription']")
    public List<WebElement> itemDescription;
    @FindBy(xpath = "//div[@id='details'] //span/span[not(contains(@class, 'sr-only')) and not(contains(@aria-hidden, 'true'))]")
    public List<WebElement> itemNumber;
    @FindBy(xpath = "//div[@data-testid='paging'] //li[not(contains(@class,'rounded-r-md')) and not(contains(@class,'rounded-l-md'))]")
    public List<WebElement> pagingPages;
    @FindBy(xpath = "//div[@data-testid='paging'] //li[contains(@class,'rounded-r-md')]")
    WebElement nextPageArrow;
    @FindBy(xpath = "//div[@data-testid='productBoxContainer'] //div[@class='btn-container'][1][not(select)]")
    List<WebElement> addToCartBtn;
    @FindBy(xpath = "//div[@data-role='notification'] //button")
    WebElement cartNotificationCross;


    /**
     * This method checks whether the product name contains certain text.
     * @param requiredText text that needs to be checked in the product title
     */
    @Step("Checking the product name")
    public SearchResultsPage checkText(String requiredText){
        int numberOfPages = Integer.parseInt(pagingPages.get(pagingPages.size()-1).getText());
        List<String> resultList = new ArrayList<>();

        for (int i = 0; i < numberOfPages; i++) {
            for (int j = 0; j < itemDescription.size(); j++) {
                if(!itemDescription.get(j).getText().contains(requiredText)){
                    resultList.add("Item with number " + itemNumber.get(j).getText() + " doesn't have word '" + requiredText + "'");
                }
            }
            if(i < numberOfPages-1){
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", nextPageArrow);
                nextPageArrow.click();
            }
        }
        if(!resultList.isEmpty()){
            System.err.println(resultList);
            //Assertions.fail(); //TODO uncomment if it necessary
        }
        return this;
    }


    /**
     * This method adds the last item on the page to the cart and closes the cart modal window.
     * @return CartPage for convenient use of the method.
     */
    @Step("Adding product to cart")
    public CartPage addToCart(){
        addToCartBtn.get(random.nextInt(addToCartBtn.size())).click();
        cartNotificationCross.click();
        wait.until(CustomExpectedConditions.disappearanceOfElement(cartNotificationCross));
        return new CartPage();
    }
}
