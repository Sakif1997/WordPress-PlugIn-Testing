package TestPages;

import org.testng.annotations.Test;

import TestingMethodsAndPages.SwitchSettings6to12;
import TestingMethodsAndPages.WordPressLoginPortal;
import Wppool.AssignmentWP.BrowserSetup;

public class Switch_Accessibility_AnimationSettings extends BrowserSetup{
	WordPressLoginPortal login = new WordPressLoginPortal();
	SwitchSettings6to12 settingsConfig = new SwitchSettings6to12();
	@Test(description = "Secenario 6 to 11")
	public void AllSettingsSecenario6to12() throws InterruptedException{
		getDriver().get("http://localhost/wordpress-6.3.1/wordpress/wp-admin");
		Thread.sleep(2000);
		login.WordPressLogin();
		settingsConfig.switchsettingsControl();
		login.Hover(login.SAKIF);
		login.clickElement(login.LOGOUT);
		
	}


}
