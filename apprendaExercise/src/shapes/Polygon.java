package shapes;

import utilities.PointOfIntersection;
import utilities.Segment;
import utilities.Vertex;

import java.util.ArrayList;

/**
 * 
 * @author brads
 *
 */
public interface Polygon {

	ArrayList<PointOfIntersection> getIntersectionsWithPolygon(Polygon otherPolygon);

	boolean containsPolygon(Polygon otherPolygon);

	boolean isAdjacentToPolygon(Polygon otherPolygon);

	boolean validatePointOfIntersectionOnSegment(PointOfIntersection poi, Segment segmenta, Segment segmentb);

	ArrayList<Segment> getAllLineSegments();

	ArrayList<Vertex> getAllVertices();

	boolean equals(Object otherObject);

	String toString();

}
