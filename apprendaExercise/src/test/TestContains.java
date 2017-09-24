package test;


import org.junit.Test;

import shapes.Polygon;
import shapes.Rectangle;
import utilities.Vertex;

public class TestContains {
	@Test
	public void testContainsRectangle() {
		Polygon polygona = new Rectangle(new Vertex(2.0, 10.0), new Vertex(9.0, 10.0), new Vertex(9.0, 4.0),
				new Vertex(2.0, 4.0));
		Polygon polygonb = new Rectangle(new Vertex(4.0, 9.0), new Vertex(8.0, 9.0), new Vertex(8.0, 5.0),
				new Vertex(4.0, 5.0));

		assert (polygona.containsPolygon(polygonb));
	}

	@Test
	public void testNotContainsRectangle() {
		Polygon polygona = new Rectangle(new Vertex(2.0, 11.0), new Vertex(12.0, 11.0), new Vertex(12.0, 5.0),
				new Vertex(2.0, 5.0));
		Polygon polygonb = new Rectangle(new Vertex(8.0, 8.0), new Vertex(16.0, 8.0), new Vertex(16.0, 1.0),
				new Vertex(8.0, 1.0));

		assert (!polygona.containsPolygon(polygonb));
	}

	@Test
	public void testContainsRectangleNegativeQuadrant() {
		Polygon polygona = new Rectangle(new Vertex(-2.0, -10.0), new Vertex(-9.0, -10.0), new Vertex(-9.0, -4.0),
				new Vertex(-2.0, -4.0));
		Polygon polygonb = new Rectangle(new Vertex(-4.0, -9.0), new Vertex(-8.0, -9.0), new Vertex(-8.0, -5.0),
				new Vertex(-4.0, -5.0));

		assert (polygona.containsPolygon(polygonb));
	}
	
	@Test
	public void testNotContainsRectangleNegativeQuadrant() {
		Polygon polygona = new Rectangle(new Vertex(-2.0, -11.0), new Vertex(-12.0, -11.0), new Vertex(-12.0, -5.0),
				new Vertex(-2.0, -5.0));
		Polygon polygonb = new Rectangle(new Vertex(-8.0, -8.0), new Vertex(-16.0, -8.0), new Vertex(-16.0, -1.0),
				new Vertex(-8.0, -1.0));

		assert (!polygona.containsPolygon(polygonb));
	}

}
