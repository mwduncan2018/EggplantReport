package com.github.mwduncan2018.eggplantreport.practice2;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({ "name", "project", "tests", "started", "failures", "errors", "ignored" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonRootName("testrun")
public class PracticeTrun {

	@JacksonXmlProperty(isAttribute = true)
	private String name;
	@JacksonXmlProperty(isAttribute = true)
	private String project;
	@JacksonXmlProperty(isAttribute = true)
	private String tests;
	@JacksonXmlProperty(isAttribute = true)
	private String started;
	@JacksonXmlProperty(isAttribute = true)
	private String failures;
	@JacksonXmlProperty(isAttribute = true)
	private String errors;
	@JacksonXmlProperty(isAttribute = true)
	private String ignored;
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<PracticeTsuite> testsuite;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getTests() {
		return tests;
	}

	public void setTests(String tests) {
		this.tests = tests;
	}

	public String getStarted() {
		return started;
	}

	public void setStarted(String started) {
		this.started = started;
	}

	public String getFailures() {
		return failures;
	}

	public void setFailures(String failures) {
		this.failures = failures;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public String getIgnored() {
		return ignored;
	}

	public void setIgnored(String ignored) {
		this.ignored = ignored;
	}

	public List<PracticeTsuite> getTestsuite() {
		return testsuite;
	}

	public void setTestsuite(List<PracticeTsuite> testsuite) {
		this.testsuite = testsuite;
	}

	public PracticeTrun() {
		testsuite = new ArrayList<PracticeTsuite>();
	}

}
