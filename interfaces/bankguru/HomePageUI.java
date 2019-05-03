package bankguru;

/*
 Tầng Interfaces chứa các class tương ứng với các class bên tầng pageObjects. Ví dụ LoginPageUI là class bao gồm các cái locator của Login Page như là email text box, password text box, submit button,...
 
 
 
 * */

public class HomePageUI {
	public static final String WELCOME_MESSAGE_TEXT = "//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"; 
	public static final String USER_ID_TEXT = "//td[text()='Manger Id : %s']"; 
}
