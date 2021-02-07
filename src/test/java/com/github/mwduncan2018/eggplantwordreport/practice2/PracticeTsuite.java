package com.github.mwduncan2018.eggplantwordreport.practice2;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({ "name", "time", "displayname", "uniqueid" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PracticeTsuite {

	@JacksonXmlProperty(isAttribute = true)
	private String name;
	@JacksonXmlProperty(isAttribute = true)
	private String time;
	@JacksonXmlProperty(isAttribute = true)
	private String displayname;
	@JacksonXmlProperty(isAttribute = true)
	private String uniqueid;
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<PracticeTcase> testcase;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	public List<PracticeTcase> getTestcase() {
		return testcase;
	}

	public void setTestcase(List<PracticeTcase> testcase) {
		this.testcase = testcase;
	}

	public PracticeTsuite() {
		testcase = new ArrayList<PracticeTcase>();
	}

}
