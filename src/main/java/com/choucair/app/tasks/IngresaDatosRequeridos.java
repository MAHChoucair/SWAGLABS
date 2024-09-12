package com.choucair.app.tasks;

import static com.choucair.app.userinterface.UIMortgageRequest.*;

import com.choucair.app.interactions.ScrollToElement;
import com.choucair.app.models.DatosSolicitudMortgage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class IngresaDatosRequeridos implements Task {
    DatosSolicitudMortgage datosSecundario;

    public IngresaDatosRequeridos(DatosSolicitudMortgage datosSecundario) {
        this.datosSecundario = datosSecundario;
    }

    public static IngresaDatosRequeridos deSolicitudHipoteca(DatosSolicitudMortgage datosSecundario) {
        return Tasks.instrumented(IngresaDatosRequeridos.class, datosSecundario);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(TYPE_OF_LOAN_LABEL, WebElementStateMatchers.isVisible()).forNoMoreThan(30).seconds(),
                ScrollToElement.withText(datosSecundario.getTypeofloan())
        );

        // Hacer clic en el país una vez que esté visible
        Target DROPDOWN_OPTION = Target.the("dropdown option")
                .locatedBy("//android.widget.CheckedTextView[@resource-id='com.experitest.ExperiBank:id/rowTextView' and @text='{0}']");

        actor.attemptsTo(
                Click.on(DROPDOWN_OPTION.of(datosSecundario.getTypeofloan())),
                ScrollToElement.withText(datosSecundario.getNumberyear()),
                Click.on(DROPDOWN_OPTION.of(datosSecundario.getNumberyear())),
                ScrollToElement.withText(datosSecundario.getTypeofocupation()),
                Click.on(DROPDOWN_OPTION.of(datosSecundario.getTypeofocupation())),
                ScrollToElement.withText(datosSecundario.getYearlyIncome()),
                Click.on(DROPDOWN_OPTION.of(datosSecundario.getYearlyIncome()))
        );

        actor.attemptsTo(
                Click.on(SAVE_BUTTON)
        );
    }
}
