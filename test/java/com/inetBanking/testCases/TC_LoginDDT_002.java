package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.loginPage;
import com.inetBanking.utilities.XLUtils;

   public class TC_LoginDDT_002 extends BaseClass
   {
	   
    @Test(dataProvider="LoginData")
   public void loginDDT(String user,String pwd) throws InterruptedException
  {
	loginPage lp=new loginPage(driver);
	lp.setUserName(user);
	logger.info("user name provided");
	lp.setPassword(pwd);
	logger.info("password is provided");
	lp.clickSubmit();
	
	Thread.sleep(3000);
	
	if(isAlertPresent()==true)
	{
		driver.switchTo().alert().accept();//close alert
		driver.switchTo().defaultContent();
		Assert.assertTrue(false);
		logger.warn("login failed");
	}
	else
	{
		Assert.assertTrue(true);
		logger.warn("login passed");
		lp.clickLogout();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();//close logout alert
		driver.switchTo().defaultContent();
	}
  }
   
    
    public boolean isAlertPresent() //user defined method created to check alert is present or not
    {
    	try
    	{
    	driver.switchTo().alert();
    	return true;
    	}
    	catch(NoAlertPresentException e)
    	{
    		return false;
    	}
    	
    }
 
  @DataProvider(name="LoginData")
   String[][] getData() throws IOException
   {
	String path =System.getProperty("user.dir")+"/test/java/com/inetBanking/testData/LoginData.xlsx";
	
	int rownum=XLUtils.getRowCount(path,"Sheet1");
	int colcount=XLUtils.getCellCount(path,"Sheet1",1);
	
	String loginData[][]=new String[rownum][colcount];
	
	for(int i=1;i<=rownum;i++)
	{
		for(int j=0;j<colcount;j++)
		{
			loginData[i-1][j]=XLUtils.getCellData(path,"Sheet1",i,j);
			
		}
	}
	return loginData;
   }
			
		
	
   
}
