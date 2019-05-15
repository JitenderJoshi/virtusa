package assessment.virtusa.UnitConvertor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Jitender_Joshi
 * 
 * This class contains the logic of reading the input file, making sense out of various statements
 * and accordingly processing them into different collections which are the used by output processor to provide answers
 *
 */
public class InputProcessor {

	/*
	 * Different collections to hold differnt statements*/
	
	//for statement such as -- glob is I
	public static HashMap<String, String> unitDefiner = new HashMap<>();

	//for statement such as -- glob glob Silver is 34 Credits
	public static HashMap<String, String> rateDefiner = new HashMap<>();

	//for holding questions
	public static List<Question> questionDefiner = new ArrayList<>();

	//for holding metal rate for 1 unit
	public static HashMap<String, Double> metalRate = new HashMap<>();


	/**
	 * 
	 * @param file
	 */
	public static void processInput(File file ) {
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = "";

			while ((line = br.readLine()) != null) {
				System.out.println(line);
				InputProcessor.processLine(line.toLowerCase().trim());
			}

			InputProcessor.processMetalRate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param line
	 */
	private static void processLine(String line) {
		String [] lineTokens = line.split("\\s+");
		
		/* A statement can be of 1 of 3 types and based on that it is accordingly processed
		 * statement defining the unit
		 * statement defining the rate
		 * statement defining the question
		 */

		if(lineTokens.length == 3 && lineTokens[1].equals("is")) {
			processUnit(lineTokens);
		}
		else if(line.endsWith("credits")) {
			processRate(line);
		}
		else if(line.endsWith("?")) {
			processQuestion(line);
		}
	}


	/**
	 * 
	 * @param lineTokens
	 */
	private static void processUnit(String[] lineTokens) {
		unitDefiner.put(lineTokens[0].trim(), lineTokens[2].trim().toUpperCase());
	}

	/**
	 * 
	 * @param line
	 */
	private static void processRate(String line) {
		String [] lineTokens = line.split("\\s+is\\s+");
		rateDefiner.put(lineTokens[0].trim(), lineTokens[1].substring(0, lineTokens[1].indexOf("credits")).trim());
	}

	/**
	 * 
	 * @param line
	 */
	private static void processQuestion(String line) {
		String [] lineTokens = line.split("\\s+is\\s+");
		Question question = new Question();
		
		question.setLine(line);
		
		if(lineTokens.length == 2  && (lineTokens[0].trim().equals("how much") ||  lineTokens[0].trim().equals("how many credits"))){
			question.setQuestion(lineTokens[0].trim());
			question.setAbout(lineTokens[1].substring(0, lineTokens[1].indexOf("?")).trim());
			question.setAnswer("");
		}
		else {
			question.setQuestion(null);
			question.setAbout(null);
			question.setAnswer("I have no idea what you are talking about");
		}
		
		questionDefiner.add(question);
	}

	/**
	 * 
	 */
	private static void processMetalRate() {
		Set<String> rateDefinerKeySet = rateDefiner.keySet();

		for(String key : rateDefinerKeySet) {
			String totalvalue = rateDefiner.get(key);
			
			String [] keyTokens = key.split("\\s+");
			String metalName = keyTokens[keyTokens.length-1];

			StringBuffer sb = new StringBuffer();
			for(int i = 0 ; i < keyTokens.length -1 ; i++) {
				sb.append(unitDefiner.get(keyTokens[i]));				
			}

			int numofUnits = RomanConvertor.getRomanConvertorInstance().romanToNum(sb.toString().trim());
			double perUnit = Double.parseDouble(totalvalue)/numofUnits ;
			
			metalRate.put(metalName, perUnit);
		}
	}

}
