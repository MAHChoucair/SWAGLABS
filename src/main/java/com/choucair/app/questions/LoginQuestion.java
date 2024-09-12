package com.choucair.app.questions;


import static com.choucair.app.userinterface.UIPageHome.*;

import com.choucair.moviles.libreria.interactions.choucair.TakeScreenshot;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class LoginQuestion implements Question <Boolean> {

    String mensaje;

    public LoginQuestion(String mensaje) {
        this.mensaje = mensaje;
    }

    public static LoginQuestion verifiedLogin(String mensaje) {
        return new LoginQuestion(mensaje);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(LOGIN_SUCCESSFULL_TXT, WebElementStateMatchers.isPresent()).forNoMoreThan(30).seconds(),
                TakeScreenshot.asEvidence()
        );
        String actual = Text.of(LOGIN_SUCCESSFULL_TXT).answeredBy(actor);
        return actual.contains(mensaje);
    }
}
