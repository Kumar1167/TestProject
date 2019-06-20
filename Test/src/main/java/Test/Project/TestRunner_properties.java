package Test.Project;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class TestRunner_properties extends BaseComponent.ReusableMethods{
	static Properties prop = new Properties();
	public TestRunner_properties() throws IOException {
		super();
	}
	
	@Test
	public void OpenApplication() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		BaseComponent.ReusableMethods obj = new BaseComponent.ReusableMethods();
		prop = obj.LoadProperties(obj.configFile);
		String browserType = prop.getProperty("browser");
		String appURL = prop.getProperty("applicationURL");
		WebDriver driver = obj.LaunchBrowser(browserType);
		String WinHandle = obj.LoadApplication(driver, appURL);
		System.out.println(WinHandle);
		String WindowTitle = driver.getTitle();
		System.out.println(WindowTitle);
		Thread.sleep(2000);
		
		prop.setProperty("Date","Today's Date");
		prop.storeToXML(new FileOutputStream("newProp.xml"), "This is a property XML");
		FileReader file = new FileReader(System.getProperty("user.dir") + "\\newProp.xml");
		prop.load(file);
		System.out.println(prop.getProperty("Date"));	
	}
		
		@Test(dependsOnMethods = {"OpenApplication"})
		public void doOperatrion() throws Throwable {
		//for each
		boolean val;
		List<WebElement> element = driver.findElements(By.xpath("//*[contains(text(), 'History')]"));
		System.out.println(element.size());
		for (WebElement we : element) {
			System.out.println(we != null);
			val = we.isDisplayed();
		}
		//Assert.assertEquals(WindowTitle,"JavaScript");
		//drag and drop
		WebElement Source = driver.findElement(By.xpath("(//*[contains(text(), 'History')])[1]"));
		WebElement Dest = driver.findElement(By.xpath("(//*[contains(text(), 'Magazines')])[2]"));
		Actions act = new Actions(driver);
		//Scroll Window
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("live-demo")));
		Thread.sleep(2000);
		//act.contextClick(driver.findElement(By.id("live-demo"))).pause(2000).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).
		//sendKeys(Keys.ENTER).pause(2000).build().perform();
		//Thread.sleep(2000);
		//act.sendKeys(Keys.ESCAPE);
		//Thread.sleep(1000);
		act.clickAndHold(Source).pause(2000).moveToElement(Dest).release().build().perform();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		WebElement btnDownload = driver.findElement(By.partialLinkText("Download"));
		js.executeScript("arguments[0].scrollIntoView();",btnDownload);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();",btnDownload);
		Thread.sleep(2000);
		}
		
		@AfterClass
		public void closeBrowser() {
		driver.close();
	}
}
