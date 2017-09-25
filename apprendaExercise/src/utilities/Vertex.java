package utilities;
/**
 * Describes a collection of two points
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

}
