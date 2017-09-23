package shapes;

import utilities.Vertex;
import java.util.ArrayList;
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
	/*
	 * Vertices must be specified either clockwise or counter clockwise along the perimiter
	 */
	public Rectangle(Vertex vertex1, Vertex vertex2, Vertex vertex3, Vertex vertex4) {
		// This could be made more extensible to allow any type of polygon with an
		// arbitrary
		// number of sides to be analyzed
		segments.add(new Segment(vertex1, vertex2));
		segments.add(new Segment(vertex2, vertex3));
		segments.add(new Segment(vertex3, vertex4));
		segments.add(new Segment(vertex4, vertex1));

	}

	@Override
	/**
	 * There may be a fancier way to check for intersections of two polygons, but
	 * I'm not a fancy guy.
	 * 
	 * What we do here is anchor on a line segment of this polygon and then iterate
	 * through all other line segments of the other polygon until we've checked for
	 * all possible intersections of every line segment combination between the two
	 * polygons
	 * 
	 */
	public ArrayList<PointOfIntersection> getIntersectionsWithPolygon(Polygon otherPolygon) {

		ArrayList<PointOfIntersection> allPois = new ArrayList<PointOfIntersection>();
		for (Segment mySegment : segments) {
			for (Segment otherSegment : otherPolygon.getAllSegments()) {
				PointOfIntersection poi = null;// Flush for this analysis
				poi = getPointOfIntersectionBetweenSegments(mySegment, otherSegment);
				if (poi != null) {
					allPois.add(poi);
				}
			}
		}

		return allPois;

	}

	@Override
	public boolean containsPolygon(Polygon shapeb) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAdjacentToPolygon(Polygon shapeb) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Segment> getAllSegments() {
		return segments;
	}

	private PointOfIntersection getPointOfIntersectionBetweenSegments(Segment segmenta, Segment segmentb) {
		PointOfIntersection poi = null;
		// Diverge here to handle the case that both segments are parallel to the x or y
		// axis
		if (segmenta.getSlope() == null && segmentb.getSlope() == null) {
			if (segmenta.getyLine() == null && segmentb.getyLine() == null
					|| segmenta.getxLine() == null && segmentb.getxLine() == null) {
				return null;
			}
			if (segmenta.getxLine() == null && segmentb.getyLine() == null) {
				// segmenta is horizontal and segmentb is vertical
				poi = new PointOfIntersection(segmentb.getxLine(), segmenta.getyLine());
			}
			if (segmenta.getyLine() == null && segmentb.getxLine() == null) {
				// segmenta is vertical and segmentb is horizontal
				poi = new PointOfIntersection(segmenta.getxLine(), segmentb.getyLine());
			}

		}
		if (validatePointOfIntersectionOnSegment(poi, segmenta, segmentb)) {
			return poi;

		}
		return null;
	}

	@Override
	//TODO: I do not know why this is not letting me use the private scope on this method.
	public boolean validatePointOfIntersectionOnSegment(PointOfIntersection poi, Segment segmenta, Segment segmentb) {
		return (isBetween(segmenta.getVertex1().getxValue(), segmenta.getVertex2().getxValue(), poi.getX())&&
				isBetween(segmenta.getVertex1().getyValue(), segmenta.getVertex2().getyValue(), poi.getY())&&
				isBetween(segmentb.getVertex1().getxValue(), segmentb.getVertex2().getxValue(), poi.getX())&&
				isBetween(segmentb.getVertex1().getyValue(), segmentb.getVertex2().getyValue(), poi.getY()));
	}

	private boolean isBetween(Double a, Double b, Double c) {
		Double low = Math.min(a, b);
		Double high = Math.max(a, b);
		return (low <= c && c <= high);
	}
}
