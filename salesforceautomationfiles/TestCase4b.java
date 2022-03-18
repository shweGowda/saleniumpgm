package com.SFDC;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase4b {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      WebDriverManager.chromedriver().setup();
      WebDriver driver = new ChromeDriver();
      driver.get("https://tekarch118-dev-ed.my.salesforce.com/");
      driver.findElement(By.id("username")).sendKeys("123");
      driver.findElement(By .id("password")).sendKeys("1234");
      driver.findElement(By .id("Login")).click();
	}

}
