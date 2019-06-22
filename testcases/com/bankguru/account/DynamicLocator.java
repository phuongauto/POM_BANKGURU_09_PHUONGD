package com.bankguru.account;

public class DynamicLocator {

	public static void main(String[] args) {
		String DYNAMIC_LINK = "//a[text()='%s']"; 
		String DYNAMIC_BUTTON = "//ul[@class='%s']"; 
		String DYNAMIC_TEXTBOX = "//ul[@value='%s']//alo123[@id='%s']//abc[@class='%s']//xyz[@text()='%s']//okhaha[@id='%s']";  
		
		clickToLink(DYNAMIC_LINK, "Click here");
		clickToLink(DYNAMIC_BUTTON, "Submit");
		clickToLink(DYNAMIC_TEXTBOX, "Enter here", "Go further", "Grab", "NowVN", "BeShip");
		
	}

	public static void clickToLink(String locator, String... values){
		locator = String.format(locator, (Object[]) values);
		System.out.println(locator);
		//=> locator = "//a[text()='']" + "Click here"
		//=> locator =  "//a[text()='Click here']"
		
		/* có 1 hay nhiều %s nó cũng tự động gán vào được
		 ví dụ: 
		 String DYNAMIC_TEXTBOX = "//ul[@value='%s']//alo123[@id='%s']//abc[@class='%s']//xyz[@text()='%s']//okhaha[@id='%s']";  
		 khi đó hàm sẽ là: 
		 clickToLink(DYNAMIC_TEXTBOX, "Enter here", "Go further", "Grab", "NowVN", "BeShip");
		 Kết quả vẫn sẽ ra được:
		 //ul[@value='Enter here']//alo123[@id='Go further']//abc[@class='Grab']//xyz[@text()='NowVN']//okhaha[@id='BeShip']

		 */
	}
	
	
	
}
