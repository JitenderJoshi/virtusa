package assessment.virtusa.UnitConvertor;

import org.junit.Assert;
import org.junit.Test;

public class RomanConvertorTest {
	
	@Test
	/**
	 * Test whether the Roman String to Decimal integer conversin is working correctly.
	 */
	public void testSubtractionLogic(){
		int result = RomanConvertor.getRomanConvertorInstance().romanToNum("CXXVIII");
		Assert.assertEquals(128, result, 00.00);
	}
}
