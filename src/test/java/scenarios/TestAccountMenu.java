package scenarios;

import helpers.LoadConfig;
import org.junit.Test;
import org.openqa.selenium.By;

public class TestAccountMenu extends LoadConfig {

    @Test
    public void checkListMenu(){
        TestAuthorisation user = new TestAuthorisation();
        //user.logInEnterCorrectData();
        //user.logInCloseTour();
        element.clickWithWait(By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']"));
        element.clickWithWait(By.xpath("//android.widget.ImageButton[@content-desc='Trading']"));
    }
    //---- menu -----
    //element.clickWithWait(By.xpath("//android.view.View[@content-desc='Cryptocurrency payments']"));
}