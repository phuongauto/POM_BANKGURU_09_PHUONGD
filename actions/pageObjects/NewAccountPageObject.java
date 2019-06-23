package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.NewAccountPageUI;
import commons.AbstractPage;
import commons.PageFactoryManager;

/* 
 Package pageObjects bên trong tầng Actions: chưa tất cả các pages(phân theo nhóm chức năng(chứ ko theo form hay màn hình) ví dụ như LoginPageObject, NewCustomerPageObject, RegisterPageObject, EditCustomerPageObject,...) của hệ thống.
Từng pageObject này sẽ quản lý các hoạt động của từng page đó. Làm đến đâu/cần viết cho chức năng nào thì define tới đó, ko phải define hết toàn bộ sự kiện.

LoginPageObject chứa/quản lý các hoạt động của Login Page.
 */
public class NewAccountPageObject extends AbstractPage {
	private WebDriver driver;

	public NewAccountPageObject(WebDriver mappingdriver) {
		driver = mappingdriver;
	}
	
	// cách viết chưa dynamic
	public boolean isNewAccountPageDisplayed() {
		waitForElementVisible(driver, NewAccountPageUI.ADD_NEW_ACCOUNT_FORM);
		return isControlDisplayed(driver, NewAccountPageUI.ADD_NEW_ACCOUNT_FORM);
		
	}
	
	
	
	/* cái này sẽ được khai báo ở AbstractPage để dùng chung
	public DepositPageObject openDepositPage() {
		waitForElementVisible(driver, NewAccountPageUI.DEPOSIT_LINK);
		clickToElement(driver, NewAccountPageUI.DEPOSIT_LINK);
		return PageFactoryManager.getDepositPage(driver);
		
	}*/

	
}
