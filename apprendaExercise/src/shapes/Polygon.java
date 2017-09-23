package shapes;

import utilities.PointOfIntersection;
import utilities.Segment;

import java.util.ArrayList;
/**
 * 
 * @author brads
 *
 */
public interface Polygon {

	ArrayList<PointOfIntersection> getIntersectionsWithPolygon(Polygon otherPolygon);

	boolean containsPolygon(Polygon shapeb);

	boolean isAdjacentToPolygon(Polygon shapeb);

	ArrayList<Segment> getAllSegments();
	
	boolean validatePointOfIntersectionOnSegment(PointOfIntersection poi, Segment segmenta, Segment segmentb);

}
