package com.choucair.app.tasks;

import com.choucair.app.models.UserLogin;
import com.choucair.moviles.libreria.interactions.choucair.builders.Tap;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.choucair.app.userinterface.UILogin.*;
import static com.choucair.moviles.libreria.interactions.choucair.builders.Tap.siElElementoEsVisible;

import static net.serenitybdd.core.Serenity.getDriver;

import org.openqa.selenium.WebElement;

public class IniciaSesion implements Task {

    UserLogin loginData;

    public IniciaSesion(UserLogin loginData) {
        this.loginData = loginData;
    }

    public static IniciaSesion correctamente (UserLogin loginData){
        return Tasks.instrumented(IniciaSesion.class, loginData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Tap.siElElementoEsVisible(TEXT_HOME);

        actor.attemptsTo(
                Enter.theValue(loginData.getUsuario()).into(USERNAME_INPUT),
                Enter.theValue(loginData.getContrasena()).into(PASSWORD_INPUT),
                Click.on(LOGIN_BTN)
        );

    }
}