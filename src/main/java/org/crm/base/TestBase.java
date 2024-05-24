package org.crm.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.crm.ExtentReportListener.ExtentReportListenerNG;
import org.crm.util.TestUtil;
import org.crm.util.WebEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Listeners;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.EventListener;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class TestBase {

    public static WebDriver driver ;
    public static Properties prop ;
    FileInputStream fileInputStream;
    static EventFiringWebDriver e_driver;
    static WebEventListener eventListener ;

    public TestBase()  {

        prop = new Properties();
        try {
            fileInputStream = new FileInputStream(
                    System.getProperty("user.dir") + "/src/main/java/org/crm/config/config.properties"
            );
            prop.load(fileInputStream);
        } catch (FileNotFoundException e) {
            System.err.println("Configuration file not found: " + e.getMessage());
            // Handle missing configuration appropriately
        } catch (IOException e) {
            System.err.println("Error reading configuration: " + e.getMessage());
            // Handle other I/O error appropriately
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    System.err.println("Error closing file stream: " + e.getMessage());
                }
            }
        }

    }

    public static void initialization(){
        String browserName = prop.getProperty("browser");
        if(browserName.equals("chrome")){
             WebDriverManager.chromedriver().setup();
             driver = new ChromeDriver();
        }
        else if(browserName.equals("msedge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        else if(browserName.equals("firefox")){
            WebDriverManager.edgedriver().setup();
            driver = new FirefoxDriver();
        }
        else{
            WebDriverManager.chromiumdriver().setup();
            driver = new SafariDriver();
        }

        e_driver = new EventFiringWebDriver(driver);
        eventListener = new WebEventListener();
        e_driver.register(eventListener);
        driver = e_driver;

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.get(prop.getProperty("url"));
    }

}
