package TestPages;

import org.testng.annotations.Test;

import TestingMethodsAndPages.PluginInstallation;
import TestingMethodsAndPages.WordPressLoginPortal;
import Wppool.AssignmentWP.BrowserSetup;

public class PlugInInstallDirectoryPage extends BrowserSetup {
	WordPressLoginPortal login = new WordPressLoginPortal();
	PluginInstallation DarkModePlugIn = new PluginInstallation();
	@Test(description = "Scenario 2 & 3 = WP Dark Mode Installation")
	public void pluginInstall() throws InterruptedException{
		getDriver().get("http://localhost/wordpress-6.3.1/wordpress/wp-admin");
		Thread.sleep(2000);
		login.WordPressLogin();
		DarkModePlugIn.PluginInstal();
		Thread.sleep(3000);
		login.Hover(login.SAKIF);
		login.clickElement(login.LOGOUT);
		
	}


}
