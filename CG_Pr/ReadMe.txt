try  grd-x10  project2 finalMain.java shapes.java shaderSetup.java simpleShape.java viewParams.java textureParams.java lightingParams.java Normals.java NormalIndices.java Vertices.java Elements.java texture.frag texture.vert phong.vert phong.frag coneTree_phong.vert coneTree_phong.frag trunk_phong.vert trunk_phong.frag smiley2.png Sample.jpg ReadMe.txt

610 - Foundation of Computer Graphics
Final Project
5/21/2016
Author: Chirag Kular

Steps to Execute the Program
1. Run the file finalMain.java

The scene create in the project is adapted from the image Sample.jpg
Using blender, i have created the following objects:
a. Torus (acting as cap)
b. Three Cylinders joined to form the body
c. Two Cones joined to form a upper part of tree.
d. Cylinder which acts as a trunk of the tree.

Note: Quad object is adapted from the LAB 6 and its texture mapped with the image Smiley2.png used in the previous lab.

The program accepts keyboard input as follows (Same as Lab 6b):
a	Start animation
r	Reset animation rotation
s	Stop animation
q, Q	Exit from the program

In the animation only the Torus object (as cap) is in the animation part.

Files Submiitted:

finalMain.java - Main function of the application

Elements.java  - contains the Elements arrays for different shapes to support their correspoding shape function

Normals.java  - contains the Normals arrays for different shapes to support their correspoding shape function

NormalIndices.java  - contains the NormalIndices arrays for different shapes to support their correspoding shape function

Vertices.java  - contains the Vertices arrays for different shapes to support their correspoding shape function

lightingParams.java - a support module that contains code for defining and managing the parameters for the Phong Illumination Model

textureParams.java - support module that contains routines for loading a texture image and for setting up parameters for using the texture

shapes.java - definition of objects, including face normals and vertex normals

viewParams.java - transformation matrices for the shapes and the camera

simpleShape.java - a support module that provides functions for manipulating the shape.

shaderSetup.java - a support module that reads, compiles, and links GLSL shader programs.

phong.vert - "starter" vertex shader for Phong shading

phong.frag - "starter" fragment shader for Phong shading

coneTree_phong.vert - "starter" vertex shader for Phong shading
coneTree_phong.frag - To apply different color for the Phong Shading on the cone tree object

trunk_phong.vert - "starter" vertex shader for Phong shading
trunk_phong.frag - To application different color for the Phong Shading on the trunk of the tree.

texture.vert - "starter" vertex shader for texture mapping

texture.frag - "starter" fragment shader for texture mapping

 