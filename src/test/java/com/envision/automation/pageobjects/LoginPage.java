package com.envision.automation.pageobjects;

import com.envision.automation.orangehrmautomationscripts.util.ObjectRepoHandler;
import com.envision.automation.orangehrmautomationscripts.util.PropertiesFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(name = "username")
    private WebElement usernameTextBox;

    @FindBy(name = "password")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement login_clickButton;

    By userNameLocator = By.name("username");
    public String getUsernameText (){
        //Step 1 - reading the value from objectrepo.properties files
        String locator_type_value = PropertiesFileReader.readPropsTestData("objectrepo.properties","orangehrm.loginpage.username.textvalue");
        //Step 2 - from the above value write code to take first portion for the type of locator
        String locator_type = locator_type_value.substring(0,locator_type_value.indexOf("_"));
        //Step 3 - from step 1 take the value that is after first _ which is locator value
        String locator_value = locator_type_value.substring(locator_type_value.indexOf("_")+1);
        //Step 4 - find the WebElement - by using the mthod written inside a class ObjectRepoHandler
        WebElement userNameText = ObjectRepoHandler.getElement(driver, locator_type,locator_value);
        //Last step - return the text that is needed for the validation
        return userNameText.getText();
    }

    public String getPasswordText (){
        //Step 1 - reading the value from objectrepo.properties files
        String locator_type_value = PropertiesFileReader.readPropsTestData("objectrepo.properties","orangehrm.loginpage.password.textvalue");
        //Step 2 - from the above value write code to take first portion for the type of locator
        String locator_type = locator_type_value.substring(0,locator_type_value.indexOf("_"));
        //Step 3 - from step 1 take the value that is after first _ which is locator value
        String locator_value = locator_type_value.substring(locator_type_value.indexOf("_")+1);
        //Step 4 - find the WebElement - by using the mthod written inside a class ObjectRepoHandler
        WebElement passwordText = ObjectRepoHandler.getElement(driver, locator_type,locator_value);
        //Last step - return the text that is needed for the validation
        return passwordText.getText();
    }

    public LoginPage enterUserName(String inputValue){
        WebElement user_name_element = driver.findElement(userNameLocator);
        user_name_element.sendKeys(inputValue);

        return this; //facade design pattern
    }

    public LoginPage enterPassword(String pwd){
        this.passwordTextBox.sendKeys(pwd);
        return this;
    }
    //as clicking on submit button will take to dashboard page, so return type is dashboard
    public DashboardPage clickSubmit(){
        login_clickButton.click();
        return new DashboardPage(super.driver);
    }
}
