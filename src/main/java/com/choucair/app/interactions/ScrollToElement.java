package com.choucair.app.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import org.openqa.selenium.WebElement;
import io.appium.java_client.MobileBy;

public class ScrollToElement implements Interaction {

    private final String text;

    public ScrollToElement(String text) {
        this.text = text;
    }

    public static ScrollToElement withText(String text) {
        return Tasks.instrumented(ScrollToElement.class, text);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElement element = actor.abilityTo(net.serenitybdd.screenplay.abilities.BrowseTheWeb.class)
                .getDriver().findElement(
                        MobileBy.AndroidUIAutomator(
                                "new UiScrollable(new UiSelector().scrollable(true))" +
                                        ".scrollIntoView(new UiSelector().text(\"" + text + "\"));"
                        )
                );
    }
}


