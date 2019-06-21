package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.FundTransferPageUI;
import commons.AbstractPage;
import commons.PageFactoryManager;

/* 
 Package pageObjects bên trong tầng Actions: chưa tất cả các pages(phân theo nhóm chức năng(chứ ko theo form hay màn hình) ví dụ như LoginPageObject, NewCustomerPageObject, RegisterPageObject, EditCustomerPageObject,...) của hệ thống.
Từng pageObject này sẽ quản lý các hoạt động của từng page đó. Làm đến đâu/cần viết cho chức năng nào thì define tới đó, ko phải define hết toàn bộ sự kiện.

LoginPageObject chứa/quản lý các hoạt động của Login Page.
 */
public class FundTransferPageObject extends AbstractPage {
	private WebDriver driver;

	public FundTransferPageObject(WebDriver mappingdriver) {
		driver = mappingdriver;
	}
	
	/* cái này sẽ được khai báo ở AbstractPage để dùng chung
	public HomePageObject openHomePage() {
		waitForElementVisible(driver, FundTransferPageUI.HOME_PAGE_LINK);
		clickToElement(driver, FundTransferPageUI.HOME_PAGE_LINK);
		return PageFactoryManager.getHomePage(driver);
		
	}*/

	
}
