package com.github.mwduncan2018.eggplantreport.sandbox;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

class TestSandbox2 {

	@Test
	@Disabled
	void test02_createWordDoc() {
		String writePath = "C:\\AutomationResults\\test02.docx";
		try (XWPFDocument doc = new XWPFDocument(); FileOutputStream out = new FileOutputStream(new File(writePath));) {
			XWPFRun run;

			// Heading
			// This can be hard coded
			XWPFParagraph heading = doc.createParagraph();
			heading.setAlignment(ParagraphAlignment.CENTER);
			run = heading.createRun();
			run.setFontSize(20);
			run.setBold(true);
			run.setText("Automation Results");
			run.addCarriageReturn();

			// Date of report
			// String reportDateTime
			String reportDateTime = LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
			XWPFParagraph dateTime = doc.createParagraph();
			dateTime.setAlignment(ParagraphAlignment.CENTER);
			run = heading.createRun();
			run.setItalic(true);
			run.setText(reportDateTime);
			run.addCarriageReturn();

			// Summary heading
			// This can be hard coded
			XWPFParagraph summaryHeading = doc.createParagraph();
			summaryHeading.setAlignment(ParagraphAlignment.LEFT);
			run = summaryHeading.createRun();
			run.setFontSize(12);
			run.setUnderline(UnderlinePatterns.SINGLE);
			run.setText("Summary");
			run.addCarriageReturn();

			// Summary paragraph
			// List<String> testProcedureNames
			List<String> testProcedureNames = new ArrayList<String>();
			testProcedureNames.add("Ozone Smoke");
			testProcedureNames.add("MFWS Smoke");
			testProcedureNames.add("Stand Alone Software Smoke");
			XWPFParagraph summaryParagraph = doc.createParagraph();
			summaryParagraph.setAlignment(ParagraphAlignment.LEFT);
			run = summaryParagraph.createRun();
			run.setText("Number of test procedures: " + testProcedureNames.size());
			run.addCarriageReturn();
			for (String x : testProcedureNames) {
				run.setText(x);
				run.addCarriageReturn();
			}
			// total tests passed
			// total tests failed

			// Summary

			doc.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
