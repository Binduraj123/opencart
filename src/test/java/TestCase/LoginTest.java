package TestCase;

import java.rmi.AccessException;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import pageobjectobject.AccounttitlePage;
import pageobjectobject.HomePage;
import pageobjectobject.Loginpage;

public class LoginTest  extends BaseClass{
	
	@Test(groups={"Regression","Master"})
	public void loginbuton() {
		logger.info("***** login testcase has started******");
		try {
		//homepage
		HomePage hpage=new HomePage(driver);
		hpage.clickonaccount();
		hpage.loginfunction();
		
		//loginpage
		Loginpage logint=new Loginpage(driver);
		logint.setemail(p.getProperty("email"));
		logint.setpassword(p.getProperty("password"));
		logint.clickonlogin();
		
		//accounttitlepage
		AccounttitlePage atp=new AccounttitlePage(driver);
		boolean texttitle=atp.pagetitle();
		Assert.assertTrue(texttitle);//Assert.assertEquals(texttitle, true,"test failed");
		logger.info("***** login testcase has ended******");
	}
catch(Exception e) {
	Assert.fail();
}
	
}
}
