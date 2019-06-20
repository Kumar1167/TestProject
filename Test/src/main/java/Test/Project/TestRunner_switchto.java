package Test.Project;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

public class TestRunner_switchto extends BaseComponent.ReusableMethods{
	static Properties prop = new Properties();
	public TestRunner_switchto() throws IOException {
		super();
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		BaseComponent.ReusableMethods obj = new BaseComponent.ReusableMethods();
		prop = obj.LoadProperties(obj.configFile);
		String browserType = prop.getProperty("browser");
		String appURL = prop.getProperty("url");
		obj.LaunchBrowser(browserType);
		obj.LoadApplication(driver, appURL);
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
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		
		FileHandler.copy(ts.getScreenshotAs(OutputType.FILE), new File(System.getProperty("user.dir") + "\\screenshot.png"));
		/*WebElement element = driver.findElement(By.xpath("//span[text()=' Search Jobs ']"));
		element.sendKeys("test");
		Actions act=new Actions(driver);
		WebElement ele=driver.findElement(By.xpath("//*[@id=’sugDrp_skill’]/ul/li[1]/div"));
		act.moveToElement(ele).pause(2000).sendKeys(Keys.RETURN).build().perform();
		//act.click(ele).build().perform();
		Thread.sleep(2000);*/
		driver.close();
}
}
