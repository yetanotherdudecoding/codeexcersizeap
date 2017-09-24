package shapes;

import utilities.Vertex;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import utilities.Segment;
import utilities.PointOfIntersection;

/**
 * Handle the rectangle type of polygon
 * 
 * @author brads
 *
 */
public class Rectangle implements Polygon {

	private ArrayList<Segment> segments = new ArrayList<Segment>();
	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();

	/*
	 * Vertices must be specified either clockwise or counter clockwise along the
	 * perimeter
	 */
	public Rectangle(Vertex vertex1, Vertex vertex2, Vertex vertex3, Vertex vertex4) {
		segments.add(new Segment(vertex1, vertex2));
		segments.add(new Segment(vertex2, vertex3));
		segments.add(new Segment(vertex3, vertex4));
		segments.add(new Segment(vertex4, vertex1));

		vertices.add(vertex1);
		vertices.add(vertex2);
		vertices.add(vertex3);
		vertices.add(vertex4);

	}

	@Override
	/**
	 * There may be a fancier way to check for intersections of two polygons, but
	 * I'm not a fancy guy.
	 * 
	 * What we do here is anchor on each line segment of this polygon and then
	 * iterate through all other line segments of the other polygon until we've
	 * checked for all possible intersections of every line segment combination
	 * between the two polygons
	 * 
	 */
	public ArrayList<PointOfIntersection> getIntersectionsWithPolygon(Polygon otherPolygon) {
		ArrayList<PointOfIntersection> allPois = new ArrayList<PointOfIntersection>();
		for (Segment mySegment : segments) {
			for (Segment otherSegment : otherPolygon.getAllLineSegments()) {
				PointOfIntersection poi = null;// Flush for this analysis
				poi = getPointOfIntersectionBetweenSegments(mySegment, otherSegment);
				if (poi != null) {
					// It is possible a point of intersection may appear twice if a line segment
					// intersects directly on the vertex
					if (!allPois.contains(poi)) {
						allPois.add(poi);
					}

				}
			}
		}

		return allPois;

	}

	@Override
	public boolean containsPolygon(Polygon otherPolygon) {

		for (Vertex aVertex : otherPolygon.getAllVertices()) {
			// For rectangle ABCD and point p is within the rectangle, then the area of
			// sub-triangles formed by
			// points ABP, BCP, CDP, DAP should equal the area of rectangle ABCD, if greater
			// than it is outside
			Double triangleArea = areaOfTriangle(vertices.get(0), vertices.get(1), aVertex)
					+ areaOfTriangle(vertices.get(1), vertices.get(2), aVertex)
					+ areaOfTriangle(vertices.get(2), vertices.get(3), aVertex)
					+ areaOfTriangle(vertices.get(3), vertices.get(0), aVertex);
			Double rectangleArea = areaOfRectangle(vertices.get(0), vertices.get(1), vertices.get(2));
			
			if(triangleArea.equals(rectangleArea)) {
				return true;
			}
		}
		return false;

	}

	public Double areaOfRectangle(Vertex vertexA, Vertex vertexB, Vertex vertexC) {
		Double side1 = getLength(vertexA, vertexB);
		Double side2 = getLength(vertexB, vertexC);

		return side1 * side2;
	}

	public Double areaOfTriangle(Vertex vertexA, Vertex vertexB, Vertex vertexC) {
		Double base = getLength(vertexA, vertexB);
		// Use the midpoint of one line to determine the height in relation to the
		// opposite point
		Vertex midpoint = new Vertex(((vertexA.getxValue() + vertexB.getxValue()) / 2),
				((vertexA.getyValue() + vertexB.getyValue()) / 2));
		Double height = getLength(midpoint, vertexC);

		return (0.5 * base * height);
	}

	public Double getLength(Vertex vertex1, Vertex vertex2) {
		return Math.sqrt(Math.pow(vertex2.getxValue() - vertex1.getxValue(), 2)
				+ Math.pow(vertex2.getyValue() - vertex1.getyValue(), 2));
	}

	public ArrayList<Segment> getAllLineSegments() {
		return segments;
	}

