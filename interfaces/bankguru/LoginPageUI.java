package bankguru;

/*
 Tầng Interfaces chứa các class tương ứng với các class bên tầng pageObjects. 
 Ví dụ: LoginPageUI là class bao gồm các cái locator của LoginPage như lÃ  email text box, password text box, submit button,...
 
 * */

public class LoginPageUI {
	
	public static final String LOGIN_FORM = "//form[@name='frmLogin']";
	public static final String USER_ID_TEXTBOX = "//input[@name='uid']";
	// public: phạm vi truy cập trong tất cả framework(class nào cũng có thể access vào biến này được)
	// static: biến tĩnh - các class khác có thể access vào mà không cần khởi tạo lên
	// ở đây nếu không khai static thì qua class khác ta phải khởi tạo LoginPageUI lên
	// final: giá trị này không thể gán lại. ví dụ final String ID = "Phuong Dam" thì không thể gán lại ID = "PD" được.
	// static kết hợp với final: trong java coi đây như là 1 hằng số
	// tên đối tượng: quy ước là viết hoa và phân tách bởi dấu gạch nối như sau: TÊN_KIỂUELEMENT
	// ví dụ như: HERE_LINK hoặc ADDRESS_TEXTBOX hoặc SUBMIT_BUTTON
	
	public static final String PASSWORD_TEXTBOX = "//input[@name='password']";
	public static final String LOGIN_BUTTON = "//input[@name='btnLogin']";
	public static final String RESET_BUTTON = "//input[@name='btnReset']";
	public static final String HERE_LINK = "//a[text()='here']";
}
