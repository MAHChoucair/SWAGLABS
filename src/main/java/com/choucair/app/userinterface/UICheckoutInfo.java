package com.choucair.app.userinterface;

import net.serenitybdd.screenplay.targets.Target;

import io.appium.java_client.AppiumBy;

public class UICheckoutInfo {
    private static final Target CHECKOUT_LABEL =
            Target.the("Checkout Label").located(AppiumBy.xpath("//android.widget.TextView[@text='CHECKOUT: INFORMATION']"));
    private static final Target FIRST_NAME_INPUT =
            Target.the("First Name Input").located(AppiumBy.accessibilityId("test-First Name"));
    private static final Target LAST_NAME_INPUT =
            Target.the("Last Name Input").located(AppiumBy.accessibilityId("test-Last Name"));
    private static final Target ZIP_CODE_INPUT =
            Target.the("Zip Code Input").located(AppiumBy.accessibilityId("test-Zip/Postal Code"));
    private static final Target CONTINUE_BTN =
            Target.the("Continue Btn").located(AppiumBy.accessibilityId("test-CONTINUE"));
}

