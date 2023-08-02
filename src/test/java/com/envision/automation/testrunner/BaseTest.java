package com.envision.automation.testrunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.envision.automation.orangehrmautomationscripts.util.BrowserFactory;
import com.envision.automation.orangehrmautomationscripts.util.PropertiesFileReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public abstract class BaseTest {

    WebDriver driver;

    @Parameters("browsername")
    @BeforeMethod
    public void openBrowser_OpenUrl(@Optional("chrome") String brname){
        this.driver = BrowserFactory.getBrowser(brname);
        BrowserFactory.openUrl(PropertiesFileReader.readPropsTestData("config.properties","orangehrm.url"));
    }

    @AfterMethod
    public void closure(){
        BrowserFactory.closeAllWindows();
    }

    public static ExtentReports extentReports;
    //public static ExtentTest extentTest;

    @BeforeSuite
    public void initializeReport(){
        ExtentSparkReporter esr = new ExtentSparkReporter("orangehrm_reports/AllTestScriptResults.html");

        BaseTest.extentReports = new ExtentReports();
        BaseTest.extentReports.attachReporter(esr);
//        BaseTest.extentReports.setSystemInfo("OS", System.getProperty("os.name"));
//        BaseTest.extentReports.setSystemInfo("OS",System.getProperty("java.version"));
    }

    @AfterSuite
    public void generateReport(){
        BaseTest.extentReports.flush();
        try{
            Desktop.getDesktop().browse(new File("orangehrm_reports/AllTestScriptResults.html").toURI());
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
