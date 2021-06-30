package com.inetBanking.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.AddCustomerPage;
import com.inetBanking.pageObjects.loginPage;

public class TC_AddCustomerTest_003 extends BaseClass
{
	@Test
	public void addNewCustomer() throws InterruptedException, Exception
	{
		loginPage lp=new loginPage(driver);
		lp.setUserName(username);
		logger.info("username is provided");
		lp.setPassword(password);
		logger.info("password is provided");
		lp.clickSubmit();
		
		Thread.sleep(5000);
		
		AddCustomerPage addcust=new AddCustomerPage(driver);
		
		addcust.clickAddNewCustomer();
		
		logger.info("providing customer details....");
		
		addcust.custName("sandhya");
		addcust.custgender("female");
		addcust.custdob("10", "15", "1985");
		Thread.sleep(3000);
		addcust.custaddress("india");
		addcust.custcity("bhopal");
		addcust.custstate("MP");
		addcust.custpinno("462023");
		addcust.custtelephoneno("9009425849");
		
		String email=randomestring()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();
		
		Thread.sleep(5000);
		
		logger.info("validation started.....");
		
		boolean res=driver.getPageSource().contentEquals("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("test case is passed");
		}
		else
		{
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
			logger.info("test case is fail");
		}
	}
	
	
}
