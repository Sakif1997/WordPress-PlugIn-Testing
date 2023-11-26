
# WordPress PlugIn testing

WordPress plugins are designed to enhance the functionality and performance of your WordPress website. We can run an automation test to see plug-in installation,  activation, and configuration of the plugin's settings to customize the appearance and behavior of the plug-in to suit your website's design and preferences.

Plugin info: https://wordpress.org/plugins/wp-dark-mode/

## Target/goals
following scenarios, which will be replicated
1. Log in to your WordPress site.
2. Check whether the “WP Dark Mode” Plugin is Active or not.
3. If Active, navigate to the WP Dark Mode & continue. Otherwise, Install the Plugin and Activate it.
4. Enable Backend Darkmode from Settings -> General Settings.
5. Validate whether the dark mode is working or not on the Admin Dashboard.
6. Navigate to the WP Dark Mode.
7. From Settings -> Switch Settings - Change the “Floating Switch Style” from the default selections (Select any one from the available options, except the default selected one).
8. From Settings -> Switch Settings - Select Custom Switch size & Scale it to 220.
9. From Settings -> Switch Settings - Change the Floating Switch Position (Left Bottom).
10. Disable Keyboard Shortcut from the Accessibility Settings.
11. From Settings -> Animation - Enable “Darkmode Toggle Animation” & change the “Animation Effect” from the default selections (Select any one from the available options, except the default selected one).
12.  Validate whether the Darkmode is working or not from the Frontend.

## Prerequisites
Install Xampp: https://www.apachefriends.org/download.html

Open Xampp to start Apache and MySQL

Download Wordpress file: https://wordpress.org/download/

Create the database(watch to the .env.example file): 

My file directory
[DB_HOST = localhost
DB_PORT = 3306
DB_DATABASE = assignmentwp
DB_USERNAME =root
DB_PASSWORD= ]



## Test workflow

### Test file information
The following instructions will help you navigate those testing pages. We will create some packages. At the package level, there is a list of classes where you can create methods, use methods for particular pages, and run and test the testing pages separately

1. Set Environment
i) pom.xml [dependencies set]
ii) BrowserSetup[create separate package ]

2. Page Object Model: create methods, using methods for separate page and create test cases of those pages
i) Methods[package name:TestingMethodsAndPages]
ii) Page objects[package name:TestingMethodsAndPages]
iii) TestCases [package name: Testpages ]

3. Create Allure report 
i) pom.xml [dependencies set for allure report]
ii) Testng.xml [to run all test file togather]

Package Visualization:  
![PackagesAndClass](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/5fbc5c13-c8d7-4c3c-bd12-0490bacf9031)



##  Set Environment
Create a Maven Project
Set pom.xml file 
pom.xml file Code:
Set Under Dependencies
```ruby
  <dependencies>
      <!-- https://mvnrepository.com/artifact/org.testng/testng -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.6.1</version>
    <scope>test</scope>
</dependency>
<!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.6.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.3.0</version>
</dependency>
  </dependencies>
</project>
```

Create Some class under Packages 
One of the package will hold BorwserSetup in which we run the automation Code

Inside BorwserSetup Class:

It will hold three Driver(Chrome, Firefox, and Edge), use according to your preferences 
I prefer to use the Edge Driver (Edge Browser) to run my code.
```ruby
package Wppool.AssignmentWP;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserSetup {
	private static String BrowserName = System.getProperty("browser", "Edge");
	private static final ThreadLocal<WebDriver> DRIVER_LOCAL = new ThreadLocal<>();
	public static WebDriver getDriver() {
		return DRIVER_LOCAL.get();
	}
	public static void setDriver(WebDriver driver) {
		BrowserSetup.DRIVER_LOCAL.set(driver);
	}
	public static WebDriver getBrowser(String BrowserName) {
		switch (BrowserName.toLowerCase()) {
		case "chrome":
			ChromeOptions option1 = new ChromeOptions();
			option1.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			return new ChromeDriver(option1);
		case "edge":
			EdgeOptions option2 = new EdgeOptions();
			option2.addArguments("--remote-allow-origins=*");
			WebDriverManager.edgedriver().setup();
			return new EdgeDriver(option2);
		case "firefox":
			FirefoxOptions option3 = new FirefoxOptions();
			option3.addArguments("--remote-allow-origins=*");
			WebDriverManager.firefoxdriver().setup();
			return new FirefoxDriver(option3);
		default:
			throw new RuntimeException("Browser Not found");
		}
	}
	@BeforeSuite
	public static synchronized void setBrowser() {
		WebDriver webDriver = getBrowser(BrowserName);
		webDriver.manage().window().maximize();
		setDriver(webDriver);
	}
	@AfterSuite
	public static synchronized void quitBrowser() {
		getDriver().quit();
	}
}

```

