package com.github.mwduncan2018.eggplantreportconversion.pojos.eggplantjson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OtherProperties {
	private int[] foundRect;
	@JsonProperty("Info")
	private String info;
	@JsonProperty("Name")
	private String name;

	public int[] getFoundRect() {
		return foundRect;
	}

	public void setFoundRect(int[] foundRect) {
		this.foundRect = foundRect;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OtherProperties() {
	}

}
