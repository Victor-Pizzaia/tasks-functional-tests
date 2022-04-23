package com.br.pizzaia.tasks.functional;

import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@Test
	public void shouldSaveTaskWithSuccess() {
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
	public void shouldNotSaveTaskWithoutDescription() {
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
	public void shouldNotSaveTaskWithouDate() {
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
	public void shouldNotSaveTaskWithPassedDate() {
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
