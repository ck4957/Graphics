//
// simpleShape.java
//
// Class that represnts a shape to be tessellated; cgShape, which includes
// all student code, is derived from this class.
//
// Of note is the protected method addTriangles() which is what students
// should use to define their tessellations.
//
// Students are not to modify this file.
//

import java.awt.*;
import java.nio.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import java.util.*;

public class simpleShape
{
    /**
     * our vertex points
     */
    private Vector<Float> points;
    private short nVerts;

    /**
     * our normals
     */
    private Vector<Float> normals;

    /**
     * our texture coordinates
     */
    private Vector<Float> uv;

    /**
     * our array elements
     */
    private Vector<Short> elements;

    /**
     * constructor
     */
    public simpleShape()
    {
        points = new Vector<Float>();
        normals = new Vector<Float>();
        uv = new Vector<Float>();
        elements = new Vector<Short>();
        nVerts = 0;
    }

    /**
     * clear the shape
     */
    public void clear()
    {
        points= new Vector<Float>();
        normals = new Vector<Float>();
        uv = new Vector<Float>();
        elements = new Vector<Short>();
        nVerts = 0;
    }
    /**
     * adds a triangle to the current shape using calculated normals
     */
    protected void addTriangleWithUV(
        float x0, float y0, float z0, float u0, float v0,
        float x1, float y1, float z1, float u1, float v1,
        float x2, float y2, float z2, float u2, float v2 )
    {
        // calculate the normal
        float ux = x1 - x0;
        float uy = y1 - y0;
        float uz = z1 - z0;

        float vx = x2 - x0;
        float vy = y2 - y0;
        float vz = z2 - z0;

        float nx = (uy * vz) - (uz * vy);
        float ny = (uz * vx) - (ux * vz);
        float nz = (ux * vy) - (uy * vx);

        // Attach the normal to all 3 vertices
        addTriangleWithNorms(
            x0, y0, z0, nx, ny, nz,
	    x1, y1, z1, nx, ny, nz,
	    x2, y2, z2, nx, ny, nz
        );

	// Attach the texture coordinates
	uv.add( new Float(u0) );
	uv.add( new Float(v0) );
	uv.add( new Float(u1) );
	uv.add( new Float(v1) );
	uv.add( new Float(u2) );
	uv.add( new Float(v2) );

    }

    /**
     * adds a triangle to the current shape using supplied normals
     */
    protected void addTriangleWithNorms(
        float x0, float y0, float z0, float nx0, float ny0, float nz0,
        float x1, float y1, float z1, float nx1, float ny1, float nz1,
        float x2, float y2, float z2, float nx2, float ny2, float nz2 )
    {
        points.add( new Float(x0) );
        points.add( new Float(y0) );
        points.add( new Float(z0) );
        points.add( new Float(1.0f) );
        elements.add( new Short(nVerts));
        nVerts++;

        normals.add( new Float(nx0) );
        normals.add( new Float(ny0) );
        normals.add( new Float(nz0) );

        points.add( new Float(x1) );
        points.add( new Float(y1) );
        points.add( new Float(z1) );
        points.add( new Float(1.0f) );
        elements.add( new Short(nVerts));
        nVerts++;

        normals.add( new Float(nx1) );
        normals.add( new Float(ny1) );
        normals.add( new Float(nz1) );

        points.add( new Float(x2) );
        points.add( new Float(y2) );
        points.add( new Float(z2) );
        points.add( new Float(1.0f) );
        elements.add( new Short(nVerts));
        nVerts++;

        normals.add( new Float(nx2) );
        normals.add( new Float(ny2) );
        normals.add( new Float(nz2) );
    }

    /**
     * get the vertex points for the current shape
     */
    public Buffer getVertices()
    {
        float v[] = new float[points.size()];
        for( int i=0; i < points.size(); i++ ) {
            v[i] = (points.elementAt(i)).floatValue();
        }
        return FloatBuffer.wrap( v );
    }

    /**
     * get the normals for the current shape
     */
    public Buffer getNormals()
    {
        float v[] = new float[normals.size()];
        for( int i=0; i < normals.size(); i++ ) {
            v[i] = (normals.elementAt(i)).floatValue();
        }
        return FloatBuffer.wrap( v );
    }

    /**
     * get the texture coords for the current shape
     */
    public Buffer getUV()
    {
        float v[] = new float[uv.size()];
        for( int i=0; i < uv.size(); i++ ) {
            v[i] = (uv.elementAt(i)).floatValue();
        }
        return FloatBuffer.wrap( v );
    }

    /**
     * get the array of elements for the current shape
     */
    public Buffer getElements()
    {
        short e[] = new short[elements.size()];
        for( int i=0; i < elements.size(); i++ ) {
            e[i] = (elements.elementAt(i)).shortValue();
        }

        return ShortBuffer.wrap( e );
    }

    public short nVertices()
    {
        return nVerts;
    }

}