## Page Object model
### Methods
Create a package which include classes like method and other testing Page

method class includes methods of getElement, click, hover, field fillup, visible wait element, click wait element and take screenshot for allure report.

```ruby
package TestingMethodsAndPages;

import static Wppool.AssignmentWP.BrowserSetup.getDriver;
import java.io.ByteArrayInputStream;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Allure;

public class Methods {
	public WebElement getElement(By locator) {
		//import static Wppool.AssignmentWP.BrowserSetup.getDriver;
		return getDriver().findElement(locator//driver = getDriver(
	}
	public void clickElement(By locator) {
		getElement(locator).click(
	}
	public void FieldValue(By locator, String text) {
		getElement(locator).sendKeys(text
	}
	public void WaitElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20)
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)
	}
	public void Hover(By locator) {
		Actions action = new Actions(getDriver()
		//action.moveToElement(driver.findElement(locator)).perform(
		action.moveToElement(getElement(locator)).perform(;
	}
	public void DropDownSelectElement(By locator) {
		Select	select = new Select(getElement(locator)
		select.selectByIndex(0
	}
	//for allure report
		public void takeScreenshot(String name) {
			Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES))
		}
}
```
### Scenario Replication Classes
Login to Dashbord
1. Log in to your WordPress site.
2. Check whether the “WP Dark Mode” Plugin is Active or not.
Login to Dashbord page objects and there intented xpaths
Overview of the sleecing path of the login page image:  
![login fields paths](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/472d4c39-0201-473e-858e-a0dfe5d5a4e2)

Fields xpath:
Username or Email Address:"//input[@id='user_login']"
Password:"//input[@id='user_pass']"
Log In button:"//input[@id='wp-submit']"
Dashbord: "//h1[normalize-space()='Dashboard']"
By those xpaths, make methods to fill up login field values, and after login, it will wait for the dashbord to appear.
Video Guidence: https://drive.google.com/file/d/1Mm98bgFtf1igQCeTzpkEtHvdBjwfYPbk/view?usp=drive_link

Login to Dashbord objects and methods
```ruby
package TestingMethodsAndPages;

import org.openqa.selenium.By;

public class WordPressLoginPortal extends Methods{
	public By USERNAME = "//input[@id='user_login']"
	public By PASSWORD = "//input[@id='user_pass']"
	public By LOGINBUTTON = "//input[@id='wp-submit']"
	public By DASHBORD = "//h1[normalize-space()='Dashboard']"
	public By SAKIF = "(//span[@class='display-name'][normalize-space()='Sakif'])[1]"
	public By LOGOUT ="//a[@class='ab-item'][normalize-space()='Log Out']"
	
	public void WordPressLogin() throws InterruptedException{
		FieldValue(USERNAME, "Sakif"
		Thread.sleep(2000
		FieldValue(PASSWORD, "sakif@006"
		Thread.sleep(2000
		clickElement(LOGINBUTTON
		WaitElementVisible(DASHBORD
		Thread.sleep(2000
	}
}
```
Login to Dashbord testSuite
```ruby
package TestPages;

import org.testng.annotations.Test;

import TestingMethodsAndPages.WordPressLoginPortal;
import Wppool.AssignmentWP.BrowserSetup;

public class LoginToDashbord extends BrowserSetup {
	WordPressLoginPortal login = new WordPressLoginPortal(
	@Test(description = "Secenario 1 = Before WP DarkMode plugin Dashbord looks like")
	public void WPlogin() throws InterruptedException{
		getDriver().get("http://localhost/wordpress-6.3.1/wordpress/wp-admin"
		Thread.sleep(2000
		login.WordPressLogin(
		login.takeScreenshot("Dashbord Before plugin"
		login.Hover(login.SAKIF
		login.clickElement(login.LOGOUT
	}
}
```
Replication of three, four, and five scenarios(3,4, and 5):
3. If Active, navigate to the WP Dark Mode & continue. Otherwise, Install the Plugin and Activate it.
4. Enable Backend Darkmode from Settings -> General Settings.
5. Validate whether the Darkmode is working or not on the Admin Dashboard.
To replicate those scenario:
Fields xpath:
Overview of the sleecing path of the login page image:  
![inspectInstall](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/b0025c3d-7a7e-4b84-b111-e0b99e0c850e)

