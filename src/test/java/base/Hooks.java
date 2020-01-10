package base;

import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends Base{
	
	/*@Before("@WebTest")
	public void setUp() {
		driver = new ChromeDriver();
	}*/
	
	@After("@WebTest")
	public void tearDown() {
		driver.close();
	}

}
