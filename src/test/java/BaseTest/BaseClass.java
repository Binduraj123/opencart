package BaseTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterMethod;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;

import org.testng.annotations.*;

public class BaseClass {

	public  static WebDriver driver;
	public Logger logger;//logger variable
	public Properties p;//property variable
	
	@BeforeClass(groups={"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os ,String br)throws IOException
	{
		
		//loading properties file.
		
		FileReader file=new FileReader(".//src/test/resources/config.properties");
		p=new Properties();
		p.load(file);
		
		//loadinng log4j file(configuration)will fectch the log42j file from resources into logger variable.
		logger = LogManager.getLogger(BaseClass.class);
	   
		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
		    DesiredCapabilities capabilities = new DesiredCapabilities();

		    // Set OS
		    if (os.equalsIgnoreCase("macos")) {
		        capabilities.setPlatform(Platform.MAC);
		    } else if (os.equalsIgnoreCase("windows")) {
		        capabilities.setPlatform(Platform.WIN11);
		    } else {
		        logger.error("No matching OS");
		        return;
		    }

		    // Set browser
		    switch (br.toLowerCase()) {
		        case "chrome":
		            capabilities.setBrowserName("chrome");
		            break;
		        case "edge":
		            capabilities.setBrowserName("MicrosoftEdge");
		            break;
		        case "firefox":
		            capabilities.setBrowserName("firefox");
		            break;
		        default:
		            logger.error("No matching browser");
		            return;
		    }

		    try {
		        URI uri = new URI("http://localhost:4444/wd/hub");
		        driver = new RemoteWebDriver(uri.toURL(), capabilities);
		    } catch (URISyntaxException | MalformedURLException e) {
		        logger.error("Error initializing RemoteWebDriver", e);
		        throw new RuntimeException("Error initializing RemoteWebDriver", e);
		    }
		}

		if (p.getProperty("execution_env").equalsIgnoreCase("Local")) {
		    switch (br.toLowerCase()) {
		        case "chrome":
		            driver = new ChromeDriver();
		            break;
		        case "edge":
		            driver = new EdgeDriver();
		            break;
		        case "firefox":
		            driver = new FirefoxDriver();
		            break;
		        default:
		            logger.error("No matching browser");
		            return;
		    }
		}

		if (driver == null) {
		    logger.error("WebDriver initialization failed");
		    throw new RuntimeException("WebDriver initialization failed");
		}

		// Browser configuration
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	

	@AfterClass(groups={"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.close();
	}
	

	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String randomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		
		return (str+"@"+num);
	}
	
	
	
	public String captureScreen(String tname) throws IOException {
	    if (driver == null) {
	        logger.error("Driver is not initialized when attempting to take a screenshot.");
	        System.out.println("Logger initialized: " + (logger != null));
	        throw new RuntimeException("Driver is not initialized");
	    }

	    // Generate a timestamp for the file name
	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	    try {
	        // Create the screenshots directory if it doesn't exist
	        File screenshotsDir = new File(System.getProperty("user.dir") + "/screenshots/");
	        if (!screenshotsDir.exists()) {
	            screenshotsDir.mkdirs(); // Create the directory if it doesn't exist
	        }

	        // Take the screenshot
	        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

	        // Define the target file path
	        String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
	        File targetFile = new File(targetFilePath);

	        // Copy the file from the source to the target location
	        Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

	        // Return the path of the saved screenshot
	        logger.info("Screenshot saved successfully: " + targetFilePath);
	        return targetFilePath;

	    } catch (Exception e) {
	        logger.error("Failed to take screenshot", e);
	        throw new RuntimeException("Failed to take screenshot", e);
	    }
	}
}