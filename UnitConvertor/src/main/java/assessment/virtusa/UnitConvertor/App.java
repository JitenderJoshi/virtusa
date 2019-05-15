package assessment.virtusa.UnitConvertor;

import java.io.File;
/**
 * 
 * @author Jitender_Joshi
 *
 * This class is the starting point for the application
 *
 */
public class App 
{
	/**
	 * 
	 * @param args
	 */
	public static void main( String[] args )
	{
		if(args.length != 0) {
			System.exit(1);
		}
		
		String inputFilePath = args[0];
		
		File file = new File(inputFilePath);

		//Read the file and process the data for providing output
		InputProcessor.processInput(file);
		
		//provide answers based on processed data
		OutputProcessor.provideAnswer();
	}
}
