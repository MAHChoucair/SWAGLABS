package com.choucair.app.tasks;

import static com.choucair.app.userinterface.UICartHome.CHECKOUT_BTN;
import static com.choucair.app.userinterface.UICheckoutOver.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.choucair.app.interactions.ScrollToElement;
import com.choucair.app.interactions.SwipeToElement;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;

public class CheckoutOver implements Task {
    public static CheckoutOver deCompra() {
        return Tasks.instrumented(CheckoutOver.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CHECKOUTOVER_LABEL, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(CHECKOUTOVER_LABEL)
        );

        Set<String> productNamesList = new HashSet<>();
        boolean isLabelVisible = false;

        while (!isLabelVisible) {
            // Localiza todos los elementos que contienen los nombres de los productos visibles
            Target PRODUCT_NAME = Target.the("nombre del producto").locatedBy("//android.view.ViewGroup[@content-desc='test-Item']//android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView[1]");
            ListOfWebElementFacades productNames = PRODUCT_NAME.resolveAllFor(actor);

            // Itera sobre los elementos y extrae los nombres de los productos
            for (WebElement productNameElement : productNames) {
                String productName = productNameElement.getText();
                productNamesList.add(productName);
            }

            // Verifica si el Payment Information Label es visible
            isLabelVisible = Target.the("Payment Information Label")
                    .located(AppiumBy.xpath("//android.widget.TextView[@text='Payment Information:']"))
                    .resolveFor(actor)
                    .isPresent();

            if (!isLabelVisible) {
                // Utiliza UiScrollable para desplazarte dentro del contenedor de carrito
                BrowseTheWeb.as(actor).getDriver().findElement(
                        MobileBy.AndroidUIAutomator(
                                "new UiScrollable(new UiSelector().scrollable(true).description(\"test-CHECKOUT: OVERVIEW\"))" +
                                        ".scrollForward();"
                        )
                );
            }
        }

        // Obtiene la lista de productos seleccionados inicialmente
        List<String> productosValidosList = Serenity.sessionVariableCalled("productosValidos");

        boolean cantidadesCoinciden = productNamesList.size() == productosValidosList.size();
        assertTrue(cantidadesCoinciden, "La cantidad de productos no coinciden");
        boolean nombresCoinciden = true;
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

        for (String producto : productNamesList) {
            String precioEsperado = Serenity.sessionVariableCalled(producto + "-PRECIO");

            // Realiza el scroll hacia el producto
            actor.attemptsTo(SwipeToElement.with(producto, "test-CHECKOUT: OVERVIEW"));

            Target PRODUCT_PRICE = Target.the("precio del producto").locatedBy("//android.widget.TextView[contains(@text, '" + producto + "')]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup//android.widget.TextView[contains(@text, '$')]");

            // Desplázate hasta que el precio del producto sea visible
            while (!PRODUCT_PRICE.resolveFor(actor).isVisible()) {
                BrowseTheWeb.as(actor).getDriver().findElement(
                        MobileBy.AndroidUIAutomator(
                                "new UiScrollable(new UiSelector().scrollable(true).description(\"test-CHECKOUT: OVERVIEW\"))" +
                                        ".scrollForward();"
                        )
                );
                actor.attemptsTo(WaitUntil.the(PRODUCT_PRICE, isVisible()).forNoMoreThan(30).seconds());
            }

            String precioActual = PRODUCT_PRICE.resolveFor(actor).getText().replace("$", "");

            if (!precioEsperado.equals(precioActual)) {
                Logger.getAnonymousLogger().warning("El precio del producto " + producto + " no coincide. Esperado: " + precioEsperado + ", Actual: " + precioActual);
                preciosCoinciden = false;
            } else {
                Logger.getAnonymousLogger().info("El precio del producto " + producto + " coincide. Precio: " + precioActual);
            }
        }

        // Si todas las validaciones son correctas, realiza el clic en el botón de checkout
        if (nombresCoinciden && cantidadesCoinciden && preciosCoinciden) {
            actor.attemptsTo(
                    ScrollToElement.withText("Shipping Information:")
            );

            String itemTotalText = ITEM_TOTAL_LABEL.resolveFor(actor).getText();
            String itemTotalNumber = itemTotalText.replaceAll("[^\\d.]", "");
            double itemTotal = Double.parseDouble(itemTotalNumber);
            String taxText = TAX_LABEL.resolveFor(actor).getText();
            String taxNumber = taxText.replaceAll("[^\\d.]", "");
            double tax = Double.parseDouble(taxNumber);
            String totalText = TOTAL_LABEL.resolveFor(actor).getText();
            String totalNumber = totalText.replaceAll("[^\\d.]", "");
            double total = Double.parseDouble(totalNumber);

            if (total == itemTotal + tax){
                actor.attemptsTo(
                        SwipeToElement.with("FINISH", "test-CHECKOUT: OVERVIEW"),
                        Click.on(FINISH_BTN));
            } else {
                Logger.getAnonymousLogger().warning("El total no coincide.");
            }
        }

    }
}
