package polygonComponents;

/**
 * Describes a collection of two points
 * 
 * @author brads
 *
 */
public class Vertex {
	private Double xValue = null;
	private Double yValue = null;

	public Vertex(Double xValue, Double yValue) {

		this.setxValue(xValue);
		this.setyValue(yValue);
	}

	public Double getyValue() {
		return yValue;
	}

	public void setyValue(Double yValue) {
		this.yValue = yValue;
	}

	public Double getxValue() {
		return xValue;
	}

	public void setxValue(Double xValue) {
		this.xValue = xValue;
	}

	@Override
	public String toString() {
		return "Vertex:{" + xValue + "," + yValue + "}";
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (!(object instanceof Vertex)) {
			return false;
		}
		Vertex aVertex = (Vertex) object;
		if (aVertex.getxValue().equals(getxValue()) && aVertex.getyValue().equals(getyValue())) {
			return true;
		}
		return false;
	}
}
