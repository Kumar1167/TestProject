package BaseComponent;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ReusableMethods {
	public static WebDriver driver = null;
	public String resourcePath;
	public FileReader configFile;
	public FileReader xmlFile;
	
public ReusableMethods() throws IOException {
	resourcePath = System.getProperty("user.dir") + "\\src\\test\\java\\Resources\\";
	configFile = new FileReader(resourcePath + "config.properties");
	//xmlFile = new FileReader(System.getProperty("user.dir") + "\\newProp.xml");
}

public WebDriver LaunchBrowser(String browserType) {

	switch(browserType) {
	case "IE":
	System.setProperty("webdriver.ie.driver", this.resourcePath + "IEDriverServer.exe");
	driver = new InternetExplorerDriver();
	
	case "FF":
	System.setProperty("webdriver.gecko.driver", this.resourcePath + "geckodriver.exe");
	driver = new FirefoxDriver();
	
	case "Chrome":
	System.setProperty("webdriver.chrome.driver", this.resourcePath + "chromedriver.exe");
	Map<String, Object> prefs = new HashMap<String, Object>();
	prefs.put("profile.default_content_setting_values.notifications", 2);
	ChromeOptions Options = new ChromeOptions();
	Options.addArguments("--disable-extensions", "--disable-infobars");
	Options.setAcceptInsecureCerts(true);
	Options.setExperimentalOption("prefs", prefs);
	driver = new ChromeDriver(Options);
	}
	return driver;
}

public String LoadApplication(WebDriver driver, String applicationURL) {
	driver.manage().window().maximize();
	driver.get(applicationURL);
	String WindowHandle = driver.getWindowHandle();
	return WindowHandle;
}

public Properties LoadProperties(FileReader file) {
	Properties prop = new Properties();
	try {
		prop.load(file);
		System.out.println(prop);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prop;
}
}
