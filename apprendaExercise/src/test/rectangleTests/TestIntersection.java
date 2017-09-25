package test.rectangleTests;


import java.util.ArrayList;

import org.junit.Test;

import shapes.Polygon;
import shapes.Rectangle;
import utilities.Vertex;
import utilities.PointOfIntersection;

public class TestIntersection {

	@Test
	public void testTwoIntersectingRectangles1() {
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
		
	}
	
	@Test
	public void testTwoIntersectingRectangles2() {
		Polygon polygona = new Rectangle(new Vertex(2.0, 11.0), new Vertex(12.0, 11.0), new Vertex(12.0, 5.0),
				new Vertex(2.0, 5.0));
		Polygon polygonb = new Rectangle(new Vertex(8.0, 8.0), new Vertex(9.0, 8.0), new Vertex(9.0, 1.0),
				new Vertex(8.0, 1.0));

		ArrayList<PointOfIntersection> pois = new ArrayList<PointOfIntersection>();
		pois = polygona.getIntersectionsWithPolygon(polygonb);

		PointOfIntersection poi1 = new PointOfIntersection(9.0, 5.0);
		PointOfIntersection poi2 = new PointOfIntersection(8.0, 5.0);
		// Assertions
		assert (pois.contains(poi1));
		assert (pois.contains(poi2));
		assert (pois.size() == 2);
		
	}
	
	@Test
	public void testNonIntersectingRectangles1() {
		Polygon polygona = new Rectangle(new Vertex(1.0, 3.0), new Vertex(4.0, 3.0), new Vertex(4.0, 1.0),
				new Vertex(1.0, 1.0));
		Polygon polygonb = new Rectangle(new Vertex(5.0, 3.0), new Vertex(8.0, 3.0), new Vertex(8.0, 1.0),
				new Vertex(5.0, 1.0));

		ArrayList<PointOfIntersection> pois = new ArrayList<PointOfIntersection>();
		pois = polygona.getIntersectionsWithPolygon(polygonb);

		// Assertions
		assert (pois.size() == 0);
	}
	@Test
	public void testNonIntersectingRectangles2() {
		Polygon polygona = new Rectangle(new Vertex(2.0, 9.0), new Vertex(10.0, 9.0), new Vertex(10.0, 3.0),
				new Vertex(2.0, 3.0));
		Polygon polygonb = new Rectangle(new Vertex(14.0, 6.0), new Vertex(19.0, 6.0), new Vertex(19.0, 3.0),
				new Vertex(14.0, 3.0));

		ArrayList<PointOfIntersection> pois = new ArrayList<PointOfIntersection>();
		pois = polygona.getIntersectionsWithPolygon(polygonb);

		// Assertions
		assert (pois.size() == 0);
	}
	@Test
	public void testIntersectionNegativeQuadrant() {
		Polygon polygona = new Rectangle(new Vertex(-2.0, -11.0), new Vertex(-12.0, -11.0), new Vertex(-12.0,-5.0),
				new Vertex(-2.0, -5.0));
		Polygon polygonb = new Rectangle(new Vertex(-8.0, -8.0), new Vertex(-16.0, -8.0), new Vertex(-16.0, -1.0),
				new Vertex(-8.0, -1.0));

		ArrayList<PointOfIntersection> pois = new ArrayList<PointOfIntersection>();
		pois = polygona.getIntersectionsWithPolygon(polygonb);

		PointOfIntersection poi1 = new PointOfIntersection(-12.0, -8.0);
		PointOfIntersection poi2 = new PointOfIntersection(-8.0, -5.0);
		// Assertions
		assert (pois.contains(poi1));
		assert (pois.contains(poi2));
		assert (pois.size() == 2);
	}
	
	@Test
	public void testNonIntersectingRectanglesNegativeQuadrant() {
		Polygon polygona = new Rectangle(new Vertex(-2.0, -9.0), new Vertex(-10.0, -9.0), new Vertex(-10.0, -3.0),
				new Vertex(-2.0, -3.0));
		Polygon polygonb = new Rectangle(new Vertex(-14.0, -6.0), new Vertex(-19.0, -6.0), new Vertex(-19.0, -3.0),
				new Vertex(-14.0, -3.0));

		ArrayList<PointOfIntersection> pois = new ArrayList<PointOfIntersection>();
		pois = polygona.getIntersectionsWithPolygon(polygonb);

		// Assertions
		assert (pois.size() == 0);
	}

