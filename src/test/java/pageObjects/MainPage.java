package pageObjects;

import core.BaseTest;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage extends BaseTest {
    public MainPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@data-testid='searchval']")
    public List<WebElement> searchField;
    @FindBy(xpath = "//button[@value='Search']")
    public List<WebElement> searchBtn;
    @FindBy(xpath = "//div[@class='modal-content'] //button[@class='btn btn-primary']")
    public WebElement modalWindowAcceptBtn;
    @FindBy(xpath = "//div//h1")
    public WebElement pictureHeader1;
    @FindBy(xpath = "//div[@data-testid='nav-full-data-items'] //a")
    public List<WebElement> categories;
    @FindBy(xpath = "//button[@data-testid='account-dropdown']")
    public WebElement accDropDownBtn;
    @FindBy(xpath = "//a[@data-testid='global-header-sign-in-dropdown']")
    public WebElement signInBtn;
    @FindBy(xpath = "//li /a[contains(@href, 'register')]")
    public WebElement createAccBtn;
    @FindBy(xpath = "//div[@class='portal__main'] /h1")
    public WebElement loginPageH1;
    @FindBy(xpath = "//div[@class='container'] /h1")
    public WebElement regPageH1;
    @FindBy(css = "#email")
    public WebElement emailField;
    @FindBy(css = "#password")
    public WebElement passwordField;
    @FindBy(css = "#the_login_button")
    public WebElement loginBtn;
    @FindBy(css = "#billname")
    public WebElement userNameField;
    @FindBy(css = "#billaddr")
    public WebElement addressField;
    @FindBy(css = "#billaddr2")
    public WebElement address2Field;
    @FindBy(css = "#billcountry")
    public WebElement countryMenu;
    @FindBy(css = "#billzip")
    public WebElement zipField;
    @FindBy(css = "#billphone")
    public WebElement phoneField;
    @FindBy(css = "#same")
    public WebElement sameCheckBox;
    @FindBy(css = "#profileCompanyType")
    public WebElement companyTypeMenu;
    @FindBy(css = "#billcompany")
    public WebElement companyNameField;
    @FindBy(css = "#billnum_employees")
    public WebElement companySizeMenu;
    @FindBy(css = "#complete_registration")
    public WebElement regBtn;


    /**
     * @param itemName name of the product we are looking for.
     * @return SearchResultsPage for convenient use of the method.
     */
    @Step("Search products by word %itemName")
    public SearchResultsPage searchItems(String itemName){
        searchField.get(0).sendKeys(itemName);
        searchBtn.get(0).click();
        return new SearchResultsPage();
    }

    public void chooseRandomCategory(){
        var amountOfCategories = categories.size();
        var randomCategory = random.nextInt(amountOfCategories);
        var categoryName = categories.get(randomCategory).getText().replaceAll("\\W","");
        categories.get(randomCategory).click();
        Assertions.assertTrue(pictureHeader1.getText().replaceAll("\\W","").contains(categoryName));
    }

    public void checkSignInBtn(){
        accDropDownBtn.click();
        signInBtn.click();
        Assertions.assertEquals("Login", loginPageH1.getText());
        Assertions.assertTrue(emailField.isDisplayed());
        Assertions.assertTrue(passwordField.isDisplayed());
        Assertions.assertTrue(loginBtn.isDisplayed());
    }

    public void checkCreateAccBtn(){
        accDropDownBtn.click();
        createAccBtn.click();
        Assertions.assertEquals("Create an Account", regPageH1.getText());
        Assertions.assertTrue(emailField.isDisplayed());
        Assertions.assertTrue(userNameField.isDisplayed());
        Assertions.assertTrue(addressField.isDisplayed());
        Assertions.assertTrue(address2Field.isDisplayed());
        Assertions.assertTrue(countryMenu.isDisplayed());
        Assertions.assertTrue(zipField.isDisplayed());
        Assertions.assertTrue(phoneField.isDisplayed());
        Assertions.assertTrue(sameCheckBox.isDisplayed());
        Assertions.assertTrue(companyTypeMenu.isDisplayed());
        Assertions.assertTrue(companyNameField.isDisplayed());
        Assertions.assertTrue(companySizeMenu.isDisplayed());
        Assertions.assertTrue(passwordField.isDisplayed());
        Assertions.assertTrue(regBtn.isDisplayed());
    }
}
