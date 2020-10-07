package testmachine;
import java.util.LinkedList;


public class TestCases extends LinkedList<TestCase> {


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i=0;
		sb.append("\nTEST CASES:\n");
		for (TestCase test : this) {
			
			sb.append(String.format("-----\nCalls:\t[%s]\nOracle:\t[%s] \n", test.path.toString(), test.oracle));
		}
		return sb.toString();
	}
}
