package com.br.pizzaia.tasks.prod;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HealthCheckIT {
	@Test
	public void healthCheck() throws MalformedURLException {
		WebDriver driver = new ChromeDriver();
		try {
			driver.navigate().to("http://localhost:9999/tasks");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String version = driver.findElement(By.id("version")).getText();
			Assert.assertTrue(version.startsWith("build"));			
		} finally {
			driver.quit();			
		}
	}
}
