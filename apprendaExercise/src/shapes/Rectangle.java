package shapes;

import java.util.ArrayList;

import polygonComponents.Segment;
import polygonComponents.Vertex;

/**
 * An implementation of the polygon interface for a rectangle
 * 
 * @author brads
 *
 */
public class Rectangle implements Polygon {

	private ArrayList<Segment> segments = new ArrayList<Segment>(); // Segments which constitute the polygon
	private ArrayList<Vertex> vertices = new ArrayList<Vertex>(); // Vertices which make up this polygon, in clockwise
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

	public Rectangle(ArrayList<Vertex> verticies) {
		segments.add(new Segment(verticies.get(0), verticies.get(1)));
		segments.add(new Segment(verticies.get(1), verticies.get(2)));
		segments.add(new Segment(verticies.get(2), verticies.get(3)));
		segments.add(new Segment(verticies.get(3), verticies.get(0)));

		vertices.add(verticies.get(0));
		vertices.add(verticies.get(1));
		vertices.add(verticies.get(2));
		vertices.add(verticies.get(3));
	}

	public ArrayList<Segment> getAllLineSegments() {
		return segments;
	}

	public ArrayList<Vertex> getAllVertices() {
		return vertices;
	}

	/**
	 * The determinate method must be applied counter clockwise to each vertex to
	 * obtain the area
	 * 
	 * @param vertexA
	 * @param vertexB
	 * @param vertexC
	 * @param vertexD
	 * @return Double - area of rectangle
	 */
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
	 * @return ArrayList<Vertex> - True indicates polygon exists within the
	 *         rectangle
	 */
	public ArrayList<Vertex> getIntersectionsWithPolygon(Polygon otherPolygon) {
		ArrayList<Vertex> allPois = new ArrayList<Vertex>();
		for (Segment mySegment : segments) {
			for (Segment otherSegment : otherPolygon.getAllLineSegments()) {
				Vertex poi = null;// Flush for this analysis
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
	 * @return poi - Vertex
	 */
	private Vertex getPointOfIntersectionBetweenSegments(Segment segmenta, Segment segmentb) {
		Vertex poi = null;

		// Intersection of two non-sloped lines
		if (segmenta.getxLine() != null && segmentb.getyLine() != null) {
			// segmenta is vertical and segmentb is horizontal
			poi = new Vertex(segmenta.getxLine(), segmentb.getyLine());
		}
		if (segmenta.getyLine() != null && segmentb.getxLine() != null) {
			// segmenta is horizontal and segmentb is vertical
			poi = new Vertex(segmentb.getxLine(), segmenta.getyLine());
		}

		// Line segment a is sloped and line segment b is non-sloped
		if (segmenta.getSlope() != null && segmentb.getSlope() == null) {

			// line segment b is vertical and we will intercept where segmenta line equation
			// resolves to y
			if (segmentb.getxLine() != null) {
				poi = new Vertex(segmentb.getxLine(), segmenta.computeYForX(segmentb.getxLine()));

			}
			// line segment b is horizontal and we will intercept where segmenta line
			// equation resolves to x
			if (segmentb.getyLine() != null) {
				poi = new Vertex(segmenta.computeXForY(segmentb.getyLine()), segmentb.getyLine());
			}
		}
		// Line segmentb is sloped and line segmenta is non-sloped
		if (segmentb.getSlope() != null && segmenta.getSlope() == null) {

			if (segmenta.getxLine() != null) {
				poi = new Vertex(segmenta.getxLine(), segmentb.computeYForX(segmenta.getxLine()));

			}
			if (segmenta.getyLine() != null) {
				poi = new Vertex(segmentb.computeXForY(segmenta.getyLine()), segmenta.getyLine());
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
	/**
	 * Verify that a point of intersection exists in the range of both segments
	 * 
	 * @param poi
	 *            - a vertex which shows the point we wish to test for intersection
	 * @param segment
	 *            a - Segment
	 * @param segment
	 *            b - Segment
	 * @return boolean - if point lies within both segments range
	 * 
	 */
	public boolean validatePointOfIntersectionOnSegment(Vertex poi, Segment segmenta, Segment segmentb) {
		return (isBetween(segmenta.getVertex1().getxValue(), segmenta.getVertex2().getxValue(), poi.getxValue())
				&& isBetween(segmenta.getVertex1().getyValue(), segmenta.getVertex2().getyValue(), poi.getyValue())
				&& isBetween(segmentb.getVertex1().getxValue(), segmentb.getVertex2().getxValue(), poi.getxValue())
				&& isBetween(segmentb.getVertex1().getyValue(), segmentb.getVertex2().getyValue(), poi.getyValue()));
	}

	/**
	 * Since the order of the points is not guaranteed, we find the minimum and
	 * maximum between these points and test that the value is between.
	 * 
	 * @param a
	 *            - end point
	 * @param b
	 *            - end point
	 * @param c
	 *            - value to test
	 * @return whether value exists between both end points
	 */
	private boolean isBetween(Double a, Double b, Double c) {
		Double low = Math.min(a, b);
		Double high = Math.max(a, b);
		return (low <= c && c <= high);
	}

	@Override
	/**
	 * Two adjacent lines should share the same line equation, and overlap in
	 * domain/range of each other
	 * 
	 * This method is ugly. but it's complex and there isn't a whole lot of
	 * duplicate code, even though it looks like there is
	 * 
	 * @param otherPolygon
	 *            - another polygon
	 * 
	 * 
	 * @param return
	 *            boolean - whether at least one side of the polygon is adjacent to
	 *            this polygon
	 * 
	 * 
	 */
	public boolean isAdjacentToPolygon(Polygon otherPolygon) {
		for (Segment segmenta : segments) {
			for (Segment segmentb : otherPolygon.getAllLineSegments()) {
				// Diverge here to handle the case that both segments are parallel to either the
				// x or
				// y axis
				if (segmenta.getSlope() == null && segmentb.getSlope() == null) {
					// Make sure that we are dealing with two lines which are parallel to y
					if (segmenta.getxLine() != null && segmentb.getxLine() != null) {
						if (segmenta.getxLine().equals(segmentb.getxLine())) {
							// These lines could potentially overlap since they share the same line equation
							// But we still need to check that the line exists in the range of the segment
							if (isBetween(segmenta.getVertex1().getyValue(), segmenta.getVertex2().getyValue(),
									segmentb.getVertex1().getyValue())
									|| isBetween(segmenta.getVertex1().getyValue(), segmenta.getVertex2().getyValue(),
											segmentb.getVertex2().getyValue())) {
								return true;
							}
							// Check for reverse in case that adjacent polygon is tested against a larger
							// segment and falls
							// out of bounds from previous test
							if (isBetween(segmentb.getVertex1().getyValue(), segmentb.getVertex2().getyValue(),
									segmenta.getVertex1().getyValue())
									|| isBetween(segmentb.getVertex1().getyValue(), segmentb.getVertex2().getyValue(),
											segmenta.getVertex2().getyValue())) {
								return true;
							}
						}
					}
					// Make sure that we are dealing with two lines which are parallel to x
					if (segmenta.getyLine() != null && segmentb.getyLine() != null) {
						if (segmenta.getyLine().equals(segmentb.getyLine())) {
							// These lines could potentially overlap since they share the same line equation
							// But we still need to check that the line exists in the range of the segment
							if (isBetween(segmenta.getVertex1().getxValue(), segmenta.getVertex2().getxValue(),
									segmentb.getVertex1().getxValue())
									|| isBetween(segmenta.getVertex1().getxValue(), segmenta.getVertex2().getxValue(),
											segmentb.getVertex2().getxValue())) {
								return true;
							}
							// Check for reverse in case that adjacent polygon is tested against a larger
							// segment and falls
							// out of bounds from previous test
							if (isBetween(segmentb.getVertex1().getxValue(), segmentb.getVertex2().getxValue(),
									segmenta.getVertex1().getxValue())
									|| isBetween(segmentb.getVertex1().getxValue(), segmentb.getVertex2().getxValue(),
											segmenta.getVertex2().getxValue())) {
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
						// These lines could potentially overlap since they share the same line equation
						// But we still need to check that the line exists in the range of the segment
						if ((isBetween(segmenta.getVertex1().getxValue(), segmenta.getVertex2().getxValue(),
								segmentb.getVertex1().getxValue())
								|| isBetween(segmenta.getVertex1().getxValue(), segmenta.getVertex2().getxValue(),
										segmentb.getVertex2().getxValue()))) {
							return true;
						}

						// Check for reverse in case that adjacent polygon is tested against a larger
						// segment and falls
						// out of bounds from previous test
						if ((isBetween(segmentb.getVertex1().getxValue(), segmentb.getVertex2().getxValue(),
								segmenta.getVertex1().getxValue())
								|| isBetween(segmentb.getVertex1().getxValue(), segmentb.getVertex2().getxValue(),
										segmenta.getVertex2().getxValue()))) {
							return true;
						}

					}
				}
			}
		}

		return false;
	}

}
