package com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion;

import java.util.ArrayList;
import java.util.List;

public class ETLTestProcedure {
	private String name;
	private List<ETLTest> tests;
 
	public ETLTestProcedure() {
		tests = new ArrayList<ETLTest>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ETLTest> getTests() {
		return tests;
	}

	public void setTests(List<ETLTest> tests) {
		this.tests = tests;
	}
	
	public int getTotalPassed() {
		return (int) tests.stream().filter(x -> x.getStatus().equals(true)).count();
	}
	
	public int getTotalFailed() {
		return (int) tests.stream().filter(x -> x.getStatus().equals(false)).count();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ETLTestProcedure)) {
			return false;
		}
		ETLTestProcedure testGroup = (ETLTestProcedure) obj;
		return name.equals(testGroup.getName());		
	}
}
