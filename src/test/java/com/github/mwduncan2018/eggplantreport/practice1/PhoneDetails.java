package com.github.mwduncan2018.eggplantreport.practice1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "internal_memory", "display_size", "phone_name" })
public class PhoneDetails {

	@JsonProperty("phone_name")
	private String name;

	@JsonProperty("display_size")
	private String displaySize;

	@JsonProperty("internal_memory")
	private String memory;

	@JsonProperty("manufacturer")
	private Manufacturer manufacturer;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplaySize() {
		return displaySize;
	}

	public void setDisplaySize(String displaySize) {
		this.displaySize = displaySize;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public PhoneDetails() {
	}

	public PhoneDetails(String name, String displaySize, String memory, Manufacturer manufacturer) {
		this.name = name;
		this.displaySize = displaySize;
		this.memory = memory;
		this.manufacturer = manufacturer;
	}

}
