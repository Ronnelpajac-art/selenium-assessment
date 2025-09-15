package page.NewBrowserTabWindowPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewBrowserTabPage {

    private final WebDriver driver;

    // Locators
    private final By pageTitle = By.xpath("//h2[contains(text(), 'Switch to a new Browser Tab')]");
    public final By newTabBtn = By.id("newTabBtn");
    private final By tableExampleHeader = By.xpath("//h2[contains(text(), 'Table Example')]");

    // Constructor
    public NewBrowserTabPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Actions
    public boolean isPageTitleDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pageTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return pageTitleElement.isDisplayed();
    }

    public void clickNewTabBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(newTabBtn));
        btn.click();
    }

    public boolean isTableExampleDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(tableExampleHeader));
        return header.isDisplayed();
    }

    public void clickNewTabBtnAndSwitch() {
        String originalWindow = driver.getWindowHandle();
        int originalWindowCount = driver.getWindowHandles().size();
        driver.findElement(newTabBtn).click();

        // Wait for new window/tab
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> driver.getWindowHandles().size() > originalWindowCount);

        // Switch to new window/tab
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}
