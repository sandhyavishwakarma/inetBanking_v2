package com.inetBanking.testCases;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.loginPage;

public class TC_LoginTest_001 extends BaseClass
{
@Test
public void loginTest() throws IOException
{
	
	logger.info("url is opened");
	
	loginPage lp=new loginPage(driver);
	lp.setUserName(username);
	logger.info("entered username");
	
	
	lp.setPassword(password);
	logger.info("entered password");
	
	
	lp.clickSubmit();
	
	if (driver.getTitle().equals("Guru99 Bank Manager HomePage...."))
	{
		Assert.assertTrue(true);
		logger.info("login test passed");
		
	}
	else
	{
		captureScreen(driver,"loginTest");
		Assert.assertFalse(false);
		logger.info("login test failed");
		
	}
	
}
}
