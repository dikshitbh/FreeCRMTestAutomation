package org.crm.testcases;

import org.crm.base.TestBase;
import org.crm.pageObjects.HomePage;
import org.crm.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;

    public LoginPageTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        loginPage  =  new LoginPage();
    }

    @Test(priority = 1)
    public void loginPageTitleTest(){
        String loginPageTitle = loginPage.validateLoginPageTitle();
        Assert.assertEquals(loginPageTitle,"#1 Free CRM Power Up your Entire Business Free Forever");
    }

    @Test(priority = 2)
    public void loginTest(){
        loginPage.login(prop.getProperty("email"),prop.getProperty("password"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
