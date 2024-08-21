package pageobjectobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Loginpage extends BasePage {

	public Loginpage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement email;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement pwd;
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginbutton;
	
	public void setemail(String useremail) {
		email.sendKeys(useremail);
	}
	public void setpassword(String passwrd) {
		pwd.sendKeys(passwrd);
	}
	public void clickonlogin() {
		loginbutton.click();
	}
}
