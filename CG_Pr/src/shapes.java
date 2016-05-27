//
//  shapes.java
//
//  Routines for shape-drawing functions.
//
//  @author Vasudev
// Contributor : Chirag Kular
//


public class shapes extends simpleShape {

	/**
	 * Object selection variables
	 */
	public static final int OBJ_QUAD = 0;
	public static final int OBJ_BODY = 1;
	public static final int OBJ_TORUS = 2;
	public static final int OBJ_TRUNK = 3;
	public static final int OBJ_CONE_TREE = 4;


	/**
	 * Constructor
	 */
	public shapes() {}

	private void makeConeTree() {
		for (int i = 0; i < Elements.coneTreeElements.length - 2; i += 3) {

			// calculate the base indices of the three vertices
			int point1 = 3 * Elements.coneTreeElements[i]; // slots 0, 1, 2
			int point2 = 3 * Elements.coneTreeElements[i + 1]; // slots 3, 4, 5
			int point3 = 3 * Elements.coneTreeElements[i + 2]; // slots 6, 7, 8


			int normal1 = 3 * NormalIndices.coneTreeNormalIndices[i];
			int normal2 = 3 * NormalIndices.coneTreeNormalIndices[i + 1];
			int normal3 = 3 * NormalIndices.coneTreeNormalIndices[i + 2];
			addTriangleWithNorms(
					Vertices.coneTreeVertices[point1 + 0], Vertices.coneTreeVertices[point1 + 1],
					Vertices.coneTreeVertices[point1 + 2],	             
					Normals.coneTreeNormals[normal1 + 0], Normals.coneTreeNormals[normal1 + 1],
					Normals.coneTreeNormals[normal1 + 2],
					Vertices.coneTreeVertices[point2 + 0], Vertices.coneTreeVertices[point2 + 1],
					Vertices.coneTreeVertices[point2 + 2],

					Normals.coneTreeNormals[normal2 + 0], Normals.coneTreeNormals[normal2 + 1],
					Normals.coneTreeNormals[normal2 + 2],
					Vertices.coneTreeVertices[point3 + 0], Vertices.coneTreeVertices[point3 + 1],
					Vertices.coneTreeVertices[point3 + 2],

					Normals.coneTreeNormals[normal3 + 0], Normals.coneTreeNormals[normal3 + 1],
					Normals.coneTreeNormals[normal3 + 2]
					);
		}
	}
	private void makeTrunk() {
		for (int i = 0; i < Elements.trunkElements.length - 2; i += 3) {

			// calculate the base indices of the three vertices
			int point1 = 3 * Elements.trunkElements[i]; // slots 0, 1, 2
			int point2 = 3 * Elements.trunkElements[i + 1]; // slots 3, 4, 5
			int point3 = 3 * Elements.trunkElements[i + 2]; // slots 6, 7, 8


			int normal1 = 3 * NormalIndices.trunkNormalIndices[i];
			int normal2 = 3 * NormalIndices.trunkNormalIndices[i + 1];
			int normal3 = 3 * NormalIndices.trunkNormalIndices[i + 2];
			addTriangleWithNorms(
					Vertices.trunkVertices[point1 + 0], Vertices.trunkVertices[point1 + 1],
					Vertices.trunkVertices[point1 + 2],	             
					Normals.trunkNormals[normal1 + 0], Normals.trunkNormals[normal1 + 1],
					Normals.trunkNormals[normal1 + 2],
					Vertices.trunkVertices[point2 + 0], Vertices.trunkVertices[point2 + 1],
					Vertices.trunkVertices[point2 + 2],

					Normals.trunkNormals[normal2 + 0], Normals.trunkNormals[normal2 + 1],
					Normals.trunkNormals[normal2 + 2],
					Vertices.trunkVertices[point3 + 0], Vertices.trunkVertices[point3 + 1],
					Vertices.trunkVertices[point3 + 2],

					Normals.trunkNormals[normal3 + 0], Normals.trunkNormals[normal3 + 1],
					Normals.trunkNormals[normal3 + 2]
					);
		}
	}


