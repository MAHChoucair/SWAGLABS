package com.choucair.app.userinterface;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class UILogin {

    public static final Target USERNAME_INPUT = Target.the("campo de nombre de usuario")
            .located(AppiumBy.accessibilityId("test-Username"));

    public static final Target PASSWORD_INPUT= Target.the("Campo de texto para el password").located(AppiumBy.accessibilityId("test-Password"));

    public static final Target LOGIN_BTN= Target.the("Botón para ingresar").located(AppiumBy.accessibilityId("test-LOGIN"));

    public static final Target TEXT_HOME= Target.the("Texto del Login").located(AppiumBy.xpath("//android.widget.ScrollView[@content-desc='test-Login']/android.view.ViewGroup/android.widget.ImageView[1]"));

}
