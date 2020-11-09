package com.flight.selenium.stepDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginFail {
	WebDriver driver;
	
	@Given("^User is on Flight Management Login Page$")
	public void user_is_on_Flight_Management_Login_Page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "D:\\seleniumTools\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:4200/");
		driver.manage().window().maximize();
		assert driver.getTitle().contains("Flight");
	}

	@When("^User logs in with invalid ([^\\\"]*) and invalid ([^\\\"]*$)")
	public void user_logs_in_with_invalid_email_and_invalid_password(String email,String password) throws Throwable {
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("loginn")).click();   
	}
	
	@Then("^User should be shown error$")
	public void user_should_be_shown_error() throws Throwable {
		assertEquals("http://localhost:4200/", driver.getCurrentUrl()); 
		driver.close();
	}


}
