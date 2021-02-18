package com.github.mwduncan2018.eggplantreportconversion.pojos.eggplantjson;

public class Root {
	private Entries[] entries;
	private String createdTimestamp;

	public Entries[] getEntries() {
		return entries;
	}

	public void setEntries(Entries[] entries) {
		this.entries = entries;
	}

	public String getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(String createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

}
