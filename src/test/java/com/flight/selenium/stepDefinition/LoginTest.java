package com.flight.selenium.stepDefinition;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginTest {
	WebDriver driver;
	
	@Given("^User is on FlightManagementLogin Page$")
	public void user_is_on_FlightManagementLogin_Page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "D:\\seleniumTools\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:4200/");
		driver.manage().window().maximize();
		assert driver.getTitle().contains("Flight");
	}

	@When("^User logs in with valid ([^\\\"]*) and valid ([^\\\"]*$)")
	public void user_logs_in_with_valid_email_and_valid_password(String email,String password) throws Throwable {
		System.out.println(email);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("loginn")).click();
        
	}
	

	@Then("^User should be navigated to home page$")
	public void user_should_be_navigated_to_home_page() throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		boolean view = driver.findElement(By.id("logout")).isDisplayed();
		Assert.assertTrue(view);
		driver.findElement(By.id("logout")).click();
		Alert a = driver.switchTo().alert();
		a.accept();
		driver.close();
	}



}
