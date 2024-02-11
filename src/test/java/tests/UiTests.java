package tests;

import core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("UI tests")
public class UiTests extends BaseTest {

    @DisplayName("Number of categories is 11")
    @Test
    @Order(4)
    public void categoriesCountIs11(){Assertions.assertEquals(11, mainPage.categories.size());}

    @DisplayName("Matching category names")
    @Test
    @Order(2)
    public void appropriateHeaderName(){
        mainPage.chooseRandomCategory();
    }

    @DisplayName("SignIn button availability")
    @Test
    @Order(1)
    public void signInBtn(){mainPage.checkSignInBtn();}

    @DisplayName("Create Acc button availability")
    @Test
    @Order(3)
    public void createAccBtn(){mainPage.checkCreateAccBtn();}
}
