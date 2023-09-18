package TestingMethodsAndPages;

import org.openqa.selenium.By;

public class PluginInstallation extends Methods {
	public By PLUGIN = By.xpath("//div[@class='wp-menu-image dashicons-before dashicons-admin-plugins']");
	public By ADDNEWPLUGIN =By.xpath("//a[@href='plugin-install.php']");
	public By WaitToApperAddpluginsPage = By.xpath("//h1[normalize-space()='Add Plugins']");
	public By SEARCHPLUGIN = By.xpath("//input[@id='search-plugins']");
	public By WaitToVisibleDesirePlugin = By.xpath("//a[contains(text(),'WP Dark Mode – Best Dark Mode Plugin for WordPress')]");
	public By INSTALLPLUGINBUTTON = By.xpath("//a[@aria-label='Install WP Dark Mode – Best Dark Mode Plugin for WordPress with Social Sharing 4.2.2 now']");
	public By DARKMODEACTIVATE = By.xpath("//a[normalize-space()='Activate']");
	public By WaitToVisibleAfterDarkModeActivationSettingsPage =By.xpath("//h2[normalize-space()='WP Dark Mode Settings']");
	public void PluginInstal() throws InterruptedException{
		Hover(PLUGIN);
		WaitElementVisible(ADDNEWPLUGIN);
		clickElement(ADDNEWPLUGIN);
		WaitElementVisible(WaitToApperAddpluginsPage);
		Thread.sleep(2000);
		FieldValue(SEARCHPLUGIN, "WP Dark Mode");
		WaitElementVisible(WaitToVisibleDesirePlugin);
		clickElement(INSTALLPLUGINBUTTON);
		takeScreenshot("Plugin Install");
		Thread.sleep(2000);
		WaitElementVisible(DARKMODEACTIVATE);
		clickElement(DARKMODEACTIVATE);
		WaitElementVisible(WaitToVisibleAfterDarkModeActivationSettingsPage);
	}

}
