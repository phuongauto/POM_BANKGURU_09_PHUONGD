package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.DepositPageUI;
import bankguru.NewAccountPageUI;
import commons.AbstractPage;
import commons.PageFactoryManager;

/* 
 Package pageObjects bên trong tầng Actions: chưa tất cả các pages(phân theo nhóm chức năng(chứ ko theo form hay màn hình) ví dụ như LoginPageObject, NewCustomerPageObject, RegisterPageObject, EditCustomerPageObject,...) của hệ thống.
Từng pageObject này sẽ quản lý các hoạt động của từng page đó. Làm đến đâu/cần viết cho chức năng nào thì define tới đó, ko phải define hết toàn bộ sự kiện.

LoginPageObject chứa/quản lý các hoạt động của Login Page.
 */
public class DepositPageObject extends AbstractPage {
	private WebDriver driver;

	public DepositPageObject(WebDriver mappingdriver) {
		driver = mappingdriver;
	}

	public boolean isDepositPageDisplayed() {
		waitForElementVisible(driver, DepositPageUI.DEPOSIT_FORM);
		return isControlDisplayed(driver, DepositPageUI.DEPOSIT_FORM);
		
	}
	
	/* cái này sẽ được khai báo ở AbstractPage để dùng chung
	public FundTransferPageObject openFundTransferPage() {
		waitForElementVisible(driver, DepositPageUI.FUND_TRANSFER_LINK);
		clickToElement(driver, DepositPageUI.FUND_TRANSFER_LINK);
		return PageFactoryManager.getFundTransferPage(driver);
		
		
	}*/

	
}
