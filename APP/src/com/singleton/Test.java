package com.singleton;

public class Test {

	public static void main(String[] args) {
		System.out.println("Singletin.test()---->" + Singleton.test());
		System.out.println("Singleton.test()----->" + Singleton.getInstance());
	}

}
