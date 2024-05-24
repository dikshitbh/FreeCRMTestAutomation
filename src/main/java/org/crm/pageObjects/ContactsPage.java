package org.crm.pageObjects;

import org.crm.base.TestBase;
import org.crm.util.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ContactsPage extends TestBase {

    @FindBy(xpath = "//div[@class='ui loader']//following-sibling::span[text()='Contacts']")
    WebElement contactsLabel;

    @FindBy(xpath = "//button[text()='Create']")
    WebElement createNewContactBtn;

    @FindBy(xpath = "//input[@name='first_name']")
    WebElement firstNameInputBox;

    @FindBy(xpath = "//input[@name='middle_name']")
    WebElement middleNameInputBox;

    @FindBy(xpath = "//input[@name='last_name']")
    WebElement lastNameInputBox;

    @FindBy(xpath = "//input[@name='value' and @placeholder='Email address']")
    WebElement emailInputBox;

    @FindBy(xpath = "//button[text()='Save']")
    WebElement saveContactBtn;

    TestUtil util = new TestUtil();


    public ContactsPage(){
        PageFactory.initElements(driver,this);
    }

    public boolean validateContactsTitle(String contactsPageLabel) throws InterruptedException {
        return contactsLabel.isDisplayed();
    }

    public void createNewContact(String ftName,String mdName,String ltName, String email){
        createNewContactBtn.click();
        firstNameInputBox.sendKeys(ftName);
        middleNameInputBox.sendKeys(mdName);
        lastNameInputBox.sendKeys(ltName);
        emailInputBox.sendKeys(email);
        saveContactBtn.click();
        WebElement ele = driver.findElement(By.xpath("//span[text()='"+ftName+" " +ltName+"']"));
        util.addWaitUntilVisibilityOfElement(ele);
//        Assert.assertTrue("");
    }

}
