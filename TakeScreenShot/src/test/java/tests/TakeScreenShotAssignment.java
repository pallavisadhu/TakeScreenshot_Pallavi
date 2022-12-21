package tests;

import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TakeScreenShotAssignment {
	
	WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void testCase1() throws IOException, InterruptedException {
		
		driver.findElement(By.xpath("//a[text()='About']")).click();
		takeScreenshot();
		driver.navigate().back();
		driver.findElement(By.xpath("//a[text()='Store']")).click();
		takeScreenshot();
		driver.navigate().back();
		
	}
	
	public void takeScreenshot() throws IOException {
		Date c = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmssZ");
		String sdate = formatter.format(c);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("./Screenshots/"+sdate+".png"));
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	

}
