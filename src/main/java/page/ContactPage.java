package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactPage {

    private static WebDriver driver;

    // Web Elements
    private static final By contactUsPageTitle = By.xpath("//h2[contains(text(), 'Contact us')]");

    // Constructor
    public ContactPage(WebDriver driver) {
        ContactPage.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Actions
    public boolean isContactUsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement contactUsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(contactUsPageTitle));
        return contactUsElement.isDisplayed();
    }
}
