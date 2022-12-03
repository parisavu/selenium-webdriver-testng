package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_03_Selenium_Locator {
	// Khai báo 1 biến đại diện cho Selenium WebDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Mở trình duyệt Firefox lên
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		// Set timeount để tìm element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
		// Mở application lên (AUT/ SUT)
		driver.get("https://www.guru.com/login.aspx");
	}

	@Test
	public void TC_01_FindElement() {
			
		// Single element: 1 Element
		WebElement loginButton = driver.findElement(By.className(""));
		loginButton.click();
		
		// findElement: Tìm element
		// By.xxx: Với locator nào
		// Action gì lên element đó: click/ sendkey/ getText/ ...
			
		// Multiple element: List<WebElement>
		List<WebElement> buttons = driver.findElements(By.className(""));
		buttons.get(0).click();
	}
	
	@Test
	public void TC_02_ID() {
		// Selenium Locator
		driver.findElement(By.id("login-button")).click();
		
		// Verify email error message xuất hiện
		Assert.assertTrue(driver.findElement(By.id("userId")).isDisplayed());
	}
	
	@Test
	public void TC_03_Class() {
		driver.navigate().refresh();

		driver.findElement(By.className("login__actionBtn")).click();
		Assert.assertTrue(driver.findElement(By.id("userId")).isDisplayed());
		
	}

	@Test
	public void TC_04_Name() {
	}
	
	@Test
	public void TC_05_Tagname() {
		driver.navigate().refresh();
		// Lấy hết tất cả đường link tại màn hình sau đó getText ra
		List<WebElement> loginPageLinks = driver.findElements(By.tagName("a"));
		
		for (WebElement webElement : loginPageLinks) {
			System.out.println(webElement.getText());
		}
		
	}
	
	
	public void TC_06_LinkText() {
		driver.navigate().refresh();
		
		driver.findElement(By.linkText("Forgot password?")).click();
		Assert.assertTrue(driver.findElement(By.className("txtInput")).isDisplayed());
		
	}
	
	
	public void TC_07_PartiaLinkText() {
		driver.findElement(By.partialLinkText("Sign Up")).click();
		Assert.assertTrue(driver.findElement(By.id("fullName")).isDisplayed());
		
	}
	
	@Test
	public void TC_08_Css() {
		
		driver.findElement(By.cssSelector("#userId")).sendKeys("phuongvt2107@gmail.com");
		driver.findElement(By.cssSelector("input[name = 'Password']")).sendKeys("123456789");
		
		
	}
	
	@Test
	public void TC_09_Xpath() {
		driver.navigate().refresh();
		
		driver.findElement(By.xpath("//input[@id='userId']")).sendKeys("phuongvt2107@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123456789");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
