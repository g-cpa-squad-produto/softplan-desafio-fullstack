package br.com.softplan.process.api.enums;

public enum StatusEnum {

	Pending,
	Canceled,
	Closed;
	
	public static StatusEnum getStatus(String status) {
		switch(status) {
		case "Pending": return Pending;
		case "Canceled": return Canceled;
		case "Closed": return Closed;
		default : return Pending;
		}
	}
}
