package helpers;

import io.appium.java_client.android.AndroidDriver;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class RunTest extends LoadConfig{
    public RunTest(String platformVersion, String app, String orientation) throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", config.android.platformName);
        desiredCapabilities.setCapability("platformVersion", platformVersion);
        desiredCapabilities.setCapability("deviceName", config.android.deviceName);
        desiredCapabilities.setCapability("automationName", config.android.automationName);
        desiredCapabilities.setCapability("app", app);

        driver = new AndroidDriver(new URL(config.android.url_driver), desiredCapabilities);
        element = new Actions(driver);
        driver.rotate(ScreenOrientation.valueOf(orientation));
        element.selectLang(element, languageEng);
    }

    public static class Retry implements TestRule {
        private int retryCount;

        public Retry(int retryCount) {
            this.retryCount = retryCount;
        }

        public Statement apply(Statement base, Description description) {
            return statement(base, description);
        }

        private Statement statement(final Statement base, final Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    Throwable caughtThrowable = null;
                    for (int i = 0; i < retryCount; i++) {
                        try {
                            base.evaluate();
                            return;
                        } catch (Throwable t) {
                            caughtThrowable = t;
                            //logger.error(description.getDisplayName() + ": run " + (i+1) + " failed");
                            element.getscreenshot();
                            driver.resetApp();
                            element.selectLang(element, languageEng);
                        }
                    }
                    logger.error(description.getDisplayName() + ": giving up after " + retryCount + " failures. " + caughtThrowable);
                    throw caughtThrowable;
                }
            };
        }
    }
}
