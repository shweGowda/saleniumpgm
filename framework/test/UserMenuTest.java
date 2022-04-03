package sfdc.test;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import sfdc.listeners.TestListeners;
import sfdc.utilities.Datautilities;
@Listeners(TestListeners.class)

public class UserMenuTest extends BaseTest {
	 
  
	    @Test(enabled=true)
	    public void selectUserMenuDropDown(Method name) throws IOException, InterruptedException
	    {
	    	 
		     //test = extent.createTest(name.getName());
	 		 driver.get(selectAppEnvironment("prod"));
	 		 loginpage.loginToApp(driver, selectAppEnvironment("prod"));
	 		// usermenupage.verifyUsername();
	 		 Thread.sleep(1000);
	 		 System.out.println( usermenupage.getUsername());
	 		  
	 		// Assert.assertTrue(verifyUsername(), "failed to verify username");
	 		 usermenupage.usermenu.click();
	 		if( reusableutil.isElementDisplayed(usermenupage.userMenuDropDown, driver))
	 		{
	 			boolean status= usermenupage.verifyUserMenuItems(driver);
		 		 Assert.assertTrue(status,"fail to verify usermenu items");	
	 		}
	 		else
	 		{
	 			System.out.println("usermenu items not displayed");
	 			//test.fail("usermenu items not displayed");
	 		}
	 		 
	    }
	   
		
}
