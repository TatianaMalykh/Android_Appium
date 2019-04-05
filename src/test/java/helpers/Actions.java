package helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import scenarios.authorisation.LogInEnterData;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Actions {

    private AppiumDriver driver;
    private FluentWait<WebDriver> messasge;
    private static final Logger logger = Logger.getLogger(LogInEnterData.class);

    public Actions(AppiumDriver appiumDriver) {
        driver = appiumDriver;
    }

    public void getscreenshot() throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date date = new Date();
        String fileName = sdf.format(date);
        File des = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(des, new File(System.getProperty("user.dir")+"//screenshot//"+fileName+".png"));
    }

    public WebElement checkElement(By by)
    {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        messasge = wait.withMessage("Element with '" + by + "' not found.\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public void presentWithWait(By by)
    {
        WebElement element = checkElement(by);
        element.isDisplayed();
    }

    public void clickWithWait(By by)
    {
        WebElement element = checkElement(by);
        element.click();
    }

    public void sendKeysWithWait(By by, String value)
    {
        WebElement element = checkElement(by);
        element.clear();
        element.sendKeys(value);
    }

    public void selectLang(Actions element, String lang) {
        presentWithWait(By.id("text"));
        try {
            if (!(driver.findElement(By.id("text")).getText().contains(lang))) {
                try { driver.hideKeyboard();}
                catch (Exception e) { }
                element.clickWithWait(By.id("icon"));
                element.presentWithWait(By.xpath("//android.widget.RelativeLayout[@content-desc='en']"));
                element.clickWithWait(By.xpath("//android.widget.RelativeLayout[@content-desc='en']"));
                /*try {
                    element.presentWithWait(By.xpath("//android.widget.TextView[@text='ENG']"));
                    logger.info("Select of language success. ");
                }
                catch (Exception e) { logger.info("Select of language has failed.");}*/

            }
        }catch (Exception e){
            /*if (!(driver.findElement(By.id("text")).getText().contains(lang))) {
                element.clickWithWait(By.id("icon"));
                element.presentWithWait(By.xpath("//android.widget.RelativeLayout[@content-desc='en']"));
                element.clickWithWait(By.xpath("//android.widget.RelativeLayout[@content-desc='en']"));
            }*/
            logger.error("Select of language has failed. ");
            throw e;
        }
        tap();
    }

    public void swipeVertical(double startPercentage, double  finalPercentage, int duration){
        Dimension size = driver.manage().window().getSize();
        int width = size.getWidth()/2;
        PointOption p1= new PointOption();
        int startPoint = (int)(size.getHeight()*startPercentage);
        int endPoint = (int)(size.getHeight()*finalPercentage);

        new TouchAction(driver).press(p1.point(width, startPoint)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration))).moveTo(p1.point(width, endPoint)).release().perform();
    }

    public void clearData(String app){
        String deleteCmd = "pm clear " + app;
        Runtime runtime = Runtime.getRuntime();
        try { runtime.exec(deleteCmd); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public void tap(){
        PointOption p1= new PointOption();
        new TouchAction(driver).press(p1.point(300, 300)).perform();
    }

    public List<String> valuesOfAttribute(By by, String nameAttribute){
        List<String> attributes = new ArrayList<>();
        List<WebElement> allElements = driver.findElements(by);

        for (WebElement element : allElements) {
            String attribute = element.getAttribute(nameAttribute);//name=content-desc
            attributes.add(attribute);
            //System.out.println(attribute);
        }
        return attributes;
    }
    public boolean checkIncludedElement(List list, String targetValue) {
        return list.contains(targetValue);
    }
}
