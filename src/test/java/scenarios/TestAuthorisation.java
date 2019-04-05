package scenarios;

import helpers.ClearData;
import helpers.LoadConfig;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.HashMap;

public class TestAuthorisation extends LoadConfig
{
   @Test
    public void logInCorrectData(){
        new ClearData().application(config.android.app_clear);
        logInEnterCorrectData();
        logInCloseTour();
        logInOutCorrectData();
    }

    protected void logInEnterCorrectData(){
        try
        {
            element.clickWithWait(By.id("btnLogin"));
            element.sendKeysWithWait(By.id("inputLoginEmail"), config.user.email);
            element.sendKeysWithWait(By.id("inputLoginPwd"), config.user.pwd);
            element.clickWithWait(By.id("btnLoginSubmit"));
            element.presentWithWait(By.id("tourOneTitle"));
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getMethodName());
        }catch (Exception e){
            logger.error("Test " + Thread.currentThread().getStackTrace()[1].getMethodName() + " has failed." + "\n" + e);
        }
    }

    protected void logInCloseTour(){
        try
        {
            element.clickWithWait(By.id("skip"));
            element.clickWithWait(By.id("skipBtnYes"));
            element.clickWithWait(By.id("btnClose"));
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getMethodName());
        }catch (Exception e){
            logger.error("Test " + Thread.currentThread().getStackTrace()[1].getMethodName() + " has failed." + "\n" + e);
        }
    }

    protected void logInOutCorrectData(){
        try
        {
            element.clickWithWait(By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']"));
            element.clickWithWait(By.xpath("//android.widget.CheckedTextView[@text='Log out']"));
            element.presentWithWait(By.id("inputLoginEmail"));
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getMethodName());
        }catch (Exception e){
            logger.error("Test " + Thread.currentThread().getStackTrace()[1].getMethodName() + " has failed." + "\n" + e);
        }
    }

    @Test
    public void logInNegativeEnterEmptyFields(){
        try
        {
            element.clickWithWait(By.id("btnLogin"));
            element.sendKeysWithWait(By.id("inputLoginEmail"), "");
            element.clickWithWait(By.id("btnLoginSubmit"));
            element.presentWithWait(By.id("errorLoginEmail"));
            element.presentWithWait(By.id("errorLoginPwd"));
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getMethodName());
        }catch (Exception e){
            logger.error("Test " + Thread.currentThread().getStackTrace()[1].getMethodName() + " has failed." + "\n" + e);
        }
    }

    @Test
    public void logInNegativeEnterWrongData(){
        try {
            element.clickWithWait(By.id("btnLogin"));
            element.sendKeysWithWait(By.id("inputLoginEmail"),  config.user.part_email + "@y.ru");
            element.sendKeysWithWait(By.id("inputLoginPwd"),  config.user.pwd);
            element.clickWithWait(By.id("btnLoginSubmit"));

            element.presentWithWait(By.id("icWarn"));
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getMethodName()+ " Got error: "+ driver.findElement(By.id("title")).getText());
            element.clickWithWait(By.id("btnOk"));
            element.sendKeysWithWait(By.id("inputLoginEmail"),  "");
            element.sendKeysWithWait(By.id("inputLoginPwd"),  "");
        } catch (Exception e) {
            logger.error("Test " + Thread.currentThread().getStackTrace()[1].getMethodName() + " has failed." + "\n" + e);
        }
    }

    @Test
    public void redirectToFacebookAccount(){
        try {
            element.clickWithWait(By.id("btnLogin"));
            element.sendKeysWithWait(By.id("inputLoginEmail"), "");
            element.clickWithWait(By.id("btnLoginFb"));
            Boolean displayed = false;
            /*try {
                element.presentWithWait(By.xpath("//android.webkit.WebView[1]"));
                displayed = true;
                logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getMethodName());
                element.clickWithWait(By.xpath("android.widget.ImageView"));
            } catch (Exception e) {
                logger.debug("Test " + Thread.currentThread().getStackTrace()[1].getMethodName() + " has failed." + "\n" + e);

            }*/
            try {
                element.clickWithWait(By.xpath("//android.widget.Button[1]"));
                element.clickWithWait(By.xpath("//android.widget.ImageView[1]"));
                //displayed = true;
                logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getMethodName());
            } catch (Exception e) {
                logger.debug("Test " + Thread.currentThread().getStackTrace()[1].getMethodName() + " has failed." + "\n" + e);

            }
            if (!displayed) {
                System.out.println("ERROR! Redirect to Facebook Account has failed.");
            }
        } catch (Exception e){
           //logger.error("Test " + Thread.currentThread().getStackTrace()[1].getMethodName() + " has failed." + "\n" + e);
        }
    }

    @Test
    public void redirectToGoogleAccount(){
        try {
            element.clickWithWait(By.id("btnLogin"));
            element.sendKeysWithWait(By.id("inputLoginEmail"), "");
            element.clickWithWait(By.id("btnLoginGp"));
            driver.resetApp();
            element.selectLang(element, config.android.language);
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e){
            logger.error("Test " + Thread.currentThread().getStackTrace()[1].getMethodName() + " has failed." + "\n" + e);
        }
    }

    @Test
    public void registrationPositive()
    {
        long unixTime;
        unixTime = System.currentTimeMillis();
        String stringUnixTime = Long.toString(unixTime);
        String phone = stringUnixTime.substring(6, 13);//10

        try{
            element.clickWithWait(By.id("btnRegistration"));
            element.sendKeysWithWait(By.id("inputRegisterEmail"), config.user.part_email + unixTime + "@gmail.com");


            try{
                element.clickWithWait(By.id("inputRegisterCountry"));
                element.clickWithWait(By.xpath("//android.widget.TextView[@text='India']"));
               element.clickWithWait(By.id("phInputCode"));
                //element.clickWithWait(By.xpath("//android.widget.TextView[@text='Åland Islands']"));



                swipeFromBottomToUp();


                element.sendKeysWithWait(By.id("phInputCode"), "Latvia");


            } catch (Exception e){ }
            element.sendKeysWithWait(By.id("inputRegisterPhone"), "2" + phone);
            element.sendKeysWithWait(By.id("inputRegisterPwd"), config.user.pwd);
            element.clickWithWait(By.id("cbTerms"));
            element.clickWithWait(By.id("btnRegisterSubmit"));
            element.presentWithWait(By.id("tourOneTitle"));
            element.clickWithWait(By.id("skip"));
            element.clickWithWait(By.id("skipBtnYes"));
            element.clickWithWait(By.id("btnClose"));
            element.clickWithWait(By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']"));
            element.clickWithWait(By.xpath("//android.widget.CheckedTextView[@text='Log out']"));
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getMethodName());
            //System.out.println("Success test " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e){
            logger.error("Test " + Thread.currentThread().getStackTrace()[1].getMethodName() + " has failed." + "\n" + e);
        }
    }

    @Test
    public void registrationNegativeWithEmptyFields()
    {
        try{
            element.clickWithWait(By.id("btnRegistration"));
            element.sendKeysWithWait(By.id("inputRegisterEmail"), "");
            element.clickWithWait(By.id("btnRegisterSubmit"));

            element.presentWithWait(By.id("errorRegisterEmail"));
            element.presentWithWait(By.id("errorRegisterPhone"));
            element.presentWithWait(By.id("errorRegisterPwd"));
            element.presentWithWait(By.id("errorRegisterTerms"));
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e){
            logger.error("Test " + Thread.currentThread().getStackTrace()[1].getMethodName() + " has failed." + "\n" + e);
        }
    }

    @Test
    public void forgotPasswordPositive() {
        try {
            element.clickWithWait(By.id("btnLogin"));
            element.clickWithWait(By.id("btnForgotPwd"));
            element.sendKeysWithWait(By.id("inputRemindEmail"), config.user.email);
            element.clickWithWait(By.id("btnRemindSubmit"));
            element.presentWithWait(By.id("inputLoginPwd"));
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            logger.error("Test " + Thread.currentThread().getStackTrace()[1].getMethodName() + " has Failed.");
            logger.debug(e);
        }
    }


    @Test
    public void forgotPasswordNegativeWithEmptyFields() {
        try{
            element.clickWithWait(By.id("btnLogin"));
            element.clickWithWait(By.id("btnForgotPwd"));
            element.sendKeysWithWait(By.id("inputRemindEmail"), "");
            element.clickWithWait(By.id("btnRemindSubmit"));
            element.presentWithWait(By.id("icWarn"));
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getMethodName()+ driver.findElement(By.id("title")).getText() + " Got error: "+ driver.findElement(By.id("title")).getText());
            element.clickWithWait(By.id("btnOk"));
            try {
                element.clickWithWait(By.id("btnHaveAccount"));
            } catch (Exception e) {
                driver.resetApp();
                element.selectLang(element, config.android.language);
            }
        }catch (Exception e) {
            logger.error("Test " + Thread.currentThread().getStackTrace()[1].getMethodName() + " has Failed.");
            logger.error(e);
        }
    }

    @Test
    public void forgotPasswordNegativeWithWrongData(){
        try{
            element.clickWithWait(By.id("btnLogin"));
            element.clickWithWait(By.id("btnForgotPwd"));
            element.sendKeysWithWait(By.id("inputRemindEmail"), config.user.part_email + "@y.ru");
            element.clickWithWait(By.id("btnRemindSubmit"));
            element.presentWithWait(By.id("icWarn"));
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getMethodName()+ ". Got error: " + driver.findElement(By.id("title")).getText());
            element.clickWithWait(By.id("btnOk"));
            try {
                element.clickWithWait(By.id("btnHaveAccount"));
            } catch (Exception e) {
                driver.resetApp();
                element.selectLang(element, config.android.language);
            }
        }catch (Exception e) {
            logger.error("Test " + Thread.currentThread().getStackTrace()[1].getMethodName() + " has Failed.");
            logger.error(e);
        }
    }
    public boolean swipeFromBottomToUp()
    {
        try  {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            HashMap<String, String> scrollObject = new HashMap<String,String>();
            scrollObject.put("direction", "down");
            js.executeScript("mobile: scroll", scrollObject);
            System.out.println("Swipe down was Successfully done");


            final JavascriptExecutor js = (JavascriptExecutor) this.driver();
            final HashMap<String, String> scrollObject = new HashMap<String, String>();
            scrollObject.put("element", ((RemoteWebElement) element).getId());
            scrollObject.put("toVisible", "true");
            js.executeScript("mobile: scroll", scrollObject);


        }
        catch (Exception e)
        {
            System.out.println("swipe down was not successfull");
        }

        return false;
    }


}
