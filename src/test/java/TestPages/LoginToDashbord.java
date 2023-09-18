package TestPages;

import org.testng.annotations.Test;

import TestingMethodsAndPages.WordPressLoginPortal;
import Wppool.AssignmentWP.BrowserSetup;

public class LoginToDashbord extends BrowserSetup {
	WordPressLoginPortal login = new WordPressLoginPortal();
	@Test(description = "Secenario 1 = Before WP DarkMode plugin Dashbord looks like")
	public void WPlogin() throws InterruptedException{
		getDriver().get("http://localhost/wordpress-6.3.1/wordpress/wp-admin");
		Thread.sleep(2000);
		login.WordPressLogin();
		login.takeScreenshot("Dashbord Before plugin");
		login.Hover(login.SAKIF);
		login.clickElement(login.LOGOUT);
	}
}
