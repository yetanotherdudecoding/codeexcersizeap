This is the code exercise for Apprenda for Bradley Savoy 9/25/2016

The program requires core java and JUnit4 libraries.

Program can be exported as stand alone jar, make sure to copy required libraries and choose "Client" for launch configuration.

Can be launched from any machine running java with java -jar apprendaExercise.jar

Algorithmically, I took to the following solutions -

1) Points of intersection
	The program decomposes each polygon into its component line segments 
	and iterates through each combination of segments
	During this iteration, it compares each member to determine if there are any 
	valid points of intersection
	
2) Containment
	The program considers one polygon and then decomposes the other into vertices which are iterated through. 
	If each vertex of the polygon exists within the rectangle, then the polygon exists within the rectangle
	
3) Adjacency
	I once again decompose each polygon into its component line segments.
	The program then verifies that the line segments share the same line equation
	If they do share the same equation, it verifies that the lines overlap on that line (are adjacent)

Written in Java 1.8 in Eclipse Oxygen IDE