PlugIn menu option = "//div[@class='wp-menu-image dashicons-before dashicons-admin-plugins']"
Add new plugin option ="//a[@href='plugin-install.php']"
Add plugins Page = "//h1[normalize-space()='Add Plugins']"
Search pluginfields = "//input[@id='search-plugins']"
Dark mode Desire Plugin = "//a[contains(text(),'WP Dark Mode – Best Dark Mode Plugin for WordPress')]"
Install plugin button = "//a[@aria-label='Install WP Dark Mode – Best Dark Mode Plugin for WordPress with Social Sharing 4.2.2 now']"
ACTIVATE button= "//a[normalize-space()='Activate']"

After login to dashbord go to plugin option in the menu bar and hover to wait add plugins option is visible, then click it and wait for track the Add plugins page visible then fill up search field to search WP Dark Mode when the desire plug in option visible then install it and wait for activation button appears and the activate it.
Videp guidence:  
![inspectInstall](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/b0025c3d-7a7e-4b84-b111-e0b99e0c850e)

Plugin install to activation
```ruby
package TestingMethodsAndPages;

import org.openqa.selenium.By;
//
public class SetupSettings extends Methods {
	public By DARKMODESETTINGSMENU = "//div[normalize-space()='WP Dark Mode']"
	public By WaitDMsettingsVisible = "//a[contains(text(),'Settings')]"
	public By WaitSettingPage ="//h2[normalize-space()='WP Dark Mode Settings']"
	public By EnableBackendDarkMode = "(//div[@class='wp-dark-mode-ignore'])[2]"
	public By DASHBORD = "//div[normalize-space()='Dashboard']"
	public By WaitDashbord = "//h1[normalize-space()='Dashboard']"
	public By VALIDATEMODEBUTTON = "//p[@class='dark wp-dark-mode-ignore']"
	public void setUpSetting() throws InterruptedException{
		Hover(DARKMODESETTINGSMENU
		WaitElementVisible(WaitDMsettingsVisible
		Thread.sleep(2000
		clickElement(WaitDMsettingsVisible
		WaitElementVisible(WaitSettingPage
		clickElement(EnableBackendDarkMode
		Thread.sleep(2000
		clickElement(DASHBORD
		WaitElementVisible(WaitDashbord
		clickElement(VALIDATEMODEBUTTON
		//screenshot
		Thread.sleep(2000
		clickElement(VALIDATEMODEBUTTON
		//screenshot
		Thread.sleep(2000
	}

}
```
Plugin install to activation TestSuite[Run as TestNG]
```ruby
package TestPages;

import org.testng.annotations.Test;

import TestingMethodsAndPages.PluginInstallation;
import TestingMethodsAndPages.WordPressLoginPortal;
import Wppool.AssignmentWP.BrowserSetup;

public class PlugInInstallDirectoryPage extends BrowserSetup {
	WordPressLoginPortal login = new WordPressLoginPortal(
	PluginInstallation DarkModePlugIn = new PluginInstallation(
	@Test(description = "Scenario 2 & 3 = WP Dark Mode Installation")
	public void pluginInstall() throws InterruptedException{
		getDriver().get("http://localhost/wordpress-6.3.1/wordpress/wp-admin"
		Thread.sleep(2000
		login.WordPressLogin(
		DarkModePlugIn.PluginInstal(
		Thread.sleep(3000
		login.Hover(login.SAKIF
		login.clickElement(login.LOGOUT
		
	}

}
```
Secenario 5. Darkmode activation validation
Dedicated xpaths:
Dark mode settings menu = "//div[normalize-space()='WP Dark Mode']"
settings page option= "//a[contains(text(),'Settings')]"
Setting Page ="//h2[normalize-space()='WP Dark Mode Settings']") 
Enable option Backend DarkMode = "(//div[@class='wp-dark-mode-ignore'])[2]DASHBORD = "//div[normalize-space()='Dashboard']"
Dark Mode button = "//p[@class='dark wp-dark-mode-ignore']"

Dark mode plugin settings and options appear image:  
![Wp Dark mode on dashbord](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/d246d1c3-5538-44bc-994e-d376a4754cfd)
Validate whether the Darkmode is working or not on the Admin Dashboard by using dedicated xpaths.

