package com.bankguru.payment;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.Common_RegisterToSystem;
import commons.PageFactoryManager;
import commons.PaymentTestData;
import pageObjects.BalanceEnquiryPageObject;
import pageObjects.DeleteAccountPageObject;
import pageObjects.DeleteCustomerPageObject;
import pageObjects.DepositPageObject;
import pageObjects.EditAccountPageObject;
import pageObjects.EditCustomerPageObject;
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.WithdrawalPageObject;

public class Payment_Function extends AbstractTest{

		private WebDriver driver;
		private LoginPageObject loginPage;
		private HomePageObject homePage;
		private NewCustomerPageObject newCustomerPage;
		private EditCustomerPageObject editcustomerPage;
		private NewAccountPageObject newAccountPage;
		private EditAccountPageObject editAccountPage;
		private DepositPageObject depositPage;
		private WithdrawalPageObject withdrawalPage;
		private FundTransferPageObject fundTransferPage;
		private BalanceEnquiryPageObject balanceEnquiryPage;
		private DeleteAccountPageObject deleteAccountPage;
		private DeleteCustomerPageObject deleteCustomerPage;
		private String customerID, accountID, payeeAccountID;
		private String email = PaymentTestData.EMAIL + "@gmail.com";

		@Parameters({ "browser", "url" })
		@BeforeTest
		public void beforeTest(String browserName, String url) {
			driver = openMultiBrowser(browserName, url);
			log.info("Precondition: Step 01 - Open Login Page");
			loginPage = PageFactoryManager.getLoginPage(driver);
			log.info("Precondition: Step 02 - Verify Login Form displayed");
			verifyTrue(loginPage.isLoginFormDisplayed());
			log.info("Precondition: Step 03 - Input to userID and 'Password' textboxes");
			loginPage.inputToDynamicTextboxTextArea(driver, "uid", Common_RegisterToSystem.USER_ID_INFOR);
			loginPage.inputToDynamicTextboxTextArea(driver, "password", Common_RegisterToSystem.PASSWORD_INFOR);
			log.info("Precondition: Step 04 - Click to Login button open HomePage");
			loginPage.clickToDynamicButton(driver, "btnLogin");
			homePage = PageFactoryManager.getHomePage(driver);
			log.info("Precondition: Step 05 - Verify Welcome message on Home page displayed");
			verifyTrue(homePage.isWelcomeMessageDisplayed());
			log.info("Precondition: Step 06 - Verify User ID infor displayed");
			verifyTrue(homePage.isUserIDDisplayed(Common_RegisterToSystem.USER_ID_INFOR));
		}

