package scenarios.account;

import helpers.LoadConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import scenarios.authorisation.LogInEnterData;

import java.util.ArrayList;
import java.util.List;

public class Trade extends LoadConfig {
    private LogInEnterData logIn = new LogInEnterData();

    List<String> dealsAdded = new ArrayList<>();

    @Before
    public void enter(){
        logIn.enter();
        logIn.closeTour();
    }

    @Test
    public void addTrade() throws Exception {
        newTrade("btnUp");
        newTrade("btnDown");
        Thread.sleep(35000);//Pause for 35 seconds
        checkClosedTradesInHistory();
    }


    private void newTrade(String button) throws Exception {
        try {
            element.clickWithWait(By.id("button3"));//deals
            List dealsInitial = element.valuesOfAttribute(By.id("itemLayout"), "name");//name = content-desc
            element.clickWithWait(By.id(button));
            String dealLast = null;
            for (int i = 1; i <= 10; i++) {
                dealLast = driver.findElement(By.id("itemLayout")).getAttribute("name");
                if ((dealLast.length() > 1) && (!dealsInitial.contains(dealLast))) {
                    dealsAdded.add(dealLast);
                    logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName());
                    break;
                } else  {
                    Thread.sleep(1000);//Pause for 1 second
                }
            }
            Assert.assertFalse("Can't initial trade", dealsInitial.contains(dealLast));
        }catch (Exception e){
            throw e;
        }
    }

    private void checkClosedTradesInHistory() throws Exception {
        List dealsInitialHistory = null;
        try {
            element.clickWithWait(By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']"));
            element.clickWithWait(By.xpath("//android.widget.CheckedTextView[@text='Trading history']"));

            Thread.sleep(2000);//Pause for 2 second
            dealsInitialHistory = element.valuesOfAttribute(By.className("android.view.ViewGroup"), "name");
            for (int i = 0; i <= dealsAdded.size(); i++) {
                Assert.assertTrue("Can't find added trades in history", dealsInitialHistory.contains(dealsAdded.get(i)));
                logger.info("Success test " + Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName());
                break;
            }
        } catch (Exception e){
            logger.error("Can't find trades in history.");
            throw e;
        }
    }


}
