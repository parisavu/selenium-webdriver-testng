package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_04_Xpath_Css {
	// Khai báo 1 biến đại diện cho Selenium WebDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Mở trình duyệt Firefox lên
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		// Set timeount để tìm element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
		// Mở application lên (AUT/ SUT)
		driver.get("https://www.guru.com/about/");
		//driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01() {
		
		//driver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).click();
		// Cuộn xuống cuối trang
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		
		driver.findElement(By.xpath("//div[@class='c-footer__section']//a[@title='Help & FAQ']")).click();
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
