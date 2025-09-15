package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormPage {

    private final WebDriver driver;

    // Web Elements
    private final By header = By.xpath("//div[@class='sidebar-header']");
    private final By pageTitle = By.xpath("//h2[contains(text(), 'CHALLENGE - Spot the BUGS!')]");
    private final By firstNameField = By.id("firstName");
    private final By firstNameLbl = By.xpath("//label[contains(text(), 'First Name')]");
    private final By lastNameField = By.id("lastName");
    private final By lastNameLbl = By.xpath("//label[contains(text(), 'Last Name')]");
    private final By phoneNumberField = By.id("phone");
    public final By phoneNumberLbl = By.xpath("//label[contains(text(), 'Phone nunber*')]");
    private final By countryDrp = By.id("countries_dropdown_menu");
    private final By emailAddressField = By.id("emailAddress");
    public final By emailAddressLbl = By.xpath("//label[contains(text(), 'Email address')]");
    private final By passwordField = By.id("password");
    public final By passwordLbl = By.xpath("//label[contains(text(), 'Password')]");

    public final By messageSubmitForm = By.id("message");

    // Registration confirmation details locators
    private final By resultFirstName = By.id("resultFn");
    private final By resultLastName = By.id("resultLn");
    private final By resultPhone = By.id("resultPhone");
    private final By resultCountry = By.id("country");
    private final By resultEmail = By.id("resultEmail");

    // Constructor
    public FormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageTitleDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pageTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return pageTitleElement.isDisplayed();
    }

    public boolean isHeaderDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(header));
        return headerElement.isDisplayed();
    }

    public String getHeaderText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(header));
        return headerElement.getText();
    }

    // Methods to get each confirmation field text
    public String getFirstNameResult() {
        return driver.findElement(resultFirstName).getText();
    }

    public String getLastNameResult() {
        return driver.findElement(resultLastName).getText();
    }

    public String getPhoneResult() {
        return driver.findElement(resultPhone).getText();
    }

    public String getCountryResult() {
        return driver.findElement(resultCountry).getText();
    }

    public String getEmailResult() {
        return driver.findElement(resultEmail).getText();
    }

    // Method for filling up the form
    public void fillForm(String firstName, String lastName, String phoneNumber, String country, String email, String password) {
        clearAndFill(firstNameField, firstName);
        clearAndFill(lastNameField, lastName);
        clearAndFill(phoneNumberField, phoneNumber);
        if (country != null) {
            driver.findElement(countryDrp).click();
            driver.findElement(By.xpath("//option[text()='" + country + "']")).click();
        }
        clearAndFill(emailAddressField, email);
        clearAndFill(passwordField, password);
    }
    private void clearAndFill(By locator, String value) {
        WebElement cl = driver.findElement(locator);
        cl.clear();
        if (value != null) {
            cl.sendKeys(value);
        }
    }

    public void clickRegisterBtn() {
        driver.findElement(By.id("registerBtn")).click();
    }

    public String getSubmitMessageText() {
        return driver.findElement(messageSubmitForm).getText();
    }
}
