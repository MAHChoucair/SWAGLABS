package com.choucair.app.tasks;

import static com.choucair.app.userinterface.UICheckoutInfo.*;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.choucair.app.models.UserInformation;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class CheckoutInfo implements Task {
    UserInformation user;

    public CheckoutInfo(UserInformation user) {
        this.user = user;
    }

    public static CheckoutInfo deInformacion(UserInformation user) {
        return Tasks.instrumented(CheckoutInfo.class, user);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CHECKOUT_LABEL, isVisible()).forNoMoreThan(30).seconds(),
                Enter.theValue(user.getFirstname()).into(FIRST_NAME_INPUT),
                Enter.theValue(user.getLastname()).into(LAST_NAME_INPUT),
                Enter.theValue(user.getPostalcode()).into(ZIP_CODE_INPUT),
                Click.on(CONTINUE_BTN)
        );
    }
}
