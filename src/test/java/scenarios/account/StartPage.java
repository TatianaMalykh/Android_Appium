package scenarios.account;

import helpers.LoadConfig;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import scenarios.authorisation.LogInEnterData;

public class StartPage extends LoadConfig {
    private LogInEnterData logIn = new LogInEnterData();

    @Before

    public void clearAndEnter(){
        logIn.enter();
        logIn.closeTour();
    }
    @Test
    public void elementsOfPageTrade(){
        try {

            element.clickWithWait(By.id("symbolPicker"));
            element.clickWithWait(By.xpath("//android.widget.TextView[@text='GBPUSD']"));

            element.clickWithWait(By.id("btnChartType"));
            element.presentWithWait(By.xpath("//android.widget.TextView[@text='Candlestick Chart']"));
            element.clickWithWait(By.xpath("//android.widget.TextView[@text='Candlestick Chart']"));

            element.clickWithWait(By.id("btnChartInterval"));
            element.presentWithWait(By.xpath("//android.widget.TextView[@text='30 sec']"));
            element.clickWithWait(By.xpath("//android.widget.TextView[@text='30 sec']"));

            element.clickWithWait(By.id("btnIndicators"));
            element.presentWithWait(By.xpath("//android.widget.TextView[@text='Alligator']"));
            element.clickWithWait(By.xpath("//android.widget.TextView[@text='Alligator']"));
            element.clickWithWait(By.id("btnSave"));

            element.clickWithWait(By.id("button3"));
            element.clickWithWait(By.id("button2"));

            element.clickWithWait(By.id("btnUp"));
            element.clickWithWait(By.id("btnDown"));

            logIn.out();
            logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName());
        }catch (Exception e){
             throw e;
        }
    }
}