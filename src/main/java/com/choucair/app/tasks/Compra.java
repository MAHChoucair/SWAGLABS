package com.choucair.app.tasks;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.choucair.app.interactions.SwipeToElement;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class Compra implements Task {
    private String productos;

    public Compra(String productos) {
        this.productos = productos;
    }

    public static Compra deProductos(String productos) {
        return Tasks.instrumented(Compra.class, productos);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                SwipeToElement.with(productos, "test-PRODUCTS")
        );

        // Busca el botón "+" dentro del contenedor encontrado usando la tercera opción
        Target ADD_CART_BUTTON = Target.the("botón de agregar al carrito")
                .locatedBy("//android.widget.TextView[contains(@text, '" + productos + "')]/following-sibling::android.view.ViewGroup//android.widget.TextView[@text='+']");

        // Busca el precio del producto
        Target PRODUCT_PRICE = Target.the("precio del producto")
                .locatedBy("//android.widget.TextView[contains(@text, '" + productos + "')]/following-sibling::android.widget.TextView[contains(@text, '$')]");

        // Realiza las acciones sobre el contenedor y el botón del producto
        actor.attemptsTo(
               // WaitUntil.the(PRODUCTO_CONTENEDOR, isVisible()).forNoMoreThan(30).seconds(),
                WaitUntil.the(ADD_CART_BUTTON, isVisible()).forNoMoreThan(30).seconds(),  // Asegura que el contenedor esté visible y seleccionado
                Click.on(ADD_CART_BUTTON)                        // Haz clic en el botón de "ADD TO CART"
        );

        // Almacena el precio del producto en una variable de sesión con un identificador único
        String precio = PRODUCT_PRICE.resolveFor(actor).getText().replace("$", "");
        Serenity.setSessionVariable(productos + "-PRECIO").to(precio);

    }
}
