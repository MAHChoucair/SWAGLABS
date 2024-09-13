package com.choucair.app.userinterface;

import net.serenitybdd.screenplay.targets.Target;

import io.appium.java_client.AppiumBy;

public class UICartHome {
    public static final Target YOUR_CART_LABEL =
        Target.the("Add Cart Btn").located(AppiumBy.xpath("//android.widget.TextView[@text='YOUR CART']"));
    public static final Target ITEM_LABEL =
        Target.the("Items Label").located(AppiumBy.xpath("//android.widget.TextView[@text='Sauce Labs Bike Light']"));
    public static final Target CHECKOUT_BTN =
        Target.the("Checkout Btn").located(AppiumBy.xpath("//android.widget.TextView[@text='CHECKOUT']"));
}
