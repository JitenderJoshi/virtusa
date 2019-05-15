package assessment.virtusa.UnitConvertor;

import java.util.HashMap;

/**
 * 
 * @author Jitender_Joshi
 * 
 * Util class to convert roman numbers to decimal number 
 *
 */
public class RomanConvertor {
	private static HashMap<String, Integer> mapToNumber = new HashMap<String, Integer>();

	private static RomanConvertor romanConvertorInstance = new RomanConvertor();

	public static RomanConvertor getRomanConvertorInstance() {
		return romanConvertorInstance;
	}

	private RomanConvertor() {
		mapToNumber.put("I", 1);
		mapToNumber.put("V", 5);
		mapToNumber.put("X", 10);
		mapToNumber.put("L", 50);
		mapToNumber.put("C", 100);
		mapToNumber.put("D", 500);
		mapToNumber.put("M", 1000);
	}

	/**
	 * Util method for Roman String to Decimal integer representation
	 * Logic to convert roman to integer is
	 * start traversing from right to left.
	 * if number to the right is bigger, reduce the number, else add that.
	 * 
	 * e.g.
	 * CXXVIII
	 * I -- num = 1
	 * I -- number to right (I) is same so we add this -- num = 2
	 * I -- number to right (I) is same so we add this -- num = 3
	 * V -- number to right (I) is small so we add this -- num = 8
	 * X -- number to right (V) is small so we add this -- num = 18
	 * X -- number to right (X) is same so we add this -- num = 28
	 * C -- number to right (X) is small so we add this -- num = 128
	 * 
	 * CXXVIII =128
	 * 
	 * @param romanStr
	 * @return
	 */
	public int romanToNum(String romanStr) {
		int roman_len =  romanStr.length();
		int num = mapToNumber.get(romanStr.charAt(roman_len-1)+"");

		for(int i = roman_len-2 ; i >=0 ; i--) {
			int x = mapToNumber.get(romanStr.charAt(i)+"");
			int y = mapToNumber.get(romanStr.charAt(i+1)+"");

			if(x < y) {
				num = num - x;
			}
			else {
				num = num + x;
			}
		}

		return num;
	}

}
