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

	private ArrayList<Segment> segments = new ArrayList<Segment>(); // Segments which constitute the polygon
	private ArrayList<Vertex> vertices = new ArrayList<Vertex>(); // Verticies which make up this polygon, in clockwise
																	// order

	/**
	 * Constructor for rectangle type of polygon
	 * 
	 * @param vertex1
	 * @param vertex2
	 * @param vertex3
	 * @param vertex4
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
	 * @param otherPolygon
	 *            - Another polygon implementing the polygon interface
	 * @return ArrayList<PointOfIntersection> - True indicates polygon exists within
	 *         the rectangle
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
	/**
	 * If another polygon is contained within this rectangle, then all points of the
	 * sub-polygon in question will exist in this rectangle.
	 * 
	 * To determine if this is valid, we imagine each point of the sub-polygon one
	 * at a time inside of the rectangle. Each vertice of the sub-polygon forms a
	 * separate triangle within the polygon with respect to each side of the
	 * rectangle. The total area of each of these triangles is equal to the area of
	 * the rectangle if it exists inside.
	 * 
	 * So, for rectangle ABCD, if point p is within the rectangle, then the area of
	 * sub-triangles formed by points ABP, BCP, CDP, DAP should equal the area of
	 * rectangle ABCD. If any point does not satisfy this requirement, then it is
	 * not contained
	 * 
	 * @param otherPolygon
	 *            - Another polygon implementing the polygon interface
	 * @return boolean - True indicates polygon exists within the rectangle
	 * 
	 */
	public boolean containsPolygon(Polygon otherPolygon) {

		for (Vertex aVertex : otherPolygon.getAllVertices()) {
			// For rectangle ABCD and point p is within the rectangle, then the area of
			// sub-triangles formed by
			// points ABP, BCP, CDP, DAP should equal the area of rectangle ABCD, if not
			// equal, it is not contained
			Double triangleArea = areaOfTriangle(vertices.get(0), vertices.get(1), aVertex)
					+ areaOfTriangle(vertices.get(1), vertices.get(2), aVertex)
					+ areaOfTriangle(vertices.get(2), vertices.get(3), aVertex)
					+ areaOfTriangle(vertices.get(3), vertices.get(0), aVertex);
			Double rectangleArea = areaOfRectangle(vertices.get(0), vertices.get(1), vertices.get(2), vertices.get(3));

			if (!triangleArea.equals(rectangleArea)) {
				return false;
			}
		}
		return true;

	}

	// public Double areaOfRectangle(Vertex vertexA, Vertex vertexB, Vertex vertexC)
	// {
	// Double side1 = getLength(vertexA, vertexB);
	// Double side2 = getLength(vertexB, vertexC);
	//
	// return side1 * side2;
	// }

	public Double areaOfRectangle(Vertex vertexA, Vertex vertexB, Vertex vertexC, Vertex vertexD) {
		Vertex vertex1 = vertexA;
		Vertex vertex2 = vertexD;
		Vertex vertex3 = vertexC;
		Vertex vertex4 = vertexB;

		Double determinate = ((vertex1.getxValue() * vertex2.getyValue()) - (vertex2.getxValue() * vertex1.getyValue()))
				+ ((vertex2.getxValue() * vertex3.getyValue()) - (vertex3.getxValue() * vertex2.getyValue()))
				+ ((vertex3.getxValue() * vertex4.getyValue()) - (vertex4.getxValue() * vertex3.getyValue()))
				+ ((vertex4.getxValue() * vertex1.getyValue()) - (vertex1.getxValue() * vertex4.getyValue()));
		return Math.abs(determinate / 2);
	}

	// Herons formula is prone to rounding errors. We also see this when using the
	// distance formula when trying to compute the sides and area of a rectangle.
	// we need a more accurate method
	// public Double areaOfTriangle(Vertex vertexA, Vertex vertexB, Vertex vertexC)
	// {
	// //Use Herons formula
	// Double sideA = getLength(vertexA, vertexB);
	// Double sideB = getLength(vertexB, vertexC);
	// Double sideC = getLength(vertexC, vertexA);
	//
	// // Half the perimeter
	// Double s = (sideA + sideB + sideC) / 2;
	//
	// return Math.sqrt((s * (s - sideA) * (s - sideB) * (s - sideC)));
	//
	// }
	/**
	 * The determinate method must be applied counter clockwise to each vertex to
	 * obtain the area
	 * 
	 * @param vertexA
	 * @param vertexB
	 * @param vertexC
	 * @return Double - area of triangle
	 */
	public Double areaOfTriangle(Vertex vertexA, Vertex vertexB, Vertex vertexC) {
		Vertex vertex1 = vertexA;
		Vertex vertex2 = vertexC;
		Vertex vertex3 = vertexB;

		// Use determinate method
		Double determinate = (vertex1.getxValue() * vertex2.getyValue()) + (vertex2.getxValue() * vertex3.getyValue())
				+ (vertex3.getxValue() * vertex1.getyValue()) - (vertex1.getxValue() * vertex3.getyValue())
				- (vertex2.getxValue() * vertex1.getyValue()) - (vertex3.getxValue() * vertex2.getyValue());

		return Math.abs(determinate / 2);

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
	 * segments. If a segment is a straight line parallel/perpendicular to the x or
	 * y axis, in that case their equation is the integer intersecting that axis
	 * (e.g. x=3 or y=3)
	 * 
	 * There are also those of sloped lines, whose equation is of the form y=mx+b
	 * 
	 * Since a either of the two received segments could be straight or sloped, we
	 * handle the following combinations - Both non-sloped segments - One
	 * non-sloped, one sloped segment - Both sloped segments
	 * 
	 * We finish by validating that these points of intersection actually occur on
	 * the domain/range of the segments we are validating prior to returning the
	 * point of intersection
	 * 
	 * @param segmenta
	 *            - Segment
	 * @param segmentb
	 *            - Segment
	 * @return poi - PointOfIntersection
	 */
	private PointOfIntersection getPointOfIntersectionBetweenSegments(Segment segmenta, Segment segmentb) {
		PointOfIntersection poi = null;

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

		{// Well then where do the two lines intersect?
			poi = segmenta.getInterceptWithSegment(segmentb);

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
	/**
	 * TODO: I should write something here on what I did Two adjacent lines should
	 * share the same line equation, and overlap in domain/range of each other
	 */
	public boolean isAdjacentToPolygon(Polygon otherPolygon) {
		for (Segment segmenta : segments) {
			for (Segment segmentb : otherPolygon.getAllLineSegments()) {
				// Diverge here to handle the case that both segments are parallel to the x and
				// y axis
				if (segmenta.getSlope() == null && segmentb.getSlope() == null) {
					// Make sure that we are dealing with two lines which are parallel to y
					if (segmenta.getxLine() != null && segmentb.getxLine() != null) {
						if (segmenta.getxLine().equals(segmentb.getxLine())) {
							// These lines could potentially overlap since they share the same line equation
							if (isBetween(segmenta.getVertex1().getxValue(), segmenta.getVertex2().getxValue(),
									segmentb.getVertex1().getxValue())
									|| isBetween(segmenta.getVertex1().getxValue(), segmenta.getVertex2().getxValue(),
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
									|| isBetween(segmenta.getVertex1().getyValue(), segmenta.getVertex2().getyValue(),
											segmentb.getVertex2().getyValue())) {
								return true;
							}
						}
					}

				}
				// For the case where one line has a slope and the other is parallel to either
				// the x or y axis,
				// we do not need to evaluate, because in principle, they cannot be adjacent

				// Both lines have a slope
				if (segmenta.getSlope() != null && segmentb.getSlope() != null) {
					// If both lines have the same slope and intercept, and lie on some subset of
					// the others domain/range, they are adjacent
					if (segmenta.getSlope().equals(segmentb.getSlope())
							&& segmenta.getyIntercept().equals(segmentb.getyIntercept())) {

						if ((isBetween(segmenta.getVertex1().getxValue(), segmenta.getVertex2().getxValue(),
								segmentb.getVertex1().getxValue())
								|| isBetween(segmenta.getVertex1().getxValue(), segmenta.getVertex2().getxValue(),
										segmentb.getVertex2().getxValue()))) {
							return true;
						}

					}
				}
			}
		}

		return false;
	}

}
