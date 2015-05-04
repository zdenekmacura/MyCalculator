import static org.junit.Assert.*;

import java.io.*;

import org.junit.Test;

public class CalculatorExampleTest {

	@Test
	public void testCreateCalculatorExampleTestObject() throws Exception {
		CalculatorExample testObject = new CalculatorExample();
		assertNotNull(testObject);
	}

	@Test
	public void testIfApplyNumberSetAfterLoadOperations() throws Exception {
		CalculatorExample testObject = new CalculatorExample();
		String operations = "add 3 \nmultiply 4 \napply 2";
		testObject.loadOperations(operations);
		assertTrue(testObject.isApplyNumberSet);
	}

	@Test
	public void testOperationListAfterLoadOperationsIfAddOperationIsPresent()
			throws Exception {
		CalculatorExample testObject = new CalculatorExample();
		String operations = "add 3 \nmultiply 4 \napply 2";
		testObject.loadOperations(operations);
		assertEquals(testObject.operationList.get(0)[0], "add");
	}

	@Test
	public void testOperationListAfterLoadOperationsIfMultiplyOperationIsPresent()
			throws Exception {
		CalculatorExample testObject = new CalculatorExample();
		String operations = "add 3 \nmultiply 4 \napply 2";
		testObject.loadOperations(operations);
		assertEquals(testObject.operationList.get(1)[0], "multiply");
	}

	@Test
	public void testPerformeOperationsWithAdd() throws Exception {
		CalculatorExample testObject = new CalculatorExample();
		String operations = "add 5 \napply 2";
		testObject.loadOperations(operations);
		float result = testObject.performOperations();
		float expectedresult = 7;
		assertEquals(result, expectedresult, 0.01);
	}

	@Test
	public void testPerformeOperationsWithMultiply() throws Exception {
		CalculatorExample testObject = new CalculatorExample();
		String operations = "multiply 3.9 \napply 6.4";
		testObject.loadOperations(operations);
		float result = testObject.performOperations();
		float expectedresult = (float) 24.96;
		assertEquals(result, expectedresult, 0.01);
	}

	@Test
	public void testPerformeOperationsWithMultiplyAndAdd() throws Exception {
		CalculatorExample testObject = new CalculatorExample();
		String operations = "multiply 3.9 \nadd 5.9\nmultiply 3.8\napply 6.4";
		testObject.loadOperations(operations);
		float result = testObject.performOperations();
		float expectedresult = (float) 117.268;
		assertEquals(result, expectedresult, 0.001);
	}

	@Test
	public void testCreateCalculatorExampleMainTestObject() throws Exception {
		CalculatorExampleMain testObject = new CalculatorExampleMain();
		assertNotNull(testObject);
	}

	@Test
	public void testRunCreateCalculatorWithNoArgument() throws Exception {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String[] arg = {};
		CalculatorExampleMain.main(arg);
		assertEquals(
				"Please specify the file name ( full path ), where are the instructions for the calculation. Example: java CalculatorExample <path_to_file>\n",
				outContent.toString());
	}

	@Test
	public void testRunCreateCalculatorWithNotExistingFile() throws Exception {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String[] arg = { "/tmp/blabla" };
		CalculatorExampleMain.main(arg);
		assertEquals("File /tmp/blabla is not found\n", outContent.toString());
	}

	@Test
	public void testErrorMessageWhenFileHasNoApplyInstruction() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		// before running this test, make sure such file exists with no line
		// containing apply instruction
		String[] arg = { "/home/zdenek/tmp/testfilenoapply" };
		CalculatorExampleMain.main(arg);
		assertEquals(
				"File /home/zdenek/tmp/testfilenoapply doesn't contain apply instruction.\n",
				outContent.toString());
	}

	@Test
	public void testResultWhenOneAddOperationIsPresent() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		// testfileoneadd should contain two lines
		// add 3
		// apply 4
		String[] arg = { "/home/zdenek/tmp/testfileoneadd" };
		CalculatorExampleMain.main(arg);
		assertEquals("7.0", outContent.toString());
	}

	@Test
	public void testResultWhenOneAddAndOneMultiplyOperationIsPresent() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		// testfileoneaddonemultiply should contain two lines
		// add 3
		// multiply 3
		// apply 4
		String[] arg = { "/home/zdenek/tmp/testfileoneaddonemultiply" };
		CalculatorExampleMain.main(arg);
		assertEquals("21.0", outContent.toString());
	}

	@Test
	public void testResultWhenMoreOperationIsPresent() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		// testfileoneaddonemultiply should contain two lines
		// add 3
		// multiply 3
		// add 6.4
		// multiply 4.9
		// apply 4
		String[] arg = { "/home/zdenek/tmp/testfilemoreoperations" };
		CalculatorExampleMain.main(arg);
		assertEquals("134.26", outContent.toString());
	}

}
