package scenarios.authorisation;

import helpers.LoadConfig;
import org.junit.Test;
import org.openqa.selenium.By;

public class Registration extends LoadConfig {

    private LogInEnterData logIn = new LogInEnterData();

    @Test
    public void positive() {
        try {
            enterData();
            logIn.closeTour();
            logIn.out();
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getClassName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void enterData() {
        long unixTime;
        unixTime = System.currentTimeMillis();
        String stringUnixTime = Long.toString(unixTime);
        String phone = stringUnixTime.substring(6, 13);//10

        try {
            element.clickWithWait(By.id("btnRegistration"));
            element.sendKeysWithWait(By.id("inputRegisterEmail"), config.user.email);
            try {//for IRON
                element.clickWithWait(By.id("inputRegisterCountry"));
                element.clickWithWait(By.xpath("//android.widget.TextView[@text='India']"));
                element.clickWithWait(By.id("phInputCode"));

                for (int i = 1; i <= 20; i++)
                    try {
                        element.presentWithWait(By.xpath("//android.widget.TextView[@text='Latvia (Latvija)']"));
                        element.clickWithWait(By.xpath("//android.widget.TextView[@text='Latvia (Latvija)']"));
                        break;
                    } catch (Exception e) {
                        element.swipeVertical(0.80, 0.20, 1000);
                    }
            } catch (Exception e) {
            }
            element.sendKeysWithWait(By.id("inputRegisterPhone"), "29609995");
            element.sendKeysWithWait(By.id("inputRegisterPwd"), config.user.pwd);
            driver.hideKeyboard();
            element.clickWithWait(By.id("cbTerms"));
            element.clickWithWait(By.id("btnRegisterSubmit"));
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void negativeWithEmptyFields() {
        try {
            element.clickWithWait(By.id("btnRegistration"));
            element.sendKeysWithWait(By.id("inputRegisterEmail"), "");
            driver.hideKeyboard();
            element.clickWithWait(By.id("btnRegisterSubmit"));
            element.presentWithWait(By.id("errorRegisterEmail"));
            element.presentWithWait(By.id("errorRegisterPhone"));
            element.presentWithWait(By.id("errorRegisterPwd"));
            element.presentWithWait(By.id("errorRegisterTerms"));
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getClassName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            throw e;
        }
    }

}
