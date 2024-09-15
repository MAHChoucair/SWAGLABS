package com.choucair.app.interactions;

import io.appium.java_client.MobileBy;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

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
        // Intenta desplazarte a través del contenedor de productos y encontrar el nombre del producto
        WebElement element = BrowseTheWeb.as(actor).getDriver().findElement(
                MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true).description(\"test-Cart Content\"))" +
                                ".scrollIntoView(new UiSelector().text(\"" + productName + "\"));"
                )
        );
    }
}