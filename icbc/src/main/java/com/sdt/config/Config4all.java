/**
 * 
 */
package com.sdt.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.ini4j.Config;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;

import com.alibaba.druid.support.spring.mvc.StatHandlerInterceptor;
import com.google.common.io.Resources;

/**
 * @author liuqiang
 *
 */
public class Config4all {
	private static final String CONFIG_NAME = "config.ini";

	private Ini ini;
	public static Ini getIni(){
		Config config = new Config();
		URL url = Resources.getResource(CONFIG_NAME);
		config.setMultiSection(true);
		Ini ini = new Ini();
		ini.setConfig(config);

		try {
			ini.load(url);
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ini;
	}
	public static Properties getProperties(String configName) throws Exception{
		String path = Class.class.getClass().getResource("/").getPath() + configName;
		FileInputStream fis = new FileInputStream(path);
		Properties p = new Properties();
		p.load(fis);
		return p;
	}
	public static void main(String[] args){
		
		Ini ini = Config4all.getIni();
		Section section = ini.get("mysql");
		System.out.println(section.get("url"));
		
		try {
			Properties properties = Config4all.getProperties("dbconfig.properties");
			System.out.println(properties.get("url"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
