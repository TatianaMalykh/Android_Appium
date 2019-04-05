package tests;

import helpers.LoadConfig;
import org.junit.runner.RunWith;
import scenarios.account.Veririfacation;

@org.junit.runners.Suite.SuiteClasses({
        //LogInSocial.class,
        //LogInEnterData.class,
        //Tour.class,
        //Registration.class,
        //ForgotPwd.class,
        //StartPage.class,
        //Trade.class,
        Veririfacation.class
        })
@RunWith(org.junit.runners.Suite.class)
public class TestSuite extends LoadConfig {
}