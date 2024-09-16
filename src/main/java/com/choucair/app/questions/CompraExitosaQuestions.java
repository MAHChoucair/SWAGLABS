package com.choucair.app.questions;

import static com.choucair.app.userinterface.UICheckoutOver.COMPLETE_LABEL;
import static com.choucair.app.userinterface.UIPageHome.PRODUCT_LABEL;

import com.choucair.moviles.libreria.interactions.choucair.TakeScreenshot;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class CompraExitosaQuestions implements Question<Boolean> {

    String mensaje;

    public CompraExitosaQuestions(String mensaje) {
        this.mensaje = mensaje;
    }

    public static CompraExitosaQuestions verified(String mensaje) {
        return new CompraExitosaQuestions(mensaje);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(COMPLETE_LABEL, WebElementStateMatchers.isPresent()).forNoMoreThan(30).seconds(),
                TakeScreenshot.asEvidence()
        );
        String actual = Text.of(COMPLETE_LABEL).answeredBy(actor);
        return actual.equals(mensaje);
    }
}
