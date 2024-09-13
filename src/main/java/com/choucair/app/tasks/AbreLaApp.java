package com.choucair.app.tasks;

import com.choucair.app.interactions.TakeScreenShot;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.util.logging.Logger;

public class AbreLaApp implements Task {

    public AbreLaApp(){  }

    public static AbreLaApp swagLabs(){
        return Tasks.instrumented(AbreLaApp.class);
    }

    @Override
    @Step("App Swag Labs abierta")
    public <T extends Actor> void performAs(T actor) {
        Logger.getAnonymousLogger().info("Se abre app Swag Labs");
        actor.attemptsTo(TakeScreenShot.asEvidencie());
    }
}