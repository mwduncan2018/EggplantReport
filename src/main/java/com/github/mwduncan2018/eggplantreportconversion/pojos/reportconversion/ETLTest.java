package com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class ETLTest {
	private String name;
	private String message;
	private LocalDateTime dateTime;
	private Boolean status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
