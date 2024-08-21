package TestCase;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import pageobjectobject.AccounttitlePage;
import pageobjectobject.HomePage;
import pageobjectobject.Loginpage;
import utility.DataProviderMethod;

public class LoginDataDrivenTest extends BaseClass {
	@Test(dataProvider="Logindata",dataProviderClass=DataProviderMethod.class,groups="datadriven")
	public void loginbuton(String email,String pwd, String exp) {
		logger.info("***** login testcase has started******");
		try {
		//homepage
		HomePage hpage=new HomePage(driver);
		hpage.clickonaccount();
		hpage.loginfunction();
		
		//loginpage
		Loginpage logint=new Loginpage(driver);
		logint.setemail(email);
		logint.setpassword(pwd);
		logint.clickonlogin();
		
		
		//accounttitlepage
		AccounttitlePage atp=new AccounttitlePage(driver);
		boolean texttitle=atp.pagetitle();
		
		
		//Assert.assertTrue(texttitle);//Assert.assertEquals(texttitle, true,"test failed");
		
		//validation of if the user pass the valid crenstionals or invalid
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(texttitle==true) {
				
				atp.logoutaction();
				Assert.assertTrue(true);
				
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid")) 
		{
			if(texttitle=true) 
			{
				atp.logoutaction();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
		}
		
	
		catch(Exception e) {
			Assert.fail();
		}
			
		
		
		logger.info("***** login testcase has ended******");
	
}
}

		
		

