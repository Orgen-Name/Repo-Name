package com.singleton;

//静态类部类
public class Singleton {
	private static Singleton singleton;
	
	//私有构造函数
	private Singleton(){
		//加载你需要加载的东西
	}
	
	public static class SingletonHelper{
		private static final Singleton INSTANCE = new Singleton();
	}
	
	public static Singleton getInstance(){
		singleton = SingletonHelper.INSTANCE;
		return singleton;
	}
	
	public static Singleton test(){
		return singleton;
	}
}
