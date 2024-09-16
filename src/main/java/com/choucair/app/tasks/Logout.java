package com.choucair.app.tasks;

import static com.choucair.app.userinterface.UILogin.USERNAME_INPUT;
import static com.choucair.app.userinterface.UIPageHome.*;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class Logout implements Task {
    public static Logout app() {
        return Tasks.instrumented(Logout.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(OPCIONES_BUTTON, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(OPCIONES_BUTTON),
                WaitUntil.the(LOGOUT_BTN, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(LOGOUT_BTN),
                WaitUntil.the(USERNAME_INPUT, isVisible()).forNoMoreThan(30).seconds()
        );
    }
}
