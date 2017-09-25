package test.utilitiesTest;

import org.junit.Test;

import polygonComponents.Vertex;

public class TestVertex {

	@Test
	public void testEquals() {
		Vertex vertex1 = new Vertex(1.0, 2.0);
		Vertex vertex2 = new Vertex(1.0, 2.0);

		assert (vertex1.equals(vertex2));
		assert (vertex2.equals(vertex1));

		vertex1 = new Vertex(2.0, 2.0);
		vertex2 = new Vertex(1.0, 2.0);

		assert (!vertex1.equals(vertex2));
		assert (!vertex2.equals(vertex1));

	}

}
