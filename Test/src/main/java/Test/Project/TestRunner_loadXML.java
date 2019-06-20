package Test.Project;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class TestRunner_loadXML extends BaseComponent.ReusableMethods{
	static Properties prop = new Properties();
	public TestRunner_loadXML() throws IOException {
		super();
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
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
		driver.close();
	}

}
