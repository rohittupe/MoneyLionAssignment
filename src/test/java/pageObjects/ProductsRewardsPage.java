package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;
import base.nativeActions.NativeActions;

public class ProductsRewardsPage extends Base {

	By earnRewardsBySection = By.xpath("//span[contains(.,'It’s easy to earn rewards.')]"); //By.xpath("//span[contains(.,'It’s easy to earn rewards.')]");
	By earnRewardsByText = By.xpath("//span[contains(.,'Earn rewards by')]/../following-sibling::h1");

	WebDriverWait wait;
	WebDriver localDriver;
	NativeActions ntvActions;
	
	public ProductsRewardsPage(WebDriver driver) {
		localDriver = driver;
		wait = new WebDriverWait(localDriver, 10);
		ntvActions = new NativeActions(localDriver);
	}
	
	public void scrollToEarnRewardsBy(String message) {
		ntvActions.performScrollIntoViewUsingJavascript(localDriver, By.xpath("//span[contains(.,'"+message+"')]"));
	}

	public boolean waitForRewardMessage(String rewardMessage) {
		long maxWaitTime = 120000;
		long waitTime = 1000;
		long totalLapsedTime = 0;
		do {
			if(ntvActions.getTextOfWebElement(localDriver, earnRewardsByText).equals(rewardMessage)) {
				System.out.println("got it");
				return true;
			}
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {}
			totalLapsedTime+=waitTime;
		} while (totalLapsedTime <= maxWaitTime);
		return false;
	}
	
	public boolean waitAndVerifyRewardMessage(List<String> rewardMessageList) {
		long maxWaitTime = 120000;
		long waitTime = 3000;
		long totalLapsedTime = 0;
		int counter=0;
		do {
			if(rewardMessageList.contains(ntvActions.getTextOfWebElement(localDriver, earnRewardsByText)))
				counter++;
			System.out.println("counter="+counter);
			if(counter == 4)
				return true;
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {}
			totalLapsedTime+=waitTime;
		} while (counter<=4 || totalLapsedTime <= maxWaitTime);
		return false;
	}

}
