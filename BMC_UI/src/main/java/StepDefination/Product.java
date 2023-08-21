package StepDefination;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Product {
	Actions a;
	public static WebDriver driver;
	public String added;
	@Given("Launch the browser")
	public static WebDriver Launch()
	{
		System.setProperty("webdriver.chrome.driver", "D://Infy_bmc//BMC_UI//src//test//chromedriver.exe");
		driver=new ChromeDriver();
		return driver;
		
			}
	
	@Given ("Enter URL")
	public void url()
	{
		
		driver.get("https://google.com");
		driver.manage().window().maximize();
	}

	@When("Enter the keyword {string} in the search bar")
	public void keyword(String text)
	{
	driver.findElement(By.xpath("//textarea[@id=\"APjFqb\"]")).sendKeys(text);

		
	}
	
	@When ("print all the search results")
	public void results() throws InterruptedException
	{
		Thread.sleep(5000);
		List<WebElement> li =driver.findElements(By.xpath("//ul//li[@role=\"presentation\"]//div//div[1]//div[1]/span/b"));
		System.out.println(li.size());
	    for(int i=0;i<li.size();i++)
	    {
	    	
	    	String sr=driver.findElements(By.xpath("//ul//li[@role=\"presentation\"]//div//div[1]//div[1]/span/b")).get(i).getText();
	    	System.out.println(sr);   
	    }
	    
	}
	
	@When ("Click on the link which takes you to the amazon login page")
	public void login()
	{
		driver.findElement(By.xpath("//div[@aria-label=\"amazon\"]")).click();
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(45));
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class=\"tF2Cxc\"]//a"))));
		driver.findElement(By.xpath("//div[@class=\"tF2Cxc\"]//a")).click();
	}
	
	@When("Sign in to amazon")
	public void amazonLogin() throws IOException
	{
		WebElement ab= driver.findElement(By.xpath("//div[@class=\"nav-line-1-container\"]"));
		a= new Actions(driver);
		a.moveToElement(ab).build().perform();
		driver.findElement(By.xpath("//div[@id=\"nav-al-signin\"]/div/a")).click();
		FileInputStream fs = new FileInputStream("D:\\Infy_bmc\\BMC_UI\\test-input\\testdata.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fs);
		XSSFSheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(0);
		Cell cell1 = row.getCell(0);
		Cell cell2 = row.getCell(1);		
		String usrname=cell1.getStringCellValue();
		String pwd= cell2.getStringCellValue();
		System.out.println(usrname);
		System.out.println(pwd);
		driver.findElement(By.name("email")).sendKeys(usrname);
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.name("password")).sendKeys(pwd);
		driver.findElement(By.id("signInSubmit")).click();
		
		
		
		
				
	}
	
	@When("click on all buttons on search & select Electronics")
	
	public void seacrhElectronics() throws InterruptedException
	{
		Thread.sleep(5000);
		//driver.findElement(By.xpath("//select[@id=\"searchDropdownBox\"]")).click();
		//Thread.sleep(4000);
		Select s=new Select(driver.findElement(By.xpath("//select[@id=\"searchDropdownBox\"]")));
		s.selectByVisibleText("Electronics");
		
		
	}
	@When ("search for dell computers")
	public void search()
	{
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("dell computers");
		driver.findElement(By.xpath("//input[@id=\"nav-search-submit-button\"]")).click();
	}
	@When("apply the filter of range Rs {string} to {string}")
	public void range(String min, String max)
	{
		driver.findElement(By.name("low-price")).sendKeys(min);
		driver.findElement(By.name("high-price")).sendKeys(max);
		driver.findElement(By.xpath("//*[@class=\"a-spacing-micro\"]//input[@type=\"submit\"]")).click();
		
	}
	@When("Validate all the products on the first 2 pages are shown in the range of Rs {int} to {int}")
	public void verification(int a, int b) throws InterruptedException
	{
		System.out.println("First list of page");
		List<WebElement> li1=driver.findElements(By.xpath("//span[@class=\"a-price-whole\"]"));
		for(int i=0;i<li1.size();i++)
		{
			String range_firstlist=driver.findElements(By.xpath("//span[@class=\"a-price-whole\"]")).get(i).getText();
			//System.out.println(range_firstlist);
			range_firstlist = range_firstlist.replaceAll("[^a-zA-Z0-9]", "");
			
			int num= Integer.parseInt(range_firstlist);
			if(num>=a && num<=b)
			{
				System.out.println("Range is in between the values");
			}
			
		}
		driver.findElement(By.xpath("//a[@aria-label=\"Go to page 2\"]")).click();
		System.out.println("Second list of page");
		Thread.sleep(5000);
		List<WebElement> li2=driver.findElements(By.xpath("//span[@class=\"a-price-whole\"]"));
		for(int j=0;j<li2.size();j++)
		{
			String range_seconglist=driver.findElements(By.xpath("//span[@class=\"a-price-whole\"]")).get(j).getText();
			//System.out.println(range_seconglist);
			
			range_seconglist = range_seconglist.replaceAll("[^a-zA-Z0-9]", "");
			int num2= Integer.parseInt(range_seconglist);
			
			if(num2>=a && num2<=b)
			{
				System.out.println("Range is in between the values");
			}
			
		}
		
	}
	@When("print all the products on the first 2 pages whose rating is 5 out of 5")
	public void starrating() throws InterruptedException
	{
		driver.findElement(By.xpath("//a[@aria-label=\"Go to page 1\"]")).click();
		System.out.println("first page list of starrating");
		Thread.sleep(5000);
		List<WebElement> li3=driver.findElements(By.xpath("//span[contains(@class,'a-size-base puis')]"));
		
		for(int k=0; k<li3.size();k++)
		{
			String rating_firstpage=driver.findElements(By.xpath("//span[contains(@class,'a-size-base puis')]")).get(k).getText();
			
			String star=rating_firstpage.substring(0, 1);
			
			
			int starrating_firstpage=Integer.parseInt(star);
			if(starrating_firstpage==5)
			{
				System.out.println(starrating_firstpage +"  This item got 5 start rating");
			}
			
		}
		driver.findElement(By.xpath("//a[@aria-label=\"Go to page 2\"]")).click();
		Thread.sleep(5000);
		System.out.println("Second page list of starrating");
		List<WebElement> li4=driver.findElements(By.xpath("//span[contains(@class,'a-size-base puis')]"));
		for(int l=0; l<li4.size();l++)
		{
			String rating_secondpage=driver.findElements(By.xpath("//span[contains(@class,'a-size-base puis')]")).get(l).getText();
			String star_secongpage=rating_secondpage.substring(0, 1);
			
			int starrating_secondpage=Integer.parseInt(star_secongpage);
			if(starrating_secondpage==5)
			{
				System.out.println(starrating_secondpage + " This item got 5 start rating");
			}
			
		}
		
	}
	@When("add the first product whose rating is 5 out of 5 to the wish list")
	public void addcart() throws InterruptedException
	{
		driver.findElement(By.xpath("//a[@aria-label=\"Go to page 1\"]")).click();
		Thread.sleep(6000);
		List<WebElement> li5=driver.findElements(By.xpath("//span[contains(@class,'a-size-base puis')]"));
		List<WebElement> li6= driver.findElements(By.xpath("//h2//a//span"));
		for(int z=0; z<li5.size();z++)
		{
			String rating=driver.findElements(By.xpath("//span[contains(@class,'a-size-base puis')]")).get(z).getText();
			String starr=rating.substring(0, 1);
			
			int starrating=Integer.parseInt(starr);
			if(starrating==5)
			{
				
				System.out.println("This item got 5 start rating");
				String ad	= li6.get(z).getText();
				System.out.println(ad);
				added= ad.substring(0, 10);
				driver.findElements(By.xpath("//h2//a//span")).get(z).click();	
				Thread.sleep(5000);
				Set<String> s= driver.getWindowHandles();
				Iterator<String> i= s.iterator();
				while(i.hasNext())
				{
					String child=i.next();
					driver.switchTo().window(child);
				}
				
				
				JavascriptExecutor js= (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,1000)");
				driver.findElement(By.xpath("//span[@id=\"submit.add-to-cart\"]")).click();
				Thread.sleep(6000);
				List<WebElement> s1=driver.findElements(By.xpath("//a[@id=\"attach-close_sideSheet-link\"]"));
				for(int a=0; a<s1.size(); a++)
				{
					s1.get(a).click();
					break;
				}
					
				
				break;
			
				
				
			}
			
		}
		
			
		}
		
	
	@Then ("Validate the product is added to the wish list")
	public void cart() throws InterruptedException
	{

	JavascriptExecutor js= (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,-1000)");
	Thread.sleep(5000);
	driver.findElement(By.xpath("//div[@id=\"nav-cart-count-container\"]")).click();
	String actual=driver.findElement(By.xpath("//div[@data-name=\"Active Items\"]//span[@class=\"a-list-item\"]/a//span[@class=\"a-truncate-cut\"]")).getText();
	System.out.println(actual);
	String ac= actual.substring(0, 10);
	if(ac.matches(added))
	{
		System.out.println("Item added to the Cart");
		driver.quit();
	}
	else
	{
		System.out.println("Item not added to cart");
		Assert.fail();
	}
	
}

		
	}
	
	
	


