package test;

import org.junit.Test;

import shapes.Rectangle;
import utilities.Vertex;

public class TestDurability {

	@Test
	public void testInputWasRectangle() {
		//TODO: We should see an error when we try to initialize a polygon without 4 right angles and 2 pairs of opposite, but equal sides
		Rectangle rectangle1 = new Rectangle(new Vertex(2.0, 8.0), new Vertex(8.0, 8.0), new Vertex(8.0, 4.0),
				new Vertex(2.0, 4.0));
		
		assert(rectangle1.isValidPolygon());
		
		rectangle1 = new Rectangle(new Vertex(2.0, 7.0), new Vertex(8.0, -8.0), new Vertex(8.0, 4.0),
				new Vertex(2.0, 4.0));
		
		assert(!rectangle1.isValidPolygon());
	}
	
	@Test
	public void testCounterClockwiseSpecification() {
		assert(false);
	}
	
	@Test
	public void testBothInputsAreSameRectangle() {
		assert(false);
	}

}
