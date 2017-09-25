package test.rectangleTests;

import org.junit.Test;

import polygonComponents.Vertex;
import shapes.Rectangle;

public class TestRectangle {

	@Test
	public void testEquals() {
		Rectangle rectangle1 = new Rectangle(new Vertex(2.0, 8.0), new Vertex(8.0, 8.0), new Vertex(8.0, 4.0),
				new Vertex(2.0, 4.0));
		Rectangle rectangle2 = new Rectangle(new Vertex(2.0, 8.0), new Vertex(8.0, 8.0), new Vertex(8.0, 4.0),
				new Vertex(2.0, 4.0));

		assert (rectangle1.equals(rectangle2));
		assert (rectangle2.equals(rectangle1));

		rectangle1 = new Rectangle(new Vertex(2.0, 8.0), new Vertex(8.0, 8.0), new Vertex(8.0, 4.0),
				new Vertex(2.0, 4.0));
		rectangle2 = new Rectangle(new Vertex(6.0, 8.0), new Vertex(8.0, 8.0), new Vertex(8.0, 4.0),
				new Vertex(2.0, 4.0));

		assert (!rectangle1.equals(rectangle2));
		assert (!rectangle2.equals(rectangle1));
	}

}
