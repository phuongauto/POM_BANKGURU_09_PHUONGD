package bankguru.SingletonPattern;

import org.openqa.selenium.WebDriver;

import bankguru.LoginPageUI;
import commons.AbstractPage;
import commons.PageFactoryManagerSingleton;

/* 
 Package pageObjects bên trong tầng Actions: chưa tất cả các pages(phân theo nhóm chức năng(chứ ko theo form hay màn hình) ví dụ như LoginPageObject, NewCustomerPageObject, RegisterPageObject, EditCustomerPageObject,...) của hệ thống.
Từng pageObject này sẽ quản lý các hoạt động của từng page đó. Làm đến đâu/cần viết cho chức năng nào thì define tới đó, ko phải define hết toàn bộ sự kiện.

LoginPageObject chứa/quản lý các hoạt động của Login Page.
 * */

public class LoginPageObjectSingletonPattern extends AbstractPage {
	private WebDriver driver;

	public LoginPageObjectSingletonPattern(WebDriver mappingdriver) {
		driver = mappingdriver;
	}

	public boolean isLoginFormDisplayed() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_FORM);
		return isControlDisplayed(driver, LoginPageUI.LOGIN_FORM);

	}

	public String getLoginPageUrl() {
		return getCurrentPageUrl(driver);
	}

	// hàm này dùng cho SingletonPattern
	public RegisterPageObjectSingletonPattern clickToHereLink() {
		waitForElementVisible(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
		// return new RegisterPageObjectSingletonPattern(driver);
		return PageFactoryManagerSingleton.getregisterPage(driver);
	}

	public void inputToUserIDTextbox(String userIDInfor) {
		waitForElementVisible(driver, LoginPageUI.USER_ID_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USER_ID_TEXTBOX, userIDInfor);
	}

	public void inputToPasswordTextbox(String passwordInfor) {

		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, passwordInfor);
	}

	public HomePageObjectSingletonPattern clickToLoginButton() {
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		// return new HomePageObjectSingletonPattern(driver);
		return PageFactoryManagerSingleton.gethomePage(driver);
	}
}