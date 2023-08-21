package Pages;

import common.Util;
import logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class MainPage extends Util {
    Log log = new Log();

    public MainPage(WebDriver driver) {
        super(driver);
    }
    public final By transactionCenter = By.xpath("//em[@class='icon-account-regular']");
    private final By loginOrSignUp = By.xpath("//a[@class='a-btn a-btn--full a-btn--white js-fast-login-btn a-btn--fast-login']");

    public void goToFastLoginPage() {
        log.info(" Turkcell web sitesine gidildi. ");
        waitForVisibility(transactionCenter);
        click(transactionCenter);
        waitForVisibility(loginOrSignUp);
        click(loginOrSignUp);
    }
}
