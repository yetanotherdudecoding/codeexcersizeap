package test.rectangleTests;



import org.junit.Test;

import polygonComponents.Vertex;
import shapes.Polygon;
import shapes.Rectangle;

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
	@Test
	public void testRotatedRectangleContains() {
		Polygon polygona = new Rectangle(new Vertex(2.0, 7.0), new Vertex(11.0, 7.0), new Vertex(11.0, 1.0),
				new Vertex(2.0, 1.0));
		Polygon polygonb = new Rectangle(new Vertex(6.0, 7.0), new Vertex(8.0, 5.0), new Vertex(6.0, 3.0),
				new Vertex(4.0, 5.0));

		assert (polygona.containsPolygon(polygonb));
		assert (!polygonb.containsPolygon(polygona));
	}
	@Test 
	public void testRotatedRectangleNotContains() {
		Polygon polygona = new Rectangle(new Vertex(1.0, 4.0), new Vertex(6.0, 9.0), new Vertex(9.0, 6.0),
				new Vertex(4.0, 1.0));
		Polygon polygonb = new Rectangle(new Vertex(7.0, 9.0), new Vertex(12.0, 9.0), new Vertex(12.0, 4.0),
				new Vertex(7.0, 4.0));

		assert (!polygona.containsPolygon(polygonb));
	}
	
	@Test
	public void testRotatedRectangleContainedInRotatedRectangle() {
		Polygon polygona = new Rectangle(new Vertex(4.0, 7.0), new Vertex(7.0, 4.0), new Vertex(4.0, 1.0),
				new Vertex(1.0, 4.0));
		Polygon polygonb = new Rectangle(new Vertex(4.0, 5.0), new Vertex(5.0, 4.0), new Vertex(4.0, 3.0),
				new Vertex(3.0, 4.0));

		assert (polygona.containsPolygon(polygonb));
		assert (!polygonb.containsPolygon(polygona));
	}
	@Test 
	public void testRotatedRectanglenNotContainedInRotatedRectangle() {
		Polygon polygona = new Rectangle(new Vertex(3.0, 6.0), new Vertex(5.0, 4.0), new Vertex(3.0, 2.0),
				new Vertex(1.0, 4.0));
		Polygon polygonb = new Rectangle(new Vertex(5.0, 3.0), new Vertex(6.0, 2.0), new Vertex(5.0, 1.0),
				new Vertex(4.0, 2.0));

		assert (!polygona.containsPolygon(polygonb));
	}
}
