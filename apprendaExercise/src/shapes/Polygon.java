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

	ArrayList<Segment> getAllLineSegments();

	ArrayList<Vertex> getAllVertices();

	String toString();

}
