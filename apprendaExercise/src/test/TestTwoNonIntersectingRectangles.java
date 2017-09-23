package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import shapes.Polygon;
import shapes.Rectangle;
import utilities.PointOfIntersection;
import utilities.Vertex;

public class TestTwoNonIntersectingRectangles {

	@Test
	public void test() {
		Polygon polygona = new Rectangle(new Vertex(2.0, 9.0), new Vertex(10.0, 9.0), new Vertex(10.0, 3.0),
				new Vertex(2.0, 3.0));
		Polygon polygonb = new Rectangle(new Vertex(14.0, 6.0), new Vertex(19.0, 6.0), new Vertex(19.0, 3.0),
				new Vertex(14.0, 3.0));

		ArrayList<PointOfIntersection> pois = new ArrayList<PointOfIntersection>();
		pois = polygona.getIntersectionsWithPolygon(polygonb);

		// Assertions
		assert (pois.size() == 0);
	}

}
