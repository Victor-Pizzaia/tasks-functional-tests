package com.br.pizzaia.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		// WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.15.2:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void shouldSaveTaskWithSuccess() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// Clicar em add ToDo
			driver.findElement(By.id("addTodo")).click();
			
			// Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
			
		} finally {
			// Fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void shouldNotSaveTaskWithoutDescription() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// Clicar em add ToDo
			driver.findElement(By.id("addTodo")).click();
			
			// Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
			
		} finally {
			// Fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void shouldNotSaveTaskWithouDate() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// Clicar em add ToDo
			driver.findElement(By.id("addTodo")).click();
			
			// Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
			
		} finally {
			// Fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void shouldNotSaveTaskWithPassedDate() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// Clicar em add ToDo
			driver.findElement(By.id("addTodo")).click();
			
			// Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			
			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
			
		} finally {
			// Fechar o browser
			driver.quit();
		}
	}
}
