package com.example.routingDB.thread;

public class DatabaseContext {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
	
	public static void setDatabaseKey(String key) {
		contextHolder.set(key);
	}
	
	public static String getDatabaseKey() {
		return contextHolder.get();
	}
	
	public static void clearDatabaseKey() {
		contextHolder.remove();
	}
}
