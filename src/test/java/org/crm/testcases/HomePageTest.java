package org.crm.testcases;

import org.crm.base.TestBase;
import org.crm.pageObjects.ContactsPage;
import org.crm.pageObjects.HomePage;
import org.crm.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {

    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;

    public HomePageTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.login(prop.getProperty("email"),prop.getProperty("password"));
    }

    @Test(priority = 1,enabled = true)
    public void validateHomePageTitleTest(){
        String homePageTitle = homePage.validateHomePageTitle();
        Assert.assertEquals(homePageTitle,"Cogmento CRM","Home page title not matched");
    }

    @Test(priority = 2,enabled = true)
    public void validateUserTest(){
        String usrName = homePage.validateValidUserName();
        Assert.assertEquals(usrName,prop.getProperty("username"),"User name did not match");
    }

    @Test(priority = 3)
    public void validateContactsLinkTest(){
        contactsPage = homePage.ClickOnContactsLink();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


}