		@Test
		public void Payment_01_CreateANewCustomer() {
			log.info("Payment_01: Step 01 - Click to 'New Customer' link");
			homePage.openMultiplePage(driver, "New Customer");
			newCustomerPage = PageFactoryManager.getNewCustomerPage(driver);
			
			log.info("Payment_01: Step 02 - Verify 'Add New Customer' message displayed");
			verifyTrue(newCustomerPage.isDynamicPageTitleDisplayed(driver, PaymentTestData.ADD_NEW_CUSTOMER_TITLE));

			log.info("Payment_01: Step 03 - Input to 'Customer Name' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "name", PaymentTestData.NAME);

			log.info("Payment_01: Step 04 - Select Male gender");
			newCustomerPage.checkToDynamicCheckboxOrRadioButton(driver, "m");

			log.info("Payment_01: Step 05 - Remove 'type' attribute of Date of Birth textbox");
			newCustomerPage.removeAtrributeInDynamicTextboxArea(driver, "dob", "type");

			log.info("Payment_01: Step 06 - Input to 'Date Of Birth' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "dob", PaymentTestData.DATE_OF_BIRTH);

			log.info("Payment_01: Step 07 - Input to 'Adress' text area");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "addr", PaymentTestData.ADDRESS);

			log.info("Payment_01: Step 08 - Input to 'City' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "city", PaymentTestData.CITY);

			log.info("Payment_01: Step 09 - Input to 'State' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "state", PaymentTestData.STATE);

			log.info("Payment_01: Step 10 - Input to 'Pin' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "pinno", PaymentTestData.PIN);

			log.info("Payment_01: Step 11 - Input to 'Mobile Number' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "telephoneno", PaymentTestData.MOBILE_NUMBER);

			log.info("Payment_01: Step 12 - Input to 'Email' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "emailid", email);

			log.info("Payment_01: Step 13 - Input to 'Password' textbox");
			newCustomerPage.inputToDynamicTextboxTextArea(driver, "password", PaymentTestData.PASSWORD);

			log.info("Payment_01: Step 14 - Click to 'Submit' button");
			newCustomerPage.clickToDynamicButton(driver, "sub");

			log.info("Payment_01: Step 15 - Verify title 'Customer Registered Successfully!!!' displayed");
			verifyTrue(newCustomerPage.isDynamicPageTitleDisplayed(driver, PaymentTestData.CUSTOMER_REGISTERED_SUCCESSFULLY_MESSAGE));

			log.info("Payment_01: Step 16 - Get 'Customer ID'");
			customerID = newCustomerPage.getTextDynamicTableInfo(driver, "Customer ID");

			log.info("Payment_01: Step 17 - Verify all informations of new customer are correct");
			verifyEquals(newCustomerPage.getTextDynamicTableInfo(driver, "Customer Name"), PaymentTestData.NAME);
			verifyEquals(newCustomerPage.getTextDynamicTableInfo(driver, "Gender"), PaymentTestData.GENDER);
			verifyEquals(newCustomerPage.getTextDynamicTableInfo(driver, "Birthdate"), PaymentTestData.DATE_OF_BIRTH);
			verifyEquals(newCustomerPage.getTextDynamicTableInfo(driver, "Address"), PaymentTestData.ADDRESS);
			verifyEquals(newCustomerPage.getTextDynamicTableInfo(driver, "City"), PaymentTestData.CITY);
			verifyEquals(newCustomerPage.getTextDynamicTableInfo(driver, "State"), PaymentTestData.STATE);
			verifyEquals(newCustomerPage.getTextDynamicTableInfo(driver, "Pin"), PaymentTestData.PIN);
			verifyEquals(newCustomerPage.getTextDynamicTableInfo(driver, "Mobile No."), PaymentTestData.MOBILE_NUMBER);
			verifyEquals(newCustomerPage.getTextDynamicTableInfo(driver, "Email"), email);

		}

		@Test
		public void Payment_02_EditCustomer() {

			log.info("Payment_02: Step 01 - Click to 'Delete Customer' link");
			newCustomerPage.openMultiplePage(driver, "Edit Customer");
			editcustomerPage = PageFactoryManager.getEditCustomerPage(driver);
			
			log.info("Payment_02: Step 02 - Verify 'Edit Customer Form' message displayed");
			verifyTrue(editcustomerPage.isDynamicPageTitleDisplayed(driver, PaymentTestData.EDIT_CUSTOMER_TITLE));

			log.info("Payment_02: Step 03 - Input to 'Customer ID' textbox");
			editcustomerPage.inputToDynamicTextboxTextArea(driver, "cusid", customerID);

			log.info("Payment_02: Step 04 - Click to Submit textbox");
			editcustomerPage.clickToDynamicButton(driver, "AccSubmit");

			log.info("Payment_02: Step 05 - Verify all informations of Customer are correct");
			verifyEquals(editcustomerPage.getTextInDynamicTextbox(driver, "name"), PaymentTestData.NAME);
			verifyEquals(editcustomerPage.getTextInDynamicTextbox(driver, "gender"), PaymentTestData.GENDER);
			verifyEquals(editcustomerPage.getTextInDynamicTextbox(driver, "dob"), PaymentTestData.DATE_OF_BIRTH);
			verifyEquals(editcustomerPage.getTextInDynamicTextArea(driver, "addr"), PaymentTestData.ADDRESS);
			verifyEquals(editcustomerPage.getTextInDynamicTextbox(driver, "city"), PaymentTestData.CITY);
			verifyEquals(editcustomerPage.getTextInDynamicTextbox(driver, "state"), PaymentTestData.STATE);
			verifyEquals(editcustomerPage.getTextInDynamicTextbox(driver, "pinno"), PaymentTestData.PIN);
			verifyEquals(editcustomerPage.getTextInDynamicTextbox(driver, "telephoneno"), PaymentTestData.MOBILE_NUMBER);
			verifyEquals(editcustomerPage.getTextInDynamicTextbox(driver, "emailid"), email);

			log.info("Payment_02: Step 06 - Clear 'Address' Text Area");
			editcustomerPage.clearDynamicTextboxTextArea(driver, "addr");

			log.info("Payment_02: Step 07 - Input to 'Address' Text Area");
			editcustomerPage.inputToDynamicTextboxTextArea(driver, "addr", PaymentTestData.EDIT_ADRESS);

			log.info("Payment_02: Step 08 - Clear 'City' textbox");
			editcustomerPage.clearDynamicTextboxTextArea(driver, "city");

			log.info("Payment_02: Step 09 - Input to 'City' textbox");
			editcustomerPage.inputToDynamicTextboxTextArea(driver, "city", PaymentTestData.EDIT_CITY);

			log.info("Payment_02: Step 10 - Clear 'State' textbox");
			editcustomerPage.clearDynamicTextboxTextArea(driver, "state");

			log.info("Payment_02: Step 11 - Input to 'State' textbox");
			editcustomerPage.inputToDynamicTextboxTextArea(driver, "state", PaymentTestData.EDIT_STATE);

			log.info("Payment_02: Step 12 - Clear 'PIN' textbox");
			editcustomerPage.clearDynamicTextboxTextArea(driver, "pinno");

			log.info("Payment_02: Step 13 - Input to 'PIN' textbox");
			editcustomerPage.inputToDynamicTextboxTextArea(driver, "pinno", PaymentTestData.EDIT_PIN);

			log.info("Payment_02: Step 14 - Clear 'Mobile Number' textbox");
			editcustomerPage.clearDynamicTextboxTextArea(driver, "telephoneno");

			log.info("Payment_02: Step 15 - Input to 'Mobile Number' textbox");
			editcustomerPage.inputToDynamicTextboxTextArea(driver, "telephoneno", PaymentTestData.EDIT_PHONE_NUMBER);

			log.info("Payment_02: Step 16 - Clear 'Email' textbox");
			editcustomerPage.clearDynamicTextboxTextArea(driver, "emailid");

			log.info("Payment_02: Step 17 - Input to 'Email' textbox");
			editcustomerPage.inputToDynamicTextboxTextArea(driver, "emailid", PaymentTestData.EDIT_EMAIL);

			log.info("Payment_02: Step 18 - Click to Submit button");
			editcustomerPage.clickToDynamicButton(driver, "sub");

			log.info("Payment_02: Step 19 - Verify 'Customer details updated Successfully!!!' message displayed");
			verifyTrue(editcustomerPage.isDynamicPageTitleDisplayed(driver, PaymentTestData.CUSTOMER_DETAILS_UPDATE_SUCCESSFULLY_MESSAGE));

			log.info("Payment_02: Step 20 - Verify all informations of edit customer are correct");
			verifyEquals(editcustomerPage.getTextDynamicTableInfo(driver, "Customer ID"), customerID);
			verifyEquals(editcustomerPage.getTextDynamicTableInfo(driver, "Customer Name"), PaymentTestData.NAME);
			verifyEquals(editcustomerPage.getTextDynamicTableInfo(driver, "Gender"), PaymentTestData.GENDER);
			verifyEquals(editcustomerPage.getTextDynamicTableInfo(driver, "Birthdate"), PaymentTestData.DATE_OF_BIRTH);
			verifyEquals(editcustomerPage.getTextDynamicTableInfo(driver, "Address"), PaymentTestData.EDIT_ADRESS);
			verifyEquals(editcustomerPage.getTextDynamicTableInfo(driver, "City"), PaymentTestData.EDIT_CITY);
			verifyEquals(editcustomerPage.getTextDynamicTableInfo(driver, "State"), PaymentTestData.EDIT_STATE);
			verifyEquals(editcustomerPage.getTextDynamicTableInfo(driver, "Pin"), PaymentTestData.EDIT_PIN);
			verifyEquals(editcustomerPage.getTextDynamicTableInfo(driver, "Mobile No."), PaymentTestData.EDIT_PHONE_NUMBER);
			verifyEquals(editcustomerPage.getTextDynamicTableInfo(driver, "Email"), PaymentTestData.EDIT_EMAIL);

		}

		@Test
		public void Payment_03_CreateANewAccountAndCheckAmount() {

			log.info("Payment_03: Step 01 - Click to 'New Account' link");
			editcustomerPage.openMultiplePage(driver, "New Account");
			newAccountPage = PageFactoryManager.getNewAccountPage(driver);

			log.info("Payment_03: Step 02 - Verify 'Add new account form' message displayed");
			verifyTrue(newAccountPage.isDynamicPageTitleDisplayed(driver, PaymentTestData.ADD_NEW_ACCOUNT_TITLE));

			log.info("Payment_03: Step 03 - Input to 'Customer id' textbox");
			newAccountPage.inputToDynamicTextboxTextArea(driver, "cusid", customerID);

			log.info("Payment_03: Step 04 - Select 'Current' in 'Account type' dropdown");
			newAccountPage.selectItemInDynamicDropdown(driver, "selaccount", PaymentTestData.SAVINGS_ACCOUNT_TYPE);

			log.info("Payment_03: Step 05 - Input to 'Initial deposit' textbox");
			newAccountPage.inputToDynamicTextboxTextArea(driver, "inideposit", PaymentTestData.INITIAL_AMOUNT);

			log.info("Payment_03: Step 06 - Click to Submit button");
			newAccountPage.clickToDynamicButton(driver, "button2");

			log.info("Payment_03: Step 07 - Verify 'Account Generated Successfully!!!' message displayed");
			verifyTrue(newAccountPage.isDynamicPageTitleDisplayed(driver, PaymentTestData.ACCOUNT_GENERATED_SUCCESSFULLY_MESSAGE));

			log.info("Payment_03: Step 08 - Verify all infor are correct");
			verifyEquals(newAccountPage.getTextDynamicTableInfo(driver, "Customer ID"), customerID);
			verifyEquals(newAccountPage.getTextDynamicTableInfo(driver, "Customer Name"), PaymentTestData.NAME);
			verifyEquals(newAccountPage.getTextDynamicTableInfo(driver, "Email"), PaymentTestData.EDIT_EMAIL);
			verifyEquals(newAccountPage.getTextDynamicTableInfo(driver, "Account Type"), PaymentTestData.SAVINGS_ACCOUNT_TYPE);
			verifyEquals(newAccountPage.getTextDynamicTableInfo(driver, "Date of Opening"), getToday());
			verifyEquals(newAccountPage.getTextDynamicTableInfo(driver, "Current Amount"), PaymentTestData.INITIAL_AMOUNT);

			accountID = newAccountPage.getTextDynamicTableInfo(driver, "Account ID");
		}

		@Test
		public void Payment_04_EditAccount() {

			log.info("Payment_04: Step 01 - Click to 'Edit Account' link");
			newAccountPage.openMultiplePage(driver, "Edit Account");
			editAccountPage = PageFactoryManager.getEditAccountPage(driver);

			log.info("Payment_04: Step 02 - Verify 'Edit Account Form' message displayed");
			verifyTrue(editAccountPage.isDynamicPageTitleDisplayed(driver, PaymentTestData.EDIT_ACCOUNT_TITLE));

			log.info("Payment_04: Step 02 - Input to 'Account No' textbox");
			editAccountPage.inputToDynamicTextboxTextArea(driver, "accountno", accountID);

			log.info("Payment_04: Step 03 - Click to Submit button");
			editAccountPage.clickToDynamicButton(driver, "AccSubmit");

			log.info("Payment_04: Step 04 - Verify all informations of Account are correct");
			verifyEquals(editAccountPage.getTextInDynamicTextbox(driver, "txtcid"), customerID);
			verifyEquals(editAccountPage.getSelectedItemInDynamicDropdown(driver, "a_type"), PaymentTestData.SAVINGS_ACCOUNT_TYPE);
			verifyEquals(editAccountPage.getTextInDynamicTextbox(driver, "txtinitdep"), PaymentTestData.INITIAL_AMOUNT);

			log.info("Payment_04: Step 05 - Select 'Current' from 'Type of Account' dropdown");
			editAccountPage.selectItemInDynamicDropdown(driver, "a_type", PaymentTestData.CURRENT_ACCOUNT_TYPE);

			log.info("Payment_04: Step 06 - Click to Submit button");
			editAccountPage.clickToDynamicButton(driver, "AccSubmit");

			log.info("Payment_04: Step 07 - Verify 'Account details updated Successfully!!!' message displayed");
			verifyTrue(editAccountPage.isDynamicPageTitleDisplayed(driver, PaymentTestData.ACCOUNT_DETAILS_UPDATE_SUCCESSFULLY_MESSAGE));

			log.info("Payment_04: Step 08 - Verify all infor are correct");
			verifyEquals(editAccountPage.getTextDynamicTableInfo(driver, "Account ID"), accountID);
			verifyEquals(editAccountPage.getTextDynamicTableInfo(driver, "Customer ID"), customerID);
			verifyEquals(editAccountPage.getTextDynamicTableInfo(driver, "Customer Name"), PaymentTestData.NAME);
			verifyEquals(editAccountPage.getTextDynamicTableInfo(driver, "Email"), PaymentTestData.EDIT_EMAIL);
			verifyEquals(editAccountPage.getTextDynamicTableInfo(driver, "Account Type"), PaymentTestData.CURRENT_ACCOUNT_TYPE);
			verifyEquals(editAccountPage.getTextDynamicTableInfo(driver, "Date of Opening"), getToday());
			verifyEquals(editAccountPage.getTextDynamicTableInfo(driver, "Current Amount"), PaymentTestData.INITIAL_AMOUNT);

		}

		@Test
		public void Payment_05_TransferMoneyToCurrentAccount() {

			log.info("Payment_05: Step 01 - Click to 'Deposit' link");
			editAccountPage.openMultiplePage(driver, "Deposit");
			depositPage = PageFactoryManager.getDepositPage(driver);

			log.info("Payment_05: Step 02 - Verify 'Amount Deposit form' displayed");
			verifyTrue(newAccountPage.isDynamicPageTitleDisplayed(driver, PaymentTestData.AMOUNT_DEPOSIT_TITLE));

			log.info("Payment_05: Step 03 - Input to 'Account Number' textbox");
			newAccountPage.inputToDynamicTextboxTextArea(driver, "accountno", accountID);

			log.info("Payment_05: Step 04 - Input to 'Amount' textbox");
			newAccountPage.inputToDynamicTextboxTextArea(driver, "ammount", PaymentTestData.DEPOSIT_AMOUNT);

			log.info("Payment_05: Step 05 - Input to 'Description' textbox");
			newAccountPage.inputToDynamicTextboxTextArea(driver, "desc", PaymentTestData.DEPOSIT_DESCRIPTION);

			log.info("Payment_05: Step 06 - Click to Submit button");
			depositPage.clickToDynamicButton(driver, "AccSubmit");

			log.info("Payment_05: Step 07 - Verify Tracsaction Details message displayed");
			verifyTrue(newAccountPage.isDynamicPageTitleDisplayed(driver, (PaymentTestData.TRACSACTION_DETAILS_DEPOSIT_MESSAGE + accountID)));

			log.info("Payment_05: Step 08 - Verify Current Balance and all infor are correct");
			verifyEquals(depositPage.getTextDynamicTableInfo(driver, "Current Balance"), PaymentTestData.AFTER_DEPOSIT);
			verifyEquals(depositPage.getTextDynamicTableInfo(driver, "Account No"), accountID);
			verifyEquals(depositPage.getTextDynamicTableInfo(driver, "Amount Credited"), PaymentTestData.DEPOSIT_AMOUNT);
			verifyEquals(depositPage.getTextDynamicTableInfo(driver, "Type of Transaction"), "Deposit");
			verifyEquals(depositPage.getTextDynamicTableInfo(driver, "Description"), PaymentTestData.DEPOSIT_DESCRIPTION);

		}

		@Test
		public void Payment_06_WithdrawalMoneyFromCurrentAccount() {

			log.info("Payment_06: Step 01 - Click to 'Withdrawal' link ");
			depositPage.openMultiplePage(driver, "Withdrawal");
			withdrawalPage = PageFactoryManager.getWithdrawalPage(driver);

			log.info("Payment_06: Step 02 - Verify Amount Withdraw form displayed");
			verifyTrue(newAccountPage.isDynamicPageTitleDisplayed(driver, PaymentTestData.AMOUNT_WITHDRAWAL_TITLE));

			log.info("Payment_06: Step 03 - Input to 'Account Number' textbox");
			withdrawalPage.inputToDynamicTextboxTextArea(driver, "accountno", accountID);

			log.info("Payment_06: Step 04 - Input to 'Amount' textbox");
			withdrawalPage.inputToDynamicTextboxTextArea(driver, "ammount", PaymentTestData.WITHDRAW_AMOUNT);

			log.info("Payment_06: Step 05 - Input to 'Description' textbox");
			withdrawalPage.inputToDynamicTextboxTextArea(driver, "desc", PaymentTestData.WITHDRAW_DESCRIPTION);

			log.info("Payment_06: Step 06 - Click to Submit button");
			withdrawalPage.clickToDynamicButton(driver, "AccSubmit");

			log.info("Payment_06: Step 07 - Verify Tracsaction Details message displayed");
			verifyTrue(newAccountPage.isDynamicPageTitleDisplayed(driver, (PaymentTestData.TRACSACTION_DETAILS_WITHDRAWAL_MESSAGE + accountID)));

			log.info("Payment_06: Step 08 - Verify Current Balance and all infors are correct");
			verifyEquals(withdrawalPage.getTextDynamicTableInfo(driver, "Current Balance"), PaymentTestData.AFTER_WITHDRAW);
			verifyEquals(withdrawalPage.getTextDynamicTableInfo(driver, "Account No"), accountID);
			verifyEquals(withdrawalPage.getTextDynamicTableInfo(driver, "Amount Debited"), PaymentTestData.WITHDRAW_AMOUNT);
			verifyEquals(withdrawalPage.getTextDynamicTableInfo(driver, "Type of Transaction"), "Withdrawal");
			verifyEquals(withdrawalPage.getTextDynamicTableInfo(driver, "Description"), PaymentTestData.WITHDRAW_DESCRIPTION);

		}

		@Test
		public void Payment_07_TransferMoneyToAnotherAccount() {
			/* ========= Create Payee Account ==========*/
			log.info("Payment_07: Step 01 - Click to 'New Account' link");
			withdrawalPage.openMultiplePage(driver, "New Account");
			newAccountPage = PageFactoryManager.getNewAccountPage(driver);

			log.info("Payment_07: Step 02 - Verify 'Add new account form' message displayed");
			verifyTrue(newAccountPage.isDynamicPageTitleDisplayed(driver, PaymentTestData.ADD_NEW_ACCOUNT_TITLE));

			log.info("Payment_07: Step 03 - Input to 'Customer id' textbox");
			newAccountPage.inputToDynamicTextboxTextArea(driver, "cusid", customerID);

			log.info("Payment_07: Step 04 - Select 'Current' in 'Account type' dropdown");
			newAccountPage.selectItemInDynamicDropdown(driver, "selaccount", PaymentTestData.SAVINGS_ACCOUNT_TYPE);

			log.info("Payment_07: Step 05 - Input to 'Initial deposit' textbox");
			newAccountPage.inputToDynamicTextboxTextArea(driver, "inideposit", PaymentTestData.INITIAL_AMOUNT);

			log.info("Payment_07: Step 06 - Click to Submit button");
			newAccountPage.clickToDynamicButton(driver, "button2");

			log.info("Payment_07: Step 07 - Verify 'Account Generated Successfully!!!' message displayed");
			verifyTrue(newAccountPage.isDynamicPageTitleDisplayed(driver, PaymentTestData.ACCOUNT_GENERATED_SUCCESSFULLY_MESSAGE));

			log.info("Payment_07: Step 08 - Verify all infor are correct");
			verifyEquals(newAccountPage.getTextDynamicTableInfo(driver, "Customer ID"), customerID);
			verifyEquals(newAccountPage.getTextDynamicTableInfo(driver, "Customer Name"), PaymentTestData.NAME);
			verifyEquals(newAccountPage.getTextDynamicTableInfo(driver, "Email"), PaymentTestData.EDIT_EMAIL);
			verifyEquals(newAccountPage.getTextDynamicTableInfo(driver, "Account Type"), PaymentTestData.SAVINGS_ACCOUNT_TYPE);
			verifyEquals(newAccountPage.getTextDynamicTableInfo(driver, "Date of Opening"), getToday());
			verifyEquals(newAccountPage.getTextDynamicTableInfo(driver, "Current Amount"), PaymentTestData.INITIAL_AMOUNT);

			payeeAccountID = newAccountPage.getTextDynamicTableInfo(driver, "Account ID");

			log.info("Payment_07: Step 09 - Click to 'Fund Transfer' link ");
			newAccountPage.openMultiplePage(driver, "Fund Transfer");
			fundTransferPage = PageFactoryManager.getFundTransferPage(driver);
			
			log.info("Payment_07: Step 10 - Verify 'Fund transfer' message displayed");
			verifyTrue(fundTransferPage.isDynamicPageTitleDisplayed(driver, PaymentTestData.FUND_TRANSFER_TITLE));

			log.info("Payment_07: Step 11 - Input to 'Payers Account Number' textbox ");
			fundTransferPage.inputToDynamicTextboxTextArea(driver, "payersaccount", accountID);

			log.info("Payment_07: Step 12 - Input to 'Payees Account Number' textbox ");
			fundTransferPage.inputToDynamicTextboxTextArea(driver, "payeeaccount", payeeAccountID);

			log.info("Payment_07: Step 13 - Input to 'Amount' textbox ");
			fundTransferPage.inputToDynamicTextboxTextArea(driver, "ammount", PaymentTestData.TRANSFER_AMOUNT);

			log.info("Payment_07: Step 14 - Input to 'Description' textbox ");
			fundTransferPage.inputToDynamicTextboxTextArea(driver, "desc", PaymentTestData.FUND_TRANSFER_DESCRIPTION);

			log.info("Payment_07: Step 15 - Click to Submit button");
			fundTransferPage.clickToDynamicButton(driver, "AccSubmit");

			log.info("Payment_07: Step 16 - Verify 'Fund Transfer Details' message displayed");
			verifyTrue(newAccountPage.isDynamicPageTitleDisplayed(driver, PaymentTestData.FUND_TRANSFER_DETAILS_MESSAGE));

			log.info("Payment_07: Step 17 - Verify Payers Account is correct");
			verifyEquals(fundTransferPage.getTextDynamicTableInfo(driver, "From Account Number"), accountID);

			log.info("Payment_07: Step 18 - Verify Payees Account is correct");
			verifyEquals(fundTransferPage.getTextDynamicTableInfo(driver, "To Account Number"), payeeAccountID);

			log.info("Payment_07: Step 19 - Verify Transferred amount is correct");
			verifyEquals(fundTransferPage.getTextDynamicTableInfo(driver, "Amount"), PaymentTestData.TRANSFER_AMOUNT);

			log.info("Payment_07: Step 20 - Verify Description is correct");
			verifyEquals(fundTransferPage.getTextDynamicTableInfo(driver, "Description"), PaymentTestData.FUND_TRANSFER_DESCRIPTION);

		}

		@Test
		public void Payment_08_CheckCurrentAccountBalance() {

			log.info("Payment_08: Step 01 - Click to 'Balance Enquiry' link ");
			fundTransferPage.openMultiplePage(driver, "Balance Enquiry");
			balanceEnquiryPage = PageFactoryManager.getBalanceEnquiryPage(driver);

			log.info("Payment_08: Step 02 - Verify Balance Enquiry Form message displayed");
			verifyTrue(balanceEnquiryPage.isDynamicPageTitleDisplayed(driver, PaymentTestData.BALANCE_ENQUIRY_TITLE));

			log.info("Payment_08: Step 02 - Input to 'Account Number' textbox ");
			balanceEnquiryPage.inputToDynamicTextboxTextArea(driver, "accountno", accountID);

			log.info("Payment_08: Step 03 - Click to Submit button");
			balanceEnquiryPage.clickToDynamicButton(driver, "AccSubmit");

			log.info("Payment_08: Step 04 - Verify Balance Details message displayed");
			verifyTrue(newAccountPage.isDynamicPageTitleDisplayed(driver, ("Balance Details for Account " + accountID)));

			log.info("Payment_08: Step 05 - Verify current balance after Transferring is correct");
			verifyEquals(balanceEnquiryPage.getTextDynamicTableInfo(driver, "Balance"), PaymentTestData.AFTER_TRANSFER);

			log.info("Payment_08: Step 06 - Verify Account Number and Type of Account are correct");
			verifyEquals(balanceEnquiryPage.getTextDynamicTableInfo(driver, "Account No"), accountID);
			verifyEquals(balanceEnquiryPage.getTextDynamicTableInfo(driver, "Type of Account"), PaymentTestData.CURRENT_ACCOUNT_TYPE);

		}

		@Test
		public void Payment_09_DeleteAccountsAndVerify() {
			String[] allAccounts = { accountID, payeeAccountID };
			log.info("Payment_09: Step 00 - Click to 'Delete Account' link");
			balanceEnquiryPage.openMultiplePage(driver, "Delete Account");
			deleteAccountPage = PageFactoryManager.getDeleteAccountPage(driver);
			
			for (String eachAccount : allAccounts) {
				
				log.info("Payment_09: Step 01 - Click to 'Delete Account' link");
				deleteAccountPage.openMultiplePage(driver, "Delete Account");

				log.info("Payment_09: Step 02 - Verify 'Delete Account Form' message displayed");
				verifyTrue(deleteAccountPage.isDynamicPageTitleDisplayed(driver, PaymentTestData.DELETE_ACCOUNT_TITLE));

				log.info("Payment_09: Step 03 - Input to 'Account Number' textbox ");
				deleteAccountPage.inputToDynamicTextboxTextArea(driver, "accountno", eachAccount);

				log.info("Payment_09: Step 04 - Click to Submit button");
				deleteAccountPage.clickToDynamicButton(driver, "AccSubmit");

				log.info("Payment_09: Step 04 - Verify 'Do you really want to delete this Account?' alert displayed");
				if (!driver.toString().toLowerCase().contains("internet explorer")) {
					verifyEquals(deleteAccountPage.getTextInAlert(driver), PaymentTestData.DELETE_ACCOUNT_CONFIRMATION_ALERT_MESSAGE);
				}

				log.info("Payment_09: Step 05 - Accept alert");
				deleteAccountPage.acceptAlert(driver);

				log.info("DeleteAccount: Step 06 - Verify 'Account Deleted Sucessfully' alert displayed");
				if (!driver.toString().toLowerCase().contains("internet explorer")) {
					verifyEquals(deleteAccountPage.getTextInAlert(driver), PaymentTestData.ACCOUNT_DELETE_SUCCESSFULLY_ALERT_MESSAGE);
				}

				log.info("Payment_09: Step 07 - Accept alert");
				deleteAccountPage.acceptAlert(driver);
				homePage = PageFactoryManager.getHomePage(driver);

				log.info("Payment_09: Step 08 - Check Account does not exist anymore");
				homePage.openMultiplePage(driver, "Delete Account");
				deleteAccountPage = PageFactoryManager.getDeleteAccountPage(driver);
				deleteAccountPage.inputToDynamicTextboxTextArea(driver, "accountno", eachAccount);
				deleteAccountPage.clickToDynamicButton(driver, "AccSubmit");

				log.info("Payment_09: Step 09 - Verify 'Do you really want to delete this Account?' alert displayed");
				if (!driver.toString().toLowerCase().contains("internet explorer")) {
					verifyEquals(deleteAccountPage.getTextInAlert(driver), PaymentTestData.DELETE_ACCOUNT_CONFIRMATION_ALERT_MESSAGE);
				}

				log.info("Payment_09: Step 10 - Accept alert");
				deleteAccountPage.acceptAlert(driver);

				log.info("Payment_09: Step 11 - Verify 'Account does not exist' alert displayed");
				if (!driver.toString().toLowerCase().contains("internet explorer")) {
					verifyEquals(deleteAccountPage.getTextInAlert(driver), PaymentTestData.ACCOUNT_DOES_NOT_EXIST_ALERT_MESSAGE);
				}

				log.info("Payment_09: Step 12 - Accept alert");
				deleteAccountPage.acceptAlert(driver);

			}

		}

		@Test
		public void Payment_10_DeleteCustomerAndVerify() {

			log.info("Payment_10: Step 01 - Click to 'Delete Customer' link ");
			deleteAccountPage.openMultiplePage(driver, "Delete Customer");
			deleteCustomerPage = PageFactoryManager.getDeleteCustomerPage(driver);
			
			log.info("Payment_10: Step 02 - Verify 'Delete Customer Form' message displayed");
			verifyTrue(deleteCustomerPage.isDynamicPageTitleDisplayed(driver, PaymentTestData.DELETE_CUSTOMER_TITLE));

			log.info("Payment_10: Step 03 - Input to 'Customer ID' textbox ");
			deleteCustomerPage.inputToDynamicTextboxTextArea(driver, "cusid", customerID);

			log.info("Payment_10: Step 04 - Click to Submit button");
			deleteCustomerPage.clickToDynamicButton(driver, "AccSubmit");

			log.info("Payment_10: Step 05 - Verify 'Do you really want to delete this Customer?' alert displayed");
			if (!driver.toString().toLowerCase().contains("internet explorer")) {
				verifyEquals(deleteCustomerPage.getTextInAlert(driver), PaymentTestData.DELETE_CUSTOMER_CONFIRMATION_ALERT_MESSAGE);
			}

			log.info("Payment_10: Step 06 - Accept alert");
			deleteCustomerPage.acceptAlert(driver);

			log.info("Payment_10: Step 07 - Verify 'Customer deleted Successfully' alert displayed");
			if (!driver.toString().toLowerCase().contains("internet explorer")) {
				verifyEquals(deleteCustomerPage.getTextInAlert(driver), PaymentTestData.CUSTOMER_DELETE_SUCCESSFULLY_ALERT_MESSAGE);
			}

			log.info("Payment_10: Step 08 - Accept alert");
			deleteCustomerPage.acceptAlert(driver);
			homePage = PageFactoryManager.getHomePage(driver);

			log.info("Payment_10: Step 09 - Click to 'Delete Customer' link ");
			deleteAccountPage.openMultiplePage(driver, "Delete Customer");
			deleteCustomerPage = PageFactoryManager.getDeleteCustomerPage(driver);

			log.info("Payment_10: Step 10 - Input to 'Customer ID' textbox ");
			deleteCustomerPage.inputToDynamicTextboxTextArea(driver, "cusid", customerID);

			log.info("Payment_10: Step 11 - Click to Submit button");
			deleteCustomerPage.clickToDynamicButton(driver, "AccSubmit");

			log.info("Payment_10: Step 12 - Verify 'Do you really want to delete this Customer?' alert displayed");
			if (!driver.toString().toLowerCase().contains("internet explorer")) {
				verifyEquals(deleteCustomerPage.getTextInAlert(driver), PaymentTestData.DELETE_CUSTOMER_CONFIRMATION_ALERT_MESSAGE);
			}

			log.info("Payment_10: Step 13 - Accept alert");
			deleteCustomerPage.acceptAlert(driver);

			log.info("Payment_10: Step 14 - Verify 'Customer does not exist!' alert displayed");
			if (!driver.toString().toLowerCase().contains("internet explorer")) {
				verifyEquals(deleteCustomerPage.getTextInAlert(driver), PaymentTestData.CUSTOMER_DOES_NOT_EXIST_ALERT_MESSAGE);
			}

			log.info("Payment_09: Step 15 - Accept alert");
			deleteCustomerPage.acceptAlert(driver);

		}

		@AfterTest(alwaysRun = true)
		public void afterTest() {
			closeBrowserAndDriver(driver);
		}

	}