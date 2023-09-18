package TestingMethodsAndPages;

import org.openqa.selenium.By;

public class WordPressLoginPortal extends Methods{
	public By USERNAME = By.xpath("//input[@id='user_login']");
	public By PASSWORD = By.xpath("//input[@id='user_pass']");
	public By LOGINBUTTON = By.xpath("//input[@id='wp-submit']");
	public By DASHBORD = By.xpath("//h1[normalize-space()='Dashboard']");
	public By SAKIF = By.xpath("(//span[@class='display-name'][normalize-space()='Sakif'])[1]");
	public By LOGOUT =By.xpath("//a[@class='ab-item'][normalize-space()='Log Out']");
	
	public void WordPressLogin() throws InterruptedException{
		FieldValue(USERNAME, "Sakif");
		Thread.sleep(2000);
		FieldValue(PASSWORD, "sakif@006");
		Thread.sleep(2000);
		clickElement(LOGINBUTTON);
		WaitElementVisible(DASHBORD);
		Thread.sleep(2000);
	}

}
