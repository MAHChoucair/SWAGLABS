package com.choucair.app.userinterface;

import net.serenitybdd.screenplay.targets.Target;

import io.appium.java_client.AppiumBy;

public class UIMortgageRequest {
    public static final Target LASTNAME_INPUT = Target.the("lastname input").located(AppiumBy.id("com.experitest.ExperiBank:id/lastNameTextField"));
    public static final Target AGE_INPUT = Target.the("age input").located(AppiumBy.id("com.experitest.ExperiBank:id/ageTextField"));
    public static final Target ADDRESS1_INPUT = Target.the("address1 input").located(AppiumBy.id("com.experitest.ExperiBank:id/addressOneTextField"));
    public static final Target ADDRESS2_INPUT = Target.the("address2 input").located(AppiumBy.id("com.experitest.ExperiBank:id/addressTwoTextField"));
    //public static final Target TYPEOFLOAN_LIST_DROPDOWN = Target.the("Type of Loan list dropdown").located(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id=\"com.experitest.ExperiBank:id/rowTextView\"]"));
    public static final Target TYPE_OF_LOAN_LABEL = Target.the("pais dropdown").located(AppiumBy.id("com.experitest.ExperiBank:id/typeOfLoanTextView"));
    public static final Target NEXT_BUTTON = Target.the("next button").located(AppiumBy.id("com.experitest.ExperiBank:id/nextButton"));
    public static final Target SAVE_BUTTON = Target.the("save button").located(AppiumBy.id("com.experitest.ExperiBank:id/saveButton"));

}
