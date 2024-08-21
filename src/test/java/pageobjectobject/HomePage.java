package pageobjectobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	

	
	public HomePage(WebDriver driver) {
		super(driver);
		
	}
		
		
		@FindBy(xpath="//a[@title='My Account']")
		WebElement account;
		@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
		WebElement register;
		@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
		WebElement login;
		
		
		public void clickonaccount() {
			account.click();
		}
		public void clickonregister() {
			register.click();
		}
		public void loginfunction() {
			login.click();
		}
}

