package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;
import base.nativeActions.NativeActions;

public class AboutPage extends Base {

	By comeJoinUsText = By.xpath("//span[contains(.,'Come Join Us')]");
	
	By officeLocationsText = By.xpath("//span[contains(.,'Come Join Us')]/../../following-sibling::div[contains(@class,'container')]//span[contains(.,'Offices located')]");
	
	WebDriverWait wait;
	WebDriver localDriver;
	NativeActions ntvActions;
	
	public AboutPage(WebDriver driver) {
		localDriver = driver;
		wait = new WebDriverWait(localDriver, 10);
		ntvActions = new NativeActions(localDriver);
	}
	
	public boolean isAboutPageDisplayed() {
		try {
			ntvActions.performScrollIntoViewUsingJavascript(localDriver, comeJoinUsText);
			ntvActions.waitForVisibilityOfWebElement(localDriver, comeJoinUsText);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getOfficeLocationsText() {
		return ntvActions.getTextOfWebElement(localDriver, officeLocationsText);
	}
	
}
