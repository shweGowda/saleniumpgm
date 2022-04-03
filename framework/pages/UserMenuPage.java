package sfdc.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sfdc.utilities.Datautilities;

public class UserMenuPage {
	 public UserMenuPage(WebDriver driver)
     {
  	   PageFactory.initElements(driver, this);
     }
	 
      @FindBy(xpath="//div[@id='userNav-menuItems']//a")
      public List<WebElement> listOfuserMenuOptions;
      @FindBy(xpath="//*[@id='userNav-arrow']")
      public WebElement usermenu;
      @FindBy(id="userNav")
      public WebElement homepageusername;
      @FindBy(css = ".menuButtonMenu.menuWidthExtended")
      public WebElement userMenuDropDown;
      
    
      
      
      public boolean verifyUserMenuItems(WebDriver driver)
      {
    	  boolean isOptionVerified=false;
    	  String[] userMenuOptions = { "My Profile", "My Settings", "Developer Console", "Switch to Lightning Experience",
			"Logout" };
    	 // List<WebElement>listOfuserMenuOptions = driver.findElements(By .xpath("//div[@id=\"userNav-menuItems\"]//a"));
    	  for (int i = 0; i < userMenuOptions.length; i++)
    	   {
  			String optionValue = listOfuserMenuOptions.get(i).getText();
  			 
  			if (optionValue.equals(userMenuOptions[i])) {
  				System.out.println("Option " + userMenuOptions[i] + " is verified");
  		   } 
  		  else
  			{
  				System.out.println("Verification of " + userMenuOptions[i] + " failed");
  				isOptionVerified = false;
  			}
  		}
  		return isOptionVerified;
  	}
     public boolean verifyUsername() throws IOException
      {
 	   boolean msg=false;
 	   Datautilities du = new Datautilities();   
 	   String expectedMessage = du.readPageValidationTexts("homepage.username"); 
 	  
 	   if(expectedMessage.equals(homepageusername.getText()))
 	   {
 		   msg=true;
 	   }
 	      return msg;
      } 
    public String getUsername()
    {
    	return homepageusername.getText();
    }
     
      }
     

