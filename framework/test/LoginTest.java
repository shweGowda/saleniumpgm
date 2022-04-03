package sfdc.test;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import sfdc.listeners.TestListeners;
@Listeners(TestListeners.class)

public class LoginTest extends BaseTest {
	
	@BeforeMethod 
	public void launchApp() throws IOException
	{
		 driver.get(selectAppEnvironment("prod"));	
	}
	
	 @Test(invocationCount=1,groups= {"regression","login"})
	 public void loginErrorMsgTc01(Method name) throws IOException
	 {
		 // test = extent.createTest(name.getName());
		  
	      if(reusableutil.isElementDisplayed(loginpage.username,driver))
	      {
		      loginpage.username.sendKeys(du.readAccountProperties("username.prod"));
		      test.info("username entered");
		      loginpage.password.clear();
	      }
	      else
	      {
	    	  System.out.println("login page is not displayed");

	      }
	      if(reusableutil.waitForElementClickable(driver,loginpage.loginbutton))
	      {
		      loginpage.loginbutton.click();
		      Assert.assertTrue(loginpage.verifyErrorMessage(),"fail to verify");
		    //  test.pass("loginErrorMsg test case passed");
		      test.info("loginerrormsg passed");
	          //test.addScreenCaptureFromPath(takeScreenShot());



	      }
	      else
	      {
	    	  System.out.println("loginbutton is not clickable");
	      }
	 }
	 @Test()
	 public void loginToSalesForceTC02(Method name) throws IOException
	 {
		  //test = extent.createTest(name.getName());
		 
		  String appUrl =du.readAppEnvironments("prod.url");
	 
	 if(reusableutil.isElementDisplayed(loginpage.username,driver))
       {
		 loginpage.loginToApp(driver,appUrl);
		// test.info("logined to salesforce webpage");
	 
       }
	 }
     @Test()
     public void checkRememberMeTC03(Method name) throws IOException, InterruptedException
	 {
		//  test = extent.createTest(name.getName());
		  	 
	    if(reusableutil.isElementDisplayed(loginpage.username,driver))
            {
		         loginpage.username.sendKeys(du.readAccountProperties("username.prod"));
	             test.info("username entered");
		         loginpage.password.sendKeys(du.readAccountProperties("password.prod"));
		         test.info("password entered");
		         loginpage.rememberMeCheckBox.click();
		         test.info("remember me checkbox is clicked");
		         loginpage.loginbutton.click();
		         loginpage.userdropdown.click();
            } 
	    else
	        {
	    	     System.out.println("login page is not displayed");

	        }
	              	
		  if(reusableutil.waitForElementClickable(driver,loginpage.logout))
			      {
				    // loginpage.userdropdown.click(); 
		        	 loginpage.logout.click();
				      test.pass("logout button is clicked");
			      }
			      else
			      {
			    	  System.out.println("logoutbutton is not clickable");
			      }
		  Thread.sleep(1000);     
		  if(reusableutil.isElementDisplayed(loginpage.usernamefield,driver))
			      {
		        	 Assert.assertTrue(loginpage.verifyUsernameField(),"fail to verify");
		        	 //Assert.assertTrue(loginpage.usernamefield); 
				     // test.pass("username is displayed in userfield");
			          //test.addScreenCaptureFromPath(takeScreenShot());

			      }else
			      {
			    	  test.fail("username is not displayed");
			      }
	 
          }
     @Test()
     public void forgotPasswordTC04(Method name) throws IOException
	 {
		  //test = extent.createTest(name.getName());
		
		  //du.readWebElementProperties("forgotpasswordlink");
		  loginpage.forgotpasswordlink.click();
		  test.info("forgotbutton is clicked");
	      loginpage.inputusername.sendKeys(du.readAccountProperties("username.prod"));
		  test.info("usename is passed");
	      loginpage.continuebutton.click();
		  test.info("continue button is clicked");
	 
	  }
     @Test()
     public void validateLoginErrorMessageTC04(Method name) throws IOException
     {
          	//test = extent.createTest(name.getName()) ;
          	
          	if(reusableutil.isElementDisplayed(loginpage.username,driver))
            {
		         loginpage.username.sendKeys(du.readAccountProperties("wrongusername.prod"));
	             test.info("wrongusername entered");
		         loginpage.password.sendKeys(du.readAccountProperties("wrongpassword.prod"));
		         test.info("wrongpassword entered");
		        // System.out.println("loginpage ");
		         loginpage.loginbutton.click();
		         loginpage.verifyErrorMessage();
		         test.pass("error message should be displayed");
            } 
          	
	    else
	        {
	    	    System.out.println("login page is not displayed");

	        }
          	
     }
    
     
     @Test(dataProvider="testAccounts",dataProviderClass=LoginTest.class)
     public void loginAccounts(String username,String password) throws IOException
     {
    	// test = extent.createTest("loginAccounts");
    	 driver.get(selectAppEnvironment("prod"));

    	 if(reusableutil.isElementDisplayed(loginpage.username,driver))
	      {
		      loginpage.username.sendKeys(username);
		     // test.info("username entered");
		     // loginpage.password.clear();
		      loginpage.password.sendKeys(password);

	      }
	      else
	      {
	    	  System.out.println("login page is not displayed");

	      }
	      if(reusableutil.waitForElementClickable(driver,loginpage.loginbutton))
	      {
		      loginpage.loginbutton.click();
		      Assert.assertTrue(loginpage.verifyErrorMessage(),"fail to verify");
		    //  test.pass("loginErrorMsg test case passed");
		     // test.info("loginerrormsg passed");
	        //  test.addScreenCaptureFromPath(takeScreenShot());

	      }
	      else
	      {
	    	  System.out.println("loginbutton is not clickable");
	      }
	 }
         
          	
     
     
     @DataProvider(name="testAccounts")
     public Object[][] userAccounts()
     {
    	 return new Object[][] {{"shwetha.feb22@tekarch.com","shwetha"},{"username@gmail.com","password"}};
     }
    
     
    	
     
     
 }
    

  


