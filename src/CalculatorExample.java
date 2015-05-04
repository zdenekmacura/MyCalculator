import java.util.*;

public class CalculatorExample {

	public ArrayList<String[]> operationList = new ArrayList<String[]>();
	private HashMap<String, MathOperation> supportedOperations = new HashMap<String, MathOperation>();
	protected float applyNumber;
	public boolean isApplyNumberSet = false;

	interface MathOperation {
		float runMathOperation(float previousResult, float number);
	}

	public CalculatorExample() {
		supportedOperations.put("add", new MathOperation() {
			public float runMathOperation(float previousResult, float number) {
				return previousResult + number;
			};
		});
		supportedOperations.put("multiply", new MathOperation() {
			public float runMathOperation(float previousResult, float number) {
				return previousResult * number;
			};
		});
	}

	public void setApplyNumber(float number) {
		applyNumber = number;
		isApplyNumberSet = true;
	}

	public void addOperation(String[] operation) {
		operationList.add(operation);
	}

	public float performOperations() throws NoApplyInstructionException,
			Exception {
		float returnValue;
		if (!(isApplyNumberSet)) {
			throw new NoApplyInstructionException("No apply number is set");
		} else {
			returnValue = applyNumber;
		}
		if (operationList.size() == 0) {
			return applyNumber;
		}
		for (String[] o : operationList) {
			returnValue = supportedOperations.get(o[0]).runMathOperation(
					returnValue, Float.parseFloat(o[1]));
		}
		return returnValue;
	}

	public void loadOperations(String operations) {
		Scanner scanner = new Scanner(operations);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			Scanner scanner2 = new Scanner(line);
			float value;
			String operation;
			if (scanner2.hasNext()) {
				operation = scanner2.next();
				if (scanner2.hasNextFloat()) {
					value = scanner2.nextFloat();
					if (supportedOperations.containsKey(operation)) {
						addOperation(new String[] { operation,
								Float.toString(value) });
					}
					if (operation.equals("apply")) {
						setApplyNumber(value);
					}
				}
			}
			scanner2.close();
		}
		scanner.close();
	}
}
