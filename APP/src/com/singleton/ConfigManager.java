package com.singleton;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	//实例
	private static ConfigManager configManger = new ConfigManager();
	//配置文件
	private static Properties properties;
	
	//定义私有的构造器
	private ConfigManager(){
		String configFile = "database.properties";
		properties = new Properties();
		InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
		try {
			properties.load(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//全局访问点     简单的单例模式，会存在线程不安全问题
	
	//懒汉模式
	public static ConfigManager getInstance(){
//		if(configManger == null){
//			configManger = new ConfigManager();
//		}
		return configManger;
	}
	
	//访问属性的方法
	public String getValue(String key){
		return properties.getProperty(key);
	}
}
