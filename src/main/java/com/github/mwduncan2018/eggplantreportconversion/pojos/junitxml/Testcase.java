package com.github.mwduncan2018.eggplantreportconversion.pojos.junitxml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({ "name", "classname", "time", "displayname", "uniqueid" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Testcase {

	@JacksonXmlProperty(isAttribute = true)
	private String name;
	@JacksonXmlProperty(isAttribute = true)
	private String classname;
	@JacksonXmlProperty(isAttribute = true)
	private String time;
	@JacksonXmlProperty(isAttribute = true)
	private String displayname;
	@JacksonXmlProperty(isAttribute = true)
	private String uniqueid;
	private String failure;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
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

	public String getFailure() {
		return failure;
	}

	public void setFailure(String failure) {
		this.failure = failure;
	}

	public Testcase() {
	}

}
