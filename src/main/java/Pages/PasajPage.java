package Pages;

import common.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasajPage extends Util {
    private final WebDriver driver;

    public PasajPage(WebDriver driver) {

        super(driver);
        this.driver = driver;

    }

    private final By passageIcon = By.xpath("//a[@href='/pasaj?place=menu']");
    private final By myAccount = By.xpath("//a[@class='o-p-header_my-account_button js-account-button']");
    private final By myUserInformation = By.xpath("//a[@href='/pasaj/hesabim/kullanici-bilgilerim']");
    private final By jobBox = By.xpath("//div[@class='m-select__input-container css-ackcql']");
    private final By updateButton = By.xpath("//button[@class='a-p-btn a-update-btn']");
    private final By myContactInfoNeingSavedCkeckbox = By.xpath("//span[@aria-label='İletişim bilgilerimin kaydedilmesine izin veriyorum.']");

    private final By bilgisayarTablet = By.xpath("(//a[@href='/pasaj/bilgisayar-tablet'])[1]");
    private final By firstDropdown = By.xpath("//div[@class='m-accordion__head groupm-container-head']");
    private final By secondDropdown = By.xpath("(//div[@class='m-accordion__head '])[1]");
    private final By firstSelect = By.xpath("//label[@for='sort-3']");
    private final By secondSelect = By.xpath("//label[@for='sort-3']");
    private final By price = By.xpath("//div[@class='m-p-pc__foot']");
    private final By transactionSuccessful = By.xpath("//div[@class='components-molecules-modal_m-modal-custom_body_2J20h']");

    public void changeJobandSubmit(String newJob) {
        click(passageIcon);
        click(myAccount);
        waitFor(3000);
        System.out.println(" Hesabım dropdown menüsü açıldı. ");
        click(myUserInformation);
        WebElement meslekButton = driver.findElement(jobBox);
        Actions actions = new Actions(driver);
        actions.click(meslekButton).sendKeys(newJob).sendKeys(Keys.ENTER).perform();
        System.out.println(" Yeni meslek bilgisi girildi. ");
        clickWithActions(myContactInfoNeingSavedCkeckbox);
        click(updateButton);
        waitFor(5000);
    }

    public void verifySuccessfulTransaction() {

        if (isVisible(transactionSuccessful)) {
            System.out.println(" Meslek bilgisi başarılı bir şekilde güncellendi.");
        } else {
            System.out.println(" İşlem başarılı değil !");
        }
    }

    public void clickTheHighestPriceOptionInDropdown() {
        Util util = new Util(driver);
        try {
            waitForClickable(firstDropdown);
            click(firstDropdown);
            util.waitFor(3000);
            click(firstSelect);
        } catch (Exception e) {
            waitForClickable(secondDropdown);
            click(secondDropdown);
            util.waitFor(3000);
            click(secondSelect);
        }
    }

    public void listPricesHighestToLowest() {
        click(passageIcon);
        click(bilgisayarTablet);
        System.out.println(" Bilgisayar-Tablet bölümüne girildi.");
        waitFor(5000);
        scrollDown(650);
        waitFor(3000);
        clickTheHighestPriceOptionInDropdown();
        System.out.println(" En Yüksek Fiyat seçeneği seçildi.");
        scrollDown(1500);
    }

    public void verifyPricesListedCorrectly() {
        List<WebElement> priceElements = driver.findElements(price);
        List<Double> prices = new ArrayList<>(); // Bu Listede toplanacak fiyatlar depolanır.

        for (WebElement priceElement : priceElements) { // Her fiyat ögesi bir elemente işlenir
            String priceText = priceElement.getText().replaceAll("[^0-9.]", "");
            // Listede sadece sayılar ve nokta bulunur.
            if (!priceText.isEmpty()) {
                try { // Ondalığa çevir
                    double price = Double.parseDouble(priceText);
                    prices.add(price);
                } catch (NumberFormatException ignored) { // Çevrilmezse hatayı görmezden gel
                }

            }
        }

        prices.sort(Collections.reverseOrder()); // fiyatları büyükten küçüğe sırala
        boolean isSorted = prices.equals(new ArrayList<>(prices));
        if (isSorted) {
            System.out.println(" Fiyatlar büyükten küçüğe doğru sıralandı.");
        } else {
            System.out.println(" Fiyatlar doğru şekilde sıralanmadı.");
        }
        // Konsola basmak için fiyatları bir dize olarak oluştur
        StringBuilder stringBuilder = new StringBuilder(" Fiyat Listesi: [");

        for (Double price : prices) { // Döngü her bir fiyat için çalışır ve append fiyatı StringBuilderın sonuna ekler.
            stringBuilder.append(price).append(" , ");
        }
        stringBuilder.setLength(stringBuilder.length() - 3); // En sondaki "," silinsin
        stringBuilder.append("]");

        // Fiyatları konsola bir dize olarak yazdır
        System.out.println(stringBuilder);
        waitFor(4000);

    }
}