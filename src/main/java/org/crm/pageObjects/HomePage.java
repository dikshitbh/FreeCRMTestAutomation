package org.crm.pageObjects;

import org.crm.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {

    @FindBy(xpath = "//span[text()='Dikshit Bhardwaj']")
    WebElement userNameLbl;

    @FindBy(xpath = "//span[contains(text(),'Contacts')]")
    WebElement contactsLink;

    @FindBy(xpath = "//span[contains(text(),'Deals')]")
    WebElement dealsLink;

    @FindBy(xpath = "//span[contains(text(),'Tasks')]")
    WebElement tasksLink;

    public HomePage(){
        PageFactory.initElements(driver,this);
    }

    public String validateHomePageTitle(){
        return driver.getTitle();
    }

    public String validateValidUserName(){
        if(userNameLbl.isDisplayed()){
            userNameLbl.getText();
        }
        return userNameLbl.getText();
    }

    public ContactsPage ClickOnContactsLink(){
        contactsLink.click();
        return new ContactsPage();
    }

    public DealsPage ClickOnDealsLink(){
        contactsLink.click();
        return new DealsPage();
    }

    public TasksPage ClickOnTasksLink(){
        contactsLink.click();
        return new TasksPage();
    }



}
