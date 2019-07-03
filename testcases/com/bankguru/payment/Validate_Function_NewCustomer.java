package com.bankguru.payment;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageFactoryManager;
import commons.ValidateMessage;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObject;
import commons.Common_RegisterToSystem;
import commons.TestData;


public class Validate_Function_NewCustomer extends AbstractTest {
	WebDriver driver;
	String userIDInfor, passwordInfor, loginPageUrl, email;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	NewCustomerPageObject newCustomerPage;
	
	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}
	
	@Parameters({"browser","url"})
	@BeforeTest
	public void beforeTest(String browserName, String url)  {
		driver = openMultiBrowser(browserName, url);
		
		log.info("Precondition: Step 01 - Open Login Page");
		loginPage = PageFactoryManager.getLoginPage(driver);
		
		log.info("Precondition: Step 02 - Verify Login Form displayed");
		verifyTrue(loginPage.isLoginFormDisplayed());
		
		log.info("Precondition: Step 03 - Input to userID and 'Password' textboxes");
		loginPage.inputToDynamicTextboxTextArea(driver, "uid", Common_RegisterToSystem.USER_ID_INFOR);
		loginPage.inputToDynamicTextboxTextArea(driver, "password", Common_RegisterToSystem.PASSWORD_INFOR);
		
		log.info("Precondition: Step 04 - Click Login button to open HomePage");
		loginPage.clickToDynamicButton(driver, "btnLogin");
		homePage = PageFactoryManager.getHomePage(driver);

		log.info("Precondition: Step 05 - Verify Welcome message in Home page displayed");
		verifyTrue(homePage.isWelcomeMessageDisplayed());

		log.info("Precondition: Step 06 - Verify User ID infor displayed");
		verifyTrue(homePage.isUserIDDisplayed(Common_RegisterToSystem.USER_ID_INFOR));

		log.info("Precondition: Step 07 - Click to New Customer link");
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManager.getNewCustomerPage(driver);
	}

	@Test
	public void TC01_NewCustomer_NameCanNotBeEmpty() {
		log.info("NewCustomer 01: Step 01 - Click to 'Customer Name' textbox");
		newCustomerPage.clickToDynamicTextboxTextArea(driver, "name");

		log.info("NewCustomer 01: Step 02 - Press TAB key");
		newCustomerPage.pressTabToDynamicTextboxTextArea(driver, "name");

		log.info("NewCustomer 01: Step 03 - Verify 'Customer name must not be blank' message displayed");
		verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "Customer Name"), ValidateMessage.CUSTOMER_NAME_MUST_NOT_BE_BLANK);
		// nếu dùng Assert.assertEquals thì là dạng (String, String)
	}
	
	@Test
	public void TC02_NewCustomer_NameCanNotBeNumeric() {
		for (String numericName : TestData.NUMERIC_VALUES) {
			log.info("NewCustomer 02: Step 01 - Clear 'Customer Name' textbox");
			newCustomerPage.clearDynamicTextboxTextArea(driver, "name");

			log.info("NewCustomer 02: Step 02 - Input to 'Customer Name' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "name", numericName);

			log.info("NewCustomer 02: Step 03 - Verify 'Numbers are not allowed' message displayed");
			verifyEquals((newCustomerPage.getTextDynamicValidateMessage(driver, "Customer Name")), ValidateMessage.NUMBERS_ARE_NOT_ALLOWED);
		}
		
	}
	
	@Test
	public void TC03_NewCustomer_NameCanNotHaveSpecialCharacters() {
		log.info("NewCustomer 04: Step 01 - Clear 'Customer Name' textbox");
		newCustomerPage.clearDynamicTextboxTextArea(driver, "name");

		log.info("NewCustomer 04: Step 02 - Input to 'Customer Name' textbox");
		newCustomerPage.inputToDynamicTextboxTextArea(driver, "name", TestData.BLANK_SPACE);

		log.info("NewCustomer 04: Step 03 - Verify 'First character can not have space' message displayed");
		verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "Customer Name"), ValidateMessage.FIRST_CHARACTER_CAN_NOT_HAVE_SPACE);

	}
	
	@Test
	public void TC04_NewCustomer_NameCanNotHaveFirstCharacterAsBlankSpace() {

		log.info("NewCustomer 04: Step 01 - Clear 'Customer Name' textbox");
		newCustomerPage.clearDynamicTextboxTextArea(driver, "name");

		log.info("NewCustomer 04: Step 02 - Input to 'Customer Name' textbox");
		newCustomerPage.inputToDynamicTextboxTextArea(driver, "name", TestData.BLANK_SPACE);

		log.info("NewCustomer 04: Step 03 - Verify 'First character can not have space' message displayed");
		verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "Customer Name"), ValidateMessage.FIRST_CHARACTER_CAN_NOT_HAVE_SPACE);

	}

	@Test
	public void TC05_NewCustomer_AddressCanNotBeEmpty() {
		log.info("NewCustomer 05: Step 01 - Clear 'Address' text area");
		newCustomerPage.clearDynamicTextboxTextArea(driver, "addr");

		log.info("NewCustomer 05: Step 02 - Click to 'Address' text area");
		newCustomerPage.clickToDynamicTextboxTextArea(driver, "addr");

		log.info("NewCustomer 05: Step 03 - Press TAB key");
		newCustomerPage.pressTabToDynamicTextboxTextArea(driver, "addr");

		log.info("NewCustomer 05: Step 04 - Verify 'Address Field must not be blank' message displayed");
		verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "Address"), ValidateMessage.ADDRESS_FIELD_MUST_NOT_BE_BLANK);
	}

	public void TC06_NewCustomer_AdressCanNotHaveSpecialCharacters() {
		for (String specialCharactersAddress : TestData.SPECIAL_VALUE) {
			log.info("NewCustomer 06: Step 01 - Clear 'Address' text area");
			newCustomerPage.clearDynamicTextboxTextArea(driver, "addr");

			log.info("NewCustomer 06: Step 02 - Input to 'Address' text area");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "addr", specialCharactersAddress);

			log.info("NewCustomer 06: Step 03 - Verify 'Special characters are not allowed' message displayed");
			verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "Address"), ValidateMessage.SPECIAL_CHARACTERS_ARE_NOT_ALLOWED);
		}
	}

	@Test
	public void TC07_NewCustomer_AddressCanNotHaveFirstBlankSpace() {

		log.info("NewCustomer 07: Step 01 - Clear 'Address' text area");
		newCustomerPage.clearDynamicTextboxTextArea(driver, "addr");

		log.info("NewCustomer 07: Step 02 - Input to 'Address' text area");
		newCustomerPage.inputToDynamicTextboxTextArea(driver, "addr", TestData.BLANK_SPACE);

		log.info("NewCustomer 07: Step 03 - Verify 'First character can not have space' message displayed");
		verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "Address"), ValidateMessage.FIRST_CHARACTER_CAN_NOT_HAVE_SPACE);

	}

	@Test
	public void TC08_NewCustomer_CityCanNotBeEmpty() {

		log.info("NewCustomer 08: Step 01 - Clear 'City' textbox");
		newCustomerPage.clearDynamicTextboxTextArea(driver, "city");

		log.info("NewCustomer 08: Step 02 - Click to 'City' textbox");
		newCustomerPage.clickToDynamicTextboxTextArea(driver, "city");

		log.info("NewCustomer 08: Step 03 - Press TAB key");
		newCustomerPage.pressTabToDynamicTextboxTextArea(driver, "city");

		log.info("NewCustomer 08: Step 04 - Verify 'City Field must not be blank' message displayed");
		verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "City"), ValidateMessage.CITY_FIELD_MUST_NOT_BE_BLANK);
	}

	@Test
	public void TC09_NewCustomer_CityCanNotBeNumeric() {
		for (String numericCity : TestData.NUMERIC_VALUES) {

			log.info("NewCustomer 09: Step 01 - Clear 'City' textbox");
			newCustomerPage.clearDynamicTextboxTextArea(driver, "city");

			log.info("NewCustomer 09: Step 02 - Input to 'City' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "city", numericCity);

			log.info("NewCustomer 09: Step 03 - Verify 'Numbers are not allowed' message displayed");
			verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "City"), ValidateMessage.NUMBERS_ARE_NOT_ALLOWED);
		}
	}

	@Test
	public void TC10_NewCustomer_CityCanNotHaveSpecialCharacters() {
		for (String specialCharactersCity : TestData.SPECIAL_VALUE) {

			log.info("NewCustomer 10: Step 01 - Clear 'City' textbox");
			newCustomerPage.clearDynamicTextboxTextArea(driver, "city");

			log.info("NewCustomer 10: Step 02 - Input to 'City' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "city", specialCharactersCity);

			log.info("NewCustomer 10: Step 03 - Verify 'Special characters are not allowed' message displayed");
			verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "City"), ValidateMessage.SPECIAL_CHARACTERS_ARE_NOT_ALLOWED);
		}
	}

	@Test
	public void TC11_NewCustomer_CityCanNotHaveFirstBlankSpace() {

		log.info("NewCustomer 11: Step 01 - Clear 'City' textbox");
		newCustomerPage.clearDynamicTextboxTextArea(driver, "city");

		log.info("NewCustomer 11: Step 02 - Input to 'City' textbox");
		newCustomerPage.inputToDynamicTextboxTextArea(driver, "city", TestData.BLANK_SPACE);

		log.info("NewCustomer 11: Step 03 - Verify 'First character can not have space' message displayed");
		verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "City"), ValidateMessage.FIRST_CHARACTER_CAN_NOT_HAVE_SPACE);

	}

	@Test
	public void TC12_NewCustomer_StateCanNotBeEmpty() {

		log.info("NewCustomer 12: Step 01 - Clear 'State' textbox");
		newCustomerPage.clearDynamicTextboxTextArea(driver, "state");

		log.info("NewCustomer 12: Step 02 - Click to 'State' textbox");
		newCustomerPage.clickToDynamicTextboxTextArea(driver, "state");

		log.info("NewCustomer 12: Step 03 - Press TAB key");
		newCustomerPage.pressTabToDynamicTextboxTextArea(driver, "state");

		log.info("NewCustomer 12: Step 04 - Verify 'State must not be blank' message displayed");
		verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "State"), ValidateMessage.STATE_MUST_NOT_BE_BLANK);
	}

	@Test
	public void TC13_NewCustomer_StateCanNotBeNumeric() {
		for (String numericState : TestData.NUMERIC_VALUES) {

			log.info("NewCustomer 13: Step 01 - Clear 'State' textbox");
			newCustomerPage.clearDynamicTextboxTextArea(driver, "state");

			log.info("NewCustomer 13: Step 02 - Input to 'State' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "state", numericState);

			log.info("NewCustomer 13: Step 03 - Verify 'Numbers are not allowed' message displayed");
			verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "State"), ValidateMessage.NUMBERS_ARE_NOT_ALLOWED);
		}
	}

	@Test
	public void TC14_NewCustomer_StateCanNotHaveSpecialCharacters() {
		for (String specialCharactersState : TestData.SPECIAL_VALUE) {
			log.info("NewCustomer 14: Step 01 - Clear 'State' textbox");
			newCustomerPage.clearDynamicTextboxTextArea(driver, "state");
			log.info("NewCustomer 14: Step 02 - Input to 'State' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "state", specialCharactersState);
			log.info("NewCustomer 14: Step 03 - Verify 'Special characters are not allowed' message displayed");
			verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "State"), ValidateMessage.SPECIAL_CHARACTERS_ARE_NOT_ALLOWED);
		}
	}

	@Test
	public void TC15_NewCustomer_StateCanNotHaveFirstBlankSpace() {

		log.info("NewCustomer 15: Step 01 - Clear 'State' textbox");
		newCustomerPage.clearDynamicTextboxTextArea(driver, "state");

		log.info("NewCustomer 15: Step 02 - Input to 'State' textbox");
		newCustomerPage.inputToDynamicTextboxTextArea(driver, "state", TestData.BLANK_SPACE);

		log.info("NewCustomer 15: Step 03 - Verify 'First character can not have space' message displayed");
		verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "State"), ValidateMessage.FIRST_CHARACTER_CAN_NOT_HAVE_SPACE);

	}

	@Test
	public void TC17_NewCustomer_PinMustBeNumeric() {
		for (String characterPIN : TestData.CHARACTER_PIN_VALUE) {
			
			log.info("NewCustomer 17: Step 01 - Clear 'PIN' textbox");
			newCustomerPage.clearDynamicTextboxTextArea(driver, "pinno");
			
			log.info("NewCustomer 17: Step 02 - Input to 'PIN' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "pinno", characterPIN);
			
			log.info("NewCustomer 17: Step 03 - Verify 'Characters are not allowed' message displayed");
			verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "PIN"), ValidateMessage.CHARACTERS_ARE_NOT_ALLOWED);
		}
	}
	
	@Test
	public void TC16_NewCustomer_PinCanNotBeEmpty() {

		log.info("NewCustomer 16: Step 01 - Clear 'PIN' textbox");
		newCustomerPage.clearDynamicTextboxTextArea(driver, "pinno");
		
		log.info("NewCustomer 16: Step 02 - Click to 'PIN' textbox");
		newCustomerPage.clickToDynamicTextboxTextArea(driver, "pinno");
		
		log.info("NewCustomer 16: Step 03 - Press TAB key");
		newCustomerPage.pressTabToDynamicTextboxTextArea(driver, "pinno");
		
		log.info("NewCustomer 16: Step 04 - Verify 'PIN Code must not be blank' message displayed");
		verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "PIN"), ValidateMessage.PIN_CODE_MUST_NOT_BE_BLANK);
	}

	@Test
	public void TC18_NewCustomer_PinMustHave6Digits() {
		for (String lessThan6Digits : TestData.LESS_THAN_6_DIGITS_PIN_VALUE) {

			log.info("NewCustomer 18: Step 01 - Clear 'PIN' textbox");
			newCustomerPage.clearDynamicTextboxTextArea(driver, "pinno");
			
			log.info("NewCustomer 18: Step 02 - Input to 'PIN' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "pinno", lessThan6Digits);
			
			log.info("NewCustomer 18: Step 03 - Verify 'PIN Code must have 6 Digits' message displayed");
			verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "PIN"), ValidateMessage.PIN_CODE_MUST_HAVE_6_DIGITS);
		}
	}
	
	@Test
	public void TC19_NewCustomer_PinCanNotHaveFirstBlankSpace() {

		log.info("NewCustomer 19: Step 01 - Clear 'PIN' textbox");
		newCustomerPage.clearDynamicTextboxTextArea(driver, "pinno");
		
		log.info("NewCustomer 19: Step 02 - Input to 'PIN' textbox");
		newCustomerPage.inputToDynamicTextboxTextArea(driver, "pinno", TestData.BLANK_SPACE);
		
		log.info("NewCustomer 19: Step 03 - Verify 'First character can not have space' message displayed");
		verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "PIN"), ValidateMessage.FIRST_CHARACTER_CAN_NOT_HAVE_SPACE);

	}

	@Test
	public void TC20_NewCustomer_MobileNumberCanNotBeEmpty() {

		log.info("NewCustomer20: Step 01 - Clear 'Mobile Number' textbox");
		newCustomerPage.clearDynamicTextboxTextArea(driver, "telephoneno");
		
		log.info("NewCustomer20: Step 02 - Click to 'Mobile Number' textbox");
		newCustomerPage.clickToDynamicTextboxTextArea(driver, "telephoneno");
		
		log.info("NewCustomer20: Step 03 - Press TAB key");
		newCustomerPage.pressTabToDynamicTextboxTextArea(driver, "telephoneno");
		
		log.info("NewCustomer20: Step 04 - Verify 'Mobile no must not be blank' message displayed");
		verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "Mobile Number"), ValidateMessage.MOBILE_NO_MUST_NOT_BE_BLANK);
	}

	@Test
	public void TC21_NewCustomer_MobileNumberCanNotHaveFirstBlankSpace() {

		log.info("NewCustomer 21: Step 01 - Clear 'Mobile Number' textbox");
		newCustomerPage.clearDynamicTextboxTextArea(driver, "telephoneno");
		
		log.info("NewCustomer 21: Step 02 - Input to 'Mobile Number' textbox");
		newCustomerPage.inputToDynamicTextboxTextArea(driver, "telephoneno", TestData.BLANK_SPACE);
		
		log.info("NewCustomer 21: Step 03 - Verify 'First character can not have space' message displayed");
		verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "Mobile Number"), ValidateMessage.FIRST_CHARACTER_CAN_NOT_HAVE_SPACE);

	}

	@Test
	public void TC22_NewCustomer_MobileNumberCharactersAreNotAllowed() {
		for (String characterPhoneNumber : TestData.CHARACTER_VALUE) {
			log.info("NewCustomer 22: Step 01 - Clear 'Mobile Number' textbox");
			newCustomerPage.clearDynamicTextboxTextArea(driver, "telephoneno");
			log.info("NewCustomer 22: Step 02 - Input to 'Mobile Number' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "telephoneno", characterPhoneNumber);
			log.info("NewCustomer 22: Step 03 - Verify 'Characters are not allowed' message displayed");
			verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "Mobile Number"), ValidateMessage.CHARACTERS_ARE_NOT_ALLOWED);
		}
	}

	@Test
	public void TC23_NewCustomer_MobileNumberCanNotHaveSpecialCharacters() {
		for (String specialPhoneNumber : TestData.SPECIAL_VALUE) {
			log.info("NewCustomer 23: Step 01 - Clear 'Mobile Number' textbox");
			newCustomerPage.clearDynamicTextboxTextArea(driver, "telephoneno");
			log.info("NewCustomer 23: Step 02 - Input to 'Mobile Number' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "telephoneno", specialPhoneNumber);
			log.info("NewCustomer 23: Step 03 - Verify 'Special characters are not allowed' message displayed");
			verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "Mobile Number"), ValidateMessage.SPECIAL_CHARACTERS_ARE_NOT_ALLOWED);
		}
	}

	@Test
	public void TC24_NewCustomer_EmailCanNotBeEmpty() {
		log.info("NewCustomer_24: Step 01 - Clear 'Email' textbox");
		newCustomerPage.clearDynamicTextboxTextArea(driver, "emailid");
		log.info("NewCustomer_24: Step 02 - Click to 'Email' textbox");
		newCustomerPage.clickToDynamicTextboxTextArea(driver, "emailid");
		log.info("NewCustomer_24: Step 03 - Press TAB key");
		newCustomerPage.pressTabToDynamicTextboxTextArea(driver, "emailid");
		log.info("NewCustomer_24: Step 04 - Verify 'Email-ID must not be blank' message displayed");
		verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "E-mail"), ValidateMessage.EMAIL_ID_MUST_NOT_BE_BLANK);
	}

	@Test
	public void TC25_NewCustomer_EmailMustBeInCorrectFormat() {
		for (String incorrectEmailID : TestData.INCORRECT_EMAILS) {
			log.info("NewCustomer_25: Step 01 - Clear 'Email' textbox");
			newCustomerPage.clearDynamicTextboxTextArea(driver, "emailid");
			log.info("NewCustomer_25: Step 02 - input to 'Email' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "emailid", incorrectEmailID);
			log.info("NewCustomer_25: Step 03 - Verify 'Email-ID is not valid' message displayed");
			verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "E-mail"), ValidateMessage.EMAIL_ID_IS_NOT_VALID);
		}
	}

	@Test
	public void TC26_NewCustomer_EmailCanNotHaveFirstCharacterAsBlankSpace() {
		log.info("NewCustomer 26: Step 01 - Clear 'Email' textbox");
		newCustomerPage.clearDynamicTextboxTextArea(driver, "emailid");
		log.info("NewCustomer 26: Step 02 - input to 'Email' textbox");
		newCustomerPage.inputToDynamicTextboxTextArea(driver, "emailid", TestData.BLANK_SPACE);
		log.info("NewCustomer 26: Step 03 - Verify 'First character can not have space' message displayed");
		verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "E-mail"), ValidateMessage.FIRST_CHARACTER_CAN_NOT_HAVE_SPACE);
	}

	public void TC27_NewCustomer_PinCanNotHaveSpecialCharacter() {
		for (String specialPin : TestData.SPECIAL_VALUE) {
			log.info("NewCustomer 27: Step 01 - Clear 'PIN' textbox");
			newCustomerPage.clearDynamicTextboxTextArea(driver, "pinno");
			log.info("NewCustomer 27: Step 02 - Input to 'PIN' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "pinno", specialPin);
			log.info("NewCustomer 27: Step 03 - Verify 'Special characters are not allowed' message displayed");
			verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "PIN"), ValidateMessage.SPECIAL_CHARACTERS_ARE_NOT_ALLOWED);
		}
	}
	
	@Test
	public void TC28_NewCustomer_PasswordCanNotBeEmpty() {

		log.info("NewCustomer 28: Step 01 - Clear 'Password' textbox");
		newCustomerPage.clearDynamicTextboxTextArea(driver, "password");

		log.info("NewCustomer 28: Step 02 - Click to 'Password' textbox");
		newCustomerPage.clickToDynamicTextboxTextArea(driver, "password");

		log.info("NewCustomer 28: Step 03 - Press TAB key");
		newCustomerPage.pressTabToDynamicTextboxTextArea(driver, "password");

		log.info("NewCustomer 28: Step 04 - Verify 'Password must not be blank' message displayed");
		verifyEquals(newCustomerPage.getTextDynamicValidateMessage(driver, "Password"), ValidateMessage.PASSWORD_MUST_NOT_BE_BLANK);
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		closeBrowserAndDriver(driver);
	}

}
