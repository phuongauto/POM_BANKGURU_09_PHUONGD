package commons;

public class PaymentTestData {
	
	public static final String NAME = "Phuong Dam";
	public static final String GENDER = "male";
	public static final String DATE_OF_BIRTH = "1989-12-06";
	public static final String ADDRESS = "Van Canh";
	public static final String CITY = "Ha Noi";
	public static final String STATE = "Hoai Duc";
	public static final String PIN = "666888";
	public static final String MOBILE_NUMBER = "0374525033";
	public static final String EMAIL = "phuongdam";
	public static final String PASSWORD = "hoilamgi68";
	
	public static final String EDIT_ADRESS = "422B hoai duc";
	public static final String EDIT_CITY = "Hai Phong";
	public static final String EDIT_STATE = "Le Chan";
	public static final String EDIT_PIN = "333999";
	public static final String EDIT_PHONE_NUMBER = "0982075688";
	public static final String EDIT_EMAIL = "phuongedit@gmail.com";

	private static int INT_INITIAL_AMOUNT = 50000;
	private static int INT_DEPOSIT_AMOUNT = 5000;
	private static int INT_AFTER_DEPOSIT = INT_INITIAL_AMOUNT + INT_DEPOSIT_AMOUNT;
	private static int INT_WITHDRAW_AMOUNT = 15000;
	private static int INT_AFTER_WITHDRAW = INT_AFTER_DEPOSIT - INT_WITHDRAW_AMOUNT;
	private static int INT_TRANSFER_AMOUNT = 10000;
	private static int INT_AFTER_TRANSFER = INT_AFTER_WITHDRAW - INT_TRANSFER_AMOUNT;

	public static final String INITIAL_AMOUNT = String.valueOf(INT_INITIAL_AMOUNT);
	public static final String DEPOSIT_AMOUNT = String.valueOf(INT_DEPOSIT_AMOUNT);
	public static final String AFTER_DEPOSIT = String.valueOf(INT_AFTER_DEPOSIT);
	public static final String WITHDRAW_AMOUNT = String.valueOf(INT_WITHDRAW_AMOUNT);
	public static final String AFTER_WITHDRAW = String.valueOf(INT_AFTER_WITHDRAW);
	public static final String TRANSFER_AMOUNT = String.valueOf(INT_TRANSFER_AMOUNT);
	public static final String AFTER_TRANSFER = String.valueOf(INT_AFTER_TRANSFER);

	public static final String DEPOSIT_DESCRIPTION = "Deposit";
	public static final String WITHDRAW_DESCRIPTION = "Withdraw";
	public static final String FUND_TRANSFER_DESCRIPTION = "Transfer";

	public static final String SAVINGS_ACCOUNT_TYPE = "Savings";
	public static final String CURRENT_ACCOUNT_TYPE = "Current";
	
	public static final String ADD_NEW_CUSTOMER_TITLE = "Add New Customer";
	public static final String EDIT_CUSTOMER_TITLE = "Edit Customer Form";
	public static final String ADD_NEW_ACCOUNT_TITLE = "Add new account form";
	public static final String EDIT_ACCOUNT_TITLE = "Edit Account Form";
	public static final String AMOUNT_DEPOSIT_TITLE = "Amount Deposit Form";
	public static final String AMOUNT_WITHDRAWAL_TITLE = "Amount Withdrawal Form";
	public static final String FUND_TRANSFER_TITLE = "Fund transfer";
	public static final String BALANCE_ENQUIRY_TITLE = "Balance Enquiry Form";
	public static final String DELETE_ACCOUNT_TITLE = "Delete Account Form";
	public static final String DELETE_CUSTOMER_TITLE = "Delete Customer Form";
	
	public static final String CUSTOMER_REGISTERED_SUCCESSFULLY_MESSAGE = "Customer Registered Successfully!!!";
	public static final String CUSTOMER_DETAILS_UPDATE_SUCCESSFULLY_MESSAGE = "Customer details updated Successfully!!!";
	
	public static final String ACCOUNT_GENERATED_SUCCESSFULLY_MESSAGE = "Account Generated Successfully!!!";
	public static final String ACCOUNT_DETAILS_UPDATE_SUCCESSFULLY_MESSAGE = "Account details updated Successfully!!!";
	
	public static final String TRACSACTION_DETAILS_DEPOSIT_MESSAGE = "Transaction details of Deposit for Account ";
	public static final String TRACSACTION_DETAILS_WITHDRAWAL_MESSAGE = "Transaction details of Withdrawal for Account ";
	
	public static final String FUND_TRANSFER_DETAILS_MESSAGE = "Fund Transfer Details";
	
	public static final String BALANCE_DETAILS_ACCOUNT_MESSAGE = "Balance Details for Account ";
	
	public static final String DELETE_ACCOUNT_CONFIRMATION_ALERT_MESSAGE = "Do you really want to delete this Account?";
	public static final String ACCOUNT_DELETE_SUCCESSFULLY_ALERT_MESSAGE = "Account Deleted Sucessfully";
	
	public static final String ACCOUNT_DOES_NOT_EXIST_ALERT_MESSAGE = "Account does not exist";
	public static final String CUSTOMER_DOES_NOT_EXIST_ALERT_MESSAGE = "Customer does not exist!!";
	
	public static final String DELETE_CUSTOMER_CONFIRMATION_ALERT_MESSAGE = "Do you really want to delete this Customer?";
	public static final String CUSTOMER_DELETE_SUCCESSFULLY_ALERT_MESSAGE = "Customer deleted Successfully";
}
