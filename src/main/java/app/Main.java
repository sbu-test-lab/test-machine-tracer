package app;

import java.lang.reflect.Field;

import bsh.EvalError;
import oracle.IExecuteModel;
import testmachine.State;
import testmachine.TestCases;
import testmachine.TestMachine;

public class Main {

	/**
	 * @param args
	 * @throws EvalError
	 */
	public static void main(String[] args) throws EvalError {

		// simpleQueueExample();

		// bulidGraphFromFile();

		// bulidGraphFromFile("E:\\tetris\\queue\\states.txt","E:\\tetris\\queue\\arcs.txt",
		// new QueueModel());

/*		bulidGraphFromFile("E:\\tetris\\Sq\\states.txt",
				"E:\\tetris\\Sq\\arcs.txt", new SquareOracle());*/

		simpleQueueExample();
		
//		bulidGraphFromFile("E:\\tetris\\poly\\states.txt",
//				"E:\\tetris\\poly\\arcs.txt", new TOracle());


/*		bulidGraphFromFile("E:\\tetris\\Square\\new\\states.txt",
				"E:\\tetris\\Square\\new\\arcs.txt", new SquareOracle());*/

	/*	bulidGraphFromFile("E:\\tetris\\Square\\new2\\states.txt",
				"E:\\tetris\\Square\\new2\\arcs.txt", new SquareOracle());		
	*/	/*JButton button = new JButton( "My Button" );
		JFrame frame = new JFrame( "My Frame" );
		frame.getContentPane().add( button, "Center" );
		frame.pack();
		frame.setVisible(true);*/
	}

	private static void bulidGraphFromFile(String path_state, String path_arcs,
			IExecuteModel oracle) {
		// TODO Auto-generated method stub
		State states = new State();
		states.fromFile(path_state);
		//System.out.print(states.toString());

		TestMachine test = new TestMachine(states, 0);
		test.fromFile(path_arcs);

		TestCases primes = test.primePaths(oracle);
		System.out.print(" Number of Test cases: " + primes.size() + "\n==============================");

		System.out.print(primes.toString());
		
		System.out.print(test.ExportToDotString());
	}

	private static void simpleQueueExample() {
		// TODO Auto-generated method stub
		State states = new State();

		states.add("0", "x==0");
		states.add("1", "x==1");
		states.add("2", "x==2");
		states.add("3", "x>2");

		TestMachine test = new TestMachine(states, 0);

		test.addTransition(0, 1, "Add", false);
		test.addTransition(1, 2, "Add", false);
		test.addTransition(2, 3, "Add", false);
		test.addTransition(3, 3, "Add", false);

		test.addTransition(1, 0, "Rem", false);
		test.addTransition(2, 1, "Rem", false);

		test.addTransition(3, 2, "Rem", true);
		test.addTransition(3, 3, "Rem", true);

		test.addTransition("null", "0", "init", false);

	/*	QueueModel m = new QueueModel();
		TestCases primes = test.primePaths(m);
		System.out.print(primes.toString());
	*/	

		System.out.print(test.ExportToDotString());
	}

}

class A {
	public int x, y;

	public A() {
		x = 10;
		y = 6;
	}

	public void p() {
		Field field;
		try {
			field = this.getClass().getDeclaredField("x");
			System.out.println(field.get(this));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
