package com.choucair.app.userinterface;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;

import io.appium.java_client.AppiumBy;

public class UICheckoutOver {
    private static final Target CHECKOUT_LABEL =
            Target.the("Checkout Overview Label").located(By.xpath("//android.widget.TextView[@text='CHECKOUT: OVERVIEW']"));
    private static final Target ITEM_TOTAL_LABEL =
            Target.the("Item Total Label").located(By.xpath("//android.widget.TextView[starts-with(@text,'Item total')]"));
    private static final Target TAX_LABEL =
            Target.the("Tax Label").located(By.xpath("//android.widget.TextView[starts-with(@text,'Tax')]"));
    private static final Target TOTAL_LABEL =
            Target.the("Total Label").located(By.xpath("//android.widget.TextView[starts-with(@text,'Total')]"));
    static Target ITEM_LABEL =
            Target.the("Item Label").locatedBy("//android.widget.TextView[@text='{0}']");
    static Target PRICE_LABEL =
            Target.the("Price Label").locatedBy("//android.widget.TextView[@text='{0}']");
    private static final Target FINISH_BTN =
            Target.the("Finish Btn").located(AppiumBy.accessibilityId("test-FINISH"));
    private static final Target COMPLETE_LABEL =
            Target.the("Thank You Label").located(AppiumBy.xpath("//android.widget.TextView[@text='THANK YOU FOR YOU ORDER']"));
}
