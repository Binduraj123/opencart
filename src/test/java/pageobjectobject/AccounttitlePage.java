package pageobjectobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccounttitlePage extends BasePage {

	public AccounttitlePage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement title;
    @FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement logout;
	
	public boolean pagetitle() {
		try {
		return title.isDisplayed();
	}
		catch(Exception e) {
			return false;
		}
		
}
	public void logoutaction() {
		logout.click();
	}
	
}