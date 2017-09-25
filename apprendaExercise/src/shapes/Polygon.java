package shapes;

import java.util.ArrayList;

import polygonComponents.Segment;
import polygonComponents.Vertex;

/**
 * Program to an interface in case I have time to extend this to other polygons
 *
 * @author brads
 *
 */
public interface Polygon {
	
	ArrayList<Vertex> getIntersectionsWithPolygon(Polygon otherPolygon);

	boolean containsPolygon(Polygon otherPolygon);

	boolean isAdjacentToPolygon(Polygon otherPolygon);

	boolean validatePointOfIntersectionOnSegment(Vertex pointOfIntersection, Segment segmenta, Segment segmentb);

	ArrayList<Segment> getAllLineSegments();

	ArrayList<Vertex> getAllVertices();

	boolean equals(Object otherObject);

	String toString();

}
