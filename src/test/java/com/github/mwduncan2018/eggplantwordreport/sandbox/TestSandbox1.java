
package com.github.mwduncan2018.eggplantwordreport.sandbox;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

class TestSandbox1 {

	@Test
	void test07_datetime() {
		DateTimeFormatter x = DateTimeFormatter.ofPattern("M/d/yy h:mm:ss a");
		LocalDate.parse("7/9/20 8:30:10 PM", x);
	}
	
	@Test
	@Disabled
	void test06_fineTuned() {
		Path file = Paths.get("C:/AutomationResults/AutomationResults.txt");
		try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {

				if (line.contains("@WordReport")) {
					// Get Date
					String strDateTime = String.join(" ", Arrays.asList(line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim().split(" ")).subList(0, 3)).trim();
					// Get Status
					String status = Arrays.asList(line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim().split(" ")).get(3).trim();
					// Get Test Group Name
					String testGroupName = Arrays.asList(Arrays.asList(Arrays.asList(String.join(" ", Arrays.asList(line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim().split(" ")).subList(4, Arrays.asList(line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim().split(" ")).size())).split("\\)")).get(0).trim().split("\\(")).get(1).split(",")).get(0).trim();
					// Get Test Name
					String testName = Arrays.asList(Arrays.asList(Arrays.asList(String.join(" ", Arrays.asList(line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim().split(" ")).subList(4, Arrays.asList(line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim().split(" ")).size())).split("\\)")).get(0).trim().split("\\(")).get(1).split(",")).get(1).trim();
					// Get Message
					String message = Arrays.asList(String.join(" ", Arrays.asList(line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim().split(" ")).subList(4, Arrays.asList(line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim().split(" ")).size())).split("\\)")).get(1).trim();

					System.out.println(strDateTime);
					System.out.println(status);
					System.out.println(testGroupName);
					System.out.println(testName);
					System.out.println(message);
					System.out.println("");
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Disabled
	void test05_getDate_getStatus_getTestGroup_getTestName_getMessage() {
		Path file = Paths.get("C:/AutomationResults/AutomationResults.txt");
		try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {

				if (line.contains("@WordReport")) {
					line = line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim();
					List<String> lineList = Arrays.asList(line.split(" "));
					// Get Date
					String strDateTime = String.join(" ", lineList.subList(0, 3)).trim();
					// Get Status
					String status = lineList.get(3).trim();
					lineList = lineList.subList(4, lineList.size());
					String strWordReport = Arrays.asList(String.join(" ", lineList).split("\\)")).get(0).trim();
					// Get Test Group Name
					String testGroupName = Arrays.asList(Arrays.asList(strWordReport.split("\\(")).get(1).split(","))
							.get(0).trim();
					// Get Test Name
					String testName = Arrays.asList(Arrays.asList(strWordReport.split("\\(")).get(1).split(",")).get(1)
							.trim();
					// Get Message
					String message = Arrays.asList(String.join(" ", lineList).split("\\)")).get(1).trim();
					System.out.println(strDateTime);
					System.out.println(status);
					System.out.println(testGroupName);
					System.out.println(testName);
					System.out.println(message);
					System.out.println("");
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Disabled
	void test04_getDate_getStatus_getWordReport_getMessage() {
		Path file = Paths.get("C:/AutomationResults/AutomationResults.txt");
		try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {

				if (line.contains("@WordReport")) {
					line = line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim();
					List<String> lineList = Arrays.asList(line.split(" "));
					String strDateTime = String.join(" ", lineList.subList(0, 3)).trim();
					String status = lineList.get(3).trim();
					lineList = lineList.subList(4, lineList.size());
					String strWordReport = Arrays.asList(String.join(" ", lineList).split("\\)")).get(0).trim() + ")";
					String message = Arrays.asList(String.join(" ", lineList).split("\\)")).get(1).trim();
					System.out.println(strDateTime);
					System.out.println(status);
					System.out.println(strWordReport);
					System.out.println(message);
					System.out.println("");
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Disabled
	void test03_getDate_getStatus() {
		Path file = Paths.get("C:/AutomationResults/AutomationResults.txt");
		try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {

				if (line.contains("@WordReport")) {
					line = line.replace("\t", " ").replaceAll("\\s{2,}", " ").trim();
					List<String> lineList = Arrays.asList(line.split(" "));
					String strDateTime = String.join(" ", lineList.subList(0, 3));
					String status = lineList.get(3);
					lineList = lineList.subList(4, lineList.size());
					System.out.println(strDateTime + " " + status);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Disabled
	void test02_lineContainsWordReport() {
		Path file = Paths.get("C:/AutomationResults/AutomationResults.txt");
		try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {

				if (line.contains("@WordReport")) {
					System.out.println(line);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Disabled
	void test01_readLine() {
		Path file = Paths.get("C:/AutomationResults/AutomationResults.txt");
		try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
