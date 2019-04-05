package helpers;

import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import scenarios.TestAuthorisation;

import java.util.Set;

public class ActionsForElement {

    private AppiumDriver driver;
    private FluentWait<WebDriver> messasge;
    private static final Logger logger = Logger.getLogger(TestAuthorisation.class);

    public ActionsForElement(AppiumDriver appiumDriver) {
        driver = appiumDriver;
    }

    public void selectLang(ActionsForElement element, String lang) {
        checkElement(By.id("text"));
        if (!(driver.findElement(By.id("text")).getText().contains(lang))) {
            element.clickWithWait(By.id("icon"));
            element.clickWithWait(By.xpath("//android.widget.TextView[@text='"+lang+"']"));
        }
    }

    public WebElement checkElement(By by)
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
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
        element.sendKeys(value);
    }

    public void switchContextToNative()
    {
        Set<String> allContext=driver.getContextHandles();
        for(String context:allContext){
            if(context.contains("NATIVE_APP")){
                driver.context(context);
            }
        }
    }
}
