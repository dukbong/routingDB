package com.example.routingDB.routing.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DriverClassNameEnum {

	ORACLE("oracle", "oracleDriver"),
	H2("h2", "org.h2.Driver");
	
	private String driverName;
	private String driverClassName;
	
	public static String searchDriver(String driverName) {
		DriverClassNameEnum[] allDriver = DriverClassNameEnum.values();
		for(DriverClassNameEnum pick : allDriver) {
			if(driverName.equals(pick.getDriverName())) {
				return pick.getDriverClassName();
			}
		}
		throw new IllegalArgumentException("지원 하지 않는 드라이버 입니다.");
	}
}
