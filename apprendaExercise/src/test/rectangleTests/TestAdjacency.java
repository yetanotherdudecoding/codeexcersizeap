package test.rectangleTests;

import org.junit.Test;

import polygonComponents.Vertex;
import shapes.Polygon;
import shapes.Rectangle;

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
	public void testNonAdjacentRectanglesOnSameLine() {
		Polygon polygona = new Rectangle(new Vertex(2.0, 8.0), new Vertex(8.0, 8.0), new Vertex(8.0, 4.0),
				new Vertex(2.0, 4.0));
		Polygon polygonb = new Rectangle(new Vertex(8.0, 3.0), new Vertex(12.0, 3.0), new Vertex(12.0, 0.0),
				new Vertex(8.0, 0.0));

		assert (!polygona.isAdjacentToPolygon(polygonb));
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

	@Test
	public void testHorizontalAdjacency() {
		Polygon polygona = new Rectangle(new Vertex(2.0, 8.0), new Vertex(8.0, 8.0), new Vertex(8.0, 4.0),
				new Vertex(2.0, 4.0));
		Polygon polygonb = new Rectangle(new Vertex(4.0, 4.0), new Vertex(6.0, 4.0), new Vertex(6.0, 2.0),
				new Vertex(4.0, 2.0));

		assert (polygona.isAdjacentToPolygon(polygonb));
		assert (polygonb.isAdjacentToPolygon(polygona));
	}

	@Test
	public void testMultipleAdjacency() {
		Polygon polygona = new Rectangle(new Vertex(2.0, 8.0), new Vertex(8.0, 8.0), new Vertex(8.0, 4.0),
				new Vertex(2.0, 4.0));
		Polygon polygonb = new Rectangle(new Vertex(2.0, 8.0), new Vertex(9.0, 8.0), new Vertex(9.0, 4.0),
				new Vertex(2.0, 4.0));

		assert (polygona.isAdjacentToPolygon(polygonb));
		assert (polygonb.isAdjacentToPolygon(polygona));
	}

	@Test
	public void testHorizontalAdjacencyNegativeQuadrant() {
		Polygon polygona = new Rectangle(new Vertex(-2.0, -8.0), new Vertex(-8.0, -8.0), new Vertex(-8.0, -4.0),
				new Vertex(-2.0, -4.0));
		Polygon polygonb = new Rectangle(new Vertex(-4.0, -4.0), new Vertex(-6.0, -4.0), new Vertex(-6.0, -2.0),
				new Vertex(-4.0, -2.0));

		assert (polygona.isAdjacentToPolygon(polygonb));
		assert (polygonb.isAdjacentToPolygon(polygona));
	}

	@Test
	public void testAdjacencyOfRotatedRectangle1() {

		Polygon polygona = new Rectangle(new Vertex(3.0, 8.0), new Vertex(6.0, 5.0), new Vertex(3.0, 2.0),
				new Vertex(0.0, 5.0));
		Polygon polygonb = new Rectangle(new Vertex(5.0, 4.0), new Vertex(7.0, 2.0), new Vertex(5.0, 0.0),
				new Vertex(3.0, 2.0));

		assert (polygona.isAdjacentToPolygon(polygonb));
		assert (polygonb.isAdjacentToPolygon(polygona));
	}

	@Test
	public void testAdjacencyOfRotatedRectangle2() {

		Polygon polygona = new Rectangle(new Vertex(3.0, 8.0), new Vertex(6.0, 5.0), new Vertex(3.0, 2.0),
				new Vertex(0.0, 5.0));
		Polygon polygonb = new Rectangle(new Vertex(5.0, 8.0), new Vertex(6.0, 7.0), new Vertex(5.0, 6.0),
				new Vertex(4.0, 7.0));

		assert (polygona.isAdjacentToPolygon(polygonb));
		assert (polygonb.isAdjacentToPolygon(polygona));
	}

	@Test
	public void testNonAdjacencyOfRotatedRectangle() {
		Polygon polygona = new Rectangle(new Vertex(3.0, 8.0), new Vertex(6.0, 5.0), new Vertex(3.0, 2.0),
				new Vertex(0.0, 5.0));
		Polygon polygonb = new Rectangle(new Vertex(6.0, 3.0), new Vertex(7.0, 2.0), new Vertex(5.0, 0.0),
				new Vertex(4.0, 1.0));

		assert (!polygona.isAdjacentToPolygon(polygonb));
		assert (!polygonb.isAdjacentToPolygon(polygona));
	}

	@Test
	public void testNonAdjacencyOfRotatedRectangleOnSameLine() {
		Polygon polygona = new Rectangle(new Vertex(1.0, 6.0), new Vertex(5.0, 10.0), new Vertex(6.0, 9.0),
				new Vertex(2.0, 5.0));
		Polygon polygonb = new Rectangle(new Vertex(4.0, 3.0), new Vertex(8.0, 7.0), new Vertex(9.0, 6.0),
				new Vertex(5.0, 2.0));

		assert (!polygona.isAdjacentToPolygon(polygonb));
		assert (!polygonb.isAdjacentToPolygon(polygona));
	}

}
