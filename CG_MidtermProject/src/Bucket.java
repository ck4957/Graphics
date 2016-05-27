//
//  Bucket.java
//
//  version : 1.0
//  Contributor:  Chirag Kular
///
// 
// This is a class of which objects are created and stored in Global edge table and active edge table
//
///


import java.util.Comparator;

public class Bucket implements Comparable<Bucket>{
	//Integer ;
	Double ymin,ymax, x_val,slope;
	//Bucket next;
	public Bucket(double ymin,double ymax,double x_val, double slope)
	{
		this.ymin = ymin;
		this.ymax = ymax;
		this.x_val = x_val;
		this.slope = slope;
	}
	public Bucket(double ymax,double x_val, double slope)
	{
		this.ymax = ymax;
		this.x_val = x_val;
		this.slope = slope;
	}
	public int compareTo(Bucket b2) {
		int sort_ymin = this.ymin.compareTo(b2.ymin);
		if(sort_ymin == 0){
			int sort_ymax = this.x_val.compareTo(b2.x_val);//this.ymax.compareTo(b2.ymax); 
			if (sort_ymax == 0)
				return this.ymax.compareTo(b2.ymax);//this.x_val.compareTo(b2.x_val);
			else
				return sort_ymax;
		}
		return sort_ymin;
	}
}
