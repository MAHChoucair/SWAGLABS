package com.choucair.app.userinterface;

import net.serenitybdd.screenplay.targets.Target;

import io.appium.java_client.AppiumBy;

public class UIPageHome {
    public static final Target LOGIN_SUCCESSFULL_TXT= Target.the("Logeo Exitoso").located(AppiumBy.xpath("//android.widget.TextView[starts-with(@text, 'Your balance is:')]"));

    public static Target BTN_OPCIONES(String id) {
        return Target.the("Opciones del Menu principal").located(AppiumBy.id("com.experitest.ExperiBank:id/" + id));
    }
}

