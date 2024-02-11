package core;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;
import java.util.List;

import static core.Constants.IMPLICIT_WAIT;

public class CustomExpectedConditions {

    public static ExpectedCondition<Boolean> disappearanceOfElement(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            boolean appeared = false;

            @Override
            public Boolean apply(WebDriver driver) {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
                try {
                    boolean displayed = element.isDisplayed();
                    if (!appeared && displayed) {
                        appeared = true;
                        return false;
                    }
                    if (appeared && !displayed) {
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
                        return true;
                    }
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    if (appeared) {
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
                        return true;
                    }
                }
                return false;
            }
        };
    }
}
