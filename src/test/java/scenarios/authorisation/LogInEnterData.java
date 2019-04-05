package scenarios.authorisation;

import helpers.LoadConfig;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LogInEnterData extends LoadConfig
{
    @Before
    public void lang() {
        //element.selectLang(element, languageEng);
    }

    @Test
    public void positive() {
        try
        {   enter();
            closeTour();
            out();
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            throw e;
        }
    }

    public void enter(){
        try
        {
            element.selectLang(element, languageEng);
            element.clickWithWait(By.id("btnLogin"));
            element.sendKeysWithWait(By.id("inputLoginEmail"), config.user.email);
            element.sendKeysWithWait(By.id("inputLoginPwd"), config.user.pwd);
            driver.hideKeyboard();
            element.clickWithWait(By.id("btnLoginSubmit"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void closeTour() {
        try
        {
            element.presentWithWait(By.id("tourOneTitle"));
            element.clickWithWait(By.id("skip"));
            element.clickWithWait(By.id("skipBtnYes"));
            element.clickWithWait(By.id("btnClose"));
        } catch (Exception e) {
        }
    }

    public void out() {
        try
        {
            element.clickWithWait(By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']"));
            element.clickWithWait(By.xpath("//android.widget.CheckedTextView[@text='Log out']"));
            element.presentWithWait(By.id("inputLoginEmail"));
        } catch (Exception e) {
            throw e;
        }
    }
    @Test
    public void negativeEnterEmptyFields(){
        try
        {
            element.clickWithWait(By.id("btnLogin"));
            element.sendKeysWithWait(By.id("inputLoginEmail"), "");
            driver.hideKeyboard();
            element.clickWithWait(By.id("btnLoginSubmit"));
            element.presentWithWait(By.id("errorLoginEmail"));
            element.presentWithWait(By.id("errorLoginPwd"));
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            throw e;
        }
    }
    @Test
    public void negativeEnterWrongData(){
        try {
            element.clickWithWait(By.id("btnLogin"));
            element.sendKeysWithWait(By.id("inputLoginEmail"),  config.user.part_email + "@y.ru");
            element.sendKeysWithWait(By.id("inputLoginPwd"),  config.user.pwd);
            driver.hideKeyboard();
            element.clickWithWait(By.id("btnLoginSubmit"));

            element.presentWithWait(By.id("icWarn"));
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName()+ " Got error: "+ driver.findElement(By.id("title")).getText());
            element.clickWithWait(By.id("btnOk"));
        } catch (Exception e) {
            throw e;
        }
    }
}
