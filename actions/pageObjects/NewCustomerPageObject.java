package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.NewCustomerPageUI;
import commons.AbstractPage;

public class NewCustomerPageObject extends AbstractPage{
	private WebDriver driver;
	public NewCustomerPageObject(WebDriver mappingdriver) {
		driver = mappingdriver;
	}
	
	public boolean isAddNewCustomerDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.ADD_NEW_CUSTOMER_PAGE_TITLE);
		return isControlDisplayed(driver, NewCustomerPageUI.ADD_NEW_CUSTOMER_PAGE_TITLE);
	}
	
	public void inputToCustomerName() {
		waitForElementVisible(driver, NewCustomerPageUI.CUSTOMER_NAME_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.CUSTOMER_NAME_TEXTBOX, "phuongb");
	}
	
	public void selectGenderRadioButton() {
		waitForElementVisible(driver, NewCustomerPageUI.GENDER_MALE_RADIO_BUTTON);
		clickToElement(driver, NewCustomerPageUI.GENDER_MALE_RADIO_BUTTON);
	}
	
	public void inputToDOB() {
		waitForElementVisible(driver, NewCustomerPageUI.DATE_OF_BIRTH_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.DATE_OF_BIRTH_TEXTBOX, "10-05-2019");
	}
	
	public void inputToAddress() {
		waitForElementVisible(driver, NewCustomerPageUI.ADDRESS_TEXTBOX_AREA);
		sendkeyToElement(driver, NewCustomerPageUI.ADDRESS_TEXTBOX_AREA, "số 29 ngõ 29 đường 422B, Vân Canh, Hoài Đức, Hà Nội");
		
	}
	
	public void inputToCity() {
		waitForElementVisible(driver, NewCustomerPageUI.CITY_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.CITY_TEXTBOX, "Hoai Duc");
	}
	
	public void inputToState() {
		waitForElementVisible(driver, NewCustomerPageUI.STATE_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.STATE_TEXTBOX, "Ha Noi");
	}
	
	public void inputToPIN() {
		waitForElementVisible(driver, NewCustomerPageUI.PIN_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.PIN_TEXTBOX, "080311");
	}
	
	public void inputToMobileNumber() {
		waitForElementVisible(driver, NewCustomerPageUI.MOBILE_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.MOBILE_TEXTBOX, "0374525039");
	}
	
	public void inputToEmail() {
		waitForElementVisible(driver, NewCustomerPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.EMAIL_TEXTBOX, "phuongb@gmail.com");
	}
	
	public void inputToPassword() {
		waitForElementVisible(driver, NewCustomerPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.PASSWORD_TEXTBOX, "080311");
	}
	
	public void clickToSubmit() {
		waitForElementVisible(driver, NewCustomerPageUI.SUBMIT_BUTTON);
		clickToElement(driver, NewCustomerPageUI.SUBMIT_BUTTON);
	
	}
	
	public void inputToReset() {
		waitForElementVisible(driver, NewCustomerPageUI.CITY_TEXTBOX);
		clickToElement(driver, NewCustomerPageUI.RESET_BUTTON);
	}
	
	
	
}
