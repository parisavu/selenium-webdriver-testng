package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Xpath_Css_II {
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
		driver.get("https://automationfc.github.io/basic-form/");
	}

	@Test
	public void TC_01_Verify_Text() {
		//1-  Get text của element đó ra --> Lưu vào 1 biến
		// Biến này để kiểm tra chứa text mong muốn hay không -> Java String (contains)
		String populationValue = driver.findElement(By.xpath("//div[@id='population']")).getText();
		System.out.print(populationValue);
		Assert.assertTrue(populationValue.contains("Mongolia: 500-1,000"));
		
		// Xpath check contains text có nằm trong element đó không
		// Check displayd cái element có xpath đó (isDisplayed)
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='population' and contains(string(),'Mongolia: 500-1,000')]")).isDisplayed());
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
