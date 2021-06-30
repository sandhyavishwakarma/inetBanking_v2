package com.inetBanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;


public class Reporting extends TestListenerAdapter
{
   public ExtentSparkReporter htmlReporter;
   public ExtentReports extent;//specify location of report
   public ExtentTest logger;//what details should be populated in the report
   
    public void onStart(ITestContext testContext)
  {
	  String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//TIME STAMP
	  String repName="Test-Report-"+timeStamp+".html";
	  
	  htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/" +repName);//SPECIFY LOCATION OF REPORT
	  try {
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"\\extent-config.xml");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	  extent=new ExtentReports();
	  
	  extent.attachReporter(htmlReporter);
	  extent.setSystemInfo("hostName", "localHost");
	  extent.setSystemInfo("Environment","QA");
	  extent.setSystemInfo("User","sandhya");
	  
	  htmlReporter.config().setDocumentTitle("InetBanking Test Project");//TITLE OF REPORT
	  htmlReporter.config().setReportName("Functional Test Automation Report");//NAME OF REPORT
	  //htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);//LOCATION OF CHART
	  htmlReporter.config().setTheme(Theme.DARK);
    }
	
      public void onTestSuccess(ITestResult tr)
	  {
		  logger=extent.createTest(tr.getName());//CREATE NEW ENTRY IN THE REPORT
		  logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));//SEND THE PASSED INFORMATION
	  }
	  
      public void onTestFailure(ITestResult tr)
	  {
		  logger=extent.createTest(tr.getName());//CREATE NEW ENTRY IN THE REPORT
		  logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));//SEND THE PASSED INFORMATION
		
	      String screenshotPath=System.getProperty("user.dir")+"\\screenshorts\\"+tr.getName()+".png";
	  
	      File f=new File(screenshotPath);
	 
	      if(f.exists())
	      {
	      // try
	      // {
	          logger.fail("Screenshort is below:"+logger.addScreenCaptureFromPath(screenshotPath));
	      //  }
	      // catch (IOException e)
	      // {
	       // 	e.printStackTrace();
	      // }
	    	            
	       }
	  
	   }
	 
      public void onTestSkipped(ITestResult tr)
	  {
		  logger=extent.createTest(tr.getName());//create new entry in the report
		  logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	  }
      
      public void onFinish(ITestContext testcontext)
      {
	     extent.flush();
      }

}