Validate Darkmode methods
```ruby
package TestingMethodsAndPages;
import org.openqa.selenium.By;
public class SetupSettings extends Methods {
	public By DARKMODESETTINGSMENU = "//div[normalize-space()='WP Dark Mode']"
	public By WaitDMsettingsVisible = "//a[contains(text(),'Settings')]"
	public By WaitSettingPage ="//h2[normalize-space()='WP Dark Mode Settings']"
	public By EnableBackendDarkMode = "(//div[@class='wp-dark-mode-ignore'])[2]"
	public By DASHBORD = "//div[normalize-space()='Dashboard']"
	public By WaitDashbord = "//h1[normalize-space()='Dashboard']"
	public By VALIDATEMODEBUTTON = "//p[@class='dark wp-dark-mode-ignore']"
	public void setUpSetting() throws InterruptedException{
		Hover(DARKMODESETTINGSMENU
		WaitElementVisible(WaitDMsettingsVisible
		Thread.sleep(2000
		clickElement(WaitDMsettingsVisible
		WaitElementVisible(WaitSettingPage
		clickElement(EnableBackendDarkMode
		Thread.sleep(2000
		clickElement(DASHBORD
		WaitElementVisible(WaitDashbord
		clickElement(VALIDATEMODEBUTTON
		//screenshot
		Thread.sleep(2000
		clickElement(VALIDATEMODEBUTTON
		//screenshot
		Thread.sleep(2000
	}

}
```
Validate Darkmode TestSuite[Run as TestNG]
```ruby
package TestPages;

import org.testng.annotations.Test;

import TestingMethodsAndPages.SetupSettings;
import TestingMethodsAndPages.WordPressLoginPortal;
import Wppool.AssignmentWP.BrowserSetup;

public class ValidateDarkmodeAdminDashbord extends BrowserSetup{
	WordPressLoginPortal login = new WordPressLoginPortal(
	SetupSettings validateDMode = new SetupSettings(
	@Test(description = "Secenario 4 & 5 = Validate WP DarkMode plugin Dashbord")
	public void ValidateDarkModeDashbord() throws InterruptedException{
		getDriver().get("http://localhost/wordpress-6.3.1/wordpress/wp-admin"
		Thread.sleep(2000
		login.WordPressLogin(
		validateDMode.setUpSetting(
		login.Hover(login.SAKIF
		login.clickElement(login.LOGOUT
	}
}
```
Now secenario 6 to 11:
6. Navigate to the WP Dark Mode.
7. From Settings -> Switch Settings - Change the “Floating Switch Style” from the default selections (Select any one from the available options, except the default selected one).
8. From Settings -> Switch Settings - Select Custom Switch size & Scale it to 220.
9. From Settings -> Switch Settings - Change the Floating Switch Position (Left Bottom).
10. Disable Keyboard Shortcut from the Accessibility Settings.
11. From Settings -> Animation - Enable “Darkmode Toggle Animation” & change the “Animation Effect” from the default selections (Select any one from the available options, except the default selected one).
To execute those scenarios, the following elements xapths:
Dark Mode settings = "//div[normalize-space()='WP Dark Mode']"
DM settings menu bar = "//a[contains(text(),'Settings')]"
SWITCH SETTINGS option ="(//span[contains(text(),'Switch Settings')])[1]"
Switch Setting Page = "(//span[contains(text(),'Switch Settings')])[2]"
Floating Switch Style = "(//img[@class='image-choose-img'])[16]"
Switch Style Custom Button = "//span[normalize-space()='Custom']"
Switch To 220 ="(//div[@class='ui-slider-range ui-corner-all ui-widget-header ui-slider-range-min'])[4]"
SELECT = "(//select[@id='wp_dark_mode_switch[switcher_position]'])[1]"
SAVE BUTTON ="(//input[@id='save_settings'])[4]"
Accessible Settings option ="(//span[contains(text(),'Accessibility Settings')])[1]"
Disable Keybord Shortcut Button ="(//div[@class='wp-dark-mode-ignore'])[23]"
Accessible Page Save Button = "(//input[@id='save_settings'])[8]"
Animation Settings ="(//span[contains(text(),'Animation')])[1]"
Animation Page visible ="//form[@method='post']//span[contains(text(),'Animation')]"
Enable DarkMode option="(//div[@class='wp-dark-mode-ignore'])[30]"
Animation Effect option  = "//label[normalize-space()='Animation Effect']"
Animation Effect Select option = "//select[contains(@id,'wp_dark_mode_animation')]"
Animation Save Button ="(//input[@id='save_settings'])[13]"

