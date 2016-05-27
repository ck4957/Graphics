author : Chirag Kular
email : ck4957@rit.edu

Foundation of Computer Graphics(610)

Midterm Project

Programming Environment : Java
Files:

midtermMain.java - the main function for the application
simpleCanvas.java - a simple 2D canvas that allows the ability to set pixels
cgCanvas.java - an extension to simpleCanvas that includes the methods i implemented
MyPolgon.java - a class which creates a polygon objects
Rasterizer.java - which has the drawPolygon function implemented in Lab2
Bucket.java - which creates the buckets for edgeTable and is part of Rasterizer
clipper.java - which has clipPolygon function implemented in Lab3


The projects was implemented as follows:
-Downloaded the Jama jar file and learned how to add it to the eclipse.
-http://math.nist.gov/javanumerics/jama/ learned about the basic matrix creation and basic operations of multiplication and identity matrix.
-After learning this, implemented the translation, rotation and scaling matrix
-For normalization and viewing coordinates, initially took time to understand it, but after going through the lecture slides,
was able to implement both setClipWindow and setViewport function
-However was confused on matrix multiplication i.e. which matrix to be multiplied with which one.
-After implementing everything, the output was still getting messed up, then did a lot of debugging and found that in addPolygon method,
while was creating the object of MyPolygon, i was copying array's reference and was not copying the array elements. After using the clone()
method for copying, the output was proper.
-For clipping polygon, function, the star symbol was not getting properly clipped, and then analyzed that i did mistake in intersection formula.
After correcting it, all polygon were clipped properly.
-For the draw polygon, there are scanline getting skipped for particular type of polygon only (E.g star). I tried to find the error but was not successful in fixing that error.
-However, the program is working properly, if i used the drawOutline function. All the output is coming properly.
