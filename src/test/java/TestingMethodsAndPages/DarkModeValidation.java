package TestingMethodsAndPages;

import org.openqa.selenium.By;

public class DarkModeValidation extends Methods {
	public By VISITSITEWPPOOL = By.xpath("//a[normalize-space()='WPPOOL']");
	public By SITEPAGEVISIT = By.xpath("(//p[@class='wp-block-site-title'])[1]");
	public By DARKMODESWITCH = By.xpath("//label[@for='wp-dark-mode-switch']");
	public void BackendDarkmode() throws InterruptedException{
		clickElement(VISITSITEWPPOOL);
		WaitElementVisible(SITEPAGEVISIT);
		clickElement(DARKMODESWITCH);
		Thread.sleep(2000);
		clickElement(DARKMODESWITCH);
		Thread.sleep(2000);
		clickElement(DARKMODESWITCH);
		Thread.sleep(2000);	
	}

}
