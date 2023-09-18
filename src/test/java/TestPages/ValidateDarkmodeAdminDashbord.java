package TestPages;

import org.testng.annotations.Test;

import TestingMethodsAndPages.SetupSettings;
import TestingMethodsAndPages.WordPressLoginPortal;
import Wppool.AssignmentWP.BrowserSetup;

public class ValidateDarkmodeAdminDashbord extends BrowserSetup{
	WordPressLoginPortal login = new WordPressLoginPortal();
	SetupSettings validateDMode = new SetupSettings();
	@Test(description = "Secenario 4 & 5 = Validate WP DarkMode plugin Dashbord")
	public void ValidateDarkModeDashbord() throws InterruptedException{
		getDriver().get("http://localhost/wordpress-6.3.1/wordpress/wp-admin");
		Thread.sleep(2000);
		login.WordPressLogin();
		validateDMode.setUpSetting();
		login.Hover(login.SAKIF);
		login.clickElement(login.LOGOUT);
	}


}
