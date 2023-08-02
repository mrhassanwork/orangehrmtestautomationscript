package com.envision.automation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage{
    public DashboardPage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath = "//a[normalize-space()='My Info']")
    private WebElement myInfoLink;

    public MyInfoPage click_MyInfoLink(){
        this.myInfoLink.click();

        //return new MyInfoPage(super.driver);

        return PageFactory.initElements(driver,MyInfoPage.class);
    }
}
