package scenarios.authorisation;

import helpers.LoadConfig;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LogInSocial extends LoadConfig
{
    @Before
    public void lang(){
        element.selectLang(element, languageEng);
    }

    @Test
    public void redirectToFacebookAccount(){
        try {
            element.clickWithWait(By.id("btnLogin"));
            element.clickWithWait(By.id("btnLoginFb"));
            Boolean displayed = false;
            try {
                element.clickWithWait(By.xpath("//android.widget.Button[1]"));
                element.clickWithWait(By.xpath("//android.widget.ImageView[1]"));
                displayed = true;
                logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName());
            } catch (Exception e) {
                logger.debug("Test " + Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName() + " has failed." + e);
            }
            if (!displayed) {
                System.out.println("ERROR! Redirect to Facebook Account has failed.");
            }
        } catch (Exception e){
            throw e;
        }
        driver.resetApp();
        //element.selectLang(element, languageEng);
    }
    @Test
    public void redirectToGoogleAccount(){
        try {
            element.clickWithWait(By.id("btnLogin"));
            element.clickWithWait(By.id("btnLoginGp"));
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e){
            throw e;
        }
        driver.resetApp();
        //element.selectLang(element, languageEng);
    }
}
