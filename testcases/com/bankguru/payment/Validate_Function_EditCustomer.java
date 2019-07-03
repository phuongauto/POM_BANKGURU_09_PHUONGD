package com.bankguru.payment;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.Common_RegisterToSystem;
import commons.Common_CreateNewCustomer;
import commons.PageFactoryManager;
import commons.TestData;
import commons.ValidateMessage;
import pageObjects.EditCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;


public class Validate_Function_EditCustomer extends AbstractTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private HomePageObject homePage;
	private EditCustomerPageObject editCustomerPage;
	
	// Muốn chạy Edit Customer thì phải chạy RegisterToSystem trước -> chạy Create New Customer sau -> rồi mới chạy tới class Validate_Function_EditCustomer được
	
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

		log.info("Precondition: Step 04 - Click to Login button to open HomePage");
		loginPage.clickToDynamicButton(driver, "btnLogin");
		homePage = PageFactoryManager.getHomePage(driver);

		log.info("Precondition: Step 05 - Verify Welcome message on Home page displayed");
		verifyTrue(homePage.isWelcomeMessageDisplayed());

		log.info("Precondition: Step 06 - Verify User ID infor displayed");
		verifyTrue(homePage.isUserIDDisplayed(Common_RegisterToSystem.USER_ID_INFOR));

		log.info("Precondition: Step 07 - Click To 'Edit Customer' link");
		homePage.openMultiplePage(driver, "Edit Customer");		
		editCustomerPage = PageFactoryManager.getEditCustomerPage(driver);
		
		/*
		log.info("Precondition: Step 08 - Verify Edit Customer Page displayed");
		verifyTrue(editCustomerPage.isDynamicPageTitleDisplayed(driver, "Edit Customer Form"));
		
		log.info("Precondition: Step 09 - Input Customer ID to textbox");
		editCustomerPage.inputToDynamicTextboxTextArea(driver, "cusid", Common_02_CreateNewCustomer.CUSTOMER_ID);
		editCustomerPage.clickToDynamicButton(driver, "AccSubmit");
		
		log.info("Precondition: Step 08 - Verify Edit Customer Form displayed");
		verifyTrue(editCustomerPage.isDynamicPageTitleDisplayed(driver, "Edit Customer"));
		*/
		
	}

	@Test
	public void TC01_EditCustomer_CustomerIDCanNotBeEmpty() {
		log.info("EditCustomer_01: Step 01 - Clear 'Customer ID' textbox");
		editCustomerPage.clearDynamicTextboxTextArea(driver, "cusid");
		log.info("EditCustomer_01: Step 02 - Click to 'Customer ID' textbox");
		editCustomerPage.clickToDynamicTextboxTextArea(driver, "cusid");
		log.info("EditCustomer_01: Step 03 - Press TAB key");
		editCustomerPage.pressTabToDynamicTextboxTextArea(driver, "cusid");
		log.info("EditCustomer_01: Step 04 - Verify 'Customer ID is required' message displayed");
		verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "Customer ID"), "Customer ID is required");
	}
	
	@Test
	public void TC02_EditCustomer_CustomerIDMustBeNumeric() {
		for (String charactervalue : TestData.CHARACTER_VALUE) {
			log.info("EditCustomer 02: Step 01 - Clear 'Customer ID' textbox");
			editCustomerPage.clearDynamicTextboxTextArea(driver, "cusid");
			
			log.info("EditCustomer 02: Step 02 - Input to 'Customer ID' textbox");
			editCustomerPage.inputToDynamicTextboxTextArea(driver, "cusid", charactervalue);
			
			log.info("EditCustomer 02: Step 03 - Verify 'Characters are not allowed' message displayed");
			verifyEquals((editCustomerPage.getTextDynamicValidateMessage(driver, "Customer ID")), ValidateMessage.CHARACTERS_ARE_NOT_ALLOWED);
		}
	}
	
	@Test
	public void TC03_EditCustomer_CustomerIDCanNotHaveSpecialCharacters() {
		for(String specialCharacter : TestData.SPECIAL_VALUE) {
			log.info("EditCustomer 03: Step 01 - Clear 'Customer ID' textbox");
			editCustomerPage.clearDynamicTextboxTextArea(driver, "cusid");
			
			log.info("EditCustomer 03: Step 02 - Input to 'Customer ID' textbox");
			editCustomerPage.inputToDynamicTextboxTextArea(driver, "cusid", specialCharacter);
			
			log.info("EditCustomer 03: Step 03 - Verify 'Special characters are not allowed' message displayed");
			verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "Customer ID"), ValidateMessage.SPECIAL_CHARACTERS_ARE_NOT_ALLOWED);
		}
		
	}
	
	@Test
	public void TC04_EditCustomer_ValidCustomerID() {
		log.info("EditCustomer 04: Step 01 - Clear 'Customer ID' textbox");
		editCustomerPage.clearDynamicTextboxTextArea(driver, "cusid");
		log.info("EditCustomer 04: Step 02 - Input valid data to 'Customer ID' textbox");
		editCustomerPage.inputToDynamicTextboxTextArea(driver, "cusid", Common_CreateNewCustomer.CUSTOMER_ID);
		log.info("EditCustomer 04: Step 03 - Submit");
		editCustomerPage.clickToDynamicButton(driver, "AccSubmit");
		log.info("EditCustomer 04: Step 04 - Verify Edit Customer form displayed");
		verifyTrue(editCustomerPage.isDynamicPageTitleDisplayed(driver, "Edit Customer"));
	}

	// @Test Name cannot be edit in reality
	public void TC05_EditCustomer_NameCanNotBeEmpty() {
		log.info("EditCustomer 01: Step 01 - Click to 'Customer Name' textbox");
		editCustomerPage.clickToDynamicTextboxTextArea(driver, "name");

		log.info("EditCustomer 01: Step 02 - Press TAB key");
		editCustomerPage.pressTabToDynamicTextboxTextArea(driver, "name");

		log.info("EditCustomer 01: Step 03 - Verify 'Name cannot be empty' message displayed");
		verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "Customer Name"), ValidateMessage.CUSTOMER_NAME_MUST_NOT_BE_BLANK);
	}
	
	// @Test Name cannot be edit in reality
	public void TC06_EditCustomer_NameCanNotBeNumeric() {
		for (String numericName : TestData.NUMERIC_VALUES) {
			log.info("EditCustomer 06: Step 01 - Clear 'Customer Name' textbox");
			editCustomerPage.clearDynamicTextboxTextArea(driver, "name");

			log.info("EditCustomer 06: Step 02 - Input to 'Customer Name' textbox");
			editCustomerPage.inputToDynamicTextboxTextArea(driver, "name", numericName);

			log.info("EditCustomer 06: Step 03 - Verify 'Name cannot contain numbers' message displayed");
			verifyEquals((editCustomerPage.getTextDynamicValidateMessage(driver, "Customer Name")), ValidateMessage.NAME_CANNOT_CONTAIN_NUMBER);
		}
		
	}
	
	// @Test Name cannot be edit in reality
	public void TC07_EditCustomer_NameCanNotHaveSpecialCharacters() {
		log.info("EditCustomer 07: Step 01 - Clear 'Customer Name' textbox");
		editCustomerPage.clearDynamicTextboxTextArea(driver, "name");
		log.info("EditCustomer 07: Step 02 - Input to 'Customer Name' textbox");
		editCustomerPage.inputToDynamicTextboxTextArea(driver, "name", TestData.BLANK_SPACE);
		log.info("EditCustomer 07: Step 03 - Verify 'Name cannot contain Special Characters' message displayed");
		verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "Customer Name"), ValidateMessage.NAME_CANNOT_CONTAIN_SPECIAL_CHARACTERS);
	}
	
	@Test
	public void TC08_EditCustomer_AddressCanNotBeEmpty() {
		log.info("EditCustomer_08: Step 01 - Clear 'Address' text area");
		editCustomerPage.clearDynamicTextboxTextArea(driver, "addr");
		log.info("EditCustomer 08: Step 02 - Click to 'Address' text area");
		editCustomerPage.clickToDynamicTextboxTextArea(driver, "addr");
		log.info("EditCustomer 08: Step 03 - Press TAB key");
		editCustomerPage.pressTabToDynamicTextboxTextArea(driver, "addr");
		log.info("EditCustomer 08: Step 04 - Verify 'Address Field must not be blank' message displayed");
		verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "Address"), ValidateMessage.ADDRESS_FIELD_MUST_NOT_BE_BLANK);
	}

	@Test
	public void TC09_EditCustomer_CityCanNotBeEmpty() {
		log.info("EditCustomer 08: Step 01 - Clear 'City' textbox");
		editCustomerPage.clearDynamicTextboxTextArea(driver, "city");
		log.info("EditCustomer 08: Step 02 - Click to 'City' textbox");
		editCustomerPage.clickToDynamicTextboxTextArea(driver, "city");
		log.info("EditCustomer 08: Step 03 - Press TAB key");
		editCustomerPage.pressTabToDynamicTextboxTextArea(driver, "city");
		log.info("EditCustomer 08: Step 04 - Verify 'City Field must not be blank' message displayed");
		verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "City"), ValidateMessage.CITY_FIELD_MUST_NOT_BE_BLANK);
	}

	@Test
	public void TC10_EditCustomer_CityCanNotBeNumeric() {
		for (String numericCity : TestData.NUMERIC_VALUES) {
			log.info("EditCustomer 10: Step 01 - Clear 'City' textbox");
			editCustomerPage.clearDynamicTextboxTextArea(driver, "city");
			log.info("EditCustomer 10: Step 02 - Input to 'City' textbox");
			editCustomerPage.inputToDynamicTextboxTextArea(driver, "city", numericCity);
			log.info("EditCustomer 10: Step 03 - Verify 'Numbers are not allowed' message displayed");
			verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "City"), ValidateMessage.NUMBERS_ARE_NOT_ALLOWED);
		}
	}

	@Test
	public void TC11_EditCustomer_CityCanNotHaveSpecialCharacters() {
		for (String specialCharactersCity : TestData.SPECIAL_VALUE) {
			log.info("EditCustomer 11: Step 01 - Clear 'City' textbox");
			editCustomerPage.clearDynamicTextboxTextArea(driver, "city");
			log.info("EditCustomer 11: Step 02 - Input to 'City' textbox");
			editCustomerPage.inputToDynamicTextboxTextArea(driver, "city", specialCharactersCity);
			log.info("EditCustomer 11: Step 03 - Verify 'Special characters are not allowed' message displayed");
			verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "City"), ValidateMessage.SPECIAL_CHARACTERS_ARE_NOT_ALLOWED);
		}
	}

	@Test
	public void TC12_EditCustomer_StateCanNotBeEmpty() {
		log.info("EditCustomer 12: Step 01 - Clear 'State' textbox");
		editCustomerPage.clearDynamicTextboxTextArea(driver, "state");
		log.info("EditCustomer 12: Step 02 - Click to 'State' textbox");
		editCustomerPage.clickToDynamicTextboxTextArea(driver, "state");
		log.info("EditCustomer 12: Step 03 - Press TAB key");
		editCustomerPage.pressTabToDynamicTextboxTextArea(driver, "state");
		log.info("EditCustomer 12: Step 04 - Verify 'State must not be blank' message displayed");
		verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "State"), ValidateMessage.STATE_MUST_NOT_BE_BLANK);
	}

	@Test
	public void TC13_EditCustomer_StateCanNotBeNumeric() {
		for (String numericState : TestData.NUMERIC_VALUES) {
			log.info("EditCustomer 13: Step 01 - Clear 'State' textbox");
			editCustomerPage.clearDynamicTextboxTextArea(driver, "state");
			log.info("EditCustomer 13: Step 02 - Input to 'State' textbox");
			editCustomerPage.inputToDynamicTextboxTextArea(driver, "state", numericState);
			log.info("EditCustomer 13: Step 03 - Verify 'Numbers are not allowed' message displayed");
			verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "State"), ValidateMessage.NUMBERS_ARE_NOT_ALLOWED);
		}
	}

	@Test
	public void TC14_EditCustomer_StateCanNotHaveSpecialCharacters() {
		for (String specialCharactersState : TestData.SPECIAL_VALUE) {
			log.info("EditCustomer 14: Step 01 - Clear 'State' textbox");
			editCustomerPage.clearDynamicTextboxTextArea(driver, "state");
			log.info("EditCustomer 14: Step 02 - Input to 'State' textbox");
			editCustomerPage.inputToDynamicTextboxTextArea(driver, "state", specialCharactersState);
			log.info("EditCustomer 14: Step 03 - Verify 'Special characters are not allowed' message displayed");
			verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "State"), ValidateMessage.SPECIAL_CHARACTERS_ARE_NOT_ALLOWED);
		}
	}

	@Test
	public void TC14_EditCustomer_StateCanNotHaveFirstBlankSpace() {
		log.info("EditCustomer 15: Step 01 - Clear 'State' textbox");
		editCustomerPage.clearDynamicTextboxTextArea(driver, "state");
		log.info("EditCustomer 15: Step 02 - Input to 'State' textbox");
		editCustomerPage.inputToDynamicTextboxTextArea(driver, "state", TestData.BLANK_SPACE);
		log.info("EditCustomer 15: Step 03 - Verify 'First character can not have space' message displayed");
		verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "State"), ValidateMessage.FIRST_CHARACTER_CAN_NOT_HAVE_SPACE);

	}

	@Test
	public void TC15_EditCustomer_PinMustBeNumeric() {
		for (String characterPIN : TestData.CHARACTER_PIN_VALUE) {
			log.info("EditCustomer 15: Step 01 - Clear 'PIN' textbox");
			editCustomerPage.clearDynamicTextboxTextArea(driver, "pinno");
			log.info("EditCustomer 15: Step 02 - Input to 'PIN' textbox");
			editCustomerPage.inputToDynamicTextboxTextArea(driver, "pinno", characterPIN);
			log.info("EditCustomer 15: Step 03 - Verify 'Characters are not allowed' message displayed");
			verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "PIN"), ValidateMessage.CHARACTERS_ARE_NOT_ALLOWED);
		}
	}
	
	@Test
	public void TC16_EditCustomer_PinCanNotBeEmpty() {
		log.info("EditCustomer 16: Step 01 - Clear 'PIN' textbox");
		editCustomerPage.clearDynamicTextboxTextArea(driver, "pinno");
		log.info("EditCustomer 16: Step 02 - Click to 'PIN' textbox");
		editCustomerPage.clickToDynamicTextboxTextArea(driver, "pinno");
		log.info("EditCustomer 16: Step 03 - Press TAB key");
		editCustomerPage.pressTabToDynamicTextboxTextArea(driver, "pinno");
		log.info("EditCustomer 16: Step 04 - Verify 'PIN Code must not be blank' message displayed");
		verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "PIN"), ValidateMessage.PIN_CODE_MUST_NOT_BE_BLANK);
	}

	@Test
	public void TC17_EditCustomer_PinMustHave6Digits() {
		for (String lessThan6Digits : TestData.LESS_THAN_6_DIGITS_PIN_VALUE) {
			log.info("EditCustomer 17: Step 01 - Clear 'PIN' textbox");
			editCustomerPage.clearDynamicTextboxTextArea(driver, "pinno");
			log.info("EditCustomer 17: Step 02 - Input to 'PIN' textbox");
			editCustomerPage.inputToDynamicTextboxTextArea(driver, "pinno", lessThan6Digits);
			log.info("EditCustomer 17: Step 03 - Verify 'PIN Code must have 6 Digits' message displayed");
			verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "PIN"), ValidateMessage.PIN_CODE_MUST_HAVE_6_DIGITS);
		}
	}
	
	@Test
	public void TC18_EditCustomer_PinCanNotHaveFirstBlankSpace() {
		log.info("EditCustomer 19: Step 01 - Clear 'PIN' textbox");
		editCustomerPage.clearDynamicTextboxTextArea(driver, "pinno");
		log.info("EditCustomer 19: Step 02 - Input to 'PIN' textbox");
		editCustomerPage.inputToDynamicTextboxTextArea(driver, "pinno", TestData.BLANK_SPACE);
		log.info("EditCustomer 19: Step 03 - Verify 'First character can not have space' message displayed");
		verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "PIN"), ValidateMessage.FIRST_CHARACTER_CAN_NOT_HAVE_SPACE);
	}
	
	@Test
	public void TC18_EditCustomer_PinCanNotHaveSpecialCharacter() {
		for (String specialCharactersPIN : TestData.SPECIAL_VALUE) {
			log.info("EditCustomer 18: Step 01 - Clear 'PIN' textbox");
			editCustomerPage.clearDynamicTextboxTextArea(driver, "pinno");
			log.info("EditCustomer 18: Step 02 - Input to 'PIN' textbox");
			editCustomerPage.inputToDynamicTextboxTextArea(driver, "pinno", specialCharactersPIN);
			log.info("EditCustomer 18: Step 03 - Verify 'PIN cannot contain Special Characters' message displayed");
			verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "PIN"), ValidateMessage.SPECIAL_CHARACTERS_ARE_NOT_ALLOWED);
		}
	}
	
	@Test
	public void TC19_EditCustomer_MobileNumberCanNotBeEmpty() {
		log.info("EditCustomer19: Step 01 - Clear 'Mobile Number' textbox");
		editCustomerPage.clearDynamicTextboxTextArea(driver, "telephoneno");
		log.info("EditCustomer19: Step 02 - Click to 'Mobile Number' textbox");
		editCustomerPage.clickToDynamicTextboxTextArea(driver, "telephoneno");
		log.info("EditCustomer19: Step 03 - Press TAB key");
		editCustomerPage.pressTabToDynamicTextboxTextArea(driver, "telephoneno");
		log.info("EditCustomer19: Step 04 - Verify 'Mobile number must not be blank' message displayed");
		verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "Mobile Number"), ValidateMessage.MOBILE_NO_MUST_NOT_BE_BLANK);
	}

	@Test
	public void TC20_EditCustomer_MobileNumberCanNotHaveFirstBlankSpace() {
		log.info("EditCustomer 20: Step 01 - Clear 'Mobile Number' textbox");
		editCustomerPage.clearDynamicTextboxTextArea(driver, "telephoneno");
		log.info("EditCustomer 20: Step 02 - Input to 'Mobile Number' textbox");
		editCustomerPage.inputToDynamicTextboxTextArea(driver, "telephoneno", TestData.BLANK_SPACE);
		log.info("EditCustomer 20: Step 03 - Verify 'First character can not have space' message displayed");
		verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "Mobile Number"), ValidateMessage.FIRST_CHARACTER_CAN_NOT_HAVE_SPACE);
	}

	@Test
	public void TC20_EditCustomer_MobileNumberCharactersAreNotAllowed() {
		for (String characterPhoneNumber : TestData.CHARACTER_VALUE) {
			log.info("EditCustomer 20: Step 01 - Clear 'Mobile Number' textbox");
			editCustomerPage.clearDynamicTextboxTextArea(driver, "telephoneno");
			log.info("EditCustomer 20: Step 02 - Input to 'Mobile Number' textbox");
			editCustomerPage.inputToDynamicTextboxTextArea(driver, "telephoneno", characterPhoneNumber);
			log.info("EditCustomer 20: Step 03 - Verify 'Characters are not allowed' message displayed");
			verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "Mobile Number"), ValidateMessage.CHARACTERS_ARE_NOT_ALLOWED);
		}
	}

	@Test
	public void TC20_EditCustomer_MobileNumberCanNotHaveSpecialCharacters() {
		for (String specialPhoneNumber : TestData.SPECIAL_VALUE) {
			log.info("EditCustomer 23: Step 01 - Clear 'Mobile Number' textbox");
			editCustomerPage.clearDynamicTextboxTextArea(driver, "telephoneno");
			log.info("EditCustomer 23: Step 02 - Input to 'Mobile Number' textbox");
			editCustomerPage.inputToDynamicTextboxTextArea(driver, "telephoneno", specialPhoneNumber);
			log.info("EditCustomer 23: Step 03 - Verify 'Special characters are not allowed' message displayed");
			verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "Mobile Number"), ValidateMessage.SPECIAL_CHARACTERS_ARE_NOT_ALLOWED);
		}
	}

	@Test
	public void TC21_EditCustomer_EmailCanNotBeEmpty() {
		log.info("EditCustomer_21: Step 01 - Clear 'Email' textbox");
		editCustomerPage.clearDynamicTextboxTextArea(driver, "emailid");
		log.info("EditCustomer_21: Step 02 - Click to 'Email' textbox");
		editCustomerPage.clickToDynamicTextboxTextArea(driver, "emailid");
		log.info("EditCustomer_21: Step 03 - Press TAB key");
		editCustomerPage.pressTabToDynamicTextboxTextArea(driver, "emailid");
		log.info("EditCustomer_21: Step 04 - Verify 'Email-ID must not be blank' message displayed");
		verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "E-mail"), ValidateMessage.EMAIL_ID_MUST_NOT_BE_BLANK);
	}

	@Test
	public void TC22_EditCustomer_EmailMustBeInCorrectFormat() {
		for (String incorrectEmailID : TestData.INCORRECT_EMAILS) {
			log.info("EditCustomer_22: Step 01 - Clear 'Email' textbox");
			editCustomerPage.clearDynamicTextboxTextArea(driver, "emailid");
			log.info("EditCustomer_22: Step 02 - input to 'Email' textbox");
			editCustomerPage.inputToDynamicTextboxTextArea(driver, "emailid", incorrectEmailID);
			log.info("EditCustomer_22: Step 03 - Verify 'Email-ID is not valid' message displayed");
			verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "E-mail"), ValidateMessage.EMAIL_ID_IS_NOT_VALID);
		}
	}

	@Test
	public void TC23_EditCustomer_EmailCanNotHaveBlankSpace() {
		log.info("EditCustomer 23: Step 01 - Clear 'Email' textbox");
		editCustomerPage.clearDynamicTextboxTextArea(driver, "emailid");
		log.info("EditCustomer 23: Step 02 - Input to 'Email' textbox");
		editCustomerPage.inputToDynamicTextboxTextArea(driver, "emailid", TestData.BLANK_SPACE);
		log.info("EditCustomer 23: Step 03 - Verify 'Email-ID is not valid' message displayed");
		verifyEquals(editCustomerPage.getTextDynamicValidateMessage(driver, "E-mail"), ValidateMessage.FIRST_CHARACTER_CAN_NOT_HAVE_SPACE);
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		closeBrowserAndDriver(driver);
	}

}
