package com.example.routingDB.routing;

/***
 * key 기반으로 각 Thread 별로 별도의 DB를 연결
 */
public class DataSourceContextHolder {
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
	
	public static void setDataSourceKey(String key) {
		contextHolder.set(key);
	}
	
	public static String getDataSourceKey() {
		return contextHolder.get();
	}
	
	public static void clear() {
		contextHolder.remove();
	}

}
