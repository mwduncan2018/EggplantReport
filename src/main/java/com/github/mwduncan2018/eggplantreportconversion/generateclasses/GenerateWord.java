package com.github.mwduncan2018.eggplantreportconversion.generateclasses;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.apache.poi.xwpf.usermodel.LineSpacingRule;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.github.mwduncan2018.eggplantreportconversion.extractclasses.ExtractStrategy;
import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTest;
import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTestProcedure;
import com.github.mwduncan2018.eggplantreportconversion.pojos.reportconversion.ETLTestRun;
import com.github.mwduncan2018.eggplantreportconversion.utilities.ColorToRGB;

public class GenerateWord extends GenerateStrategy {

	public GenerateWord(String writePath) {
		super(writePath);
	}
	
	protected String capitalize(String x) {
		return x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase();
	}
	
	protected String capitalizeEachWord(String x) {
		String [] words = x.trim().split("\\s+");
		for (int i = 0; i < words.length; i++) {
			words[i] = capitalize(words[i]);
		}
		return String.join(" ", words);
	}
	
	// Creates a Word report with the data found in a TestRun. The report is written to the writeLocation.
	public void generate(ETLTestRun etlTestRun) {
		try (XWPFDocument doc = new XWPFDocument(); FileOutputStream out = new FileOutputStream(new File(writePath));) {
			XWPFRun run;

			// Report Heading
			XWPFParagraph reportHeading = doc.createParagraph();
			reportHeading.setAlignment(ParagraphAlignment.CENTER);
			run = reportHeading.createRun();
			run.setFontSize(20);
			run.setBold(true);
			run.setColor(ColorToRGB.get("PURPLE"));
			run.setText("<SystemName>");
			run = reportHeading.createRun();
			run.setFontSize(20);
			run.setBold(true);
			run.setText(" Automation Results");
			run.addCarriageReturn();

			// Date, Version, Person Executing
			run = reportHeading.createRun();
			run.setItalic(true);
			run.setText(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
			run.addCarriageReturn();
			
			run = reportHeading.createRun();
			run.setItalic(true);
			run.setText("Executed by: ");
			run = reportHeading.createRun();
			run.setItalic(true);
			run.setColor(ColorToRGB.get("PURPLE"));
			run.setText("<YourName>");
			run.addCarriageReturn();

			run = reportHeading.createRun();
			run.setItalic(true);
			run.setText("Location: ");
			run = reportHeading.createRun();
			run.setItalic(true);
			run.setColor(ColorToRGB.get("PURPLE"));
			run.setText("<YourLocation>");
			run.addCarriageReturn();
			
			run = reportHeading.createRun();
			run.setItalic(true);
			run.setText("Version: ");
			run = reportHeading.createRun();
			run.setItalic(true);
			run.setColor(ColorToRGB.get("PURPLE"));
			run.setText("<SoftwareVersion#>");
			run.addCarriageReturn();

			// Instructions
			XWPFParagraph reportInstructions = doc.createParagraph();
			reportInstructions.setAlignment(ParagraphAlignment.LEFT);
			reportInstructions.setSpacingBetween(.9d);
			reportInstructions.setSpacingLineRule(LineSpacingRule.EXACT);
			run = reportInstructions.createRun();
			run.setFontSize(9);
			String instructions = "All failures should be manually reviewed. If a failure is legitimate, add a comment next to the failure. If a failure is not legitimate (i.e., image recognition problem in Eggplant, script timeout issues), comment next to the failure, update the pass/fail total for the corresponding test procedure, and update the pass/fail totals for the test run.";
			run.setText(instructions);
			run = reportInstructions.createRun();
			run.setFontSize(16);
			run.addCarriageReturn();

			// Test Run Summary
			XWPFParagraph testRunSummary = doc.createParagraph();
			testRunSummary.setAlignment(ParagraphAlignment.LEFT);
			testRunSummary.setSpacingBetween(1.5d);
			testRunSummary.setSpacingLineRule(LineSpacingRule.EXACT);
			
			run = testRunSummary.createRun();
			run.setFontSize(16);
			run.setBold(true);
			run.setText("Total Passed = ");

			run = testRunSummary.createRun();
			run.setFontSize(16);
			run.setBold(true);
			run.setColor(ColorToRGB.get("GREEN"));
			run.setText(etlTestRun.getTotalPassed() + " passed (" + etlTestRun.getPercentagePassed() + "%)");
			run.addCarriageReturn();
			
			run = testRunSummary.createRun();
			run.setFontSize(16);
			run.setBold(true);
			run.setText("Total Failed = ");
			
			run = testRunSummary.createRun();
			run.setFontSize(16);
			run.setBold(true);
			run.setColor(ColorToRGB.get("RED"));
			run.setText(etlTestRun.getTotalFailed() + " failed (" + etlTestRun.getPercentageFailed() + "%)");
			run.addCarriageReturn();

			// Test Procedures
			for (ETLTestProcedure tp : etlTestRun.getTestProcedures()) {

				XWPFParagraph testProcedure = doc.createParagraph();
				testProcedure.setAlignment(ParagraphAlignment.LEFT);
				testProcedure.setSpacingBetween(1.1d);
				testProcedure.setSpacingLineRule(LineSpacingRule.EXACT);
				run = testProcedure.createRun();
				run.setFontSize(12);
				run.setBold(true);
				run.setUnderline(UnderlinePatterns.SINGLE);
				run.setText(capitalizeEachWord(tp.getName()) + " - "
						+ tp.getTotalPassed() + " passed, "
						+ tp.getTotalFailed() + " failed");
				run.addCarriageReturn();

				for (ETLTest t : tp.getTests()) {
					run = testProcedure.createRun();
					if (t.getStatus().equals(false)) {
						run.setColor(ColorToRGB.get("RED"));
						if (t.getMessage() == null) {
							run.setText(capitalizeEachWord(t.getName()) + " failed");
						} else {
							run.setText(capitalizeEachWord(t.getName()) + " failed (" + t.getMessage() + ")");
						}
						run.addCarriageReturn();
					} else if (t.getStatus().equals(true)) {
						//run.setColor(ColorToRGB.get("DARKGREEN"));
						if (t.getMessage() == null) {
							run.setText(capitalizeEachWord(t.getName()) + " passed");
						} else {
							run.setText(capitalizeEachWord(t.getName()) + " passed (" + t.getMessage() + ")");
						}
						run.addCarriageReturn();
					}
				}

			}

			// Original Output
			XWPFParagraph eggOutput = doc.createParagraph();
			eggOutput.setSpacingBetween(.8d);
			eggOutput.setSpacingLineRule(LineSpacingRule.EXACT);
			
			run = eggOutput.createRun();
			run.setColor(ColorToRGB.get("BLUE3"));
			run.setBold(true);
			run.setUnderline(UnderlinePatterns.SINGLE);
			run.addCarriageReturn();
			run.addCarriageReturn();
			run.setText("Eggplant Test Run Output");
			run.addCarriageReturn();

			run = eggOutput.createRun();
			run.setColor(ColorToRGB.get("BLUE3"));
			run.setFontSize(7);
			for (String s : etlTestRun.getInputFileText()) {
				if (s.length() > 5) {
					run.setText(s);
					run.addCarriageReturn();					
				}
			}

			/*
			// Tests not currently implemented in the automation
			XWPFParagraph autoNotImpl = doc.createParagraph();
			autoNotImpl.setSpacingBetween(0.005d);
			run = autoNotImpl.createRun();
			run.setColor(ColorToRGB.get("ORANGE"));
			run.setBold(true);
			run.setUnderline(UnderlinePatterns.SINGLE);
			// run.addBreak(BreakType.PAGE);
			run.addCarriageReturn();
			run.addCarriageReturn();
			run.setText("Tests NOT currently implemented in the automation");
			run.addCarriageReturn();

			run = autoNotImpl.createRun();
			run.setColor(ColorToRGB.get("ORANGE"));
			run.setFontSize(7);
			run.setText("<testProcedureName> - <testName>");
			run.addCarriageReturn();
			run.setText("<testProcedureName> - <testName>");
			run.addCarriageReturn();
			run.setText("<testProcedureName> - <testName>");
			run.addCarriageReturn();
			run.setText("<testProcedureName> - <testName>");
			run.addCarriageReturn();
			run.setText("<testProcedureName> - <testName>");
			run.addCarriageReturn();
			run.setText("... Add these later");
			*/
			doc.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
