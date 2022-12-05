package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Xpath_Css_I {
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
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Empty_data() {

		// Cuộn xuống cuối trang
		// driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		// Nhập dữ liệu
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("");
		// Click button Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Get error message text
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(),
				"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(),
				"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),
				"Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(),
				"Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),
				"Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(),
				"Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_Invalid_email() {
		driver.navigate().refresh();

		// Nhập dữ liệu
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("ParisaVu");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("parisavu.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("parisavu");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0889888888");
		// Click button Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Get error message text
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(),
				"Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),
				"Email nhập lại không đúng");

	}
	
	@Test
	public void TC_03_Incorrect_confirm_email() {
		driver.navigate().refresh();

		// Nhập dữ liệu
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("ParisaVu");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("parisavu@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("parisavu123@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0889888888");
		// Click button Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Get error message text
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),
				"Email nhập lại không đúng");

	}
	
	@Test
	public void TC_04_Password_6_characters() {
		driver.navigate().refresh();

		// Nhập dữ liệu
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("ParisaVu");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("parisavu@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("parisavu@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0889888888");
		// Click button Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Get error message text
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");

	}
	
	@Test
	public void TC_05_incorrect_confirm_password() {
		driver.navigate().refresh();

		// Nhập dữ liệu
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("ParisaVu");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("parisavu@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("parisavu@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123459789");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0889888888");
		
		// Click button Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Get error message text
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),
				"Mật khẩu bạn nhập không khớp");

	}
	
	@Test
	public void TC_06_incorrect_phone_number_9_char() {
		driver.navigate().refresh();

		// Nhập dữ liệu
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("ParisaVu");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("parisavu@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("parisavu@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("088988888");
		
		// Click button Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Get error message text
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(),
				"Số điện thoại phải từ 10-11 số.");

	}

	@Test
	public void TC_07_incorrect_phone_number_12_char() {
		driver.navigate().refresh();

		// Nhập dữ liệu
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("ParisaVu");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("parisavu@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("parisavu@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("088988888888");
		
		// Click button Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Get error message text
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(),
				"Số điện thoại phải từ 10-11 số.");

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
