package testmachine;

public class TestCase {
	public Arcs path;
	public String oracle;

	public TestCase(Arcs arcs, String oracle) {
		this.path = arcs;
		this.oracle = oracle;
	}
}
