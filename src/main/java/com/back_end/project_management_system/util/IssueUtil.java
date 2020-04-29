package com.back_end.project_management_system.util;

import org.springframework.stereotype.Component;

@Component
public class IssueUtil {
	
	public static final int minuteConvertion = 60 * 1000;
	public static final int hourConvertion = 60 * minuteConvertion;
	public static final int dayConvertion = 24 * hourConvertion;
	public static final int weekConvertion = 7 * dayConvertion;
	
	public long convertEstimatedTimeToMilliseconds(String estimatedTimeString) {
		
		long estimatedTime = 0;

		String[] array = estimatedTimeString.split(" ");

		for (int i = 0; i < array.length; i++) {
			if (!array[i].equals("")) {
				estimatedTime = estimatedTime + convertUnitToMilliseconds(array[i]);
			}
		}

		return estimatedTime;
	}
	
	private long convertUnitToMilliseconds(String eachUnit) {
		
		char unitCharacter = eachUnit.charAt(eachUnit.length()-1);
		
		long number = Integer.parseInt(eachUnit.substring(0, eachUnit.length()-1));
		
		long estimate = 0;
		
		switch (Character.toLowerCase(unitCharacter)) {
		case 'm':
			estimate = number * minuteConvertion;
			break;
		case 'h':
			estimate = number * hourConvertion;
			break;
		case 'd':
			estimate = number * dayConvertion;
			break;
		case 'w':
			estimate = number * weekConvertion;
		default:
			break;
		}
		return estimate;
	}

}
