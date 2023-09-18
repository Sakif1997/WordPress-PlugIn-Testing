package TestingMethodsAndPages;

import org.openqa.selenium.By;

public class SwitchSettings6to12 extends Methods{
	public By DMSETTINGSMENU = By.xpath("//div[normalize-space()='WP Dark Mode']");
	public By WaitVisibleDMsettings = By.xpath("//a[contains(text(),'Settings')]");
	public By SWITCHSETTINGS =By.xpath("(//span[contains(text(),'Switch Settings')])[1]");
	public By WaitSwitchSettingPage = By.xpath("(//span[contains(text(),'Switch Settings')])[2]");
	public By FloatingSwitchStyle = By.xpath("(//img[@class='image-choose-img'])[16]");
	public By SwitchStyleCustomButton = By.xpath("//span[normalize-space()='Custom']");
	//public By SwitchTo220 =By.xpath("(//div[@class='ui-slider-range ui-corner-all ui-widget-header ui-slider-range-min'])[4]");
	public By SELECT = By.xpath("(//select[@id='wp_dark_mode_switch[switcher_position]'])[1]");
	public By SAVEBUTTON =By.xpath("(//input[@id='save_settings'])[4]");
	public By AccessibleSettings =By.xpath("(//span[contains(text(),'Accessibility Settings')])[1]");
	public By AccesibleSettingPage = By.xpath("(//h2)[10]");
	public By DisableKeybordShortcutButton =By.xpath("(//div[@class='wp-dark-mode-ignore'])[23]");
	public By AccessiblePageSaveButton = By.xpath("(//input[@id='save_settings'])[8]");
	public By AnimationSettings =By.xpath("(//span[contains(text(),'Animation')])[1]");
	public By AnimationPageVisible =By.xpath("//form[@method='post']//span[contains(text(),'Animation')]");
	public By EnableDarkMode =By.xpath("(//div[@class='wp-dark-mode-ignore'])[30]");
	public By WaitAnimationEffect = By.xpath("//label[normalize-space()='Animation Effect']");
	public By AnimationEffectSelect = By.xpath("//select[contains(@id,'wp_dark_mode_animation')]");
	public By AnimationSaveButton =By.xpath("(//input[@id='save_settings'])[13]");
	public void switchsettingsControl() throws InterruptedException{
		Hover(DMSETTINGSMENU);
		WaitElementVisible(WaitVisibleDMsettings);
		clickElement(WaitVisibleDMsettings);
		Thread.sleep(2000);
		WaitElementVisible(SWITCHSETTINGS);
		takeScreenshot("Before Switch Settings");
		clickElement(SWITCHSETTINGS);
		clickElement(FloatingSwitchStyle);
		Thread.sleep(1000);
		clickElement(SwitchStyleCustomButton);
		Thread.sleep(3000);
		//clickElement(SwitchTo220);
		//Thread.sleep(1000);
		DropDownSelectElement(SELECT);
		Thread.sleep(1000);
		takeScreenshot("After Switch Settings according to the instruction");
		clickElement(SAVEBUTTON);
		Thread.sleep(2000);
		clickElement(AccessibleSettings);
		WaitElementVisible(AccesibleSettingPage);
		clickElement(DisableKeybordShortcutButton);
		Thread.sleep(4000);
		takeScreenshot("Accessible Setting according to the instruction");
		clickElement(AccessiblePageSaveButton);
		clickElement(AnimationSettings);
		WaitElementVisible(AnimationPageVisible);
		Thread.sleep(2000);
		clickElement(EnableDarkMode);
		Thread.sleep(4000);
		WaitElementVisible(WaitAnimationEffect);
		DropDownSelectElement(AnimationEffectSelect);
		Thread.sleep(4000);
		takeScreenshot("Animation setting according to the instruction");
		clickElement(AnimationSaveButton);
	}

}
