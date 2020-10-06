package oracle;

import bsh.Interpreter;

public interface IExecuteModel {
	public void invoke(String methodName);
	public boolean satisfyState(String methodName, String state);
	public void SetSateAs(IExecuteModel preState);
	public boolean CheckInvariant(IExecuteModel testModel);
	public void CopyStateVars(Interpreter interpreter);
}
