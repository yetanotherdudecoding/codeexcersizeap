package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import shapes.Polygon;
import shapes.Rectangle;
import utilities.Vertex;
import utilities.PointOfIntersection;

public class TestTwoIntersectingRectangles {

	@Test
	public void test() {
		Polygon polygona = new Rectangle(new Vertex(2.0, 11.0), new Vertex(12.0, 11.0), new Vertex(12.0, 5.0),
				new Vertex(2.0, 5.0));
		Polygon polygonb = new Rectangle(new Vertex(8.0, 8.0), new Vertex(16.0, 8.0), new Vertex(16.0, 1.0),
				new Vertex(8.0, 1.0));

		ArrayList<PointOfIntersection> pois = new ArrayList<PointOfIntersection>();
		pois = polygona.getIntersectionsWithPolygon(polygonb);

		PointOfIntersection poi1 = new PointOfIntersection(12.0, 8.0);
		PointOfIntersection poi2 = new PointOfIntersection(8.0, 5.0);
		// Assertions
		assert (pois.contains(poi1));
		assert (pois.contains(poi2));
		assert (pois.size() == 2);
		
		pois = new ArrayList<PointOfIntersection>();
		
		
	}

}
