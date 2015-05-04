import java.io.*;
import java.util.*;

public class CalculatorExampleMain {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out
					.println("Please specify the file name ( full path ), where are the instructions for the calculation. Example: java CalculatorExample <path_to_file>");
		} else {
			CalculatorExample specialCalculator = new CalculatorExample();
			try {
				File inputFile = new File(args[0]);
				Scanner readFile = new Scanner(inputFile);
				String fileContent = readFile.useDelimiter("\\Z").next();
				readFile.close();
				specialCalculator.loadOperations(fileContent);
				float result = specialCalculator.performOperations();
				System.out.print(result);
			} catch (FileNotFoundException e) {
				System.out.println("File " + args[0] + " is not found");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (NoApplyInstructionException e) {
				System.out.println("File " + args[0]
						+ " doesn't contain apply instruction.");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
