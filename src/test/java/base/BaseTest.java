package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import page.FormPage;

public class BaseTest {

    protected WebDriver driver;
    protected FormPage formPage;

    private static final String BASE_URL = "https://qa-practice.netlify.app/bugs-form";

    // Locators
    private final By homePageBtn = By.id("home");
    private final By contactPageBtn = By.id("contact");

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);

        // Initialize page objects after driver
        formPage = new FormPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Actions
    protected void goToHomePage() {
        driver.findElement(homePageBtn).click();
    }

    protected void goToContactPage() {
        driver.findElement(contactPageBtn).click();
    }

    protected String getLabelText(By labelLocator) {
        return driver.findElement(labelLocator).getText();
    }
}
