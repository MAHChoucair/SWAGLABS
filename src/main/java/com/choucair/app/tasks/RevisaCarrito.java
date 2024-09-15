package com.choucair.app.tasks;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import static com.choucair.app.userinterface.UICartHome.*;
import static com.choucair.app.userinterface.UIPageHome.CART_BTN;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.choucair.app.interactions.ScrollToElement;
import com.choucair.app.interactions.SwipeToElement;
import com.choucair.moviles.libreria.interactions.choucair.ScrollTo;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import org.openqa.selenium.By;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class RevisaCarrito implements Task {

    public static RevisaCarrito deCompras() {
        return Tasks.instrumented(RevisaCarrito.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CART_BTN, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(CART_BTN)
        );

        actor.attemptsTo(
                WaitUntil.the(YOUR_CART_LABEL, isVisible()).forNoMoreThan(30).seconds()
        );

        Set<String> productNamesList = new HashSet<>();
        boolean isCheckoutVisible = false;

        while (!isCheckoutVisible) {
            // Localiza todos los elementos que contienen los nombres de los productos visibles
            Target PRODUCT_NAME = Target.the("nombre del producto").locatedBy("//android.view.ViewGroup[@content-desc='test-Item']//android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView[1]");
            ListOfWebElementFacades productNames = PRODUCT_NAME.resolveAllFor(actor);

            // Itera sobre los elementos y extrae los nombres de los productos
            for (WebElement productNameElement : productNames) {
                String productName = productNameElement.getText();
                productNamesList.add(productName);
            }

            // Verifica si el botón de checkout es visible
            isCheckoutVisible = !Target.the("botón de checkout")
                    .located(AppiumBy.xpath("//android.widget.TextView[@text='CHECKOUT']"))
                    .resolveFor(actor)
                    .isPresent();

            if (!isCheckoutVisible) {
                // Realiza un desplazamiento dentro del contenedor que agrupa los productos
                BrowseTheWeb.as(actor).getDriver().findElement(
                        MobileBy.AndroidUIAutomator(
                                "new UiScrollable(new UiSelector()" +
                                        ".scrollable(true)).scrollForward();"
                        )
                );
            }
        }

        // Obtiene la lista de productos seleccionados inicialmente
        List<String> productosValidosList = Serenity.sessionVariableCalled("productosValidos");

        boolean nombresCoinciden = true;
        boolean cantidadesCoinciden = productNamesList.size() == productosValidosList.size();
        assertTrue(cantidadesCoinciden, "La cantidad de productos no coinciden");
        boolean preciosCoinciden = true;

        // Valida que los nombres de los productos en el carrito coincidan con los nombres de los productos seleccionados inicialmente
        for (String name : productNamesList) {
            if (!productosValidosList.contains(name)) {
                Logger.getAnonymousLogger().warning("Producto en el carrito no coincide: " + name);
                nombresCoinciden = false;
            } else {
                Logger.getAnonymousLogger().info("Producto en el carrito coincide: " + name);
            }
        }

//        int maxSwipeAttempts = productNamesList.size() == productosValidosList.size() ? productNamesList.size() : 6;
//
//        for (String producto : productosValidosList) {
//            String precioEsperado = Serenity.sessionVariableCalled(producto + "-PRECIO");
//
//            // Realiza el scroll hacia el producto
//            actor.attemptsTo(SwipeToElement.withName(producto));
//
//            Target PRODUCT_PRICE = Target.the("precio del producto").locatedBy("//android.widget.TextView[contains(@text, '" + producto + "')]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup//android.widget.TextView[contains(@text, '$')]");
//            actor.attemptsTo(WaitUntil.the(PRODUCT_PRICE, isVisible()).forNoMoreThan(30).seconds());
//
//            String precioActual = PRODUCT_PRICE.resolveFor(actor).getText().replace("$", "");
//
//            if (!precioEsperado.equals(precioActual)) {
//                Logger.getAnonymousLogger().warning("El precio del producto " + producto + " no coincide. Esperado: " + precioEsperado + ", Actual: " + precioActual);
//                preciosCoinciden = false;
//            } else {
//                Logger.getAnonymousLogger().info("El precio del producto " + producto + " coincide. Precio: " + precioActual);
//            }
//        }

        // Si todas las validaciones son correctas, realiza el clic en el botón de checkout
        /*if (nombresCoinciden && cantidadesCoinciden && preciosCoinciden) {
            actor.attemptsTo(
                    ScrollToElement.withText("CHECKOUT"),
                    Click.on(CHECKOUT_BTN)
            );
        }*/
    }
}