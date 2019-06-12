package bankguru.SingletonPattern;

import org.openqa.selenium.WebDriver;

import bankguru.RegisterPageUI;
import commons.AbstractPage;
import commons.PageFactoryManagerSingleton;

public class RegisterPageObjectSingletonPattern extends AbstractPage{
	private WebDriver driver;
	
	public RegisterPageObjectSingletonPattern(WebDriver mappingdriver) {
		driver = mappingdriver;
	}

	public boolean isRegisterPageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ID_TEXTBOX);
		return isControlDisplayed(driver, RegisterPageUI.EMAIL_ID_TEXTBOX);
	}
	
	public void inputToEmailIDTextbox(String email) {
		sendkeyToElement(driver, RegisterPageUI.EMAIL_ID_TEXTBOX, email);
	}

	public void clickToLoginButton() {
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
	}

	public String getUserIDInfor() {
		waitForElementVisible(driver, RegisterPageUI.USER_ID_TEXT);
		return getTextElement(driver, RegisterPageUI.USER_ID_TEXT);
	}

	public String getPasswordInfor() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXT);
		return getTextElement(driver, RegisterPageUI.PASSWORD_TEXT);
	}

	public LoginPageObjectSingletonPattern openLoginPage(String loginPageUrl) {
		openAnyURL(driver, loginPageUrl);
		// khởi tạo lần thứ 2
		// return new LoginPageObjectSingletonPattern(driver);
		return PageFactoryManagerSingleton.getLoginPage(driver);
	}

}
