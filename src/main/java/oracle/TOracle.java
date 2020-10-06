package oracle;

public class TOracle extends PolygonOracle {
	public TOracle() {
		occupied.add(new Tuple(0, 1));
		occupied.add(new Tuple(1, 0));
		occupied.add(new Tuple(1, 1));
		occupied.add(new Tuple(2, 1));
	}

}
