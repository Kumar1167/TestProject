package Test.Project;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import BaseComponent.AppMethods;
import BaseComponent.ReusableMethods;


public class VerifyValidLogin 
{


@Test
public void checkValidUser() throws IOException
{

// This will launch browser and specific url
	ReusableMethods obj = new ReusableMethods();
	WebDriver driver = obj.LaunchBrowser("Chrome");
	obj.LoadApplication(driver, "http://demosite.center/wordpress/wp-login.php");
// Created Page Object using Page Factory
	AppMethods login_page=PageFactory.initElements(driver, AppMethods.class);
	System.out.println(login_page.username.isDisplayed());
// Call the method
	login_page.login_wordpress("admin", "demo123");
	driver.close();

}



}