	@Test 
	public void testRotatedRectangle() {
		Polygon polygona = new Rectangle(new Vertex(1.0, 4.0), new Vertex(6.0, 9.0), new Vertex(9.0, 6.0),
				new Vertex(4.0, 1.0));
		Polygon polygonb = new Rectangle(new Vertex(7.0, 9.0), new Vertex(12.0, 9.0), new Vertex(12.0, 4.0),
				new Vertex(7.0, 4.0));

		ArrayList<PointOfIntersection> pois = new ArrayList<PointOfIntersection>();
		pois = polygona.getIntersectionsWithPolygon(polygonb);

		PointOfIntersection poi1 = new PointOfIntersection(7.0, 8.0);
		PointOfIntersection poi2 = new PointOfIntersection(7.0, 4.0);
		// Assertions
		assert (pois.contains(poi1));
		assert (pois.contains(poi2));
		assert (pois.size() == 2);
	}
	
	@Test 
	public void testOneRotatedRectangleNoIntersection() {
		Polygon polygona = new Rectangle(new Vertex(1.0, 5.0), new Vertex(4.0, 8.0), new Vertex(7.0, 5.0),
				new Vertex(4.0, 2.0));
		Polygon polygonb = new Rectangle(new Vertex(6.0, 11.0), new Vertex(9.0, 11.0), new Vertex(9.0, 8.0),
				new Vertex(6.0, 8.0));

		ArrayList<PointOfIntersection> pois = new ArrayList<PointOfIntersection>();
		pois = polygona.getIntersectionsWithPolygon(polygonb);

		// Assertions
		assert (pois.size() == 0);
	}
	
	@Test 
	public void testRotatedRectangleReverse() {
		//This time, make the non-rotated rectangle object complete the method
		Polygon polygona = new Rectangle(new Vertex(1.0, 4.0), new Vertex(6.0, 9.0), new Vertex(9.0, 6.0),
				new Vertex(4.0, 1.0));
		Polygon polygonb = new Rectangle(new Vertex(7.0, 9.0), new Vertex(12.0, 9.0), new Vertex(12.0, 4.0),
				new Vertex(7.0, 4.0));

		ArrayList<PointOfIntersection> pois = new ArrayList<PointOfIntersection>();
		pois = polygonb.getIntersectionsWithPolygon(polygona);

		PointOfIntersection poi1 = new PointOfIntersection(7.0, 8.0);
		PointOfIntersection poi2 = new PointOfIntersection(7.0, 4.0);
		// Assertions
		assert (pois.contains(poi1));
		assert (pois.contains(poi2));
		assert (pois.size() == 2);
	}
	
	@Test 
	public void testOneRotatedRectangleNoIntersectionReverse() {
		//This time, make the non-rotated rectangle object complete the method
		Polygon polygona = new Rectangle(new Vertex(1.0, 5.0), new Vertex(4.0, 8.0), new Vertex(7.0, 5.0),
				new Vertex(4.0, 2.0));
		Polygon polygonb = new Rectangle(new Vertex(6.0, 11.0), new Vertex(9.0, 11.0), new Vertex(9.0, 8.0),
				new Vertex(6.0, 8.0));

		ArrayList<PointOfIntersection> pois = new ArrayList<PointOfIntersection>();
		pois = polygonb.getIntersectionsWithPolygon(polygona);

		// Assertions
		assert (pois.size() == 0);
	}
	@Test 
	public void testOneRotatedRectangleNegativeQuadrant() {
		Polygon polygona = new Rectangle(new Vertex(-1.0, -4.0), new Vertex(-6.0, -9.0), new Vertex(-9.0, -6.0),
				new Vertex(-4.0, -1.0));
		Polygon polygonb = new Rectangle(new Vertex(-7.0, -9.0), new Vertex(-12.0, -9.0), new Vertex(-12.0, -4.0),
				new Vertex(-7.0, -4.0));

		ArrayList<PointOfIntersection> pois = new ArrayList<PointOfIntersection>();
		pois = polygona.getIntersectionsWithPolygon(polygonb);

		PointOfIntersection poi1 = new PointOfIntersection(-7.0, -8.0);
		PointOfIntersection poi2 = new PointOfIntersection(-7.0, -4.0);
		// Assertions
		assert (pois.contains(poi1));
		assert (pois.contains(poi2));
		assert (pois.size() == 2);
	}
	
