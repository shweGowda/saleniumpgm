package sfdc.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import sfdc.pages.LoginPage;
import sfdc.pages.UserMenuPage;
import sfdc.utilities.AppConstants;
import sfdc.utilities.Datautilities;
import sfdc.utilities.ReusableUtil;

public class BaseTest {
	   public static  WebDriver driver =null;
	   Datautilities du = new Datautilities();
	   public static ExtentReports extent =null;
	   public static ExtentHtmlReporter report =null;
	   public static ExtentTest test =null;
	   public static LoginPage loginpage =null;
	   public static ReusableUtil reusableutil=null;
	   public static UserMenuPage usermenupage=null;
	  // public static takeScreenShot takescreenshot=null;
	   public Logger logger = Logger.getLogger(getClass().getSimpleName());
	 @BeforeSuite
	 public void suiteLevelConfigs() throws IOException
	 {
		 intializeLog4jLogging();
		 logger.info("logging is starting");
		 
	 }
	   
	   
	   @BeforeTest
	    public void setUp() throws IOException
	    {
		   intializeReports();
		   logger.info("extent reports are configured");
		   driver= getDriver("chrome");
		   logger.info("Browsre is set");
		   loginpage = new LoginPage(driver);
		   reusableutil =new ReusableUtil();
		  
	    }
	   
	   @AfterTest
	    public void tearDown() throws InterruptedException
	    {
		   Thread.sleep(1000);
		   driver.close();
		   extent.flush();
		   logger.info("report is flushed");
	    }
	   
	   /**
	     *this function will return browsers configuration
	     * @param sBrowserName eg:chrome,firefox,safari.
	     * @return driver
	     */
	    public WebDriver getDriver(String sBrowserName)
	    {   
	    	String browserName = sBrowserName.toLowerCase();
	       switch (browserName)
	       {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			//test.log(Status.INFO, "chromeBrowser is intialized");
        	driver=new ChromeDriver();
			break;
		 case "firefox":
	        	WebDriverManager.firefoxdriver().setup();
	        	driver=new FirefoxDriver();
	        	break;
		 case "safari":
	        	WebDriverManager.safaridriver().setup();
	        	driver=new SafariDriver();
	        	break;   	
		default:
			    driver =null;
			break;
		     }
	       return driver;	
	       
	       }
	   
	    /**
	     * function for specific webpage eg:saleforce.com
	     * @param environment eg:prod,qa,dev
	     * @return specific url eg:production url
	     * @throws IOException
	     */
	    public String selectAppEnvironment(String environment) throws IOException
	    {
	        String appUrl =null;
	    	switch (environment) {
			case "prod":
				 appUrl=du.readAppEnvironments("prod.url");
				 logger.info("tests will start in prod environment");
				break;
			case "qa":
				appUrl=du.readAppEnvironments("qa.url");

			default:
				appUrl=null;
				logger.fatal("environment selection failed");
				break;
			}	
	        return appUrl;
	    }
	    
	    /**
	     * creating object for ExtentReports and HtmlReport and using function attachReporter created htmlfile
	     * config to create html file
	     */
	    public void intializeReports()
	    {
	    	extent = new ExtentReports();
	    	String dateFormat = new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date());
	    	String reportpath = System.getProperty("user.dir")+"\\target\\report\\"+dateFormat+"_SFDCReport.html";
	    	report= new ExtentHtmlReporter(reportpath);
	    	extent.attachReporter(report);
	    }
	    
	    /**
	     * @return screeshots in specified path
	     * @throws IOException
	     * config for screenshot
	     */
	    public static String takeScreenShot() throws IOException
        {
          String dateFormat = new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date());
          TakesScreenshot screenshot = (TakesScreenshot) driver;
          File srcFile = screenshot.getScreenshotAs(OutputType.FILE); 	
    	  String destinationPath = System.getProperty("user.dir")+"//target//screenshot//"+dateFormat+".PNG";
  		  File destination = new File(destinationPath);
  		  FileUtils.copyFile(srcFile, destination);
  		  return destinationPath;
        }
	       
	    public void intializeLog4jLogging() throws IOException
	    {
	    	Properties log4jProp =new Properties();
	    	FileInputStream fis = new FileInputStream(AppConstants.LOG4J_PROPERTIES_FILEPATH);
	    	log4jProp.load(fis);
	    	PropertyConfigurator.configure(fis);
	    }
 }
	   

