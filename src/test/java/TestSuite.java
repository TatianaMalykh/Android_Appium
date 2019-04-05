import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import scenarios.TestAccountMenu;
import scenarios.TestAccountTour;
import scenarios.TestAuthorisation;

@Suite.SuiteClasses({TestAuthorisation.class, TestAccountTour.class, TestAccountMenu.class})
@RunWith(Suite.class)
public class TestSuite {
}