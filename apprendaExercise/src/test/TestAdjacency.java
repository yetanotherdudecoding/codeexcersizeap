package test;

import org.junit.Test;

import shapes.Polygon;
import shapes.Rectangle;
import utilities.Vertex;

public class TestAdjacency {

	@Test
	public void testAdjacentRectangles() {
		Polygon polygona = new Rectangle(new Vertex(2.0, 8.0), new Vertex(8.0, 8.0), new Vertex(8.0, 4.0),
				new Vertex(2.0, 4.0));
		Polygon polygonb = new Rectangle(new Vertex(8.0, 5.0), new Vertex(12.0, 5.0), new Vertex(12.0, 2.0),
				new Vertex(8.0, 2.0));

		assert (polygona.isAdjacentToPolygon(polygonb));
	}

	@Test
	public void testNonAdjacentRectangles() {
		Polygon polygona = new Rectangle(new Vertex(2.0, 7.0), new Vertex(7.0, 7.0), new Vertex(7.0, 3.0),
				new Vertex(2.0, 3.0));
		Polygon polygonb = new Rectangle(new Vertex(9.0, 4.0), new Vertex(14.0, 4.0), new Vertex(14.0, 0.0),
				new Vertex(9.0, 0.0));

		assert (!polygona.isAdjacentToPolygon(polygonb));
	}

	@Test
	public void testAdjacentRectanglesNegativeQuadrant() {
		Polygon polygona = new Rectangle(new Vertex(-2.0, -8.0), new Vertex(-8.0, -8.0), new Vertex(-8.0, -4.0),
				new Vertex(-2.0, -4.0));
		Polygon polygonb = new Rectangle(new Vertex(-8.0, -5.0), new Vertex(-12.0, -5.0), new Vertex(-12.0, -2.0),
				new Vertex(-8.0, -2.0));

		assert (polygona.isAdjacentToPolygon(polygonb));
	}

	@Test
	public void testNonAdjacentRectanglesNegativeQuadrant() {
		Polygon polygona = new Rectangle(new Vertex(-2.0, -7.0), new Vertex(-7.0, -7.0), new Vertex(-7.0, -3.0),
				new Vertex(-2.0, -3.0));
		Polygon polygonb = new Rectangle(new Vertex(-9.0, -4.0), new Vertex(-14.0, -4.0), new Vertex(-14.0, 0.0),
				new Vertex(-9.0, 0.0));

		assert (!polygona.isAdjacentToPolygon(polygonb));
	}
}
