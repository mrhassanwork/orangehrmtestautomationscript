package com.envision.automation.orangehrmautomationscripts.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BrowserFactory {
    private static WebDriver driver = null;
    public static WebDriver getBrowser(String browserName){
        switch (browserName.toLowerCase().intern()){
            case "chrome":
                //System.setProperty("webdriver.chrome.driver","orangehrm_browserexes/chromedriver");
                //WebDriverManager.chromedriver().setup();
                BrowserFactory.driver = new ChromeDriver();
                break;
            case "edge":
                //System.setProperty("webdriver.chrome.driver","orangehrm_browserexes/msedgedriver");
                //WebDriverManager.edgedriver().setup();
                BrowserFactory.driver = new EdgeDriver();
                break;
            case "firefox":
                //System.setProperty("webdriver.chrome.driver","orangehrm_browserexes/geckodriver");
                //WebDriverManager.firefoxdriver().setup();
                BrowserFactory.driver = new FirefoxDriver();
                break;
            case "safari":
                //System.setProperty("webdriver.chrome.driver","orangehrm_browserexes/safaridriver");
                //WebDriverManager.safaridriver().setup();
                break;
        }
        return BrowserFactory.driver;
    }

    public static void closeCurrentWindow(){
        BrowserFactory.driver.close();
    }
    public static void closeAllWindows(){
        BrowserFactory.driver.quit();
    }

    public static void openUrl(String url){
        BrowserFactory.driver.get(url);
        BrowserFactory.driver.manage().window().maximize();
        BrowserFactory.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
}
