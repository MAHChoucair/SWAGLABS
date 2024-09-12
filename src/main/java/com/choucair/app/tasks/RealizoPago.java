package com.choucair.app.tasks;

import static com.choucair.app.userinterface.UIMakePayment.*;

import com.choucair.app.interactions.ScrollToElement;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.Random;



public class RealizoPago implements Task {
    // Generar un valor aleatorio del 1 al 10
    static Random random = new Random();

    private String country;
    private String name;
    private String phone;
    private String amount;

    public RealizoPago(String country, String phone, String name, String amount) {
        this.country = country;
        this.name = name;
        this.phone = phone;
        this.amount = amount;
    }

    public static RealizoPago conElPais(String country) {
        String defaultPhone = "+51966914593";
        String defaultName = Serenity.sessionVariableCalled("nombre");
        String defaultAmount = String.valueOf(random.nextInt(10) + 1);
        Serenity.setSessionVariable("randomMonto").to(defaultAmount);
        return Tasks.instrumented(RealizoPago.class, country, defaultPhone, defaultName, defaultAmount);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                //Tap.siElElementoEsVisible(IMG_ERIBANK),
                WaitUntil.the(PHONE_INPUT, WebElementStateMatchers.isVisible()).forNoMoreThan(30).seconds(),
                Enter.theValue(phone).into(PHONE_INPUT),
                Enter.theValue(name).into(NAME_INPUT),
                Enter.theValue(amount).into(AMOUNT_INPUT),
                Click.on(SELECT_BTN)
        );

        // Scroll hacia el país que buscas
        actor.attemptsTo(
                WaitUntil.the(LIST_DROPDOWN, WebElementStateMatchers.isVisible()).forNoMoreThan(30).seconds(),
                ScrollToElement.withText(country) // Realiza el scroll
        );

        // Hacer clic en el país una vez que esté visible
        Target DROPDOWN_OPTION = Target.the("dropdown option")
                .locatedBy("//android.widget.TextView[@resource-id='com.experitest.ExperiBank:id/rowTextView' and @text='{0}']");

        actor.attemptsTo(
                Click.on(DROPDOWN_OPTION.of(country))
        );


        actor.attemptsTo(
                Click.on(SEND_PAYMENT_BTN)
        );
    }
}
