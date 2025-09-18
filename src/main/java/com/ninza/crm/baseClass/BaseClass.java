package com.ninza.crm.baseClass;


import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverUtility.JavaUtility;
import com.ninza.crm.generic.webdriverUtility.WebdriverUtility;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.webdriver.WebDriverBrowser;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseClass {
	
	//Create the object of property file utility
	protected PropertyFileUtility pUtil = new PropertyFileUtility();
	//create the object of excel file utility
	protected ExcelFileUtility eUtil= new ExcelFileUtility();
	 //Create the object of Webdriver utility 
	protected WebdriverUtility wUtil = new WebdriverUtility();
	protected JavaUtility jUtil = new JavaUtility();
	 
	
	 
	 public WebDriver driver = null;
	 public static WebDriver sdriver = null;
  
	 @BeforeSuite
	  public void beforeSuite() {
		  System.out.println("Connect to database");
	  }
	// @Parameters("Browser")
	 @BeforeClass (groups = {"Smoke","Regression"})
	  public void beforeClass() throws IOException {
	//public void beforeClass(String BROWSER) throws IOException { 
		String browser = pUtil.toGetDataFromPropertiesFile("browser");
		  if (browser.equalsIgnoreCase("CHROME")) {
			  driver = new ChromeDriver(wUtil.getCustomChromeOptions());  
		  }
	     else if (browser.equalsIgnoreCase("Edge")) {
	    	WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		  sdriver = driver;
		  System.out.println("Launch the browser");
	  }
  @BeforeMethod(groups = {"Smoke","Regression"})
  public void beforeMethod() throws IOException  {
	  
	  		//get the values
			String BROWSER = pUtil.toGetDataFromPropertiesFile("browser");
		   // String BROWSER = System.getProperty("browser");===for maven parameter, use this for reading from the command line
			String URL = pUtil.toGetDataFromPropertiesFile("url");
			String USERNAME = pUtil.toGetDataFromPropertiesFile("username");
			String PASSWORD = pUtil.toGetDataFromPropertiesFile("password");
			 
	  LoginPage lp = new LoginPage(driver);
	  lp.loginToApp(URL,USERNAME,PASSWORD);
	  
	 
	  System.out.println("Logged In");		  
 }
	  

  @AfterMethod (groups = {"Smoke","Regression"})
 
  public void afterMethod() {
		HomePage hp = new HomePage(driver);
		hp.logout();
	  System.out.println("Logged out from the Application");
  }

  

  @AfterClass (groups = {"Smoke","Regression"})
  public void afterClass() {
	  driver.quit();
	  System.out.println("Close the browser");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Pre-conditions of parallel executions");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Post-conditions of parallel executions");
  }

 


  public void afterSuite() {
	  System.out.println("Disconnect database");
  }

}
