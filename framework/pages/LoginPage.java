package sfdc.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sfdc.utilities.Datautilities;

public class LoginPage 
   {
       public LoginPage(WebDriver driver)
       {
    	   PageFactory.initElements(driver, this);
       }

	   @FindBy(id="username")
	   public WebElement username;
       @FindBy(id="password")
       public WebElement password;
       @FindBy(id="Login")
       public WebElement loginbutton;
       @FindBy(id="error")
       public WebElement loginErrorMsg;
       @FindBy(id="rememberUn")
       public WebElement rememberMeCheckBox;
       @FindBy(xpath="//*[@id='userNav-arrow']")
       public WebElement userdropdown;
       @FindBy(xpath="//a[contains(@title,'Logout') and text()='Logout']")
       public WebElement logout;
       @FindBy(id="idcard-identity")
       public WebElement usernamefield;
       @FindBy(id="forgot_password_link")
       public WebElement forgotpasswordlink;
       @FindBy(id="un")
       public WebElement inputusername;
       @FindBy(id="continue")
       public WebElement continuebutton;

       
       
       public void loginToApp(WebDriver driver,String Url) throws IOException
       {
    	   driver.get(Url);
    	   Datautilities du = new Datautilities();
    	   username.sendKeys(du.readAccountProperties("username.prod"));
    	   password.sendKeys(du.readAccountProperties("password.prod"));
    	   loginbutton.click();
       }
       public boolean verifyErrorMessage() throws IOException 
       {
    	   boolean errorMsg=false;
    	   Datautilities du = new Datautilities();
    	   String expectedErrorMessage =du.readPageValidationTexts("loginpage.error");
    	   if(expectedErrorMessage.equals(loginErrorMsg.getText()))
    	   {
    		   errorMsg=true;
    	   }
    	      return errorMsg;
       } 
       public boolean verifyUsernameField() throws IOException
         {
    	   boolean msg=false;
    	   Datautilities du = new Datautilities();   
    	   String expectedMessage = du.readPageValidationTexts("usernamefield.text"); 
    	   if(expectedMessage.equals(usernamefield.getText()))
    	   {
    		   msg=true;
    	   }
    	      return msg;
         } 
       
       }
       
	
   
