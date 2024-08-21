package TestCase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.rmi.AccessException;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import pageobjectobject.AccountRegistrationPage;
import pageobjectobject.HomePage;

public class TC_001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void verify_account_registration()
	{
		
		logger.info(" *****starting the acoountregistrationlink testcase***");
		logger.debug("**apllication logs**");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickonaccount();
		logger.info(" clicked on Myaccount");
		hp.clickonregister();
		
		logger.info(" clicked on register");
		
		
		logger.info(" entering the user details");
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		regpage.setname(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");// randomly generated the email
		regpage.setTelephone(randomeNumber());
		
		String password=randomAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		logger.info(" clicked on continue");
		
		Thread.sleep(3000);
		String confmsg=regpage.getmessageconfirmation();
		
		logger.info(" validating the expected messesage");
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e){
			logger.error("**test failed**");
			logger.debug("**application logs end");
			Assert.fail();
		}
		
		logger.info("***finished the accountregistrationlink***");
	}
	
}