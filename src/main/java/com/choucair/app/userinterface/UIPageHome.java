package com.choucair.app.userinterface;

import net.serenitybdd.screenplay.targets.Target;

import io.appium.java_client.AppiumBy;

public class UIPageHome {
    public static final Target PRODUCT_LABEL=
            Target.the("Texto del home").located(AppiumBy.xpath("//android.widget.TextView[@text='PRODUCTS']"));
    public static final Target MODO_VISTA =
            Target.the("Modo vista").located(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Toggle']/android.widget.ImageView']"));
    public static Target ITEMS_LABEL =
            Target.the("Items").locatedBy("//android.widget.TextView[@text='{0}']");
    //public static Target ADD_CART_BTN =
    //        Target.the("Add Cart Btn").locatedBy("//android.widget.TextView[@text='ADD TO CART'])[{0}]");

    public static final Target CART_BTN =
            Target.the("Cart Btn").located(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Cart']/android.view.ViewGroup/android.widget.ImageView"));
}

