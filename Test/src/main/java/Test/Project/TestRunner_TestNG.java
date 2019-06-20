package Test.Project;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestRunner_TestNG extends BaseComponent.ReusableMethods{
	static Properties prop = new Properties();
	static ExtentTest logger;
	public TestRunner_TestNG() throws IOException {
		super();
	}
	BaseComponent.ReusableMethods obj = new BaseComponent.ReusableMethods();
	
	@BeforeClass (groups = {"smoke"})
	//@Parameters ("Browser")
	public void launchApplication() throws IOException, InterruptedException {
		//System.out.println(Browser);
		obj.LaunchBrowser("Chrome");
		Thread.sleep(2000);
	}
	
	@BeforeMethod
	public void setup()
	{
	 	}
	
	@Test (description = "Open Naukiri Site and Close unwanted popups", groups = {"smoke"}, dataProvider = "applicationURL")
	//@Parameters ("AppURL")
	public void OpenApplication(String AppUrl, String Password) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		System.out.println(AppUrl);
		System.out.println(Password);
		obj.LoadApplication(driver, AppUrl);
		String parentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		System.out.println(allWindows.size());
		System.out.println(allWindows);
		ArrayList<String> windowHandles = new ArrayList<String>(allWindows);
		System.out.println(windowHandles.get(1));
		for(String str: allWindows) {
			
		if(!parentWindow.equals(str))
		{
		driver.switchTo().window(str);

		System.out.println(driver.switchTo().window(str).getTitle());

		driver.close();
		}
	}
		driver.switchTo().window(parentWindow);	
	}
		
		@Test (description = "Perform search for jobs", groups = {"Regression"}, enabled = true, dependsOnMethods = {"OpenApplication"})
		public void performSearch() throws Throwable {
		WebElement element = driver.findElement(By.xpath("//span[text()=' Search Jobs ']"));
		Thread.sleep(2000);
		
		if (driver.findElement(By.xpath("//span[contains(text(), 'Later')]")).isDisplayed()) {
			driver.findElement(By.xpath("//span[contains(text(), 'Later')]")).click();
			Thread.sleep(2000);
		}
		element.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()=' Search Jobs ']")).sendKeys("test");
		Actions act=new Actions(driver);
		WebElement ele=driver.findElement(By.xpath("//*[@id=’sugDrp_skill’]/ul/li[1]/div"));
		act.moveToElement(ele).pause(2000).sendKeys(Keys.RETURN).build().perform();
		act.click(ele).build().perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(), 'Search') and @id = 'qsbFormBtn']")).click();
		Thread.sleep(2000);
		}
		
		@AfterMethod (groups = {"smoke"})
		public void takeScreenshot(ITestResult result) throws Throwable, IOException {
			   ExtentHtmlReporter reporter=new ExtentHtmlReporter(System.getProperty("user.dir") + "\\learn_automation2.html");
			    ExtentReports extent = new ExtentReports();
			    extent.attachReporter(reporter);
			    ExtentTest logger=extent.createTest("LoginTest");
			    logger.log(Status.INFO, "status");
			    
			//if (result.getStatus() == ITestResult.FAILURE) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			String path = System.getProperty("user.dir") + "\\screenshot_" + result.getName() + "_" + System.currentTimeMillis() +".png";
			File dest = new File(path);
			FileHandler.copy(ts.getScreenshotAs(OutputType.FILE), dest);
			logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			extent.flush();
		//}
		}
		
		@AfterTest
		public void closeBrowser() {
			driver.close();
		}
		
		@DataProvider(name = "applicationURL")
		public Object[][] getDataFromDataProvider() {
			return new Object[][] {
			{"http://www.naukri.com/", "Password56"}
		};
		}
}
