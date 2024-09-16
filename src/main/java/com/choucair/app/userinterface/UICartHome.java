package com.choucair.app.userinterface;

import net.serenitybdd.screenplay.targets.Target;

import io.appium.java_client.AppiumBy;

public class UICartHome {
    public static final Target YOUR_CART_LABEL =
        Target.the("Add Cart Btn").located(AppiumBy.xpath("//android.widget.TextView[@text='YOUR CART']"));
    //public static Target ITEM_LABEL =
        //Target.the("Items Label").locatedBy("//android.widget.TextView[@text='{0}']");
    public static final Target CHECKOUT_BTN =
        Target.the("CheckoutInfo Btn").located(AppiumBy.xpath("//android.widget.TextView[@text='CHECKOUT']"));
    //public static Target PRECIO_ITEM =
}
