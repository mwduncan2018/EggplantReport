package com.github.mwduncan2018.eggplantreport.sandbox;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mwduncan2018.eggplantreportconversion.pojos.eggplantjson.Root;

class TestSandbox5 {

	@Test
	void givenJsonHasUnknownValuesBugJacksonIsIgnoringUnknowns_whenDesiralizing_thenCorrect() throws IOException {
		String fileName = "com/github/mwduncan2018/eggplantreport/unittests/SampleJSONUnknownFields.json";
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		Root root = objectMapper.readValue(inputStream, Root.class);
	}

}
