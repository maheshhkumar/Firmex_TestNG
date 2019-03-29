package assignment;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import resources.Baseclass;

public class NewTest extends Baseclass {

	@BeforeClass
	public void load_class() throws IOException {
		ruler = intialize_driver();// Loading the driver object according to browser
		ruler.get(prop.getProperty("url"));// Navigating to the URL
		System.out.println("Landed on Welcome Page");// Console to "Landing on the URL"
	}

	@Test
	public void firmex_runner() {
		String expctd_WPT = "The World's Most Trusted Virtual Data Room | Firmex";// Expected Welcome Page Title
		String actualwptitle = ruler.getTitle();// Getting the welcome page title
		if (verify(actualwptitle, expctd_WPT)) // calling the method to verify the page title.
		{
			ruler.findElement(By.xpath("//a[@class='button login']")).click();
			System.out.println("Login button clicked");
		}

		String actual_LPT = ruler.findElement(By.xpath("//meta[@name='title']")).getAttribute("content");
		// Getting the Login page title
		String expctd_LPT = "Firmex Virtual Data Room Login";// Expected Login Page Title
		WebElement user_name = ruler.findElement(By.xpath("//input[@id='login_user']"));
		WebElement pass_word = ruler.findElement(By.xpath("//input[@id='login_pw']"));
		WebElement click = ruler.findElement(By.xpath("//input[@id='login_btn']"));
		if (verify(actual_LPT, expctd_LPT))// calling the method to verify the page title.
		{
			user_name.sendKeys("demo@demo.com");
			pass_word.sendKeys("Demo123");
			click.click();
			System.out.println("Username & Password entered and Login button clicked");
			String expctd_errormsg = "User credentials are invalid. Click here to reset your password";
			String actual_msg = ruler
					.findElement(By.xpath("//div[@class='form-container loginTriggerBox form-error']//p[1]")).getText();
			verify(actual_msg, expctd_errormsg);
		}
	}

	public boolean verify(String actual, String expected) // Method to verify Welcome, Login and Error Message
	{
		Assert.assertEquals(actual, expected);
		System.out.println(actual + " :verified");
		return true;
	}

	@AfterClass
	public void close_session() throws InterruptedException {
		Thread.sleep(1000);
		ruler.close();
	}

}
