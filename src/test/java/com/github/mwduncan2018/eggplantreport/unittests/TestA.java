package com.github.mwduncan2018.eggplantreport.unittests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mwduncan2018.eggplantreportconversion.extractclasses.ExtractJson;
import com.github.mwduncan2018.eggplantreportconversion.pojos.eggplantjson.Root;

class TestA {

	@BeforeAll
	public static void beforeAll() {
	}

	@Test
	void testExtractJsonExtractWithUnknownFields() {
		String fileName = "C:/dev/Java/EggplantReport/eggplant-report-conversion/src/test/java/com/github/mwduncan2018/eggplantreport/unittests/SampleJSONUnknownFields.json";
		ExtractJson extractJson = new ExtractJson(fileName);
		extractJson.extract();
	}

	@Test
	void testExtractJsonExtractWithKnownFields() {
		String fileName = "C:/dev/Java/EggplantReport/eggplant-report-conversion/src/test/java/com/github/mwduncan2018/eggplantreport/unittests/SampleJSON.json";
		ExtractJson extractJson = new ExtractJson(fileName);
		extractJson.extract();
	}
}
