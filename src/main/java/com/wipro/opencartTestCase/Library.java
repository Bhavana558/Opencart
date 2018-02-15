package com.wipro.opencartTestCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Library {
	public static void loadProperties() throws IOException {
	    File file = new File("C://Users//BH395801//workspace//Opencart1//Objrepo.properties");	
	    FileInputStream fis = new FileInputStream(file);
	    Properties pro = new Properties();
	    pro.load(fis);

	}
}
