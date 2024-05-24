package org.crm.testcases;

import org.crm.base.TestBase;
import org.crm.pageObjects.ContactsPage;
import org.crm.pageObjects.HomePage;
import org.crm.pageObjects.LoginPage;
import org.crm.util.TestUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ContactsPageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    ContactsPage contactsPage;
    String sheetName = "contacts";

    public ContactsPageTest(){
        super();
    }

    @BeforeMethod
    public void setup(){
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.login(prop.getProperty("email"),prop.getProperty("password"));
        contactsPage = homePage.ClickOnContactsLink();
    }

    @Test(priority = 1,enabled = false)
    public void validateContactsPageTitleTest() throws InterruptedException {
        boolean isContactsTitleAvailable = contactsPage.validateContactsTitle("Contacts");
        if(isContactsTitleAvailable){
            System.out.println("Landed on contacts page");
        }
        else {
            System.out.println("Not landed on contacts page");
        }
    }

    @DataProvider
    public Object[][] getCRMTestData(){
        Object[][] data = TestUtil.getTestData(sheetName);
        System.out.println("Data provider returned " + data.length + " rows.");
        System.out.println(data);
        return data;
    }

    @Test(priority = 2,dataProvider = "getCRMTestData")
    public void clickNewContactTest(String firstName, String middleName, String lastName, String email){
        contactsPage.createNewContact(firstName,middleName,lastName,email);
    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
