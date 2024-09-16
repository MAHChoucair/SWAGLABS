package com.choucair.app.userinterface;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;

import io.appium.java_client.AppiumBy;

public class UICheckoutOver {
    public static final Target CHECKOUTOVER_LABEL =
            Target.the("CheckoutInfo Overview Label").located(By.xpath("//android.widget.TextView[@text='CHECKOUT: OVERVIEW']"));
    public static final Target ITEM_TOTAL_LABEL =
            Target.the("Item Total Label").located(By.xpath("//android.widget.TextView[starts-with(@text,'Item total')]"));
    public static final Target TAX_LABEL =
            Target.the("Tax Label").located(By.xpath("//android.widget.TextView[starts-with(@text,'Tax')]"));
    public static final Target TOTAL_LABEL =
            Target.the("Total Label").located(By.xpath("//android.widget.TextView[starts-with(@text,'Total')]"));
//    static Target ITEM_LABEL =
//            Target.the("Item Label").locatedBy("//android.widget.TextView[@text='{0}']");
//    static Target PRICE_LABEL =
//            Target.the("Price Label").locatedBy("//android.widget.TextView[@text='{0}']");
    public static final Target FINISH_BTN =
            Target.the("Finish Btn").located(AppiumBy.accessibilityId("test-FINISH"));
    public static final Target COMPLETE_LABEL =
            Target.the("Thank You Label").located(AppiumBy.xpath("//android.widget.TextView[@text='THANK YOU FOR YOU ORDER']"));
    public static final Target BACK_HOME_BTN =
            Target.the("Back Home Btn").located(AppiumBy.xpath("//android.widget.TextView[@text='BACK HOME']"));
}
