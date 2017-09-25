package test.utilitiesTest;

import org.junit.Test;

import utilities.Segment;
import utilities.Vertex;

public class TestSegment {

	@Test
	public void testSlopeGeneration() {
		Segment mySegment = new Segment(new Vertex(1.0, 1.0), new Vertex(5.0, 5.0));
		System.out.println(mySegment);
		assert(mySegment.getSlope().equals(1.0));
		assert(mySegment.getyIntercept().equals(0.0));

		mySegment = new Segment(new Vertex(1.0, 7.0), new Vertex(-1.0, -5.0));
		System.out.println(mySegment);
		assert(mySegment.getSlope().equals(6.0));
		assert(mySegment.getyIntercept().equals(1.0));
	}
	
	@Test
	public void testNonSlopedLineGenerationHorizontal() {
		Segment mySegment = new Segment(new Vertex(1.0, 5.0), new Vertex(5.0, 5.0));
		System.out.println(mySegment);
		assert(mySegment.getSlope()== null);
		assert(mySegment.getyLine().equals(5.0));
		
		mySegment = new Segment(new Vertex(1.0, 7.0), new Vertex(-1.0, 7.0));
		System.out.println(mySegment);
		assert(mySegment.getSlope() == null);
		assert(mySegment.getyLine().equals(7.0));
		
	}
	@Test
	public void testNonSlopedLineGenerationVertical() {
		Segment mySegment = new Segment(new Vertex(8.0, 1.0), new Vertex(8.0, 5.0));
		System.out.println(mySegment);
		assert(mySegment.getSlope() == null);
		assert(mySegment.getxLine().equals(8.0));
		
		mySegment = new Segment(new Vertex(2.0, 7.0), new Vertex(2.0, -5.0));
		System.out.println(mySegment);
		assert(mySegment.getSlope() == null);
		assert(mySegment.getxLine().equals(2.0));
	}
	
	@Test
	public void testComputeYOrX() {
		Segment mySegment = new Segment(new Vertex(6.0, 9.0), new Vertex(9.0, 6.0));
		assert(mySegment.computeYForX(7.0).equals(8.0));
		assert(mySegment.computeXForY(8.0).equals(7.0));
	}
	
}
