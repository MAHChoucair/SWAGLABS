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
        ,tags = "@RequestExitosa"
        ,glue = "stepdefinitions"
        ,plugin = {"pretty","json:target/cucumber-reports/cucumber.json"}
        ,snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class RunnerCucumber {

    @Managed(driver = "appium")
    static AndroidDriver driver;

    private Actions actions;

    @BeforeClass
    public static void SetUp() throws Exception {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:app", "storage:filename=ExperiBank.apk");  // The filename of the mobile app
        caps.setCapability("appium:deviceName", "Google Pixel 5 GoogleAPI Emulator");
        caps.setCapability("appium:platformVersion", "12.0");
        caps.setCapability("appium:automationName", "UiAutomator2");
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", "oauth-caguirreh-d0632");
        sauceOptions.setCapability("accessKey", "a7b5d122-b9f1-4b75-b06a-4e7a886d13e3");
        sauceOptions.setCapability("build", "appium-build-N3G51");
        sauceOptions.setCapability("name", "<your test name>");
        sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
        caps.setCapability("sauce:options", sauceOptions);

        // Start the session
        URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
        driver = new AndroidDriver(url, caps);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void CloseApp() {
        driver.quit();
    }

}