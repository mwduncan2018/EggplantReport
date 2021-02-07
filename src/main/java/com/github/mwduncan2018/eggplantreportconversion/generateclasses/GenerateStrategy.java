package com.github.mwduncan2018.eggplantreportconversion.generateclasses;

import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTestRun;

public abstract class GenerateStrategy {

	protected String writePath;

	public GenerateStrategy(String writePath) {
		this.writePath = writePath;
	}

	// Creates a Word report with the data found in a TestRun. The report is written
	// to the writeLocation.
	public abstract void generate(ETLTestRun testRun);
}