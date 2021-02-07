package com.github.mwduncan2018.eggplantreportconversion.generateclasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.mwduncan2018.eggplantreportconversion.pojos.junitxml.Testcase;
import com.github.mwduncan2018.eggplantreportconversion.pojos.junitxml.Testrun;
import com.github.mwduncan2018.eggplantreportconversion.pojos.junitxml.Testsuite;
import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTest;
import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTestProcedure;
import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTestRun;

public class GenerateJunit extends GenerateStrategy {

	public GenerateJunit(String writePath) {
		super(writePath);
	}
	
	@Override
	public void generate(ETLTestRun etlTestRun) {
		serializeToXml(transformToJunitPojos(etlTestRun));
	}

	private Testrun transformToJunitPojos(ETLTestRun etlTestRun) {
		Testrun testrun = new Testrun();
		testrun.setName(etlTestRun.getName());
		testrun.setProject("SEC Automation Project");
		testrun.setTests(String.valueOf(etlTestRun.getTotalNumberOfTests()));
		testrun.setStarted(String.valueOf(etlTestRun.getTotalNumberOfTests()));
		testrun.setFailures(String.valueOf(etlTestRun.getTotalFailed()));
		testrun.setErrors("0");
		testrun.setIgnored("0");
		for (ETLTestProcedure etlTestProcedure : etlTestRun.getTestProcedures()) {
			Testsuite testsuite = new Testsuite();
			testsuite.setName(etlTestProcedure.getName());
			testsuite.setDisplayname(etlTestProcedure.getName());
			testsuite.setUniqueid("[engine:eggplant-functional]/[class:" + etlTestProcedure.getName() + "]");
			for (ETLTest etlTest : etlTestProcedure.getTests()) {
				Testcase testcase = new Testcase();
				testcase.setName(etlTest.getName());
				testcase.setClassname(etlTestProcedure.getName());
				testcase.setDisplayname(etlTest.getName());
				testcase.setUniqueid("[engine:eggplant-functional]/[class:" + etlTestProcedure.getName() + "]/[method:"
						+ etlTest.getName() + "]");
				if (etlTest.getStatus().equals(false)) {
					testcase.setFailure("Failure: " + etlTest.getName());
					if ((etlTest.getMessage() != null) && (etlTest.getMessage().length() > 0)) {
						testcase.setFailure(testcase.getFailure() + " -- " + etlTest.getMessage());
					}
				}
				testsuite.getTestcase().add(testcase);
			}
			testrun.getTestsuite().add(testsuite);
		}
		return testrun;
	}

	private void serializeToXml(Testrun testrun) {
		try {
			XmlMapper xmlMapper = new XmlMapper();

			String xmlStr = xmlMapper.writeValueAsString(testrun);

			File xmlOutput = new File(writePath);
			FileWriter fileWriter = new FileWriter(xmlOutput);
			fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + xmlStr);
			fileWriter.close();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
