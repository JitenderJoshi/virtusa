package assessment.virtusa.UnitConvertor;

import java.util.List;

/**
 * 
 * @author Jitender_Joshi
 *
 * This class contains the logic of reading processed data and provide answers based on that
 * 
 */
public class OutputProcessor {

	/**
	 * method to process each question and find out its answer
	 * 
	 */
	public static void provideAnswer() {
		List<Question> questionDefiner = InputProcessor.questionDefiner;
		
		StringBuffer sb = new StringBuffer();
		
		for(Question q : questionDefiner) {
			if(q.getQuestion() == null || q.getAbout() == null ) {
				sb.append(q.getAnswer() + "\n");
			}
			else {
				sb.append(getAnswer(q) + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}

	/**
	 * 
	 * @param q
	 * @return
	 */
	private static String getAnswer(Question q) {
		if(q.getQuestion().equals("how much")) {
			String about = q.getAbout();
			String [] aboutTokens = about.split("\\s+");

			StringBuffer sb = new StringBuffer();
			for(int i = 0 ; i < aboutTokens.length ; i++) {
				sb.append(InputProcessor.unitDefiner.get(aboutTokens[i]));				
			}
			
			return about + " is " + RomanConvertor.getRomanConvertorInstance().romanToNum(sb.toString().trim()) + "";
		}
		else if(q.getQuestion().equals("how many credits")) {
			String about = q.getAbout();
			String [] aboutTokens = about.split("\\s+");
			String metalName = aboutTokens[aboutTokens.length-1];

			StringBuffer sb = new StringBuffer();
			for(int i = 0 ; i < aboutTokens.length -1 ; i++) {
				sb.append(InputProcessor.unitDefiner.get(aboutTokens[i]));				
			}

			int numofUnits = RomanConvertor.getRomanConvertorInstance().romanToNum(sb.toString().trim());

			return about + " is " + (int)(numofUnits * InputProcessor.metalRate.get(metalName)) + " Credits";
		}
		else {
			return "I have no idea what you are talking about";
		}
	}

}
