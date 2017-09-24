package test;

import java.util.ArrayList;

import org.junit.Test;

import shapes.Polygon;
import shapes.Rectangle;
import utilities.PointOfIntersection;
import utilities.Vertex;

public class TestCombinations {

	@Test
	public void testAdjacentRectanglesAndIntersecting() {
		Polygon polygona = new Rectangle(new Vertex(2.0, 8.0), new Vertex(8.0, 8.0), new Vertex(8.0, 4.0),
				new Vertex(2.0, 4.0));
		Polygon polygonb = new Rectangle(new Vertex(8.0, 5.0), new Vertex(12.0, 5.0), new Vertex(12.0, 2.0),
				new Vertex(8.0, 2.0));
		ArrayList<PointOfIntersection> pois = new ArrayList<PointOfIntersection>();
		pois = polygona.getIntersectionsWithPolygon(polygonb);

		// test data
		PointOfIntersection poi1 = new PointOfIntersection(8.0, 5.0);
		PointOfIntersection poi2 = new PointOfIntersection(8.0, 4.0);
		// Assertions

		// assertions
		assert (polygona.isAdjacentToPolygon(polygonb));
		assert (pois.contains(poi1));
		assert (pois.contains(poi2));
		assert (pois.size() == 2);
	}

}
