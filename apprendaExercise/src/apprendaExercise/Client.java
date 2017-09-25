package apprendaExercise;

/**
 * Something so you can run it
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import polygonComponents.Vertex;
import shapes.Polygon;
import shapes.Rectangle;

public class Client {
	private static final String polygon1Name = "rectangle";// Because I only have time to implement a rectangle
	private static final String polygon2Name = "rectangle";

	public static void main(String args[]) throws IOException {
		String userInputString = new String();
		String[] vertexPoints = null;
		ArrayList<Vertex> polygon1Verticies = new ArrayList<Vertex>();
		ArrayList<Vertex> polygon2Verticies = new ArrayList<Vertex>();
		Polygon polygon1 = null;
		Polygon polygon2 = null;
		Double x = null;
		Double y = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("This program will inform you of the following relationships between a " + polygon1Name
				+ " and a " + polygon2Name + ".");
		System.out.println(
				"1) Points of intersection (if any)\n2) Whether one polygon is contained in the other\n3)If any side of one polygon is adjacent to a side of the other");
		System.out.println("\nYou will now enter the verticies of the first polygon, " + polygon1Name
				+ ". \nEnter each vertex in x,y format.e.g. vertex1: 1.0,3");
		System.out.println("Please enter the coordinates CLOCKWISE or COUNTERCLOCKWISE");
		System.out.println(polygon1Name + "-");
		int i = 0;
		while (i <= 3) {
			vertexPoints = null;
			System.out.print("vertex" + i + ":");
			userInputString = br.readLine();
			vertexPoints = userInputString.split(",");
			try {
				x = Double.valueOf(vertexPoints[0]);
				;
				y = Double.valueOf(vertexPoints[1]);
				polygon1Verticies.add(new Vertex(x, y));
				i++;
			} catch (Exception e) {
				System.out.println("Sorry, I couldn't parse those values. Please try again");
				x = null;
				y = null;
			}
		}

		System.out.println(polygon1Name + " has verticies: " + polygon1Verticies);
		System.out.println("\nYou will now enter the verticies of the second polygon, " + polygon2Name + ".");
		System.out.println("Please enter the coordinates CLOCKWISE or COUNTERCLOCKWISE");
		System.out.println(polygon2Name + "-");
		i = 0;
		x = null;
		y = null;
		while (i <= 3) {
			vertexPoints = null;
			System.out.print("vertex" + i + ":");
			userInputString = br.readLine();
			vertexPoints = userInputString.split(",");
			try {
				x = Double.valueOf(vertexPoints[0]);
				;
				y = Double.valueOf(vertexPoints[1]);
				polygon2Verticies.add(new Vertex(x, y));
				i++;
			} catch (Exception e) {
				System.out.println("Sorry, I couldn't parse those values. Please try again");
				x = null;
				y = null;
			}
		}

		System.out.println(polygon2Name + " has verticies: " + polygon2Verticies);

		if (polygon1Name.equals("rectangle")) {
			polygon1 = new Rectangle(polygon1Verticies);

		}
		if (polygon2Name.equals("rectangle")) {
			polygon2 = new Rectangle(polygon2Verticies);
		}

		System.out.println("Points of intersection:" + polygon1.getIntersectionsWithPolygon(polygon2));
		System.out.println("\nPolygon 2 is contained in polygon 1:" + polygon1.containsPolygon(polygon2));
		System.out.println("Polygon 1 is contained in polygon 2:" + polygon2.containsPolygon(polygon1));
		System.out.println("\nThe polygons are adjacent to one another on at least one line segment:"
				+ polygon1.isAdjacentToPolygon(polygon2));

	}
}
