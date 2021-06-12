# Automation Solution

This project uses java programming language along with maven to manage dependencies:

##Repo Link: 
Markup : [Gitlab](https://gitlab.com/testautomationacademy/atbatchnov2020/interviewquestion/automationprojectsolution.git)

## Add dependencies:
Add it in pom.xml

1. Selenium WedDriver - UI Automation
2. WeDriver Manager - Managing the driver files
3. Test NG- For writing e2e, tests
4. Rest Assured - Making API requests
5. Open CSV - Read Data from CSV file
6. Apache POI - Read Data from XLS and XLSX file
7. Extent Reports - Generating HTML Reports in report folder
8. Commons IO - working with files and folders
9. Allure Report (Optional) - generating Allure Reports

          
##Add dependencies:
```bash
<dependecies>
.
.
.

<dependency>
			<groupId>org.seleniumhq.selenium</groupId>
			<artifactId>selenium-java</artifactId>
			<version>3.141.59</version>
		</dependency>
</dependecies>

```

## Usage

```java

```
Step 1: Create a Page Class for the UI Page (src/test/com.pages)
Page Class will extend Elements

```
public class LandingPage extends Elements {

	private static final By SIGN_IN_LINK = By.xpath("//a[contains(text(),'Sign in')]");

	public SignInPage goToSignInPage() {
		clickOnElement(SIGN_IN_LINK);
		return new SignInPage();
	}

}

```
 Step 2: Create the Test Class in src/test/com.test
Convention for the Test Class Name suffix Test at the end

```
@Listeners(com.listeners.MyCustomListeners.class)
public class LandingPageTest {
	SignInPage signInPage;

	@BeforeMethod(description = "sets up the requiremnt for the tests")
	public void setup() {
		LandingPage landingPage = new LandingPage();
		landingPage.launchBrowser(BrowserName.CHROME);
		landingPage.setScreenSize(Devices.PC);
		landingPage.loadPage(Utilities.readConfigFile("URL"));
		signInPage = landingPage.goToSignInPage();

	}

	@Test(testName = "login Test", description = "verifies login feature is working or not",groups = {"e2e,smoke"})
	public void loginTest() {
		MyAccountPage myAccountPage = signInPage.doLogin(Utilities.readConfigFile("BASE_USER"),
				Utilities.readConfigFile("BASE_PASSWORD"));
		Assert.assertEquals(myAccountPage.getUserName(), "Ajay");
	}

}


```

