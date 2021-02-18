package com.github.mwduncan2018.eggplantwordreport.sandbox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.mwduncan2018.eggplantwordreport.practice1.Manufacturer;
import com.github.mwduncan2018.eggplantwordreport.practice1.PhoneDetails;
import com.github.mwduncan2018.eggplantwordreport.practice2.PracticeTcase;
import com.github.mwduncan2018.eggplantwordreport.practice2.PracticeTrun;
import com.github.mwduncan2018.eggplantwordreport.practice2.PracticeTsuite;

class TestSandbox4 {

	@Test
	@Disabled
	void deserializeXmlToObject() {
		try {
			XmlMapper xmlMapper = new XmlMapper();

			String readContent = new String(Files.readAllBytes(Paths.get("C:\\__Sandbox__\\DeserializeThis.xml")));
			PhoneDetails deserializedData = xmlMapper.readValue(readContent, PhoneDetails.class);

			System.out.println("Deserialized Data:");
			System.out.println("\tName: " + deserializedData.getName());
			System.out.println("\tMemory: " + deserializedData.getMemory());
			System.out.println("\tDisplay Size: " + deserializedData.getDisplaySize());
			System.out.println("\tManufacturer Name: " + deserializedData.getManufacturer().getName());
			System.out.println("\tManufacturer Country: " + deserializedData.getManufacturer().getCountry());
			System.out.println(
					"\tManufacturer Other Phones: " + deserializedData.getManufacturer().getPhone().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Disabled
	void serializeObjectToXml() {
		try {
			XmlMapper xmlMapper = new XmlMapper();

			List<String> otherPhones = Arrays.asList("AlphaPlus", "BetaPlus", "CharliePlus");
			Manufacturer manufacturer = new Manufacturer("Duncan", "USA", otherPhones);

			String xmlStr = xmlMapper.writeValueAsString(new PhoneDetails("OnePlus", "6.4", "6/64 GB", manufacturer));
			System.out.println(xmlStr);

			File xmlOutput = new File("C:\\__Sandbox__\\Serialized.xml");
			FileWriter fileWriter = new FileWriter(xmlOutput);
			fileWriter.write(xmlStr);
			fileWriter.close();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	@Disabled
	void serializeTestcaseToXml() {
		try {
			XmlMapper xmlMapper = new XmlMapper();

			PracticeTcase testcase = new PracticeTcase();
			testcase.setName("runtime_map");
			testcase.setClassname("TestProcedures/MAIN_GEOINT_SMOKE.script");
			testcase.setTime("25.291");
			testcase.setDisplayname("on_runtime_map");
			testcase.setUniqueid(
					"[engine:eggplant-functional]/[class:TestProcedures/MAIN_GEOINT_SMOKE.script]/[method:on_runtime_map]");
			testcase.setFailure("");

			String xmlStr = xmlMapper.writeValueAsString(testcase);
			System.out.println(xmlStr);

			File xmlOutput = new File("C:\\__Sandbox__\\TestcaseSerialize.xml");
			FileWriter fileWriter = new FileWriter(xmlOutput);
			fileWriter.write(xmlStr);
			fileWriter.close();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Disabled
	void deserializeXmlToTestcase() {
		try {
			XmlMapper xmlMapper = new XmlMapper();

			String readContent = new String(Files.readAllBytes(Paths.get("C:\\__Sandbox__\\TestcaseDeserialize.xml")));
			PracticeTcase data = xmlMapper.readValue(readContent, PracticeTcase.class);

			System.out.println("Deserialized Testcase Data:");
			System.out.println("\tTestcase.name: " + data.getName());
			System.out.println("\tTestcase.classname: " + data.getClassname());
			System.out.println("\tTestcase.time: " + data.getTime());
			System.out.println("\tTestcase.uniqueid: " + data.getUniqueid());
			System.out.println("\tTestcase.failure: " + data.getFailure());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Disabled
	void serializeTestsuiteToXml() {
		try {
			XmlMapper xmlMapper = new XmlMapper();

			PracticeTcase testcase = new PracticeTcase();
			testcase.setName("runtime_map");
			testcase.setClassname("TestProcedures/MAIN_GEOINT_SMOKE.script");
			testcase.setTime("25.291");
			testcase.setDisplayname("on_runtime_map");
			testcase.setUniqueid(
					"[engine:eggplant-functional]/[class:TestProcedures/MAIN_GEOINT_SMOKE.script]/[method:on_runtime_map]");
			testcase.setFailure("");

			PracticeTsuite testsuite = new PracticeTsuite();
			testsuite.setName("test suite name");
			testsuite.setTime("0.000");
			testsuite.setDisplayname("test suite display name");
			testsuite.setUniqueid("test suite uniqueid");
			testsuite.getTestcase().add(testcase);

			String xmlStr = xmlMapper.writeValueAsString(testsuite);
			System.out.println(xmlStr);

			File xmlOutput = new File("C:\\__Sandbox__\\TestsuiteSerialize.xml");
			FileWriter fileWriter = new FileWriter(xmlOutput);
			fileWriter.write(xmlStr);
			fileWriter.close();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Disabled
	void serializeTestrunToXml() {
		try {
			XmlMapper xmlMapper = new XmlMapper();

			PracticeTcase testcaseA = new PracticeTcase();
			testcaseA.setName("runtime_map");
			testcaseA.setClassname("TestProcedures/MAIN_GEOINT_SMOKE.script");
			testcaseA.setTime("25.291");
			testcaseA.setDisplayname("on_runtime_map");
			testcaseA.setUniqueid(
					"[engine:eggplant-functional]/[class:TestProcedures/MAIN_GEOINT_SMOKE.script]/[method:on_runtime_map]");
			testcaseA.setFailure("");

			PracticeTcase testcaseB = new PracticeTcase();
			testcaseB.setName("global_filter");
			testcaseB.setClassname("TestProcedures/MAIN_GEOINT_SMOKE.script");
			testcaseB.setTime("25.234");
			testcaseB.setDisplayname("on_global_filter");
			testcaseB.setUniqueid(
					"[engine:eggplant-functional]/[class:TestProcedures/MAIN_GEOINT_SMOKE.script]/[method:on_global_filter]");
			testcaseB.setFailure("Error: @Report(GEOINT, Global Filter)");

			PracticeTcase testcaseC = new PracticeTcase();
			testcaseC.setName("zabbix");
			testcaseC.setClassname("TestProcedures/MAIN_OZONE_SMOKE.script");
			testcaseC.setTime("22.234");
			testcaseC.setDisplayname("on_zabbix");
			testcaseC.setUniqueid(
					"[engine:eggplant-functional]/[class:TestProcedures/MAIN_OZONE_SMOKE.script]/[method:on_zabbix]");
			testcaseC.setFailure("");

			PracticeTsuite testsuite1 = new PracticeTsuite();
			testsuite1.setName("test suite #1");
			testsuite1.setTime("0.000");
			testsuite1.setDisplayname("test suite display name #1");
			testsuite1.setUniqueid("test suite uniqueid #1");
			testsuite1.getTestcase().add(testcaseA);
			testsuite1.getTestcase().add(testcaseB);

			PracticeTsuite testsuite2 = new PracticeTsuite();
			testsuite2.setName("test suite #2");
			testsuite2.setTime("0.000");
			testsuite2.setDisplayname("test suite display name #2");
			testsuite2.setUniqueid("test suite uniqueid #2");
			testsuite2.getTestcase().add(testcaseC);
			
			PracticeTrun testrun = new PracticeTrun();
			testrun.setName("Sandbox4");
			testrun.setProject("Coding in bedroom");
			testrun.setTests("3");
			testrun.setStarted("3");
			testrun.setFailures("1");
			testrun.setErrors("0");
			testrun.setIgnored("0");
			testrun.getTestsuite().add(testsuite1);
			testrun.getTestsuite().add(testsuite2);

			String xmlStr = xmlMapper.writeValueAsString(testrun);
			System.out.println(xmlStr);

			File xmlOutput = new File("C:\\__Sandbox__\\TestrunSerialize.xml");
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
