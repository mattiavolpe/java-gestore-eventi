package org.java.events;

import java.time.LocalDate;

public class DateConverter {
	protected LocalDate getConvertedInputDate(String date) throws Exception {
		short[] dateContainer = new short[3];
		
		//		USING try / catch TO ALSO CATCH ERRORS ON Short.valueOf STATEMENTS
		try {
			dateContainer[0] = Short.valueOf(date.substring(0, date.indexOf("-")));
			dateContainer[1] = Short.valueOf(date.substring(date.indexOf("-") + 1, date.lastIndexOf("-")));
			dateContainer[2] = Short.valueOf(date.substring(date.lastIndexOf("-") + 1));			
			return LocalDate.of(dateContainer[2], dateContainer[1], dateContainer[0]);
		} catch (Exception e) {
			throw new Exception("Insert a valid date");
		}		
	}
}
