package com.choucair.app.interactions;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.WebDriver;
import io.appium.java_client.android.AndroidDriver;
import java.time.Duration;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.webdriver.WebDriverFacade;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SwipeToElement implements Interaction {

    private final String text;
    private final int maxSwipeAttempts;

    public SwipeToElement(String text, int maxSwipeAttempts) {
        this.text = text;
        this.maxSwipeAttempts = maxSwipeAttempts;
    }

    public static SwipeToElement withText(String text, int maxSwipeAttempts) {
        return Tasks.instrumented(SwipeToElement.class, text, maxSwipeAttempts);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver webDriver = BrowseTheWeb.as(actor).getDriver();
        AndroidDriver driver = (AndroidDriver) ((WebDriverFacade) webDriver).getProxiedDriver();

        int attempts = 0;
        boolean isElementVisible = false;

        while (attempts < maxSwipeAttempts && !isElementVisible) {
            // Verifica si el elemento ya es visible
            isElementVisible = !driver.findElements(MobileBy.AndroidUIAutomator(
                    "new UiSelector().textContains(\"" + text + "\")")).isEmpty();

            if (!isElementVisible) {
                TouchAction action = new TouchAction(driver);

                // Realiza un swipe hacia arriba
                action.press(PointOption.point(500, 1500))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(PointOption.point(500, 500))
                        .release()
                        .perform();

                attempts++;
            }
        }

        // Espera a que el elemento sea visible
        actor.attemptsTo(
                WaitUntil.the(Target.the("elemento con texto " + text)
                        .located(MobileBy.AndroidUIAutomator(
                                "new UiSelector().textContains(\"" + text + "\")")), isVisible()).forNoMoreThan(30).seconds()
        );
    }
}