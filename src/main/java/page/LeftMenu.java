package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LeftMenu {

    private final WebDriver driver;

    // Locators
    private final By newTabWindow = By.xpath("//a[@id='buttons' and contains(text(), 'New Tab / Window')]");
    private final By newBrowserTab = By.id("browser-tab");
    private final By newBrowserWindow = By.id("browser-window");

    // Constructor
    public LeftMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickNewBrowserTabSubMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Click parent menu
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(newTabWindow));
        menu.click();
        // Click submenu item
        WebElement submenu = wait.until(ExpectedConditions.elementToBeClickable(newBrowserTab));
        submenu.click();
    }

    public void clickNewBrowserWindowSubMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Click parent menu
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(newTabWindow));
        menu.click();
        // Click submenu item
        WebElement submenu = wait.until(ExpectedConditions.elementToBeClickable(newBrowserWindow));
        submenu.click();
    }
}
