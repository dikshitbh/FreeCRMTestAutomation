package org.crm.pageObjects;

import org.crm.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends TestBase {



    //PageFactory or Object Repository

    @FindBy(xpath = "//span[text()='Log In']")
    WebElement loginLink;

    @FindBy(name = "email")
    WebElement email;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(xpath = "//div[contains(text(),'Login')]")
    WebElement loginBtn;

    @FindBy(xpath = "//a[contains(text(),'Sign Up')]")
    WebElement signUpBtn;

    @FindBy(xpath = "//a[contains(text(),'Forgot your password?')]")
    WebElement forgotPasswordBtn;

    public LoginPage(){
        PageFactory.initElements(driver,this);
    }


    //Actions

    public String validateLoginPageTitle(){
        return driver.getTitle();
    }

    public void clickLoginButton(){
        loginLink.click();
    }

    public HomePage login(String emailId, String pwd){
        loginLink.click();
        email.sendKeys(emailId);
        password.sendKeys(pwd);
        loginBtn.click();

        return new HomePage();
    }


}
