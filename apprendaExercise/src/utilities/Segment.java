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

	public Segment(Vertex vertex1, Vertex vertex2) {

		this.vertex1 = (vertex1);
		this.vertex2 = (vertex2);
		// There is hell to be paid if we assume each segment has a slope
		if (vertex1.getxValue().equals(vertex2.getxValue()) || vertex1.getyValue().equals(vertex2.getyValue())) {
			if (vertex1.getxValue().equals(vertex2.getxValue())) {
				// Then we are a vertical line
				this.setxLine(vertex1.getxValue());
			}
			if (vertex1.getyValue().equals(vertex2.getyValue())) {
				// Then we are a horizontal line
				this.setyLine(vertex1.getyValue());
			}
		} else {

			setSlope((vertex2.getyValue() - vertex1.getyValue()) / (vertex2.getxValue() - vertex1.getxValue()));
		}
	}

	public Double getSlope() {
		return slope;
	}

	public void setSlope(Double slope) {
		this.slope = slope;
	}

	public Double getyLine() {
		return yLine;
	}

	public void setyLine(Double yLine) {
		this.yLine = yLine;
	}

	public Double getxLine() {
		return xLine;
	}

	public void setxLine(Double xLine) {
		this.xLine = xLine;
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

}
