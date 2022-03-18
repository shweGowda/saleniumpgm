package com.SFDC;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver =new ChromeDriver();
		driver.get("https://tekarch118-dev-ed.my.salesforce.com/");
		driver.findElement(By .id("forgot_password_link")).click();
	    driver.findElement(By .id("un")).sendKeys("shwetha.feb22@tekarch.com");
	    driver.findElement(By .id("continue")).click();

	}

}
