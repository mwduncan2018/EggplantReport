package com.github.mwduncan2018.eggplantreportconversion;

import java.util.Arrays;
import java.util.List;

import com.github.mwduncan2018.eggplantreportconversion.extractclasses.ExtractJson;
import com.github.mwduncan2018.eggplantreportconversion.extractclasses.ExtractStrategy;
import com.github.mwduncan2018.eggplantreportconversion.extractclasses.ExtractText;
import com.github.mwduncan2018.eggplantreportconversion.generateclasses.GenerateJunit;
import com.github.mwduncan2018.eggplantreportconversion.generateclasses.GenerateStrategy;
import com.github.mwduncan2018.eggplantreportconversion.generateclasses.GenerateWord;

public class Main {
	private static String argInputType;
	private static String argInputLocation;
	private static String argOutputType;
	private static String argOutputLocation;
	private static List<String> argsList;
	private static ExtractStrategy extractStrategy;
	private static GenerateStrategy generateStrategy;

	public static void main(String[] args) {
		System.out.println("Running EggplantReportConversion.jar");

		argsList = Arrays.asList(args);
		try {
			// Display help if requested
			if (argsList.get(0).equals("-help") || argsList.get(0).equals("--help")) {
				displayHelp();
				return;
			}
			// Display help if 4 arguments are not provided
			if (argsList.size() != 4) {
				displayHelp();
				return;
			}
			// Display help if the first argument is not -text or -json
			argInputType = argsList.get(0);
			argInputLocation = argsList.get(1);
			if (!argInputType.equals("-json") && !argInputType.equals("-text")) {
				System.out.println("The first argument must be -text or -json");
				displayHelp();
				return;
			}
			// Display help if the third argument is not -word or -junit
			argOutputType = argsList.get(2);
			argOutputLocation = argsList.get(3);
			if (!argOutputType.equals("-word") && !argOutputType.equals("-junit")) {
				System.out.println("The third argument must be -word or -junit");
				displayHelp();
				return;
			}
		} catch (Exception e) {
			// Display help is an exception was thrown
			displayHelp();
			return;
		}

		// The first argument determines the format of the file where data is being
		// extracted.
		// ExtractStrategy is the abstract base class.
		// ExtractJson knows how to extract from the Eggplant JSON format.
		// ExtractText knows how to extract from the Eggplant text format.
		if (argInputType.equals("-json")) {
			extractStrategy = new ExtractJson(argInputLocation);
		} else if (argInputType.equals("-text")) {
			extractStrategy = new ExtractText(argInputLocation);
		}

		// The third argument determines the format of the file for the report being
		// generated.
		// GenerateStrategy is the abstract base class.
		// GenerateJunit knows how to generate a report in the JUnit XML format.
		// GenerateWord knows how to generate a report in the Word docx format.
		if (argOutputType.equals("-junit")) {
			generateStrategy = new GenerateJunit(argOutputLocation);
		} else if (argOutputType.equals("-word")) {
			generateStrategy = new GenerateWord(argOutputLocation);
		}

		// The extract() method of ExtractStrategy return POJOs.
		// Those POJOs are the input for the generate() method of GenerateStrategy.
		generateStrategy.generate(extractStrategy.extract());

		System.out.println("Complete");
	}

	private static void displayHelp() {
		System.out.println(
				"\nThis JAR takes the results of an Eggplant test run, which can be the Eggplant JSON format or the Eggplant text output, and converts those test results into the JUnit XML format or a Word report.");
		System.out.println("\nFour command line arguments are required:");
		System.out.println(
				"\tjava -jar eggplantreportconversion.jar <inputType> <inputFileReadLocation> <outputType> <outputFileWriteLocation>");
		System.out.println("\nCommand Line Arguments:");
		System.out.println("\t<inputType> can be -json or -text");
		System.out.println("\t<inputFileReadLocation> is the location to read the input file");
		System.out.println("\t<outputType> can be -junit or -word");
		System.out.println("\t<outputFileWriteLocation> is the location where the result file will be written");
		System.out.println("\nEggplant JSON conversion to JUnit XML:");
		System.out.println(
				"\tjava -jar eggplantreportconversion.jar -json C:\\read\\path\\to\\eggplant.json -junit C:\\write\\path\\for\\junit.xml");
		System.out.println("\nEggplant JSON conversion to Word report:");
		System.out.println(
				"\tjava -jar eggplantreportconversion.jar -json C:\\read\\path\\to\\eggplant.json -word C:\\write\\path\\for\\wordreport.docx");
		System.out.println("\nEggplant text output conversion to JUnit XML:");
		System.out.println(
				"\tjava -jar eggplantreportconversion.jar -text C:\\read\\path\\to\\eggplant.txt -junit C:\\write\\path\\for\\junit.xml");
		System.out.println("\nEggplant text output conversion to Word report:");
		System.out.println(
				"\tjava -jar eggplantreportconversion.jar -text C:\\read\\path\\to\\eggplant.txt -word C:\\write\\path\\for\\wordreport.xml");
	}
}
