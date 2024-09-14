package com.choucair.app.tasks;

import static com.choucair.app.userinterface.UIPageHome.*;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.choucair.app.interactions.ScrollToElement;
import com.choucair.moviles.libreria.interactions.choucair.builders.Tap;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.List;

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
                    WaitUntil.the(MODO_VISTA, isVisible()).forNoMoreThan(30).seconds(),
                    Click.on(MODO_VISTA),
                    ScrollToElement.withText(productos)
            );

        // Busca el contenedor del producto basado en el título del producto
        Target PRODUCTO_CONTENEDOR = Target.the("contenedor del producto")
                .locatedBy("//android.widget.TextView[@content-desc='test-item title' and @text='" + productos + "']/ancestor::android.view.ViewGroup[@content-desc='test-item']");

        // Busca el botón "+" dentro del contenedor encontrado
        Target ADD_CART_BUTTON = Target.the("botón de agregar al carrito")
                .locatedBy("//android.widget.TextView[@content-desc='test-item title' and @text='" + productos + "']/ancestor::android.view.ViewGroup[@content-desc='test-item']//android.view.ViewGroup[@content-desc='test-ADD TO CART']//android.widget.TextView[@text='+']");
            // Realiza las acciones sobre el contenedor y el botón del producto
            actor.attemptsTo(
                    WaitUntil.the(ADD_CART_BUTTON, isVisible()).forNoMoreThan(30).seconds(),  // Asegura que el contenedor esté visible y seleccionado
                    Click.on(ADD_CART_BUTTON)                        // Haz clic en el botón de "ADD TO CART"
            );

    }
}
