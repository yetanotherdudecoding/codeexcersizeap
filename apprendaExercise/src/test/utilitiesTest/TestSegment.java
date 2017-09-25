package test.utilitiesTest;

import org.junit.Test;

import polygonComponents.Segment;
import polygonComponents.Vertex;

public class TestSegment {

	@Test
	public void testSlopeGeneration() {
		Segment segment = new Segment(new Vertex(1.0, 1.0), new Vertex(5.0, 5.0));
		System.out.println(segment);
		assert (segment.getSlope().equals(1.0));
		assert (segment.getyIntercept().equals(0.0));

		segment = new Segment(new Vertex(1.0, 7.0), new Vertex(-1.0, -5.0));
		System.out.println(segment);
		assert (segment.getSlope().equals(6.0));
		assert (segment.getyIntercept().equals(1.0));
	}

	@Test
	public void testNonSlopedLineGenerationHorizontal() {
		Segment segment = new Segment(new Vertex(1.0, 5.0), new Vertex(5.0, 5.0));
		System.out.println(segment);
		assert (segment.getSlope() == null);
		assert (segment.getyLine().equals(5.0));

		segment = new Segment(new Vertex(1.0, 7.0), new Vertex(-1.0, 7.0));
		System.out.println(segment);
		assert (segment.getSlope() == null);
		assert (segment.getyLine().equals(7.0));

	}

	@Test
	public void testNonSlopedLineGenerationVertical() {
		Segment segment = new Segment(new Vertex(8.0, 1.0), new Vertex(8.0, 5.0));
		System.out.println(segment);
		assert (segment.getSlope() == null);
		assert (segment.getxLine().equals(8.0));

		segment = new Segment(new Vertex(2.0, 7.0), new Vertex(2.0, -5.0));
		System.out.println(segment);
		assert (segment.getSlope() == null);
		assert (segment.getxLine().equals(2.0));
	}

	@Test
	public void testComputeYOrX() {
		Segment segment = new Segment(new Vertex(6.0, 9.0), new Vertex(9.0, 6.0));
		assert (segment.computeYForX(7.0).equals(8.0));
		assert (segment.computeXForY(8.0).equals(7.0));
	}

	@Test
	public void testEquals() {
		Segment segment1 = new Segment(new Vertex(6.0, 9.0), new Vertex(9.0, 6.0));
		Segment segment2 = new Segment(new Vertex(9.0, 6.0), new Vertex(6.0, 9.0));
		assert (segment1.equals(segment2));
		assert (segment2.equals(segment1));

		segment1 = new Segment(new Vertex(5.0, 9.0), new Vertex(10.0, 6.0));
		segment2 = new Segment(new Vertex(9.0, 6.0), new Vertex(6.0, 9.0));
		assert (!segment1.equals(segment2));
		assert (!segment2.equals(segment1));
	}
}
