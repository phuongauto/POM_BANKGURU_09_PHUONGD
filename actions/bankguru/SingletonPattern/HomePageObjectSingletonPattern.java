package bankguru.SingletonPattern;

import org.openqa.selenium.WebDriver;

import bankguru.HomePageUI;
import commons.AbstractPage;
import commons.PageFactoryManagerSingleton;

/* 
 Package pageObjects bên trong tầng Actions: chưa tất cả các pages(phân theo nhóm chức năng(chứ ko theo form hay màn hình) ví dụ như LoginPageObject, NewCustomerPageObject, RegisterPageObject, EditCustomerPageObject,...) của hệ thống.
Từng pageObject này sẽ quản lý các hoạt động của từng page đó. Làm đến đâu/cần viết cho chức năng nào thì define tới đó, ko phải define hết toàn bộ sự kiện.

LoginPageObject chứa/quản lý các hoạt động của Login Page.
 */
public class HomePageObjectSingletonPattern extends AbstractPage {
	private WebDriver driver;

	public HomePageObjectSingletonPattern(WebDriver mappingdriver) {
		driver = mappingdriver;
	}

	public boolean isWelcomeMessageDisplayed() {
		waitForElementVisible(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
		return isControlDisplayed(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
	}

	public boolean isUserIDDisplayed(String userIDInfor) {
		String USER_ID_FORMAT = String.format(HomePageUI.USER_ID_TEXT, userIDInfor);
		waitForElementPresence(driver, USER_ID_FORMAT);
		return isControlDisplayed(driver, USER_ID_FORMAT);
	}

	public void clickToNewCustomerPage() {
		waitForElementVisible(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
		clickToElement(driver, HomePageUI.NEW_CUSTOMER_PAGE_NAVIGATOR);
	}

	public void clickToEditCustomerPage() {
		waitForElementVisible(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
		clickToElement(driver, HomePageUI.EDIT_CUSTOMER_PAGE_NAVIGATOR);
	}

	public void clickToDeleteCustomerPage() {
		waitForElementVisible(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
		clickToElement(driver, HomePageUI.DELETE_CUSTOMER_PAGE_NAVIGATOR);
	}
	
	public LoginPageObjectSingletonPattern clickToLogoutLink() {
		waitForElementVisible(driver, HomePageUI.LOG_OUT_LINK);
		clickToElement(driver, HomePageUI.LOG_OUT_LINK);
		waitForAlertPresence(driver);
		acceptAlert(driver);
		return PageFactoryManagerSingleton.getLoginPage(driver);
	}
	
	
}
