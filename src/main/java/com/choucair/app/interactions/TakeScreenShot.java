package com.choucair.app.interactions;


import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;

public class TakeScreenShot  implements Interaction {

    public TakeScreenShot(){
    }

    public static TakeScreenShot asEvidencie() {
        return Tasks.instrumented(TakeScreenShot.class);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        Serenity.takeScreenshot();
    }
}
