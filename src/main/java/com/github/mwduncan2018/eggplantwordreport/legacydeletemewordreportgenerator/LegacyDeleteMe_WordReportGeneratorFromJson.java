package com.github.mwduncan2018.eggplantwordreport.legacydeletemewordreportgenerator;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mwduncan2018.eggplantreportconversion.pojos.eggplantjson.Entries;
import com.github.mwduncan2018.eggplantreportconversion.pojos.eggplantjson.Root;
import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTest;
import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTestProcedure;

public class LegacyDeleteMe_WordReportGeneratorFromJson extends LegacyDeleteMe_WordReportGenerator {

	private Root root;

	public LegacyDeleteMe_WordReportGeneratorFromJson(String readPath, String writePath) {
		super(readPath, writePath);
	}

	private void unmarshalJson() {
		// Using Jackson to unmarshal the Eggplant test run JSON
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			File file = new File(readPath);
			root = objectMapper.readValue(file, Root.class);
		} catch (IOException e) {
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
		Arrays.asList(root.getEntries()).stream().filter(x -> x.getScriptName() != null).forEach(y -> {
			// Set the values for the test
			ETLTest test = new ETLTest();
			test.setName(y.getHandlerName().replace("on", "").replace("_", " ").trim().toUpperCase());
			String message = y.getMessage().replaceAll("@WordReport\\([a-zA-Z ]*,", "").trim()
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
			inputFileText.add(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(root));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void extract() {
		unmarshalJson();
		addTestProceduresToTestRun();
		addTestResultsToTheTestRun();
		addJsonToTheTestRun();
	}

}
