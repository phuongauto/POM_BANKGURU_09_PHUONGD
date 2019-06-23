package commons;

/*Constants là biến/hằng số dùng chung cho toàn bộ cái project/hệ thống của mình). */

public class Constants {
	// Chứa những giá trị (biến cố định) dùng cho toàn bộ system
	// username/password
	// DB connection
	// server name: Dev/Test/Staging/Production
	// biến timeout
	// số lần cần retry các testcase failed
	// đường dẫn tương đối tới các thư mục download/ file data/(xml/ excel/ json)/... / file upload
	public static final int SHORT_TIMEOUT = 5;
	public static final int LONG_TIMEOUT = 30;
	public static final String DEV_APP_URL = "http://demo.guru99.com/v4/";
	public static final String TEST_APP_URL = "http://demo.guru99.com/v4/";
	public static final String STAGE_APP_URL = "http://demo.guru99.com/v4/";
	public static final String PROD_APP_URL = "http://demo.guru99.com/v4/";
	
}
