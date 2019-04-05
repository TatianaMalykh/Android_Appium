package tests;

import config.Configuration;
import helpers.LoadConfig;
import helpers.RunTest;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.io.IOException;

@Suite.SuiteClasses({TestSuite.class})
@RunWith(Suite.class)
public class SiamPortrait8 extends LoadConfig {
    @BeforeClass
    public static void setUp() throws IOException
    {
        config = new Configuration();
        config.load();

        String platformVersion = config.android.platformVersion_8;
        languageEng = config.android.language_ENG;
        String orientation = config.android.orientation_portrait;
        apk = config.android.siam;
        String app = config.android.apk_siam;

        new RunTest(platformVersion, app, orientation);

        logger.info("\nTestSuite name: " + Thread.currentThread().getStackTrace()[1].getClassName() + " for Android version: " + platformVersion + ", orientation: " + orientation + ", apk: " + app);
    }
}