	/**
	 * We compare two line segments in this method. There are two kinds of line
	 * segments. Segments whose equation is either a straight line perpendicular to
	 * the x or y axis, in that case there equation is the integer intersecting that
	 * axis (e.g. x=3 or y=3)
	 * 
	 * There are also those of sloped lines, whose equation is of the form y=mx+b
	 * 
	 * Since a either of the two received segments could be straight or sloped, we
	 * handle the following combinations - Both non-sloped segments - One
	 * non-sloped, one sloped segment - Both sloped segments
	 * 
	 * We finish by validating that these points of intersection actually occur on
	 * the domain/range of the segments we are validating before returning the point
	 * of intersection
	 * 
	 * @param segmenta
	 *            - Segment
	 * @param segmentb
	 *            - Segment
	 * @return poi - PointOfIntersection
	 */
	private PointOfIntersection getPointOfIntersectionBetweenSegments(Segment segmenta, Segment segmentb) {
		PointOfIntersection poi = null;
		Double xIntersection = null;
		Double yIntersection = null;
		// Intersection of two non-sloped lines
		if (segmenta.getxLine() != null && segmentb.getyLine() != null) {
			// segmenta is vertical and segmentb is horizontal
			poi = new PointOfIntersection(segmenta.getxLine(), segmentb.getyLine());
		}
		if (segmenta.getyLine() != null && segmentb.getxLine() != null) {
			// segmenta is horizontal and segmentb is vertical
			poi = new PointOfIntersection(segmentb.getxLine(), segmenta.getyLine());
		}

		// Line segment a is sloped and line segment b is non-sloped
		if (segmenta.getSlope() != null && segmentb.getSlope() == null) {

			// line segment b is vertical and we will intercept where segmenta line equation
			// resolves to y
			if (segmentb.getxLine() != null) {
				poi = new PointOfIntersection(segmentb.getxLine(), segmenta.computeYForX(segmentb.getxLine()));

			}
			// line segment b is horizontal and we will intercept where segmenta line
			// equation resolves to x
			if (segmentb.getyLine() != null) {
				poi = new PointOfIntersection(segmenta.computeXForY(segmentb.getyLine()), segmentb.getyLine());
			}
		}
		// Line segmentb is sloped and line segmenta is non-sloped
		if (segmentb.getSlope() != null && segmenta.getSlope() == null) {

			if (segmenta.getxLine() != null) {
				poi = new PointOfIntersection(segmenta.getxLine(), segmentb.computeYForX(segmenta.getxLine()));

			}
			if (segmenta.getyLine() != null) {
				poi = new PointOfIntersection(segmentb.computeXForY(segmenta.getyLine()), segmenta.getyLine());
			}
		}

		// Intersection of two sloped line segments
		if (segmenta.getSlope() != null && segmentb.getSlope() != null)

		{

		}
		if (poi == null) {
			return null;
		}
		if (validatePointOfIntersectionOnSegment(poi, segmenta, segmentb)) {
			return poi;

		}
		return null;
	}

	@Override
	// TODO: I do not know why this is not letting me use a private scope on this
	// method.
	public boolean validatePointOfIntersectionOnSegment(PointOfIntersection poi, Segment segmenta, Segment segmentb) {
		return (isBetween(segmenta.getVertex1().getxValue(), segmenta.getVertex2().getxValue(), poi.getX())
				&& isBetween(segmenta.getVertex1().getyValue(), segmenta.getVertex2().getyValue(), poi.getY())
				&& isBetween(segmentb.getVertex1().getxValue(), segmentb.getVertex2().getxValue(), poi.getX())
				&& isBetween(segmentb.getVertex1().getyValue(), segmentb.getVertex2().getyValue(), poi.getY()));
	}

	private boolean isBetween(Double a, Double b, Double c) {
		Double low = Math.min(a, b);
		Double high = Math.max(a, b);
		return (low <= c && c <= high);
	}

	public ArrayList<Vertex> getAllVertices() {
		return vertices;
	}

	@Override
	public boolean isAdjacentToPolygon(Polygon otherPolygon) {
		for (Segment segmenta : segments) {
			for (Segment segmentb : otherPolygon.getAllLineSegments()) {
				// Diverge here to handle the case that both segments are parallel to the x and
				// y axis
				if (segmenta.getSlope() == null && segmentb.getSlope() == null) {
					// Make sure that we are dealing with two lines which have vertical asymptotes
					if (segmenta.getxLine() != null && segmentb.getxLine() != null) {
						if (segmenta.getxLine().equals(segmentb.getxLine())) {
							// These lines could potentially overlap since they share the same line equation
							if (isBetween(segmenta.getVertex1().getxValue(), segmenta.getVertex2().getxValue(),
									segmentb.getVertex1().getxValue())
									&& isBetween(segmenta.getVertex1().getxValue(), segmenta.getVertex2().getxValue(),
											segmentb.getVertex2().getxValue())) {
								return true;
							}
						}
					}
					// Make sure that we are dealing with two lines which have horizontal asymptotes
					if (segmenta.getyLine() != null && segmentb.getyLine() != null) {
						if (segmenta.getyLine().equals(segmentb.getyLine())) {
							// These lines could potentially overlap since they share the same line equation
							if (isBetween(segmenta.getVertex1().getyValue(), segmenta.getVertex2().getyValue(),
									segmentb.getVertex1().getyValue())
									&& isBetween(segmenta.getVertex1().getyValue(), segmenta.getVertex2().getyValue(),
											segmentb.getVertex2().getyValue())) {
								return true;
							}
						}
					}

				}
			}
		}

		return false;
	}

	@Override
	public boolean isValidPolygon() {

		// TODO: Validate relationships we only see in rectangles

		return true;
	}
}
