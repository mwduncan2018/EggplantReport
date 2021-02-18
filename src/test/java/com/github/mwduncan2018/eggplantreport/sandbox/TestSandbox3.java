package com.github.mwduncan2018.eggplantreport.sandbox;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class TestSandbox3 {

	@Test
	@Disabled
	void test0() {
		System.out.println(Instant.now().toEpochMilli());
	}
	
	@Test
	@Disabled
	void test1() {
		LocalTime localTime = LocalTime.now();
		System.out.println(localTime.toString());
	}
	
	@Test
	@Disabled
	void test2() {
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate.toString());
	}

	@Test
	@Disabled
	void test3() {
		String x = ("AutomationResults_" + LocalDate.now().toString() + "_" + LocalTime.now()).replaceAll("-|\\.|:", "_") + ".docx";
		System.out.println(x);
	}
}
