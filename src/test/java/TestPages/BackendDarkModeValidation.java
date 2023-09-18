package TestPages;

import org.testng.annotations.Test;

import TestingMethodsAndPages.DarkModeValidation;
import TestingMethodsAndPages.WordPressLoginPortal;
import Wppool.AssignmentWP.BrowserSetup;

public class BackendDarkModeValidation extends BrowserSetup{
	WordPressLoginPortal login = new WordPressLoginPortal();
	DarkModeValidation DMV = new DarkModeValidation();
	
	@Test(description = "Dark Mode Validation")
	public void DarkModeValidationBackEnd() throws InterruptedException{
		getDriver().get("http://localhost/wordpress-6.3.1/wordpress/wp-admin");
		Thread.sleep(2000);
		login.WordPressLogin();
		DMV.BackendDarkmode();
		DMV.takeScreenshot("Dark Mode Switch Working");
		login.Hover(login.SAKIF);
		login.clickElement(login.LOGOUT);
	}


}
