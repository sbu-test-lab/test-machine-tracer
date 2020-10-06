package testmachine;


public class Arc {

	String method;

	String start, end;
	boolean isPartial;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public boolean isPartial() {
		return isPartial;
	}

	public void setPartial(boolean isPartial) {
		this.isPartial = isPartial;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Arc(String start, String end, String method, boolean isPartial) {
		this.start = start;
		this.end = end;
		this.method = method;
		this.isPartial = isPartial;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Arc))
			return false;
		Arc other = (Arc) obj;
		if (other.end == this.end && other.start == this.start
				&& other.method.equals(this.method))
			return true;
		return false;
	}

	@Override
	public String toString() {
		return method + "(" + start + " to " + end + ")";
	}
}
