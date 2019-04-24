package com.bankguru.account;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Account_Level_01_StepByStep_TopDown {
  WebDriver driver;
  String userIDInfor, passwordInfor, loginPageUrl, email;
  
  public int randomNumber() {
	  Random random = new Random();
	  return random.nextInt(999999);
  }
  
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.gecko.driver", ".\\resources\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.get("http://demo.guru99.com/v4/");
	  email = "selenium" + randomNumber() + "@gmail.com";
  }
  
  
  @Test
  public void TC01_RegisterToSystem() {
	  
	  // Step 01: Check Login page displayed
	  Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
	  
	  // Step 02: Click to "here" link -> Open Register page
	  loginPageUrl = driver.getCurrentUrl();
	  driver.findElement(By.xpath("//a[text()='here']")).click();
	  
	  // Step 03: Check Register page displayed
	  Assert.assertTrue(driver.findElement(By.xpath("//input[@name='emailid']")).isDisplayed());
	  
	  // Step 04: Input random email to Email textbox
	  driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
	  
	  // Step 05: Click to Submit button
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  
	  // Step 06: Get User/pass information
	  userIDInfor = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
	  passwordInfor = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	  System.out.println("User: " + userIDInfor + " - Password: " + passwordInfor);
	  
	  /*User ID :	mngr190908
		Password :	ujEjuqA*/
	  
  }
  
  @Test
  public void TC02_LoginToSystem() {
	  // Step 01: Open Login page
	  driver.get(loginPageUrl);
	  
	  // Step 02: Check Login page displayed
	  Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
	  
	  // Step 02: Input valid data
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userIDInfor);
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passwordInfor);
	  
	  // Step 03: Click to Login button
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  
	  // Step 04: Check Homepage displayed
	  Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
	  
	  // Step 04: Check user infor
	  Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userIDInfor + "']")).isDisplayed());
	  
	  
	  
	  
	  
  }
  
  @Test
  public void TC03() {
	  
	  
  }
  
  @Test
  public void TC04() {
	  
	  
  }
  @AfterTest
  public void afterTest() {
	  driver.quit();
	  
  }

}
