package sbu.testlab.testmachine.tracer.oracle;

public class SquareOracle extends BlocksOracle {
	public SquareOracle() {
		super();
		occupied.add(new Tuple(0, 0));
		occupied.add(new Tuple(0, 1));
		occupied.add(new Tuple(1, 0));
		occupied.add(new Tuple(1, 1));
	}
}
