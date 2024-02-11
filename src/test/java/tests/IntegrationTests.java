package tests;

import core.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Integration tests")
public class IntegrationTests extends BaseTest {

    @DisplayName("Checking the search and cart functionality")
    @Test
    public void mainTestCase(){
        mainPage.searchItems("stainless work table")
                .checkText("Table")
                .addToCart()
                .emptyCart();
    }
}
