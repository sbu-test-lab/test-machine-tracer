package testmachine;

import java.util.ArrayList;

public class Arcs extends ArrayList<Arc> {
	public Arcs() {
		super();
	}

	public Arcs(Arc arc) {
		super();
		this.add(arc);
	}

	public boolean isRepeated(Arc node) {
		for (Arc arc : this)
			if (arc.equals(node))
				return true;
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Arc arc : this)
			sb.append(arc.method + "(); ");
		return sb.toString();
	}
}
