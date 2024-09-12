package com.choucair.app.tasks;

import static com.choucair.app.userinterface.UIMakePayment.*;
import static com.choucair.app.userinterface.UIMortgageRequest.*;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.choucair.app.interactions.ScrollToElement;
import com.choucair.app.models.UserMortgage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class IngresaDatosPersonales implements Task {

    UserMortgage userMortgage;

    public IngresaDatosPersonales(UserMortgage userMortgage) {
        this.userMortgage = userMortgage;
    }

    public static IngresaDatosPersonales deSolicitudHipoteca(UserMortgage userMortgage) {
        return Tasks.instrumented(IngresaDatosPersonales.class, userMortgage);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(NAME_INPUT, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(userMortgage.getName()).into(NAME_INPUT),
                Enter.theValue(userMortgage.getLastname()).into(LASTNAME_INPUT),
                Enter.theValue(userMortgage.getAge()).into(AGE_INPUT),
                Enter.theValue(userMortgage.getAddress1()).into(ADDRESS1_INPUT),
                Enter.theValue(userMortgage.getAddress2()).into(ADDRESS2_INPUT),
                Click.on(SELECT_BTN)
        );

        // Scroll hacia el país que buscas
        actor.attemptsTo(
                WaitUntil.the(LIST_DROPDOWN, WebElementStateMatchers.isVisible()).forNoMoreThan(30).seconds(),
                ScrollToElement.withText(userMortgage.getPais()) // Realiza el scroll
        );

        // Hacer clic en el país una vez que esté visible
        Target DROPDOWN_OPTION = Target.the("dropdown option")
                .locatedBy("//android.widget.TextView[@resource-id='com.experitest.ExperiBank:id/rowTextView' and @text='{0}']");

        actor.attemptsTo(
                Click.on(DROPDOWN_OPTION.of(userMortgage.getPais()))
        );
        actor.attemptsTo(
                WaitUntil.the(NEXT_BUTTON, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(NEXT_BUTTON),
                WaitUntil.the(TYPE_OF_LOAN_LABEL, WebElementStateMatchers.isVisible()).forNoMoreThan(30).seconds()
        );
    }
}
