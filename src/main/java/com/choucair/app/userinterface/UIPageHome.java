package com.choucair.app.userinterface;

import net.serenitybdd.screenplay.targets.Target;

import io.appium.java_client.AppiumBy;

public class UIPageHome {
    public static final Target PRODUCT_LABEL= Target.the("Texto del home").located(AppiumBy.xpath("//android.widget.TextView[@text='PRODUCTS']"));

    public static Target BTN_OPCIONES(String id) {
        return Target.the("Opciones del Menu principal").located(AppiumBy.id("com.experitest.ExperiBank:id/" + id));
    }
}

