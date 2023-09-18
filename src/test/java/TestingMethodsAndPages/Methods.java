package TestingMethodsAndPages;

import static Wppool.AssignmentWP.BrowserSetup.getDriver;

import java.io.ByteArrayInputStream;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;

public class Methods {
	public WebElement getElement(By locator) {
		//import static Wppool.AssignmentWP.BrowserSetup.getDriver;
		return getDriver().findElement(locator);//driver = getDriver();
	}
	public void clickElement(By locator) {
		getElement(locator).click();
	}
	public void FieldValue(By locator, String text) {
		getElement(locator).sendKeys(text);
	}
	public void WaitElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public void Hover(By locator) {
		Actions action = new Actions(getDriver());
		//action.moveToElement(driver.findElement(locator)).perform();
		action.moveToElement(getElement(locator)).perform();;
	}
	public void DropDownSelectElement(By locator) {
		Select	select = new Select(getElement(locator));
		select.selectByIndex(0);
	}
	//for allure report
		public void takeScreenshot(String name) {
			Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES)));
		}
}
