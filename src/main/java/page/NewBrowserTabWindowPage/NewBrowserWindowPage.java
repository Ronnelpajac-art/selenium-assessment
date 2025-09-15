package page.NewBrowserTabWindowPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewBrowserWindowPage {

    private final WebDriver driver;

    // Locators
    private final By pageTitle = By.xpath("//h2[contains(text(), 'Switch to a new Browser Window')]");
    public final By newWindowBtn = By.id("newWindowBtn");
    private final By tableExampleHeader = By.xpath("//h2[contains(text(), 'Table Example')]");

    // Constructor
    public NewBrowserWindowPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Checks if page title is displayed
    public boolean isPageTitleDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return title.isDisplayed();
    }

    // Checks if page title is displayed on new window
    public boolean isTableExampleDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(tableExampleHeader));
        return title.isDisplayed();
    }

    // Clicks button that opens new window and switches driver to new window
    public void clickNewWindowBtnAndSwitch() {
        String originalWindow = driver.getWindowHandle();
        int originalWindowsCount = driver.getWindowHandles().size();
        driver.findElement(newWindowBtn).click();

        // Wait for new window to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.getWindowHandles().size() > originalWindowsCount);

        // Switch to new window
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    // Close current window and switch back to original window
    public void closeWindowAndSwitchBack(String originalWindow) {
        driver.close();  // closes current (new) window
        driver.switchTo().window(originalWindow);
    }
}
