package TestingMethodsAndPages;

import org.openqa.selenium.By;
//
public class SetupSettings extends Methods {
	public By DARKMODESETTINGSMENU = By.xpath("//div[normalize-space()='WP Dark Mode']");
	public By WaitDMsettingsVisible = By.xpath("//a[contains(text(),'Settings')]");
	public By WaitSettingPage =By.xpath("//h2[normalize-space()='WP Dark Mode Settings']");
	public By EnableBackendDarkMode = By.xpath("(//div[@class='wp-dark-mode-ignore'])[2]");
	public By DASHBORD = By.xpath("//div[normalize-space()='Dashboard']");
	public By WaitDashbord = By.xpath("//h1[normalize-space()='Dashboard']");
	public By VALIDATEMODEBUTTON = By.xpath("//p[@class='dark wp-dark-mode-ignore']");
	public void setUpSetting() throws InterruptedException{
		Hover(DARKMODESETTINGSMENU);
		WaitElementVisible(WaitDMsettingsVisible);
		Thread.sleep(2000);
		clickElement(WaitDMsettingsVisible);
		WaitElementVisible(WaitSettingPage);
		clickElement(EnableBackendDarkMode);
		Thread.sleep(2000);
		clickElement(DASHBORD);
		WaitElementVisible(WaitDashbord);
		clickElement(VALIDATEMODEBUTTON);
		//screenshot
		Thread.sleep(2000);
		clickElement(VALIDATEMODEBUTTON);
		//screenshot
		Thread.sleep(2000);
	}

}
