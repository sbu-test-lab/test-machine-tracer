package oracle;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import bsh.EvalError;
import bsh.Interpreter;

public class BlocksOracle implements IExecuteModel {

	protected int x_position;
	protected int y_position;
	protected ArrayList<Tuple> occupied;

	public BlocksOracle() {
		x_position = 0;
		y_position = 22;
		occupied = new ArrayList<Tuple>();
	}

	@Override
	public boolean CheckInvariant(IExecuteModel model) {
		BlocksOracle m = (BlocksOracle) model;
		for (Tuple t : m.occupied) {
			if (!(t.x + x_position >= 0 && t.x + x_position < 10))
				return false;
			if (!(t.y + y_position >= 0))
				return false;
		}
		return true;
	}

	public void init() {
		x_position = 0;
	}
	public void init1() {
		x_position = 0;
	}
	public void init2() {
		x_position = 1;
	}

	public void MoveRight() {
		x_position = x_position + 1;
	}

	public void MoveLeft() {
		x_position = x_position - 1;
	}

	public void MoveDown() {
		y_position = y_position - 1;
	}

	public void Rotate() {

	}

	public void BeAddedToScreen() {

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder occ = new StringBuilder();
		occ.append("occupied={");
		for (Tuple t : this.occupied) {
			occ.append("(" + t.x + "," + t.y + "),");
		}

		occ.deleteCharAt(occ.length() - 1);
		occ.append("}");

		return String.format("x_position=%d; y_position=%d; %s", x_position,
				y_position, occ.toString());

	}

	public void SetSateAs(IExecuteModel preState) {
		BlocksOracle pre = (BlocksOracle) preState;
		this.x_position = pre.x_position;
		this.y_position = pre.y_position;
		this.occupied = (ArrayList<Tuple>) pre.occupied.clone();
	}

	public void CopyStateVars(Interpreter inter) {
		try {
			inter.set("x", x_position);
			inter.set("y", y_position);
		} catch (EvalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean satisfyState(String methodName, String state) {
		Interpreter interpreter = new Interpreter(); // Construct an interpreter
		try {
			IExecuteModel testModel = this.getClass().newInstance();

			testModel.SetSateAs(this);

			testModel.invoke(methodName);

			if (!testModel.CheckInvariant(testModel))
				return false;

			testModel.CopyStateVars(interpreter);

			interpreter.eval(String.format("satisfy=%s", state));

			boolean satisfy = (Boolean) interpreter.get("satisfy");
			return satisfy;

		} catch (EvalError e) {
			e.printStackTrace();
			return false;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void invoke(String methodName) {
		java.lang.reflect.Method m;

		try {

			m = this.getClass().getMethod(methodName);

			m.invoke(this);

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
