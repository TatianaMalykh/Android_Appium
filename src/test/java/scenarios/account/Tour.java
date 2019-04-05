package scenarios.account;

import helpers.LoadConfig;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import scenarios.authorisation.LogInEnterData;

public class Tour extends LoadConfig {
    private LogInEnterData logIn = new LogInEnterData();

    @Before
    public void clearAndEnter(){
        element.clearData(apk);
        driver.resetApp();
        element.selectLang(element, languageEng);
        logIn.enter();
    }

    @Test
    public void checkBntContinue(){
        try {
            element.clickWithWait(By.id("btnContinue"));
            element.clickWithWait(By.id("btnContinue"));
            element.clickWithWait(By.id("btnContinue"));
            element.clickWithWait(By.id("btnContinue"));
            element.clickWithWait(By.id("btnClose"));
            logIn.out();
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e){
            throw e;
        }
    }
    @Test
    public void checkSkip(){
        try {
            element.clickWithWait(By.id("skip"));
            element.clickWithWait(By.id("skipBtnYes"));
            element.clickWithWait(By.id("btnClose"));
            logIn.out();
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e){
            throw e;
        }
    }
}