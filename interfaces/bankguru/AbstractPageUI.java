package bankguru;

/*
 Tầng Interfaces chứa các class tương ứng với các class bên tầng pageObjects. Ví dụ LoginPageUI là class bao gồm các cái locator của Login Page như là email text box, password text box, submit button,...
 
 
 
 * */

public class AbstractPageUI {
	public static final String HOME_PAGE_LINK = "//ul[@class='menusubnav']//a[text()='Manager']"; 
	public static final String NEW_CUSTOMER_LINK = "//ul[@class='menusubnav']//a[text()='New Customer']"; 
	public static final String EDIT_CUSTOMER_LINK = "//ul[@class='menusubnav']//a[text()='Edit Customer']"; 
	public static final String DELETE_CUSTOMER_LINK = "//ul[@class='menusubnav']//a[text()='Delete Customer']"; 
	public static final String NEW_ACCOUNT_LINK = "//ul[@class='menusubnav']//a[text()='New Account']"; 
	public static final String EDIT_ACCOUNT_LINK = "//ul[@class='menusubnav']//a[text()='Edit Account']"; 
	public static final String DELETE_ACCOUNT_LINK = "//ul[@class='menusubnav']//a[text()='Delete Account']"; 
	public static final String DEPOSIT_LINK = "//ul[@class='menusubnav']//a[text()='Deposit']"; 
	public static final String WITHDRAWAL_LINK = "//ul[@class='menusubnav']//a[text()='Withdrawal']"; 
	public static final String FUND_TRANSFER_LINK = "//ul[@class='menusubnav']//a[text()='Fund Transfer']"; 
	public static final String CHANGE_PASSWORD_LINK = "//ul[@class='menusubnav']//a[text()='Change Password']"; 
	public static final String BALANCE_ENQUIRY_LINK = "//ul[@class='menusubnav']//a[text()='Balance Enquiry ']"; 
	public static final String MINI_STATEMENT_LINK = "//ul[@class='menusubnav']//a[text()='Mini Statement']"; 
	public static final String CUSTOMISED_STATEMENT_LINK = "//ul[@class='menusubnav']//a[text()='Customised Statement']"; 
	
}