Select xpath overview:  
![Switch settings](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/97b9bbc1-47a2-4efe-a7a1-05fbdccb11ce)

Execution Video:
https://drive.google.com/file/d/1Am36SQ8kiJrjKOQI1z2C_MX7r-IF2NCm/view?usp=drive_link

Settings operation methods following the 6 to 11 scenario:
```ruby
package TestingMethodsAndPages;
import org.openqa.selenium.By;

public class SwitchSettings6to12 extends Methods{
	public By DMSETTINGSMENU = "//div[normalize-space()='WP Dark Mode']"
	public By WaitVisibleDMsettings = "//a[contains(text(),'Settings')]"
	public By SWITCHSETTINGS ="(//span[contains(text(),'Switch Settings')])[1]"
	public By WaitSwitchSettingPage = "(//span[contains(text(),'Switch Settings')])[2]"
	public By FloatingSwitchStyle = "(//img[@class='image-choose-img'])[16]"
	public By SwitchStyleCustomButton = "//span[normalize-space()='Custom']"
	//public By SwitchTo220 ="(//div[@class='ui-slider-range ui-corner-all ui-widget-header ui-slider-range-min'])[4]"
	public By SELECT = "(//select[@id='wp_dark_mode_switch[switcher_position]'])[1]"
	public By SAVEBUTTON ="(//input[@id='save_settings'])[4]"
	public By AccessibleSettings ="(//span[contains(text(),'Accessibility Settings')])[1]"
	public By AccesibleSettingPage = "(//h2)[10]"
	public By DisableKeybordShortcutButton ="(//div[@class='wp-dark-mode-ignore'])[23]"
	public By AccessiblePageSaveButton = "(//input[@id='save_settings'])[8]"
	public By AnimationSettings ="(//span[contains(text(),'Animation')])[1]"
	public By AnimationPageVisible ="//form[@method='post']//span[contains(text(),'Animation')]"
	public By EnableDarkMode ="(//div[@class='wp-dark-mode-ignore'])[30]"
	public By WaitAnimationEffect = "//label[normalize-space()='Animation Effect']"
	public By AnimationEffectSelect = "//select[contains(@id,'wp_dark_mode_animation')]"
	public By AnimationSaveButton ="(//input[@id='save_settings'])[13]"
	public void switchsettingsControl() throws InterruptedException{
		Hover(DMSETTINGSMENU
		WaitElementVisible(WaitVisibleDMsettings
		clickElement(WaitVisibleDMsettings
		Thread.sleep(2000
		WaitElementVisible(SWITCHSETTINGS
		takeScreenshot("Before Switch Settings"
		clickElement(SWITCHSETTINGS
		clickElement(FloatingSwitchStyle
		Thread.sleep(1000
		clickElement(SwitchStyleCustomButton
		Thread.sleep(3000
		//clickElement(SwitchTo220
		//Thread.sleep(1000
		DropDownSelectElement(SELECT
		Thread.sleep(1000
		takeScreenshot("After Switch Settings according to the instruction"
		clickElement(SAVEBUTTON
		Thread.sleep(2000
		clickElement(AccessibleSettings
		WaitElementVisible(AccesibleSettingPage
		clickElement(DisableKeybordShortcutButton
		Thread.sleep(4000
		takeScreenshot("Accessible Setting according to the instruction"
		clickElement(AccessiblePageSaveButton
		clickElement(AnimationSettings
		WaitElementVisible(AnimationPageVisible
		Thread.sleep(2000
		clickElement(EnableDarkMode
		Thread.sleep(4000
		WaitElementVisible(WaitAnimationEffect
		DropDownSelectElement(AnimationEffectSelect
		Thread.sleep(4000
		takeScreenshot("Animation setting according to the instruction"
		clickElement(AnimationSaveButton
	}
}
```

testSuite run:
```ruby
package TestPages;

import org.testng.annotations.Test;
import TestingMethodsAndPages.SwitchSettings6to12;
import TestingMethodsAndPages.WordPressLoginPortal;
import Wppool.AssignmentWP.BrowserSetup;

public class Switch_Accessibility_AnimationSettings extends BrowserSetup{
	WordPressLoginPortal login = new WordPressLoginPortal(
	SwitchSettings6to12 settingsConfig = new SwitchSettings6to12(
	@Test(description = "Secenario 6 to 11")
	public void AllSettingsSecenario6to12() throws InterruptedException{
		getDriver().get("http://localhost/wordpress-6.3.1/wordpress/wp-admin"
		Thread.sleep(2000
		login.WordPressLogin(
		settingsConfig.switchsettingsControl(
		login.Hover(login.SAKIF
		login.clickElement(login.LOGOUT
		
	}
}
```

