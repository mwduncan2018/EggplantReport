package com.github.mwduncan2018.eggplantreportconversion.pojos.eggplantjson;

public class Root {
	private Entries[] entries;
	private String createdTimeStamp;

	public Entries[] getEntries() {
		return entries;
	}

	public void setEntries(Entries[] entries) {
		this.entries = entries;
	}

	public String getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(String createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

}
