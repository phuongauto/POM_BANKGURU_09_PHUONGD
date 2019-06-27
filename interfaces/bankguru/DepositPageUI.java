package bankguru;

/*
 Tầng Interfaces chứa các class tương ứng với các class bên tầng pageObjects. Ví dụ LoginPageUI là class bao gồm các cái locator của Login Page như là email text box, password text box, submit button,...
 
 
 
 * */

public class DepositPageUI {
	public static final String FUND_TRANSFER_LINK = "//ul[@class='menusubnav']//a[text()='Fund Transfer']"; 
	public static final String DEPOSIT_FORM = "//p[text()='Amount Deposit Form']";
	public static final String DYNAMIC_TRANSACTION_DETAILS_MESSAGE = "//p[text()='Transaction details of Deposit for Account %s']";
}
