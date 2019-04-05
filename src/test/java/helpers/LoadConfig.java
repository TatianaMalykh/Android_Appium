package helpers;

import config.Configuration;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.junit.Rule;
import scenarios.authorisation.LogInEnterData;

public class LoadConfig {
    protected static Configuration config;
    protected static AppiumDriver driver;
    protected static Actions element;
    protected static String apk;
    protected static String languageEng;
    protected static final Logger logger = Logger.getLogger(LogInEnterData.class);

    @Rule
    public RunTest.Retry retry = new RunTest.Retry(3);

/*@BeforeClass
    public static void setUp() throws IOException {
        config = new Configuration();
        config.load();

        String platformVersion = config.android.platformVersion_8;
        languageEng = config.android.language_ENG;
        String orientation = config.android.orientation_portrait;
        apk = config.android.iron;
        String app = config.android.apk_iron;

        new RunTest(platformVersion, app, orientation);

        logger.info("\nTestSuite name: " + Thread.currentThread().getStackTrace()[1].getClassName() + " for Android version: " + platformVersion + ", orientation: " + orientation + ", apk: " + app);
    }*/
}
