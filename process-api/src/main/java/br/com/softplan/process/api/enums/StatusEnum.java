package br.com.softplan.process.api.enums;

public enum StatusEnum {

	Pending,
	Analyzing,
	Canceled,
	Closed;
	
	public static StatusEnum getStatus(String status) {
		switch(status) {
		case "Pending": return Pending;
		case "Analyzing": return Analyzing;
		case "Canceled": return Canceled;
		case "Closed": return Closed;
		default : return Pending;
		}
	}
}
