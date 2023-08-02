package com.envision.automation.testrunner;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.envision.automation.orangehrmautomationscripts.excelutil.ExcelReader;
import com.envision.automation.orangehrmautomationscripts.listeners.RunFailedTests;
import com.envision.automation.pageobjects.DashboardPage;
import com.envision.automation.pageobjects.LoginPage;
import com.envision.automation.pageobjects.MyInfoPage;
import com.envision.automation.orangehrmautomationscripts.util.CommonUtil;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class OrangeHrm_EmployeePortal_TestRunner extends BaseTest{

    @Test(dataProvider = "orangehrm_testcase_data", dataProviderClass = ExcelReader.class, retryAnalyzer = RunFailedTests.class)
    public void VerifyTheLoginCredentialsAvailability(Method m, String expectedUname, String expectedPassword){
        ExtentTest current_test = BaseTest.extentReports.createTest(m.getName());
        try {

            current_test.log(Status.INFO, m.getName() + " started execution");

            LoginPage lp = new LoginPage(super.driver);
            current_test.log(Status.INFO, "login page opened successfully");

            String un_text = lp.getUsernameText();
            current_test.log(Status.INFO, "username text is displayed");

            System.out.println(un_text + "  " + expectedUname);
            Assert.assertTrue(un_text.equals(expectedUname));
            current_test.log(Status.PASS, un_text + "validation is successful");

            String pwd_txt = lp.getPasswordText();
            current_test.log(Status.INFO, pwd_txt + " text is displayed");

            System.out.println(pwd_txt + "  " + expectedPassword);
            Assert.assertTrue(pwd_txt.equals(expectedPassword));
            current_test.log(Status.PASS, pwd_txt + "validation is successful");
        }catch (Throwable t){
            current_test.fail(t.fillInStackTrace());
            //current_test.log(Status.FAIL, t);
            //CommonUtil.getScreenShot(driver,m.getName());
            //current_test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(CommonUtil.getScreenShot(driver,m.getName())));
            throw t;
        }
    }

    @Test(dataProvider = "orangehrm_testcase_data", dataProviderClass = ExcelReader.class, retryAnalyzer = RunFailedTests.class)
    public void VerifyTheUserProfilePageLinks(Method m,ITestContext c,String username, String pwd, String linkToAssert){
        ExtentTest et = BaseTest.extentReports.createTest(m.getName()+ " - " + linkToAssert);
        try {
            LoginPage lp = new LoginPage(this.driver);
            et.info("login page opened successfully");
            CommonUtil.pauseExecution_InSec(5);
            //boolean result = lp.enterUserName(username).enterPassword(pwd).clickSubmit().click_MyInfoLink().validateAnyLink(linkToAssert); // only because of facade design pattern
            //CommonUtil.pauseExecution_InSec(10);
            lp.enterUserName(username);
            et.info("Username entered successfully");
            lp.enterPassword(pwd);
            et.info("Password entered successfully");
            lp.clickSubmit();
            et.info("Submit button clicked successfully");

            DashboardPage dp = new DashboardPage(this.driver);
            et.info("Navigated to Dashboard");
            dp.click_MyInfoLink();
            et.info("Navigated to MyInfoPage");
            MyInfoPage mp = new MyInfoPage(this.driver);
            boolean linkAvailable = mp.validateAnyLink(linkToAssert);
            Assert.assertTrue(linkAvailable);
            et.pass(linkToAssert + " is displayed successfully");

            //Assert.assertTrue(result);
        }catch (Throwable t1){
            et.fail(t1.fillInStackTrace());
            et.fail(linkToAssert + " is not displayed on the web page");
            //et.addScreenCaptureFromBase64String(CommonUtil.getScreenShot(driver));

            //et.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(CommonUtil.getScreenShot(driver,m.getName())));
            throw t1;
        }
    }
}