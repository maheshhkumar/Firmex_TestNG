package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Baseclass {

	public static WebDriver ruler = null;
	public Properties prop;
	String uPath = System.getProperty("user.dir");

	public WebDriver intialize_driver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(uPath + "\\src\\resources\\data.properties");// setting the properties
																								// File
		prop.load(fis);// Loading the file
		String browser_name = prop.getProperty("browser");

		if (browser_name.equals("chrome")) {
			// exceute in chrome
			System.setProperty("webdriver.chrome.driver", uPath + "\\drivers\\chrome\\chromedriver.exe");
			ruler = new ChromeDriver();
		} else if (browser_name.equals("firefox")) {
			// exceute in firefox
			System.setProperty("webdriver.gecko.driver", uPath + "\\drivers\\gecko\\geckodriver.exe");
			ruler = new FirefoxDriver();

		}
		ruler.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return ruler;
	}

	}
