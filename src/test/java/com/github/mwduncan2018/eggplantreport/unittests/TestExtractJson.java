package com.github.mwduncan2018.eggplantreport.unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.github.mwduncan2018.eggplantreportconversion.extractclasses.ExtractJson;
import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTestRun;

class TestExtractJson {

	private static String sampleJsonUnknownFields = "C:\\dev\\Java\\EggplantReport\\eggplant-report-conversion\\src\\test\\resources\\com\\github\\mwduncan2018\\eggplantreport\\unittests\\SampleJSONUnknownFields.json";
	private static String sampleJsonKnownFields = "C:\\dev\\Java\\EggplantReport\\eggplant-report-conversion\\src\\test\\resources\\com\\github\\mwduncan2018\\eggplantreport\\unittests\\SampleJSON.json";

	@Test
	void testExtractJsonExtractWithUnknownFields() {
		String fileName = sampleJsonUnknownFields;
		ExtractJson extractJson = new ExtractJson(fileName);
		ETLTestRun etlTestRun = extractJson.extract();
		assertEquals(4, etlTestRun.getTestProcedures().size());
	}

	@Test
	void testExtractJsonExtractWithKnownFields() {
		String fileName = sampleJsonKnownFields;
		ExtractJson extractJson = new ExtractJson(fileName);
		ETLTestRun etlTestRun = extractJson.extract();
		assertEquals(4, etlTestRun.getTestProcedures().size());
	}
}