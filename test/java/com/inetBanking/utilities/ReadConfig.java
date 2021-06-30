package com.inetBanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;
	
	public ReadConfig()
	{
		File src =new File("./configurations/config.properties");
		
		try {
			FileInputStream fis=new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);
		}catch(Exception e) {
			System.out.println("exception is"+e.getMessage());
			
			
		}
		
	}
	
public String getApplicationURL()
{
	String url=pro.getProperty("BaseURL");
	return url;
}

public String getUsername()
{
	String username=pro.getProperty("username");
	return username;
}
public String getPassword()
{
	String password=pro.getProperty("password");
	return password;
}
public String getChromePath()
{
	String ChromePath=pro.getProperty("ChromePath");
	return ChromePath;
}
public String getIEPath()
{
	String Iepath=pro.getProperty("IePath");
	return Iepath;
}

public String getFireFoxPath() 
{
	
	return null;
}


}



