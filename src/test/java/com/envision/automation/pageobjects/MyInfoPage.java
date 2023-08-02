package com.envision.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyInfoPage extends BasePage{
    public MyInfoPage(WebDriver driver){
        super(driver);
    }

    @FindBy(linkText ="Personal Details")
    public WebElement personalDetails;

    @FindBy(linkText ="Memberships")
    public WebElement membership;
    public boolean validatePersonalDetailsLink(){

        return personalDetails.isDisplayed();
    }

    public boolean validateMembershipsLink(){

        return membership.isDisplayed();
    }

    public boolean validateAnyLink(String linkName){
//        if(linkName.equalsIgnoreCase("personal details")){
//            return validatePersonalDetailsLink();
//        } else if (linkName.equalsIgnoreCase("memberships")) {
//            return validateMembershipsLink();
//        }

        boolean link = driver.findElement(By.linkText(linkName)).isDisplayed();
        return link;
        //return false;
    }
}
