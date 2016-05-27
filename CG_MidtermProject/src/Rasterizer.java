//
//  Rasterizer.java
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 Rochester Institute of Technology. All rights reserved.
//
//  Contributor:  Chirag Kular
//

///
// 
// This is a class that performas rasterization algorithms
//
///

import java.awt.Graphics;
import java.util.*;


public class Rasterizer {

	///
	// number of scanlines
	///
	int n_scanlines;
	///
	// Constructor
	//
	// @param n - number of scanlines
	//
	///

	Rasterizer (int n)
	{
		n_scanlines = n;
	}

	///
	// Draw a filled polygon in the simpleCanvas C.
	//
	// The polygon has n distinct vertices. The 
	// coordinates of the vertices making up the polygon are stored in the 
	// x and y arrays.  The ith vertex will have coordinate  (x[i], y[i])
	//
	// You are to add the implementation here using only calls
	// to C.setPixel()
	///
	public void drawPolygon(int n, int x[], int y[], simpleCanvas C)
	{	
		// YOUR IMPLEMENTATION GOES HERE
		int nextOne;
		double ymin,ymax,xval,slope;
		//double slope;
		ArrayList<Bucket> edge_table = new ArrayList<Bucket>();
		ArrayList<Bucket> globalET = new ArrayList<Bucket>();
		ArrayList<Bucket> activeET = new ArrayList<Bucket>();

		for(int i=0;i<n;i++)
		{	//For last element and first element
			//nextOne = i+1<n?i+1:0;
			nextOne = i == (n - 1)?0:i+1;
			//Get Y minimum of current vertex and next vertex
			ymin =  (y[i]<y[nextOne]?y[i]:y[nextOne]);
			//Get maximum of current vertex and next vertex
			ymax = (y[i]>y[nextOne]?y[i]:y[nextOne]);
			//Get X value having Y minimum
			xval = (y[i]<y[nextOne]?x[i]:x[nextOne]);
			//Calculate slope of two vertex
			slope = (double)(y[nextOne]-y[i])/(x[nextOne]-x[i]);
			//Store all vertex in edge table
			Bucket newbucket= new Bucket(ymin,ymax,xval,1/slope);
			edge_table.add(newbucket);
		}
		//Move from edge to global
		for(int i=0;i<edge_table.size();i++)
			if((edge_table.get(i).slope != Double.POSITIVE_INFINITY) && 
					(edge_table.get(i).slope != Double.NEGATIVE_INFINITY))
				globalET.add(edge_table.get(i));
		
		//Sort the global edge table			
		Collections.sort(globalET);

		//Initializing the Scan-Line
		double first_scanline = (globalET.get(0).ymin);
		double last_scanline = (globalET.get(globalET.size()-1).ymax);
		
		//Move from global to Active
		//Initializing the Active Edge Table
		for(int i= 0;i<globalET.size();i++){
			if((globalET.get(i).ymin) == first_scanline)
			{
				Bucket temp = globalET.remove(i); 
				activeET.add(new Bucket(temp.ymax,temp.x_val,temp.slope));
				i--;
			}
		}
		
		while(first_scanline<last_scanline){
		//for(float scanline = first_scanline;scanline<last_scanline;scanline++){
			//Draw all pixels from the x value of odd to the x value of even parity edge pairs.
			for(int in=0;in<activeET.size();in=in+2){
				for (double x1 = (activeET.get(in).x_val); x1 <= activeET.get(in+1).x_val; x1++)
					C.setPixel((int)x1, (int)first_scanline);
				//Increase the scan-line by 1.
				first_scanline++;
				//Remove any edges from the active edge table for which the maximum y value is equal to the scan_line.
				for (int j =0;j<activeET.size();j++)
					if((activeET.get(j).ymax)==first_scanline){
						activeET.remove(j);
						j--;}
				//Update the x value for for each edge in the active edge table using the formula x1 = x0 + 1/m. 
				//(This is based on the line formula and the fact that the next scan-line equals the old scan-line plus one.)
				for (int j =0;j<activeET.size();j++){
					activeET.get(j).x_val = activeET.get(j).x_val + activeET.get(j).slope;
					
				}
				//Remove any edges from the global edge table for which the minimum y value is equal to the scan-line 
				//and place them in the active edge table.
				for(int k= 0;k<globalET.size();k++)
					if((globalET.get(k).ymin) == first_scanline)
					{
						Bucket temp = globalET.remove(k); 
						activeET.add(new Bucket(temp.ymax,temp.x_val,temp.slope));
						k--;
					}
				//Reorder the edges in the active edge table according to increasing x value. 
				//This is done in case edges have crossed.
				Collections.sort(activeET,new Comparator<Bucket>(){
					public int compare(Bucket b1, Bucket b2) {
						int sort_xval = b1.x_val.compareTo(b2.x_val);
						if(sort_xval == 0)
							return b1.slope.compareTo(b2.slope);
						else
							return sort_xval;
					}
				});
			}//end of for loop
		}//end of while
	}//end of draw Polygon
}//end of class Rasterizer