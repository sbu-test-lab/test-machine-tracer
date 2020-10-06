package oracle;

public class SOracle extends PolygonOracle {
	public SOracle() {
		occupied.add(new Tuple(0, 1));
		occupied.add(new Tuple(1, 1));
		occupied.add(new Tuple(1, 2));
		occupied.add(new Tuple(2, 2));
	}
}
