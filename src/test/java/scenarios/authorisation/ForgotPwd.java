package scenarios.authorisation;

import helpers.LoadConfig;
import org.junit.Test;
import org.openqa.selenium.By;

public class ForgotPwd extends LoadConfig
{

    @Test
    public void positive() {
        try {
            element.clickWithWait(By.id("btnLogin"));
            element.clickWithWait(By.id("btnForgotPwd"));
            element.sendKeysWithWait(By.id("inputRemindEmail"), config.user.email);
            driver.hideKeyboard();
            element.clickWithWait(By.id("btnRemindSubmit"));
            element.presentWithWait(By.id("inputLoginPwd"));
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            throw e;
        }
    }
    @Test
    public void negativeWithEmptyFields() {
        try{
            element.clickWithWait(By.id("btnLogin"));
            element.clickWithWait(By.id("btnForgotPwd"));
            element.sendKeysWithWait(By.id("inputRemindEmail"), "");
            driver.hideKeyboard();
            element.clickWithWait(By.id("btnRemindSubmit"));
            element.presentWithWait(By.id("icWarn"));
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName()+ driver.findElement(By.id("title")).getText() + " Got error: "+ driver.findElement(By.id("title")).getText());
            element.clickWithWait(By.id("btnOk"));
            element.tap();
            try {
                element.clickWithWait(By.id("btnHaveAccount"));
            } catch (Exception e) {
                driver.resetApp();
                element.selectLang(element, languageEng);
            }
        }catch (Exception e) {
            throw e;
        }
    }
    @Test
    public void negativeWithWrongData(){
        try{
            element.clickWithWait(By.id("btnLogin"));
            element.clickWithWait(By.id("btnForgotPwd"));
            element.sendKeysWithWait(By.id("inputRemindEmail"), config.user.part_email + "@y.ru");
            driver.hideKeyboard();
            element.clickWithWait(By.id("btnRemindSubmit"));
            element.presentWithWait(By.id("icWarn"));
            element.clickWithWait(By.id("btnOk"));
            element.tap();

            //element.clickWithWait(By.id("btnHaveAccount"));
            //element.presentWithWait(By.xpath("//android.widget.TextView[@text='Login']"));
            //element.clickWithWait(By.xpath("//android.widget.TextView[@text='Login']"));
            //element.clickWithWait(By.partialLinkText("Login"));

            //WebDriver el = (WebDriver) driver.findElementsByPartialLinkText("Login");

            //By.xpath(".//*[text()='Первая ссылка']/..")
            try {
                element.clickWithWait(By.id("btnHaveAccount"));
            } catch (Exception e) {
                driver.resetApp();
                element.selectLang(element, languageEng);
            }
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName());

        }catch (Exception e) {
            throw e;
        }
    }
}
