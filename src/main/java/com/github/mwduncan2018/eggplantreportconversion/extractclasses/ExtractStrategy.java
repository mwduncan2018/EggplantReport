package com.github.mwduncan2018.eggplantreportconversion.extractclasses;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTest;
import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTestProcedure;
import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTestRun;

public abstract class ExtractStrategy {

	protected ETLTestRun testRun;
	protected String readPath;
		
	public ExtractStrategy(String readPath) {
		this.readPath = readPath;
		this.testRun = new ETLTestRun();
	}

	// Extracts data of an Eggplant test run from a file.
	public abstract ETLTestRun extract();
	
	//For debugging purposes, print all test procedures
	protected void printTestProcedures() {
		ETLTestRun testRun = extract();
		System.out.println("Test Procedure Count = " + testRun.getTestProcedures().size() + "\n");
		for (ETLTestProcedure x : testRun.getTestProcedures()) {
			System.out.println("Test Procedure Name: " + x.getName());
			for (ETLTest y : x.getTests()) {
				System.out.println("   " + y.getName());
				System.out.println("   " + y.getMessage());
				System.out.println("   " + y.getStatus());
				System.out.println("   " + y.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				System.out.println("");
			}
		}
	}

}
