package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Xpath_Css_II_baitap {
	// Khai báo 1 biến đại diện cho Selenium WebDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String name, emailAddress, password, phone;
	// Action
	By nametextboxBy = By.id("txtFirstname");
	By emailtextboxBy = By.id("txtEmail");
	By confirmemailtextboxBy = By.id("txtCEmail");
	By passtextboxBy = By.id("txtPassword");
	By confirmpasstextboxBy = By.id("txtCPassword");
	By phonetextboxBy = By.id("txtPhone");
	By registerbuttonBy = By.xpath("//form[@id='frmLogin']//button");

	// Error
	By nameErrorMsgBy = By.id("txtFirstname-error");
	By emailErrorMsgBy = By.id("txtEmail-error");
	By confirmemailErrorMsgBy = By.id("txtCEmail-error");
	By passErrorMsgBy = By.id("txtPassword-error");
	By confirmpassErrorMsgBy = By.id("txtCPassword-error");
	By phoneErrorMsgBy = By.id("txtPhone-error");

	@BeforeClass
	public void beforeClass() {
		// Mở trình duyệt Firefox lên
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Set timeount để tìm element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Mở application lên (AUT/ SUT)
		// driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		name = "Parisa Vu";
		emailAddress = "parisavu@gmail.com";
		password = "123456";
		phone = "0889888666";
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Empty() {
		driver.findElement(registerbuttonBy).click();
		Assert.assertEquals(driver.findElement(nameErrorMsgBy).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmemailErrorMsgBy).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passErrorMsgBy).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmpassErrorMsgBy).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void TC_02_Invaild_Email() {
		// 123@134.com@
		driver.findElement(nametextboxBy).sendKeys(name);
		driver.findElement(emailtextboxBy).sendKeys("123@134.com@");
		driver.findElement(confirmemailtextboxBy).sendKeys(emailAddress);
		driver.findElement(passtextboxBy).sendKeys(password);
		driver.findElement(confirmpasstextboxBy).sendKeys(password);
		driver.findElement(phonetextboxBy).sendKeys(phone);
		//Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email hợp lệ");
		
		
		driver.findElement(registerbuttonBy).click();

		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email hợp lệ");

	}

	@Test
	public void TC_03_Invaild_Confirm_email() {
		// 123@134.com@
		driver.findElement(nametextboxBy).sendKeys(name);
		driver.findElement(emailtextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmemailtextboxBy).sendKeys("123@134.com@");
		driver.findElement(passtextboxBy).sendKeys(password);
		driver.findElement(confirmpasstextboxBy).sendKeys(password);
		driver.findElement(phonetextboxBy).sendKeys(phone);
		Assert.assertEquals(driver.findElement(confirmemailErrorMsgBy).getText(), "Email nhập lại không đúng");
		driver.findElement(registerbuttonBy).click();

		Assert.assertEquals(driver.findElement(confirmemailErrorMsgBy).getText(), "Email nhập lại không đúng");

	}

	@Test
	public void TC_04_Pass_Less_Than_6_Chars() {
		driver.findElement(nametextboxBy).sendKeys(name);
		driver.findElement(emailtextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmemailtextboxBy).sendKeys(emailAddress);
		driver.findElement(passtextboxBy).sendKeys("123");
		driver.findElement(confirmpasstextboxBy).sendKeys(password);
		driver.findElement(phonetextboxBy).sendKeys(phone);

		driver.findElement(registerbuttonBy).click();

		Assert.assertEquals(driver.findElement(passErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");

	}

	@Test
	public void TC_05_Incorect_Confirm_Password() {
		driver.findElement(nametextboxBy).sendKeys(name);
		driver.findElement(emailtextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmemailtextboxBy).sendKeys(emailAddress);
		driver.findElement(passtextboxBy).sendKeys(password);
		driver.findElement(confirmpasstextboxBy).sendKeys("123");
		driver.findElement(phonetextboxBy).sendKeys(phone);

		driver.findElement(registerbuttonBy).click();

		Assert.assertEquals(driver.findElement(confirmpassErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");

	}

	@Test
	public void TC_06_Invaild_Phone() {
		// Nhập phone = chữ
		driver.findElement(nametextboxBy).sendKeys(name);
		driver.findElement(emailtextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmemailtextboxBy).sendKeys(emailAddress);
		driver.findElement(passtextboxBy).sendKeys(password);
		driver.findElement(confirmpasstextboxBy).sendKeys(password);
		driver.findElement(phonetextboxBy).sendKeys("haha");

		driver.findElement(registerbuttonBy).click();

		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Vui lòng nhập con số");

		// Clear dữ liệu đã nhập đi để nhập lại
		driver.findElement(phonetextboxBy).clear();
		driver.findElement(phonetextboxBy).sendKeys("0889");
		driver.findElement(registerbuttonBy).click();

		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại phải từ 10-11 số.");

		// Phone > 11 ký tự
		driver.findElement(phonetextboxBy).clear();
		driver.findElement(phonetextboxBy).sendKeys("088988866688");
		driver.findElement(registerbuttonBy).click();

		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại phải từ 10-11 số.");
		// Phone không đúng định dạng sđt
		driver.findElement(phonetextboxBy).clear();
		driver.findElement(phonetextboxBy).sendKeys("8888");
		driver.findElement(registerbuttonBy).click();

		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(),
				"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
