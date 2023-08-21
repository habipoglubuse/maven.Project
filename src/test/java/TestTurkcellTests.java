import Pages.FastLoginPage;
import Pages.LoginPage;
import Pages.MainPage;
import Pages.PasajPage;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class TestTurkcellTests extends BaseTest {

    @Test
    @Order(1)
    public void LoginToTurkcellWebsite() {

        MainPage mainPage = new MainPage(driver);
        FastLoginPage fastLoginPage = new FastLoginPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.goToFastLoginPage();
        fastLoginPage.successfulFastLogin();
        loginPage.verifySuccessfulLogin();
    }

    @Test
    @Order(2)
    public void jobUpdate() {

        LoginPage loginPage = new LoginPage(driver);
        PasajPage pasajPage = new PasajPage(driver);
        loginPage.alreadyLoggedInUser();
        pasajPage.changeJobandSubmit("Doktor");
        pasajPage.verifySuccessfulTransaction();
    }

    @Test
    @Order(3)
    public void sortComputerAndTabletPrices() {

        PasajPage pasajPage = new PasajPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.alreadyLoggedInUser();
        pasajPage.listPricesHighestToLowest();
        pasajPage.verifyPricesListedCorrectly();

    }

}