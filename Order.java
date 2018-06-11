package com.weborder;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Order {
	
	final static String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

	final static java.util.Random rand = new java.util.Random();

	// consider using a Map<String,Boolean> to say whether the identifier is being used or not 
	final static Set<String> identifiers = new HashSet<String>();

	public static String randomIdentifier() {
	    StringBuilder builder = new StringBuilder();
	    while(builder.toString().length() == 0) {
	        int length = rand.nextInt(5)+5;
	        for(int i = 0; i < length; i++) {
	            builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
	        }
	        if(identifiers.contains(builder.toString())) {
	            builder = new StringBuilder();
	        }
	    }
	    return builder.toString();
	}
	public static String randomCreditCard(int len,String type) {
		StringBuilder std=new StringBuilder();
		int rdigit;
	   for (int i = 0; i < len; i++) {
		   rdigit = (int) (Math.random() * (9 - 0)) + 0;
		   std.append(rdigit);
		
	}
	   return std.toString();
}

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "/Users/LM/Documents/selenium dependencies/drivers/chromedriver");

		WebDriver driver = new ChromeDriver();
		
		
		
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		driver.findElement(By.xpath("//*[@id=\"ctl00_menu\"]/li[3]/a")).click();
		Select dropdown = new Select(driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_ddlProduct\"]")));
		Thread.sleep(2000);
		
		dropdown.selectByVisibleText("FamilyAlbum");
		
		
		double randNumber = Math.random();
		int num1 = (int) (randNumber * 100);
		
		
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_txtQuantity\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_txtQuantity\"]")).sendKeys(Integer.toString(num1));
		String randomMiddle=randomIdentifier();
	      
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_txtName\"]")).sendKeys("John "+randomMiddle+" Smith");

		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox2\"]")).sendKeys(randomIdentifier());
		
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox3\"]")).sendKeys(randomIdentifier());
		
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox4\"]")).sendKeys("Virginia");
		int rz= (int) (Math.random() * (99999 - 9999)) + 1;
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox5\"]")).sendKeys(Integer.toString(rz));
		
		int r2 = (int) (Math.random() * (2 - 0)) + 0;
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_cardList_"+r2+"\"]")).click();
		Thread.sleep(2000);
		
		
		String type="";
		int len=0;
		if(r2==0)
			{type="visa"; len=15;}
		else if(r2==1)
			{type="master"; len=15;}
		else if(r2==2)
			{type="americanExpress"; len=14;} 
		String cardnumber=randomCreditCard(len,type);
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox6\"]")).sendKeys(cardnumber);
		Thread.sleep(2000);
		
		System.out.println(type+"    "+ cardnumber);
		
		//expire
		
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox1\"]")).sendKeys("04/19");
		Thread.sleep(2000);
		
		//click process
		
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_InsertButton\"]")).click();
		Thread.sleep(2000);
		
		
		driver.close();
		
	}

	
		
	


		
		


	
	
	




	

	
}
