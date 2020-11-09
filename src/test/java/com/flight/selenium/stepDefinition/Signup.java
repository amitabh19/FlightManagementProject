package com.flight.selenium.stepDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Signup {
	WebDriver driver;

	@Given("^User is on Flight Management Signup Page$")
	public void user_is_on_Flight_Management_Signup_Page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "D:\\seleniumTools\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:4200/signup");
		driver.manage().window().maximize();
		assert driver.getTitle().contains("Flight");
	}

	@When("^User logs in with valid credentials$")
	public void user_logs_in_with_valid_credentials() throws Throwable {
		driver.findElement(By.id("username")).sendKeys("testUser");
		driver.findElement(By.id("email")).sendKeys("testUser@gmail.com");
		driver.findElement(By.id("mobileNo")).sendKeys("3434343423");
		driver.findElement(By.id("password")).sendKeys("testPassword");
		driver.findElement(By.id("submitBtn")).click();
	}

	@Then("^User should be shown welcome message and taken to login page$")
	public void user_should_be_shown_welcome_message_and_taken_to_login_page() throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assertEquals("http://localhost:4200/login", driver.getCurrentUrl()); 
		driver.close();
	}


}
