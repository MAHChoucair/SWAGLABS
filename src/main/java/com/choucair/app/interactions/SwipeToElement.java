package com.choucair.app.interactions;

import io.appium.java_client.MobileBy;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;

import org.openqa.selenium.WebElement;


public class SwipeToElement implements Interaction {

    private final String productName;

    public SwipeToElement(String productName) {
        this.productName = productName;
    }

    public static ScrollToElement withName(String productName) {
        return Tasks.instrumented(ScrollToElement.class, productName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Intenta desplazarte a través del contenedor de productos
        WebElement element = actor.abilityTo(net.serenitybdd.screenplay.abilities.BrowseTheWeb.class)
                .getDriver().findElement(
                        MobileBy.AndroidUIAutomator(
                                "new UiScrollable(new UiSelector().scrollable(true).resourceId(\"android.view.ViewGroup\"))" +
                                        ".scrollIntoView(new UiSelector().descriptionContains(\"test-Item\"))" +
                                        ".scrollIntoView(new UiSelector().text(\"" + productName + "\"));"
                        )
                );
    }
}