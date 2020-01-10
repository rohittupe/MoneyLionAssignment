package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;
import base.nativeActions.NativeActions;

public class ProductsInvestingPage extends Base {

	By projectionSliderMessage = By.xpath("//span[contains(.,'WE KNOW SMALL CHANGES')]"); //By.xpath("//span[contains(.,'Use the sliders to see projections about how much your money could grow over time.')]");
	By depositSlider = By.xpath("//div[contains(@class,'rc-slider-handle')][contains(@aria-valuemin,'25')]");
	By depositText = By.xpath("//div[contains(@class,'depositNumberText')]");
	By projectedValueOnChart = By.xpath("//div[text()='Projected value']/following-sibling::div[contains(text(), '$')]");
	By depositedValueOnChart = By.xpath("//div[text()='Deposited']/following-sibling::div[contains(text(), '$')]");
	By yearValues = By.xpath("//*[local-name()='foreignObject']//span[@class='ct-label ct-horizontal ct-end']");

	WebDriverWait wait;
	WebDriver localDriver;
	NativeActions ntvActions;

	int maxDepositSliderAmount = 500;
	int minDepositSliderAmount = 25;

	public ProductsInvestingPage(WebDriver driver) {
		localDriver = driver;
		wait = new WebDriverWait(localDriver, 10);
		ntvActions = new NativeActions(localDriver);
	}

	public void scrollToProjectionSlider() {
		ntvActions.performScrollIntoViewUsingJavascript(localDriver, projectionSliderMessage);
		ntvActions.waitForVisibilityOfWebElement(localDriver, projectionSliderMessage);
	}

	public void setDepositAmount(String depositAmountString) {
		int currentDepositAmount = 0;
		int depositAmount = 0;
		String deposit = String.valueOf(ntvActions.getTextOfWebElement(localDriver, depositText));
		if(deposit!=null && !deposit.equalsIgnoreCase("null"))
			currentDepositAmount = Integer.parseInt(ntvActions.getTextOfWebElement(localDriver, depositText).replaceAll("[^0-9.,]+", ""));
		if(depositAmountString!=null && !depositAmountString.isEmpty()) {
			depositAmount = Integer.parseInt(depositAmountString.replaceAll("[\\D]*", ""));
		}
		System.out.println("depositAmountString = " + depositAmountString);
		System.out.println("currentDepositAmount = " + currentDepositAmount);

		// move slider to the left
		if (currentDepositAmount > depositAmount) {
			do {
				ntvActions.waitForVisibilityOfWebElement(localDriver, depositText);
				new Actions(localDriver).clickAndHold(ntvActions.findWebElement(localDriver, depositSlider)).moveByOffset(-20, 0).release().build().perform();
				deposit = String.valueOf(ntvActions.getTextOfWebElement(localDriver, depositText));
				System.out.println("Deposit Text : " + deposit);
			}while (!deposit.equals(depositAmountString) && Integer.parseInt(deposit.replaceAll("[\\D]*", ""))<=maxDepositSliderAmount);
		}

		// move slider to the right
		if (currentDepositAmount < depositAmount) {
			do {
				ntvActions.waitForVisibilityOfWebElement(localDriver, depositText);
				new Actions(localDriver).clickAndHold(ntvActions.findWebElement(localDriver, depositSlider)).moveByOffset(20, 0).release().build().perform();
				deposit = String.valueOf(ntvActions.getTextOfWebElement(localDriver, depositText));
				System.out.println("Deposit Text : " + deposit);
			}while (!deposit.equals(depositAmountString) && Integer.parseInt(deposit.replaceAll("[\\D]*", ""))>=minDepositSliderAmount);
		}
	}

	public void setYearOnChart(int yearToSelect) {
		List<WebElement> yearValuesList = ntvActions.findWebElements(localDriver, yearValues);
		if(yearValuesList.size()>0)
			ntvActions.waitForVisibilityOfWebElement(localDriver, yearValuesList.get(0));
		for (int index=0; index < yearToSelect; index++)
			new Actions(localDriver).dragAndDropBy(yearValuesList.get(index), 15, 0).perform();
	}

	public String getProjectedValue() {
		String value = ntvActions.getAttributeValueOfWebElement(localDriver, projectedValueOnChart, "textContent");
		System.out.println("ProjectedValueOnChart = " + value);
		return value;
	}

	public String getDepositedValue() {
		String value = ntvActions.getAttributeValueOfWebElement(localDriver, depositedValueOnChart, "textContent");
		System.out.println("DepositedValueOnChart = " + value);
		return value;
	}

}
