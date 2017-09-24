package utilities;

/**
 * 
 * @author brads
 *
 */
public class Segment {
	private Vertex vertex1 = null;
	private Vertex vertex2 = null;
	private Double xLine = null;
	private Double yLine = null;
	private Double slope = null;
	private Double xIntercept = null;
	private Double yIntercept = null;

	public Segment(Vertex vertex1, Vertex vertex2) {

		this.vertex1 = (vertex1);
		this.vertex2 = (vertex2);
		// Set appropriate attributes of a line segment depending on whether it is
		// sloped or perpendicular to
		// an axis
		if (vertex1.getxValue().equals(vertex2.getxValue()) || vertex1.getyValue().equals(vertex2.getyValue())) {
			if (vertex1.getxValue().equals(vertex2.getxValue())) {
				// Then we are a vertical line
				this.xLine = vertex1.getxValue();
			}
			if (vertex1.getyValue().equals(vertex2.getyValue())) {
				// Then we are a horizontal line
				this.yLine = vertex1.getyValue();
			}
		} else {
			slope = (vertex2.getyValue() - vertex1.getyValue()) / (vertex2.getxValue() - vertex1.getxValue());
			yIntercept = (vertex1.getyValue() - slope * vertex1.getxValue());
		}
	}

	public Double getSlope() {
		return slope;
	}

	public void setSlope(Double slope) {
		this.slope = slope;
	}

	public Double getyIntercept() {
		return yIntercept;
	}

	public void setyIntercept(Double yIntercept) {
		this.yIntercept = yIntercept;
	}

	public Double getxIntercept() {
		return xIntercept;
	}

	public void setxIntercept(Double xIntercept) {
		this.xIntercept = xIntercept;
	}

	public Vertex getVertex2() {
		return vertex2;
	}

	public void setVertex2(Vertex vertex2) {
		this.vertex2 = vertex2;
	}

	public Vertex getVertex1() {
		return vertex1;
	}

	public void setVertex1(Vertex vertex1) {
		this.vertex1 = vertex1;
	}

	public Double computeYForX(Double x) {
		return (slope * x) + yIntercept;
	}

	public Double computeXForY(Double y) {
		return (y - yIntercept) / slope;
	}

	@Override
	public String toString() {

		return "Line Segment{slope:" + ((slope == null) ? "null" : slope.toString()) + " yIntercept:"
				+ ((yIntercept == null) ? "null" : yIntercept.toString()) + " xIntercept:"
				+ ((xIntercept == null) ? "null" : xIntercept.toString()) + "}";

	}

	public Double getxLine() {
		return xLine;
	}

	public void setxLine(Double xLine) {
		this.xLine = xLine;
	}

	public Double getyLine() {
		return yLine;
	}

	public void setyLine(Double yLine) {
		this.yLine = yLine;
	}

	/**
	 * Simple distance formula
	 * 
	 * @return - distance between two points
	 */
	public Double getLength() {
		return Math.sqrt(Math.pow(vertex2.getxValue() - vertex1.getxValue(), 2)
				+ Math.pow(vertex2.getyValue() - vertex1.getyValue(), 2));
	}

}
