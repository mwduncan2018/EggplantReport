package com.github.mwduncan2018.eggplantreportconversion.extractclasses;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mwduncan2018.eggplantreportconversion.pojos.eggplantjson.Root;
import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTest;
import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTestProcedure;
import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTestRun;

public class ExtractJson extends ExtractStrategy {

	private Root root;

	public ExtractJson(String readPath) {
		super(readPath);
	}

	private void unmarshalJson() {
		// Using Jackson to unmarshal the Eggplant test run JSON
		try {
			ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			File file = new File(readPath);
			root = objectMapper.readValue(file, Root.class);
		} catch (IOException e) {
			System.out.println(
					"An exception occurred while processing the JSON file. Check to see that the path to the JSON file is correct.");
			e.printStackTrace();
		}
	}

	private void addTestProceduresToTestRun() {
		// Create test procedures and put them in the test run
		Arrays.asList(root.getEntries()).stream().filter(x -> x.getScriptName() != null).forEach(y -> {
			ETLTestProcedure testProcedure = new ETLTestProcedure();
			String testProcedureName = y.getScriptName().replace("TestProcedures/", "").replace(".script", "")
					.replace("_", " ").replace("MAIN", "").replace("Main", "").trim().toUpperCase();
			testProcedure.setName(testProcedureName);
			if (!testRun.getTestProcedures().contains(testProcedure)) {
				testRun.getTestProcedures().add(testProcedure);
			}
		});
	}

	private void addTestResultsToTheTestRun() {
		// Populate the test procedures with the tests
		Arrays.asList(root.getEntries()).stream()
				.filter(x -> (x.getScriptName() != null
						&& (x.getIdentifier().equals("LogSuccess") || x.getIdentifier().equals("LogError"))))
				.forEach(y -> {
					// Set the values for the test
					ETLTest test = new ETLTest();
					test.setName(y.getHandlerName().replace("on_", "").replace("_", " ").trim().toUpperCase());
					String message = y.getMessage().replaceAll("@Report\\([a-zA-Z ]*,", "").trim()
							.replaceAll("[a-zA-Z ]*\\)", "").trim();
					message = message.equals("") ? null : message;
					test.setMessage(message);
					test.setStatus((y.getIdentifier().equals("LogSuccess")) ? true : false);
					test.setDateTime(null); // Implement later if needed
					// Add the test to the test procedure
					String testProcedureName = y.getScriptName().replace("TestProcedures/", "").replace(".script", "")
							.replace("_", " ").replace("MAIN", "").replace("Main", "").trim().toUpperCase();
					testRun.getTestProcedures().stream().filter(z -> z.getName().equals(testProcedureName))
							.forEach(a -> a.getTests().add(test));
				});
	}

	private void addJsonToTheTestRun() {
		try {
			testRun.getInputFileText()
					.add(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(root));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ETLTestRun extract() {
		unmarshalJson();
		addTestProceduresToTestRun();
		addTestResultsToTheTestRun();
		addJsonToTheTestRun();
		return testRun;
	}

}
