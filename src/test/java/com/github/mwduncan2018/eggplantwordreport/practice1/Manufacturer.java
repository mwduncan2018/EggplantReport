package com.github.mwduncan2018.eggplantwordreport.practice1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({ "manufacturer_name", "country", "other_phones" })
public class Manufacturer {

	// @JsonProperty("manufacturer_name")
	@JacksonXmlProperty(isAttribute = true)
	private String manufacturer_name;

	@JsonProperty("country")
	private String country;

	@JacksonXmlElementWrapper(localName = "other_phones")
	private List<String> phone;

	public String getName() {
		return manufacturer_name;
	}

	public void setName(String name) {
		this.manufacturer_name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> getPhone() {
		return phone;
	}

	public void setPhone(List<String> phone) {
		this.phone = phone;
	}

	public Manufacturer() {
	}

	public Manufacturer(String name, String country, List<String> phone) {
		this.manufacturer_name = name;
		this.country = country;
		this.phone = phone;
	}

}
