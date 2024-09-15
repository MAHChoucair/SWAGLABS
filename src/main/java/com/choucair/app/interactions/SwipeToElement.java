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
import io.appium.java_client.android.AndroidDriver;
import java.time.Duration;
import net.serenitybdd.screenplay.targets.Target;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SwipeToElement implements Interaction {

    private final String text;

    public SwipeToElement(String text) {
        this.text = text;
    }

    public static SwipeToElement withText(String text) {
        return Tasks.instrumented(SwipeToElement.class, text);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        AndroidDriver driver = (AndroidDriver) BrowseTheWeb.as(actor).getDriver();
        TouchAction action = new TouchAction(driver);

        // Realiza un swipe hacia arriba
        action.press(PointOption.point(500, 1500))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(500, 500))
                .release()
                .perform();

        // Espera a que el elemento sea visible
        actor.attemptsTo(
                WaitUntil.the(Target.the("elemento con texto " + text)
                        .located(MobileBy.AndroidUIAutomator(
                                "new UiSelector().textContains(\"" + text + "\")")), isVisible()).forNoMoreThan(30).seconds()
        );
    }
}