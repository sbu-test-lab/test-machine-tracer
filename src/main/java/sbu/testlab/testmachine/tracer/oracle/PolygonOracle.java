package sbu.testlab.testmachine.tracer.oracle;

import java.util.ArrayList;

import bsh.EvalError;
import bsh.Interpreter;

public class PolygonOracle extends BlocksOracle {

	@Override
	public void CopyStateVars(Interpreter inter) {
		// TODO Auto-generated method stub
		super.CopyStateVars(inter);

		try {
			inter.eval("occupied=new boolean[3][3]");
			for (Tuple t : this.occupied) {
				inter.set(String.format("occupied[%d][%d]", t.x, t.y), "true");
			}
		} catch (EvalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Override
	public void Rotate() {
		ArrayList<Tuple> newOccupied = new ArrayList<Tuple>();
		for (Tuple tuple : occupied) {
			if (tuple.x == 0)
				newOccupied.add(new Tuple(tuple.y, 2));
			if (tuple.x == 1)
				newOccupied.add(new Tuple(tuple.y, 1));
			if (tuple.x == 2)
				newOccupied.add(new Tuple(tuple.y, 0));
		}
	}
}
