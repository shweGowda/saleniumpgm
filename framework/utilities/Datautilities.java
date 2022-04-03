package sfdc.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Datautilities {
	
	/**
	 * @param keyName pass the username/password eg:username.prod
	 * @return specific username
	 * @throws IOException
	 */
	public String readAccountProperties(String keyName) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(AppConstants.USER_ACC_FILEPATH);
		prop.load(fis);
		return prop.getProperty(keyName);
		
	}
	/**
	 * @param keyName pass the URL eg:prod.url
	 * @return specific url
	 * @throws IOException
	 */
	public String readAppEnvironments(String keyName) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(AppConstants.USER_APP_ENVIR_FILEPATH);
		prop.load(fis);
		return prop.getProperty(keyName);
	}
	public String readPageValidationTexts(String keyName) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(AppConstants.PAGE_VALIDATION_FILEPATH);
		prop.load(fis);
		return prop.getProperty(keyName);
	}
	public String readWebElementProperties(String keyName) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(AppConstants.USER_WEB_ELEMENT_FILEPATH);
		prop.load(fis);
		return prop.getProperty(keyName);
		
	}
}
