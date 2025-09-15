package Test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import page.ContactPage;
import base.BaseTest;
import org.testng.annotations.Test;
import page.HomePage;
import page.LeftMenu;
import page.NewBrowserTabWindowPage.NewBrowserTabPage;
import page.NewBrowserTabWindowPage.NewBrowserWindowPage;

public class AssessmentTest extends BaseTest {

    private HomePage homePage;
    private ContactPage contactPage;
    private LeftMenu leftMenu;
    private NewBrowserTabPage newBrowserTabPage;
    private NewBrowserWindowPage newWindowPage;

     @BeforeMethod
     public void initPageObjects() {
         homePage = new HomePage(driver);
         contactPage = new ContactPage(driver);
         leftMenu = new LeftMenu(driver);
         newBrowserTabPage = new NewBrowserTabPage(driver);
         newWindowPage = new NewBrowserWindowPage(driver);

     }

     @Test
     public void TC01_PageLoadValidation(){
         Assert.assertTrue(formPage.isHeaderDisplayed(),"Header is not displayed");
         Assert.assertEquals(formPage.getHeaderText(), "QA Practice", "Actual and Expected header is not equal!");
         Assert.assertTrue(formPage.isPageTitleDisplayed(), "Page title is not displayed");
        }

    @Test
    public void TC02_NavigationLink() {
         goToHomePage();
         Assert.assertTrue(homePage.isWelcomeMsgDisplayed(), "Welcome message is not displayed");
         goToContactPage();
         Assert.assertTrue(contactPage.isContactUsDisplayed(), "Contact us is not displayed");
        }

    @Test
    public void TC03_SuccessfulRegistration() {
        // Test data
        String firstName = "test";
        String lastName = "test";
        String phone = "63945781254";
        String country = "Australia";
        String email = "user@gmail.com";
        String password = "validPass123";

        // Fill form and submit
        formPage.fillForm(firstName, lastName, phone, country, email, password);
        formPage.clickRegisterBtn();

        // Assert success message
        Assert.assertEquals(formPage.getSubmitMessageText(), "Successfully registered the following information");

        // Assert confirmation details match inputs
        Assert.assertEquals(formPage.getFirstNameResult(), "First Name: " + firstName);

        // Assertion for last name is failing since last name is incomplete on showed information: BUG
        Assert.assertEquals(formPage.getLastNameResult(), "Last Name: " + lastName);

        // Assertion is failing since last digit in phone number is incrementing: BUG
        Assert.assertEquals(formPage.getPhoneResult(), "Phone Number: " + phone);
        Assert.assertEquals(formPage.getCountryResult(), "Country: " + country);
        Assert.assertEquals(formPage.getEmailResult(), "Email: " + email);
    }

    @Test
    public void TC04_VerifyBugs() {
         // Verify Phone Number Label
         String actualText = getLabelText(formPage.phoneNumberLbl);
         Assert.assertEquals(actualText, "Phone number*", "Phone number label is incorrect");
        }

    @Test
    public void TC05_UsernameFieldValidation() {

        // Null username should NOT register successfully: BUG
        formPage.fillForm(null, "Test Last Name", "1234567890", "Australia", "test@gmail.com", "password123");
        formPage.clickRegisterBtn();
        String msg = formPage.getSubmitMessageText();
        Assert.assertFalse(msg.contains("Successfully registered"), "Registration should fail when username is null");
    }

    @Test
    public void TC06_PasswordFieldValidation() {

        // Null password should NOT register successfully: BUG
        formPage.fillForm("Test First Name", null, "1234567890", "Australia", "test@gmail.com", "password123");
        formPage.clickRegisterBtn();
        String msg = formPage.getSubmitMessageText();
        Assert.assertFalse(msg.contains("Successfully registered"), "Registration should fail when password is null");
    }

    @Test
    public void TC07_EmailFieldValidation() {

        // Null password should NOT register successfully: BUG
        formPage.fillForm("Test First Name", "Test Last Name", "1234567890", "Australia", null, "password123");
        formPage.clickRegisterBtn();
        String msg = formPage.getSubmitMessageText();
        Assert.assertFalse(msg.contains("Successfully registered"), "Registration should fail when email is null");
    }

    @Test
    public void TC08_VerifyNewTab() {

         // Verify New Tab
        leftMenu.clickNewBrowserTabSubMenu();
        Assert.assertTrue(newBrowserTabPage.isPageTitleDisplayed(),"Title is not displayed");

        // Switch to the new Tab
        newBrowserTabPage.clickNewTabBtnAndSwitch();
        Assert.assertTrue(newBrowserTabPage.isTableExampleDisplayed(), "Table Example title is not displayed");
    }

    @Test
    public void TC09_VerifyNewWindow() {

         // Verify New Window
        leftMenu.clickNewBrowserWindowSubMenu();
        Assert.assertTrue(newWindowPage.isPageTitleDisplayed(), "Page title is not displayed in the new window");
        String originalWindow = driver.getWindowHandle();
        newWindowPage.clickNewWindowBtnAndSwitch();
        Assert.assertTrue(newWindowPage.isTableExampleDisplayed(), "Page title is not displayed in the new window");

        // Switching back to the original window
        newWindowPage.closeWindowAndSwitchBack(originalWindow);
        Assert.assertEquals(driver.getWindowHandle(), originalWindow, "Did not switch back to original window");
    }
}
