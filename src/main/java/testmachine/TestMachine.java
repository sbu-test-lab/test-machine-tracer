package testmachine;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import oracle.IExecuteModel;

public class TestMachine {

public HashMap<String, Arcs> startStateTrans;
public HashMap<String, Arcs> endStateTrans;
public List<Arc> allArcs;
public List<Arc> initArcs;
public State states;

public int curr;

public TestMachine(State states, int init) {
	startStateTrans = new HashMap<String, Arcs>(states.size());
	endStateTrans = new HashMap<String, Arcs>(states.size());
	for (String stateId : states.keySet()) {
		startStateTrans.put(stateId, new Arcs());
		endStateTrans.put(stateId, new Arcs());
	}
	this.states = states;
	this.allArcs = new ArrayList<Arc>();
	this.initArcs = new ArrayList<Arc>();
}

public void addTransition(int i, int j, String method, boolean isPartial) {
	// TODO Auto-generated method stub
addTransition(i + "", j + "", method, isPartial);
}

public void addTransition(String start, String end, String method,
		boolean isPartial) {
	Arc arc = new Arc(start, end, method, isPartial);
	if (!arc.getStart().equals("null")) {
		startStateTrans.get(start).add(arc);
		endStateTrans.get(end).add(arc);
		allArcs.add(arc);
	} else {
		initArcs.add(arc);
	}
}

public TestCases primePaths(IExecuteModel exeModel) {

TestCases result = new TestCases();
Date d1 = new Date();
long iteration = 0;
for (Arc initArc : initArcs) {

	Stack<StackNode> stack = new Stack<StackNode>();

	stack.push(new StackNode(new Arcs(initArc), startStateTrans
			.get(initArc.getEnd()), exeModel));

	stack.peek().getExeModel().invoke(initArc.getMethod());

	while (!stack.isEmpty()) {
		if (iteration > 10000)
			break;
		Arcs currPath = (Arcs) stack.peek().getPath().clone();

		if (stack.peek().getArcs().isEmpty()) {
			if (stack.peek().isPrime())
				result.add(new TestCase(currPath, stack.peek()
						.getExeModel().toString()));

			stack.pop();
			continue;
		}

		while (!stack.peek().getArcs().isEmpty()) {
			Arc arc = stack.peek().getArcs().remove();
			iteration++;
			boolean isSatisfy = stack
					.peek()
					.getExeModel()
					.satisfyState(arc.getMethod(),
							states.get(arc.getEnd()));
			if (isSatisfy) {
				if (!currPath.isRepeated(arc)) {
					stack.peek().setPrime(false);
					currPath.add(arc);

					stack.push(new StackNode(currPath, startStateTrans
							.get(arc.getEnd()), stack.peek()
							.getExeModel()));
					stack.peek().getExeModel().invoke(arc.getMethod());
					break;
				}
			}
		}
	}
	}
	Date d2 = new Date();
	long time = d2.getTime() - d1.getTime();

	System.out.print("======Test Machine Tools======\n==========Statistics==========\n Elapsed Time: "
		+ time / 1000 + "s");
System.out.print("\n Number of Comparisons: " + iteration + " \n");

	return result;
}

public String ExportToDotString() {
	StringBuilder s = new StringBuilder();
	s.append("digraph G {\n");
for (Arc arc : allArcs) {
	s.append(String.format("%s -> %s [label=%s];\n", arc.start,
			arc.end, arc.method));
}
s.append("}");
	return s.toString();

}

public void fromFile(String path) {
	InputStream fis;
	BufferedReader br = null;
	String line;

	try {
		fis = new FileInputStream(path);
		br = new BufferedReader(new InputStreamReader(fis,
				Charset.forName("UTF-8")));
	while ((line = br.readLine()) != null) {
		if (line.trim().equals(""))
			continue;
		String[] part = line.trim().split("\t");

		this.addTransition(part[0], part[1], part[2], true);
	}
	br.close();
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {

		br = null;
		fis = null;
	}
}

public class StackNode {

	Arcs path;
	Queue<Arc> arcs;
	boolean isPrime;

	IExecuteModel exeModel;

	StackNode(Arcs path, List<Arc> arcs, IExecuteModel exeModel) {
		this.path = path;
		this.isPrime = true;
		this.arcs = new ArrayDeque<Arc>();
		for (Arc arc : arcs)
			this.arcs.add(arc);

		try {
			this.exeModel = exeModel.getClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.exeModel.SetSateAs(exeModel);
}

public Arcs getPath() {
	return path;
}

public Queue<Arc> getArcs() {
	return arcs;
}

public boolean isPrime() {
	return isPrime;
}

public void setPrime(boolean isPrime) {
	this.isPrime = isPrime;
}

public IExecuteModel getExeModel() {
	return exeModel;
}

@Override
public String toString() {
	return "[path:" + path.toString() + " - arcs:" + arcs.toString();
		}
	}

}