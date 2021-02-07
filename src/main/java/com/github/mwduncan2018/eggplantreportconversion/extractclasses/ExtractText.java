package com.github.mwduncan2018.eggplantreportconversion.extractclasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTest;
import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTestProcedure;
import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTestRun;

public class ExtractText extends ExtractStrategy {

	public ExtractText(String readPath) {
		super(readPath);
	}

	@Override
	public ETLTestRun extract() {
		Path file = Paths.get(readPath);
		try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {
				testRun.getInputFileText().add(line);

				if (line.contains("@Report")) {
					ETLTest test = new ETLTest();

					// Get Test Name
					String testName = Arrays.asList(Arrays.asList(Arrays
							.asList(String.join(" ",
									Arrays.asList(line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim().split(" "))
											.subList(4,
													Arrays.asList(line.replace("\t", " ").replaceAll("\\s{2,}", " ")
															.trim().split(" ")).size()))
									.split("\\)"))
							.get(0).trim().split("\\(")).get(1).split(",")).get(1).trim();
					test.setName(testName);

					// Get Message
					String message;
					if (Arrays.asList(String.join(" ",
							Arrays.asList(line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim().split(" "))
									.subList(4, Arrays.asList(
											line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim().split(" "))
											.size()))
							.split("\\)")).size() > 1) {
						message = Arrays.asList(String.join(" ",
								Arrays.asList(line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim().split(" "))
										.subList(4, Arrays.asList(
												line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim().split(" "))
												.size()))
								.split("\\)")).get(1).trim();
					} else {
						message = null;
					}
					test.setMessage(message);

					// Get Status
					String status = Arrays.asList(line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim().split(" "))
							.get(3).trim();
					if (status.equals("LogSuccess")) {
						test.setStatus(true);
					} else if (status.equals("LogError")) {
						test.setStatus(false);
					}

					// Get Date
					String strDateTime = String.join(" ", Arrays
							.asList(line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim().split(" ")).subList(0, 3))
							.trim();
					test.setDateTime(
							LocalDateTime.parse(strDateTime, DateTimeFormatter.ofPattern("M/d/yy, h:mm:ss a")));

					// Get Test Group Name
					String testProcedureName = Arrays.asList(Arrays.asList(Arrays
							.asList(String.join(" ",
									Arrays.asList(line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim().split(" "))
											.subList(4,
													Arrays.asList(line.replace("\t", " ").replaceAll("\\s{2,}", " ")
															.trim().split(" ")).size()))
									.split("\\)"))
							.get(0).trim().split("\\(")).get(1).split(",")).get(0).trim();

					ETLTestProcedure testProcedure = testRun.getTestProcedures().stream()
							.filter(x -> x.getName().equals(testProcedureName)).findFirst().orElse(null);
					if (testProcedure == null) {
						testProcedure = new ETLTestProcedure();
						testProcedure.setName(testProcedureName);
						testProcedure.getTests().add(test);
						testRun.getTestProcedures().add(testProcedure);
					} else {
						testProcedure.getTests().add(test);
					}
				}
			}
			// For debugging purposes
			// printTestProcedures();
		} catch (IOException e) {
			System.out.println("An exception occurred while processing the text file. Check to see that the path to the text file is correct.");
			e.printStackTrace();
		}
		return testRun;
	}

}
