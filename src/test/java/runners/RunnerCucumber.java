package runners;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.interactions.Actions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features"}
        ,tags = "@CompraExitosa"
        ,glue = "stepdefinitions"
        ,plugin = {"pretty","json:target/cucumber-reports/cucumber.json"}
        ,snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class RunnerCucumber {
    private static AndroidDriver driver;

    private Actions actions;

   /* @BeforeClass
    public static void SetUp() throws Exception {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:app", "storage:filename=swaglabs.apk");  // The filename of the mobile app
        caps.setCapability("appium:deviceName", "Google Pixel 5 GoogleAPI Emulator");
        caps.setCapability("appium:platformVersion", "13");
        caps.setCapability("appium:automationName", "UiAutomator2");
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", "oauth-melisaa562-ec87c");
        sauceOptions.setCapability("accessKey", "f7904bb6-27e2-4693-ac11-3c1b48ca82d5");
        sauceOptions.setCapability("build", "appium-build-VSCZS");
        sauceOptions.setCapability("name", "<your test name>");
        sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
        caps.setCapability("sauce:options", sauceOptions);

        // Start the session
        URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
        driver = new AndroidDriver(url, caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterClass
    public static void CloseApp() {

        if (driver != null){
        driver.quit();}
    }*/

}