	private void makeBody() {

		for( int i = 0; i < Elements.bodyElements.length - 2; i += 3 ) {

			// calculate the base indices of the three vertices
			int point1 = 3 * Elements.bodyElements[i]; // slots 0, 1, 2
			int point2 = 3 * Elements.bodyElements[i + 1]; // slots 3, 4, 5
			int point3 = 3 * Elements.bodyElements[i + 2]; // slots 6, 7, 8

			int normal1 = 3 * NormalIndices.bodyNormalIndices[i];
			int normal2 = 3 * NormalIndices.bodyNormalIndices[i + 1];
			int normal3 = 3 * NormalIndices.bodyNormalIndices[i + 2];

			addTriangleWithNorms(
					Vertices.bodyVertices[point1 + 0], Vertices.bodyVertices[point1 + 1],
					Vertices.bodyVertices[point1 + 2],
					Normals.bodyNormals[normal1 + 0], Normals.bodyNormals[normal1 + 1],
					Normals.bodyNormals[normal1 + 2],

					Vertices.bodyVertices[point2 + 0], Vertices.bodyVertices[point2 + 1],
					Vertices.bodyVertices[point2 + 2],
					Normals.bodyNormals[normal2 + 0], Normals.bodyNormals[normal2 + 1],
					Normals.bodyNormals[normal2 + 2],

					Vertices.bodyVertices[point3 + 0], Vertices.bodyVertices[point3 + 1],
					Vertices.bodyVertices[point3 + 2],
					Normals.bodyNormals[normal3 + 0], Normals.bodyNormals[normal3 + 1],
					Normals.bodyNormals[normal3 + 2]
					);
		}
	}


	private void makeTorus() {

		for( int i = 0; i < Elements.torusElements.length - 2; i += 3 ) {

			// calculate the base indices of the three vertices
			int point1 = 3 * Elements.torusElements[i]; // slots 0, 1, 2
			int point2 = 3 * Elements.torusElements[i + 1]; // slots 3, 4, 5
			int point3 = 3 * Elements.torusElements[i + 2]; // slots 6, 7, 8

			int normal1 = 3 * NormalIndices.torusNormalIndices[i];
			int normal2 = 3 * NormalIndices.torusNormalIndices[i + 1];
			int normal3 = 3 * NormalIndices.torusNormalIndices[i + 2];

			addTriangleWithNorms(
					Vertices.torusVertices[point1 + 0], Vertices.torusVertices[point1 + 1],
					Vertices.torusVertices[point1 + 2],
					Normals.torusNormals[normal1 + 0], Normals.torusNormals[normal1 + 1],
					Normals.torusNormals[normal1 + 2],

					Vertices.torusVertices[point2 + 0], Vertices.torusVertices[point2 + 1],
					Vertices.torusVertices[point2 + 2],
					Normals.torusNormals[normal2 + 0], Normals.torusNormals[normal2 + 1],
					Normals.torusNormals[normal2 + 2],

					Vertices.torusVertices[point3 + 0], Vertices.torusVertices[point3 + 1],
					Vertices.torusVertices[point3 + 2],
					Normals.torusNormals[normal3 + 0], Normals.torusNormals[normal3 + 1],
					Normals.torusNormals[normal3 + 2]
					);
		}
	}


	/**
	 * makeQuad() - create a quad object
	 */
	private void makeQuad() {
		int i;

		for( i = 0; i < Elements.quadElements.length - 2; i += 3 ) {

			// Calculate the base indices of the three vertices
			int point1 = 3 * Elements.quadElements[i];     // slots 0, 1, 2
			int point2 = 3 * Elements.quadElements[i + 1]; // slots 3, 4, 5
			int point3 = 3 * Elements.quadElements[i + 2]; // slots 6, 7, 8

			// Calculate the base indices of the three texture coordinates
			int normal1 = 2 * Elements.quadElements[i];     // slots 0, 1, 2
			int normal2 = 2 * Elements.quadElements[i + 1]; // slots 3, 4, 5
			int normal3 = 2 * Elements.quadElements[i + 2]; // slots 6, 7, 8

			// Add triangle and texture coordinates
			addTriangleWithUV(
					Vertices.quadVertices[point1 + 0], Vertices.quadVertices[point1 + 1],
					Vertices.quadVertices[point1 + 2],
					Vertices.quadUV[normal1 + 0], Vertices.quadUV[normal1 + 1],
					Vertices.quadVertices[point2 + 0], Vertices.quadVertices[point2 + 1],
					Vertices.quadVertices[point2 + 2],
					Vertices.quadUV[normal2 + 0], Vertices.quadUV[normal2 + 1],
					Vertices.quadVertices[point3 + 0], Vertices.quadVertices[point3 + 1],
					Vertices.quadVertices[point3 + 2],
					Vertices.quadUV[normal3 + 0], Vertices.quadUV[normal3 + 1]
					);

		}

	}

	/**
	 * Make either a quad or teapot
	 * @param choice - 0 for quad, 1 for teapot
	 */
	public void makeShape( int choice ) {
		if( choice == shapes.OBJ_QUAD )
			makeQuad();
		else if( choice == shapes.OBJ_BODY )
			makeBody();
		else if( choice == shapes.OBJ_TORUS )
			makeTorus();
		else if( choice == shapes.OBJ_CONE_TREE )
			makeConeTree();
		else
			makeTrunk();
	}
}
