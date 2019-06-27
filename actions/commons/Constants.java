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
	public static final int LONG_TIMEOUT = 10;
	public static final String BANKGURU_DEV_APP_URL = "http://demo.guru99.com/v4/";
	public static final String BANKGURU_TEST_APP_URL = "http://demo.guru99.com/v3/";
	public static final String BANKGURU_STAGE_APP_URL = "http://demo.guru99.com/v2/";
	public static final String BANKGURU_PROD_APP_URL = "http://demo.guru99.com/v1/";
	public static final String LIVEGURU_FRONT_END = "http://live.guru99.com/";
	public static final String LIVEGURU_BACK_END = "http://live.guru99.com/index.php/backendlogin";
	public static final String EMAIL = "phuongdam";
}