	@Test 
	public void testOneRotatedRectangleNoIntersectionNegativeQuadrant() {
		Polygon polygona = new Rectangle(new Vertex(-1.0, -5.0), new Vertex(-4.0, -8.0), new Vertex(-7.0, -5.0),
				new Vertex(-4.0, -2.0));
		Polygon polygonb = new Rectangle(new Vertex(-6.0, -11.0), new Vertex(-9.0, -11.0), new Vertex(-9.0, -8.0),
				new Vertex(-6.0, -8.0));

		ArrayList<PointOfIntersection> pois = new ArrayList<PointOfIntersection>();
		pois = polygona.getIntersectionsWithPolygon(polygonb);

		// Assertions
		assert (pois.size() == 0);
	}
	@Test 
	public void testOneRotatedRectangleNegativeQuadrantReverse() {
		//This time, make the non-rotated rectangle object complete the check
		Polygon polygona = new Rectangle(new Vertex(-1.0, -4.0), new Vertex(-6.0, -9.0), new Vertex(-9.0, -6.0),
				new Vertex(-4.0, -1.0));
		Polygon polygonb = new Rectangle(new Vertex(-7.0, -9.0), new Vertex(-12.0, -9.0), new Vertex(-12.0, -4.0),
				new Vertex(-7.0, -4.0));

		ArrayList<PointOfIntersection> pois = new ArrayList<PointOfIntersection>();
		pois = polygonb.getIntersectionsWithPolygon(polygona);

		PointOfIntersection poi1 = new PointOfIntersection(-7.0, -8.0);
		PointOfIntersection poi2 = new PointOfIntersection(-7.0, -4.0);
		// Assertions
		assert (pois.contains(poi1));
		assert (pois.contains(poi2));
		assert (pois.size() == 2);
	}
	
	@Test 
	public void testOneRotatedRectangleNoIntersectionNegativeQuadrantReverse() {
		//This time, make the non-rotated rectangle object complete the check
		Polygon polygona = new Rectangle(new Vertex(-1.0, -5.0), new Vertex(-4.0, -8.0), new Vertex(-7.0, -5.0),
				new Vertex(-4.0, -2.0));
		Polygon polygonb = new Rectangle(new Vertex(-6.0, -11.0), new Vertex(-9.0, -11.0), new Vertex(-9.0, -8.0),
				new Vertex(-6.0, -8.0));

		ArrayList<PointOfIntersection> pois = new ArrayList<PointOfIntersection>();
		pois = polygonb.getIntersectionsWithPolygon(polygona);

		// Assertions
		assert (pois.size() == 0);
	}
	
	@Test 
	public void testTwoRotatedRectangleIntersection() {
		
		Polygon polygona = new Rectangle(new Vertex(3.0, 8.0), new Vertex(6.0, 5.0), new Vertex(3.0, 2.0),
				new Vertex(0.0, 5.0));
		Polygon polygonb = new Rectangle(new Vertex(4.0, 4.0), new Vertex(6.0, 2.0), new Vertex(4.0, 0.0),
				new Vertex(2.0, 2.0));

		ArrayList<PointOfIntersection> pois = new ArrayList<PointOfIntersection>();
		pois = polygonb.getIntersectionsWithPolygon(polygona);

		PointOfIntersection poi1 = new PointOfIntersection(4.5, 3.5);
		PointOfIntersection poi2 = new PointOfIntersection(2.5, 2.5);

		// Assertions
		assert (pois.contains(poi1));
		assert (pois.contains(poi2));
		assert (pois.size() == 2);
	}
}
