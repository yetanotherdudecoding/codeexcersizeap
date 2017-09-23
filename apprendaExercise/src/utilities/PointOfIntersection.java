package utilities;

/**
 * 
 * @author brads
 *
 */
public class PointOfIntersection {

	private Double x = null;
	private Double y = null;

	public PointOfIntersection(Double x, Double y) {
		this.setX(x);
		this.setY(y);

	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	@Override
	public String toString() {
		return " PointOfIntersection{" + getX().toString() + "," + getY().toString() + "}";
	}

	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == this) {
			return true;
		}
		if (!(otherObject instanceof PointOfIntersection)) {
			return false;
		}
		PointOfIntersection poi = (PointOfIntersection) otherObject;
		if (poi.getX().equals(getX()) && poi.getY().equals(getY())) {
			return true;
		}
		return false;
	}
}
