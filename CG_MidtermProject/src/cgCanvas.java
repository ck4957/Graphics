//
//  cgCanvas.java 20155
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 Rochester Institute of Technology. All rights reserved.
//
//  Contributor:  CHIRAG KULAR
//

///
// This is a simple canvas class for adding functionality for the
// 2D portion of Computer Graphics.
//
///

import Jama.*;
import java.util.*;

public class cgCanvas extends simpleCanvas {

	
	clipper clpr= new clipper();
	Rasterizer rz;
    
	ArrayList<MyPolygon> repository = new ArrayList<MyPolygon>();
	int uniqueID = 0;
    Matrix currentTransform,viewport_matrix,normalize_matrix;
    float llx,lly,urx,ury;
    int xvmin,yvmin,xvmax,yvmax;
    //int vpx,vpy,vpwidth,vpheight;
    //MyPolygon windowClipped;
    
    // Constructor
    //
    // @param w width of canvas
    // @param h height of canvas
    ///

    cgCanvas (int w, int h)
    {
        super (w, h);
        rz = new Rasterizer(h);
        // YOUR IMPLEMENTATION HERE if you need to modify the constructor
    }

    ///
    // addPoly - Adds and stores a polygon to the canvas.  Note that this
    //           method does not draw the polygon, but merely stores it for
    //           later draw.  Drawing is initiated by the draw() method.
    //
    //           Returns a unique integer id for the polygon.
    //
    // @param x - Array of x coords of the vertices of the polygon to be added
    // @param y - Array of y coords of the vertices of the polygin to be added
    // @param n - Number of verticies in polygon
    //
    // @return a unique integer identifier for the polygon
    ///

    public int addPoly (float x[], float y[], int n)
    {
        // REMEMBER TO RETURN A UNIQUE ID FOR THE POLYGON
    	MyPolygon newPolygon = new MyPolygon(x.clone(),y.clone(),n); 
    	repository.add(newPolygon);
    	return uniqueID++;
    }

    ///
    // drawPoly - Draw the polygon with the given id.  Should draw the
    //        polygon after applying the current transformation on the
    //        vertices of the polygon.
    //
    // @param polyID - the ID of the polygon to be drawn
    ///

    public void drawPoly (int polyID)
    {
    	//Get the polygon from the arraylist
    	MyPolygon mypoly = repository.get(polyID);
    	float inputx[] = new float[50];
    	float inputy[] = new float[50];
    	
    	
    	//Applying the current transformation
    	for(int i=0;i<mypoly.num;i++)
    	{
			double[][] m = new double[][]{{mypoly.xcoord[i]},{mypoly.ycoord[i]},{1}};
			Matrix res= new Matrix(m); 
			res = currentTransform.times(res);
	    	inputx[i] = (int)res.get(0,0);
			inputy[i] = (int)res.get(1,0);		
    	}    	

    	//Clipping
    	float outx[] = new float[50];
    	float outy[] = new float[50];
    	int out = clpr.clipPolygon(mypoly.num, inputx, inputy, outx, outy, llx,lly,urx,ury);
    	
    	int[] getResultx = new int[out];
    	int[] getResulty = new int[out];
    	for(int i=0;i<out;i++)
    	{
 
    		double[][] m = new double[][]{{outx[i]},{outy[i]},{1}};
    		Matrix res= new Matrix(m); 
    		res = normalize_matrix.times(res);
        	res = viewport_matrix.times(res);
        	//res = currentTransform.times(res);
        	getResultx[i] = (int)res.get(0,0);
    		getResulty[i] = (int)res.get(1,0);
    		
    	}
    
    	//drawOutline(out, getResultx, getResulty);
    	rz.drawPolygon(out, getResultx, getResulty, this);
    }

    ///
    // clearTransform - Set the current transformation to the identity matrix.
    ///

    public void clearTransform()
    {
    	currentTransform = Matrix.identity(3, 3);
        // YOUR IMPLEMENTATION HERE
    }

    ///
    // translate - Add a translation to the current transformation by
    //             pre-multiplying the appropriate translation matrix to
    //             the current transformation matrix.
    //
    // @param x - Amount of translation in x
    // @param y - Amount of translation in y
    ///

    public void translate (float x, float y)
    {
        // YOUR IMPLEMENTATION HERE
    	double[][] matrix = {{1,0,x},
    						 {0,1,y},
    						 {0,0,1}};
    	Matrix translation_matrix = new Matrix(matrix);
    	currentTransform = translation_matrix.times(currentTransform); 
    }

    ///
    // rotate - Add a rotation to the current transformation by
    //          pre-multiplying the appropriate rotation matrix to the
    //          current transformation matrix.
    //
    // @param degrees - Amount of rotation in degrees
    ///

    public void rotate (float degrees)
    {
    	double rd = Math.toRadians(degrees);
    	double[][] matrix = {{Math.cos(rd),-Math.sin(rd),0},
				 			 {Math.sin(rd), Math.cos(rd),0},
				 			 {0,0,1}};
    	Matrix rotation_matrix = new Matrix(matrix);
    	currentTransform = rotation_matrix.times(currentTransform);
    }

    ///
    // scale - Add a scale to the current transformation by pre-multiplying
    //         the appropriate scaling matrix to the current transformation
    //         matrix.
    //
    // @param x - Amount of scaling in x
    // @param y - Amount of scaling in y
    ///

    public void scale (float x, float y)
    {
        // YOUR IMPLEMENTATION HERE
    	double[][] matrix = {{x,0,0},
    						 {0,y,0},
    						 {0,0,1}};
    	Matrix scaling_matrix = new Matrix(matrix);
    	currentTransform = scaling_matrix.times(currentTransform); 
    }

    ///
    // setClipWindow - defines the clip window
    //
    // @param bottom - y coord of bottom edge of clip window (in world coords)
    // @param top - y coord of top edge of clip window (in world coords)
    // @param left - x coord of left edge of clip window (in world coords)
    // @param right - x coord of right edge of clip window (in world coords)
    ///

    public void setClipWindow (float bottom, float top, float left, float right)
    {
    	llx = left;
    	lly = bottom;
    	urx = right;
    	ury = top;
    	double [][] m = {{2/(right-left),0, ((-2*left)/(right-left)) - 1},
    					{0,2/(top-bottom),((-2*bottom)/(top-bottom))-1},
    					{0,0,1}};
    	normalize_matrix = new Matrix(m);
    	
    }

    ///
    // setViewport - defines the viewport
    //
    // @param xmin - x coord of lower left of view window (in screen coords)
    // @param ymin - y coord of lower left of view window (in screen coords)
    // @param width - width of view window (in world coords)
    // @param height - height of view window (in world coords)
    ///

    public void setViewport (int x, int y, int width, int height)
    {
    	xvmin = x;
    	yvmin= y;
    	xvmax = x + width;
    	yvmax = y+height;
    	double [][] m = {{(xvmax-xvmin)/2,0,(xvmax+xvmin)/2},
    			{0,(yvmax-yvmin)/2,(yvmax+yvmin)/2},
    			{0,0,1}};
    	viewport_matrix = new Matrix(m);
    	
    }
}
