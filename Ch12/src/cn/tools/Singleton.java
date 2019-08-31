package cn.tools;

public class Singleton {
	private static Singleton singleton;
	private Singleton() {
		
	}
	public static class SingletonHelper{

		private static final Singleton INSTANCE = new Singleton();
	}
	
	public static Singleton getInstance() {
		singleton=SingletonHelper.INSTANCE;
		return singleton;
	}
	
	public static Singleton test() {
		return singleton;
	}
}

