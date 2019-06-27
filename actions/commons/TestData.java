package commons;

public class TestData {
	
	public static final String BLANK_SPACE = " ";
	public static final String[] NUMERIC_VALUES = new String[] { "6688", "fine6688" };
	public static final String[] SPECIAL_VALUE = new String[] { "pd@#^&", "@*#&" };
	public static final String[] CHARACTER_VALUE = new String[] { "phuongdam", "123 4567" };
	public static final String[] CHARACTER_PIN_VALUE = new String[] { "12PDPD", "PD123456" };
	public static final String[] LESS_THAN_6_DIGITS_PIN_VALUE = new String[] { "6", "68", "369", "3698", "61289" };
	public static final String[] INCORRECT_EMAILS = new String[] { "phuongguru@gmail", "phuongguru", "phuongguru@", "phuongguru@gmail.", "phuonggurugmail.com" };
	
	public static final String VALID_CUSTOMER_ID = "061289";
	
	public static final String VALID_NAME = "Phuong Dam";
	public static final String EXPECTED_GENDER = "male";
	public static final String VALID_DOB = "1989-12-06";
	
	public static final String ADDRESS = "422B Hoai Duc";
	public static final String CITY = "Ha Noi";
	public static final String STATE = "Hoai Duc";
	public static final String PIN = "336688";
	public static final String MOBILE_NUMBER = "0374525368";
	public static final String EMAIL = "phuongdam";
	public static final String PASSWORD = "phuong0374525368";
	
	public static final String AMOUNT = "50000";

	public static final String WITHOUT_NUMBER_NEW_PASSWORD = "auto#pro@tester";
	public static final String WITHOUT_SPECIAL_CHARACTER_NEW_PASSWORD = "4utoprot3st3r";
	public static final String[] PASSWORD_STRING_NEW_PASSWORDS = new String[] {"4uto@proT3st3r","4uto@proT3st3r"};
	public static final String VALID_NEW_PASSWORD = "Autopro@tester";
	public static final String CONFIRMED_PASSWORD = "Autopro@tester";
	
	public static final String CHANGE_PASSWORD_TITLE = "Change Password";
	public static final String CUSTOMIZED_STATEMENT_TITLE = "Customized Statement Form";
	public static final String MINI_STATEMENT_TITLE = "Mini Statement Form";
}
