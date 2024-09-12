package com.choucair.app.userinterface;

import net.serenitybdd.screenplay.targets.Target;

import io.appium.java_client.AppiumBy;

public class UIMakePayment {
    public static final Target PHONE_INPUT = Target.the("phone input").located(AppiumBy.id("com.experitest.ExperiBank:id/phoneTextField"));
    public static final Target NAME_INPUT = Target.the("name input").located(AppiumBy.id("com.experitest.ExperiBank:id/nameTextField"));
    public static final Target AMOUNT_INPUT = Target.the("amount input").located(AppiumBy.id("com.experitest.ExperiBank:id/amountTextField"));
    public static final Target SELECT_BTN = Target.the("select country button").located(AppiumBy.id("com.experitest.ExperiBank:id/countryButton"));
    public static final Target SEND_PAYMENT_BTN = Target.the("send payment button").located(AppiumBy.id("com.experitest.ExperiBank:id/sendPaymentButton"));
    public static final Target ACEPT_BUTTON = Target.the("confirmar button").located(AppiumBy.id("android:id/button1"));
    public static final Target LIST_DROPDOWN = Target.the("list dropdown").located(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.experitest.ExperiBank:id/rowTextView\"]"));
}
