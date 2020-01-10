package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;
import base.nativeActions.NativeActions;

public class HomePage extends Base {

	WebDriverWait wait;
	WebDriver localDriver;
	NativeActions ntvActions;

	By menuMembershipsLink = By.xpath("//div[contains(@class,'menuLinks')]//span[contains(.,'Memberships')]");

	By subMenuMembershipsPlusLink = By.xpath("//div[contains(@class,'menuLinks')]//span[contains(.,'Plus')]");

	By menuProductsLink = By.xpath("//div[contains(@class,'menuLinks')]//span[contains(.,'Products')]");

	By subMenuProductsInvestingLink = By.xpath("//div[contains(@class,'menuLinks')]//span[contains(.,'Investing')]");

	By subMenuProductsRewardsLink = By.xpath("//div[contains(@class,'menuLinks')]//span[contains(.,'Rewards')]");

	By productsRewardsBottomLink = By.xpath("//div[contains(@class,'block')]//a/span[contains(.,'Rewards')]");

	By menuCompanyLink = By.xpath("//div[contains(@class,'menuLinks')]//span[contains(.,'Company')]");

	By subMenuCompanyAboutLink = By.xpath("//div[contains(@class,'menuLinks')]//span[contains(.,'About')]");


	public HomePage(WebDriver driver) {
		localDriver = driver;
		wait = new WebDriverWait(localDriver, 10);
		ntvActions = new NativeActions(localDriver);
	}

	public void clickOnRewardsLinkPresentAtBottomOfPage() {
		ntvActions.performClickOnWebElement(localDriver, productsRewardsBottomLink);
	}

	public By getMenuElementsBasedOnName(String name) {
		if(name!=null) {
			if(name.equalsIgnoreCase("company"))
				return menuCompanyLink;
			else if(name.equalsIgnoreCase("products"))
				return menuProductsLink;
			else if(name.equalsIgnoreCase("investing"))
				return subMenuProductsInvestingLink;
			else if(name.equalsIgnoreCase("about"))
				return subMenuCompanyAboutLink;
			else if(name.equalsIgnoreCase("rewards"))
				return subMenuProductsRewardsLink;
		}
		return null;
	}

	public void hoverOverAndClickOnMenuAndSubMenu(String menu, String subMenu) {
		ntvActions.performClickUsingJavascript(localDriver, getMenuElementsBasedOnName(menu));
		ntvActions.performClickUsingJavascript(localDriver, getMenuElementsBasedOnName(subMenu));
	}
}
