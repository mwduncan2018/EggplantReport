package com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion;

import java.util.ArrayList;
import java.util.List;

public class ETLTestRun {
	private List<ETLTestProcedure> testProcedures;
	private List<String> inputFileText; // The text from the input file
	private String name;
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getInputFileText() {
		return inputFileText;
	}
	public void setInputFileText(List<String> inputFileText) {
		this.inputFileText = inputFileText;
	}
	
	public ETLTestRun() {
		testProcedures = new ArrayList<ETLTestProcedure>();
		inputFileText = new ArrayList<String>();
		name = "";
	}
	
	public List<ETLTestProcedure> getTestProcedures() {
		return testProcedures;
	}

	public void setTestProcedures(List<ETLTestProcedure> testProcedures) {
		this.testProcedures = testProcedures;
	}

	public int getTotalPassed() {
		return (int) testProcedures.stream()
				.mapToDouble(x -> x.getTests().stream().filter(y -> y.getStatus().equals(true)).count()).sum();
	}

	public int getTotalFailed() {
		return (int) testProcedures.stream()
				.mapToDouble(x -> x.getTests().stream().filter(y -> y.getStatus().equals(false)).count()).sum();
	}
	
	public int getTotalNumberOfTests() {
		return (int) testProcedures.stream()
				.mapToDouble(x -> x.getTests().stream().count()).sum();
	}
	
	public long getPercentagePassed() {
		double result = (double) getTotalPassed() / getTotalNumberOfTests() * 100;
		return Math.round(result);
	}
	
	public long getPercentageFailed() {
		double result = (double) getTotalFailed() / getTotalNumberOfTests() * 100;
		return Math.round(result);
	}
}