Scenario 12: Validate whether the Darkmode is working or not from the Frontend.
Dedicated Xpath: 
VISIT SITE WPPOOL = "//a[normalize-space()='WPPOOL']"
SITE PAGE VISIT = "(//p[@class='wp-block-site-title'])[1]"
DARKMODE SWITCH = "//label[@for='wp-dark-mode-switch']"


To validate whether darkmode is working or not, check the visit site by xpath, then when the site page appears, use the wait element to see whether the desired site page appears or not. When it appears, inspect the visible darkmode switch, and by clicking the switch, it will validate the changes executed by that switch, which leads to darkmode validation.

Darkmode Switch Validation image:  
![12](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/555b3290-60bc-4fa5-9d76-677bcea81075)

Scenario 12 Execution video:
https://drive.google.com/file/d/1xsKYCgyvGtaSixTuGYHdVJcKod1u1Xbl/view?usp=drive_link

Dark mode working objects
```ruby
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

```
Dark mode validation testSuite[Run as TestNG]
```ruby
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
```
## Automation of Scenario 1 to 12 Togather

Execution video for reference:
https://drive.google.com/file/d/1s9aqVHH1KHOfTLRNIgbcT02dc4eeuP4F/view?usp=drive_link

To run the whole test scenario togather:
By convert the whole test run files to TestNG
-instruction image   
![cnvt](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/c4c7161e-1404-4e97-ac12-e3f657f2d92c)
-After clicking Convert to TestNG  click on finish option  
![Finish option](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/80f56833-9c24-4888-843d-4d5e7cecba26)

-Now you can ovserve a Testng.xml file under pom.xml file and run that file to see whole testing procedure togather  
![testngxmlfile](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/37ed2a6f-789c-41d5-b853-e9ce3cc0161a)


## Allure Report Creation

To create an allure report, 
first set dependency in the pom.xml file.
<dependency>
		<groupId>io.qameta.allure</groupId>
		<artifactId>allure-testng</artifactId>
		<version>2.19.0</version>
</dependency>
2. then run the testing.xml file 
3. then refresh the whole package and see a "allure-results" file created under Maven Dependencies
-before refresh  
![beforeallure](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/4420d77c-b9ab-4350-b7d8-d6fd85cd686a)

-after runing the testng.xml file and refresh the whole package allure reasult appear  
![allure result](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/b7056999-6690-43eb-b031-3113831ac649)


4. To get allure report open the whole package terminal
5. then write in terminal to clean previous files> ""allure generate ./allure-result --clean""
6. then write in terminal to create allure report> ""allure open ./allure-report""
7. terminal gives us http to show us an allure report file directory
Terminal image(5,6,7):
![allure generarte](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/17defcb2-60d4-43d7-9b08-5774a75775bd)

8. Create some methods for allure report (like allure ScreenShot) which is added already
9. method add:
```ruby
public void takeScreenshot(String name) {
		Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES)));
	}
```
### Allure report file
file link: http://192.168.0.4:58373/
File generation instruction video: https://drive.google.com/file/d/1NXQ0IShmoAQHU3SiEziYfoiopfQ3AKXX/view?usp=drive_link

report overview:  
![Report overview](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/2347362b-7323-4864-8e95-b75c1c67543c)


Login to Dashbord Page Report:  
![login to dashbord report](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/5d951251-5d11-45b2-b0ca-d014024304eb)


Plugin installation report:  
![Plugin installation report](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/97ff814e-8f2a-4f0f-887d-625616db7ea5)

Scenario 4 & 5 page Report:  
![scenario 4 5 reports](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/f1a36ce5-afc5-4025-abd7-8c67bebe9212)

Settings (6-11) page report:  
![Setting report](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/6203f1ec-a073-4fa8-9b29-cb489cb6bf49)


Dark Moda validate report:  
![Dark mode validation report](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/88a19fc6-f170-4ac3-9046-ed5c171127d1)

Total Report graph:  
![Total report](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/ef3364cc-294c-4d8d-9081-c1a4cfc3c1c9)


