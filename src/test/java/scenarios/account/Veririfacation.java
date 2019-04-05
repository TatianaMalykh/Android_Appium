package scenarios.account;

import helpers.API;
import helpers.LoadConfig;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import scenarios.authorisation.LogInEnterData;
import scenarios.authorisation.Registration;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Veririfacation extends LoadConfig {
    private Registration registration = new Registration();
    private LogInEnterData logIn = new LogInEnterData();
    private API api = new API();

    @Before
    public void enter() {

        //registration.enterData();
        logIn.enter();
        logIn.closeTour();
    }

    @Test
    public void enterData() throws InterruptedException {
        element.clickWithWait(By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']"));
        element.clickWithWait(By.xpath("//android.widget.CheckedTextView[@text='Profile']"));
        element.presentWithWait(By.id("btnVerification"));
        element.clickWithWait(By.id("btnVerification"));


        element.presentWithWait(By.id("btnSubmit"));
        element.clickWithWait(By.id("btnSubmit"));
        System.setProperty("webdriver.chrome.driver", "c:/server/chromedriver.exe");
        WebDriver webdriver = new ChromeDriver();
        webdriver.manage().timeouts().implicitlyWait(5, SECONDS);

        Thread.sleep(1000);
        webdriver.get("http://baza.irontrade-sg.local/baza/ru/api/user-codes?email=testmighty22@gmail.com");
        String codePhone = webdriver.findElement(By.xpath("//body")).getText().replaceAll(".*phone\":\"|\",\"email.*", "");
        element.presentWithWait(By.id("codeInput"));
        element.sendKeysWithWait(By.id("codeInput"), codePhone);
        driver.hideKeyboard();
        element.clickWithWait(By.id("btnSubmit"));

        Thread.sleep(1000);
        webdriver.get("http://baza.irontrade-sg.local/baza/ru/api/user-codes?email=testmighty22@gmail.com");
        String codeEmail = webdriver.findElement(By.xpath("//body")).getText().replaceAll(".*email\":\"|\".*", "");
        element.sendKeysWithWait(By.id("codeInput"), codeEmail);
        driver.hideKeyboard();
        element.clickWithWait(By.id("btnSubmit"));



//        Thread.sleep(5000);
//        String serverResponse = api.serverRequest("http://baza.irontrade-sg.local/baza/ru/api/user-codes?email=testmighty22@gmail.com");
//        String codePhone = serverResponse.replaceAll(".*phone\":\"|\",\"email.*", "");
//        element.presentWithWait(By.id("codeInput"));
//        element.sendKeysWithWait(By.id("codeInput"), codePhone);
//        driver.hideKeyboard();
//        element.clickWithWait(By.id("btnSubmit"));
//
//        Thread.sleep(5000);
//        serverResponse = api.serverRequest("http://baza.irontrade-sg.local/baza/ru/api/user-codes?email=testmighty22@gmail.com");
//        String codeEmail = serverResponse.replaceAll(".*email\":\"|\".*", "");
//        element.sendKeysWithWait(By.id("codeInput"), codeEmail);
//        driver.hideKeyboard();
//        element.clickWithWait(By.id("btnSubmit"));


        element.clickWithWait(By.id("ctzInput"));
        element.presentWithWait(By.xpath("//android.widget.TextView[@text='Việt Nam']"));
        element.clickWithWait(By.xpath("//android.widget.TextView[@text='Việt Nam']"));


        element.sendKeysWithWait(By.id("textInput"), "TEST TEST");
        element.sendKeysWithWait(By.id("numberInput"), "123456789");
        driver.hideKeyboard();

        element.clickWithWait(By.id("dateDay"));
        element.presentWithWait(By.xpath("//android.widget.TextView[@text='07']"));
        element.clickWithWait(By.xpath("//android.widget.TextView[@text='07']"));
        element.clickWithWait(By.id("dateMonth"));
        element.presentWithWait(By.xpath("//android.widget.TextView[@text='May']"));
        element.clickWithWait(By.xpath("//android.widget.TextView[@text='May']"));
        element.clickWithWait(By.id("dateYear"));
        element.presentWithWait(By.xpath("//android.widget.TextView[@text='1993']"));
        element.clickWithWait(By.xpath("//android.widget.TextView[@text='1993']"));

        element.clickWithWait(By.id("dropdownInput"));
        element.presentWithWait(By.xpath("//android.widget.TextView[@text='Female']"));
        element.clickWithWait(By.xpath("//android.widget.TextView[@text='Female']"));

        element.presentWithWait(By.xpath("//*[@resource-id='dateDay']"));//#
        element.clickWithWait(By.xpath("//*[@resource-id='dateDay']"));
        element.presentWithWait(By.xpath("//android.widget.TextView[@text='02']"));
        element.clickWithWait(By.xpath("//android.widget.TextView[@text='02']"));


        //element.clickWithWait(By.id("btnSubmit"));

    }

}
