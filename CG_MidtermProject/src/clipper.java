import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//
//  Clipper.java
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 Rochester Institute of Technology. All rights reserved.
//
//  Contributor:  Chirag Kular
//
///
// Object for performing clipping
//
///

public class clipper {
	
	/*
	 * Function to check if a point is inside or outside with respect to LEFT,RIGHT,TOP and BOTTOM edge
	 * 	
	 * // @param in the number of vertices in the polygon to be clipped
	// @param x - x coord of vertice of point to be checked.
	// @param y - y coord of vertice of polygon to be checked.
	// @param Ax - x coord of one end point of the clipping edge
	// @param Ay - y coord of one end point of the clipping edge
	// @param Bx - x coord of other end point of the clipping edge
	// @param By - y coord of other end point of the clipping edge
	// @param edge - Left, Right, TOp or Bottom clipping edge
	 */
	public clipper()
	{}
	public boolean inside(float x,float y, float Ax,float Ay, float Bx,float By,String edge)
	{
		if(edge=="BOTTOM" && y>=Ay)
			return true;
		if( edge=="TOP" && y<=By)
			return true;
		if(edge=="RIGHT" && x<=Bx)
				return true;
		if(edge=="LEFT" && x>=Ax)
				return true;
		return false;
	}
	/*Function to check if polygon is inside or outside with respect to one particular edge
	 *  and find the intersecting points and store resulting vertices in outx and out y arrays
	 *  and return the count of vertices in the outx and outy array
	 */
	public int SHPC(int in, float startx[],float starty[],float outx[],float outy[],
			float Ax,float Ay,float Bx,float By,String edge){
		int out = 0;
		float endx,endy; //coord of end point of the clipping edge
//		if (in==0){
//			//If Number of input vertices are zero, assign start point as end point
//			endx=startx[0];
//			endy=starty[0];
//		}
//		else
//		{
//			//Make the last coord of polygon as end point of clipping edge
			endx=startx[in-1];
			endy=starty[in-1];
		for(int i=0;i<in;i++)
		{	
			//if start point is inside
			if(inside(startx[i], starty[i], Ax,Ay,Bx,By,edge))
			{	//if end point of edge is also inside 
				if(inside(endx, endy,Ax,Ay,Bx,By,edge))
				{
					//if Both points inside, then store end point in output array
					outy[out]=starty[i];
					outx[out]=startx[i];
					out++;
				}
				else
				{	//end point is outside
					//Calculate the intersecting point and store start point and intersecting point in output array
					if(Ay==By){
						outy[out]=Ay;
						outx[out]=startx[i]+(Ay-starty[i])*(endx-startx[i])/(endy-starty[i]);
					}
					else{
						outx[out]=Ax;
						outy[out]=starty[i]+(Ax-startx[i])*(endy-starty[i])/(endx-startx[i]);
					}
					out++;
					outy[out]=starty[i];
					outx[out]=startx[i];
					out++;
				}
			}
			else{
				//Start point is out side but end point of edge is inside 
				if(inside(endx, endy, Ax,Ay,Bx,By,edge))
				{//Calculate the intersecting point and store that point in output array
										if(Ay==By){
						outy[out]=Ay;
						outx[out]=startx[i]+(Ay-starty[i])*(endx-startx[i])/(endy-starty[i]);
					}
					else{
						outx[out]=Ax;
						outy[out]=starty[i]+(Ax-startx[i])*(endy-starty[i])/(endx-startx[i]);
					}
		            out++;
				}				
			}
			//Make the start point as end point for next edge 
			endx=startx[i];
			endy=starty[i];
		}
		return out;
	}
	///
	// clipPolygon
	//
	// Clip the polygon with vertex count in and vertices inx/iny
	// against the rectangular clipping region specified by lower-left corner
	// (llx,lly) and upper-right corner (urx,ury). The resulting vertices are
	// placed in outx/outy.
	//
	// The routine should return the the vertex count of the polygon
	// resulting from the clipping.
	//
	// @param in the number of vertices in the polygon to be clipped
	// @param inx - x coords of vertices of polygon to be clipped.
	// @param iny - y coords of vertices of polygon to be clipped.
	// @param outx - x coords of vertices of polygon resulting after clipping.
	// @param outy - y coords of vertices of polygon resulting after clipping.
	// @param llx - x coord of lower left of clipping rectangle.
	// @param lly - y coord of lower left of clipping rectangle.
	// @param urx - x coord of upper right of clipping rectangle.
	// @param ury - y coord of upper right of clipping rectangle.
	//
	// @return number of vertices in the polygon resulting after clipping

	public int clipPolygon(int in, float inx[], float iny[],
			float outx[], float outy[],
			float llx, float lly, float urx, float ury)
	{
		int out=0; //no. of vertices in the resulting polygon after clipping at every edge
		float Ax,Ay,Bx,By; //Coordinates to store end points  of each edge
		float out1x[] = new float[50];float out1y[] = new float[50];
		float out2x[] = new float[50];float out2y[] = new float[50];
		float out3x[] = new float[50];float out3y[] = new float[50];

		//left edge: 
		Ax=llx;Ay=lly;Bx=llx;By=ury;
		out = SHPC(in,inx,iny,out1x,out1y,Ax,Ay,Bx,By,"LEFT");
		if(out!=0)
			in = out;
		//bottom edge:
		Ax=llx;Ay=lly;Bx=urx;By=lly;
		out = SHPC(in,out1x,out1y,out2x,out2y,Ax,Ay,Bx,By,"BOTTOM");
		if(out!=0)
			in = out;
		//right edge: 
		Ax=urx;Ay=lly;Bx=urx;By=ury;
		out = SHPC(in,out2x,out2y,out3x,out3y,Ax,Ay,Bx,By,"RIGHT");
		if(out!=0)
			in = out;
		//top edge:
		Ax=llx;Ay=ury;Bx= urx;By=ury;
		out = SHPC(in,out3x,out3y,outx,outy,Ax,Ay,Bx,By,"TOP");
		
		 
		return out; // should return number of vertices in clipped poly.
	}
}
