package com.choucair.app.tasks;

import static com.choucair.app.userinterface.UICartHome.*;
import static com.choucair.app.userinterface.UIPageHome.CART_BTN;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

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

        actor.attemptsTo(
                Click.on(CHECKOUT_BTN)
        );
    }
}
