//
// lightingParams.java
//
// Created by Joe Geigel on 1/23/13.
//
// Contributor:  CHIRAG KULAR
//
// 20155 version
//

import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;

public class lightingParams
{
	// Add any data members you need here.

	/**
	 * constructor
	 */
	public lightingParams()
	{
	}

	/**
	 * This function sets up the lighting, material, and shading parameters
	 * for the Phong shader.
	 *
	 * You will need to write this function, and maintain all of the values
	 * needed to be sent to the vertex shader.
	 *
	 * @param program - The ID of an OpenGL (GLSL) shader program to which
	 *    parameter values are to be sent
	 *
	 * @param gl3 - GL3 object on which all OpenGL calls are to be made
	 *
	 */
	public void setUpPhong (int program, GL3 gl3)
	{
		//Material Properties and Light Source properties

		//Green Color for the cone tree
		float[] amb_col_coneTree = {0.0f,0.6f,0.0f,1.0f};
		float[] diff_col_conTree = {0.4f,0.5f,0.4f,1.0f};

		//Brown color for the trunk
		float[] amb_col_trunk = {0.61f,0.13f,0.13f,1.0f};
		float[] diff_col_trunk = {0.61f,0.13f,0.13f,1.0f};

		//Purple color for the remaining shapes
		float[] amb_col = {0.5f,0.1f,0.9f,1.0f};
		float[] diff_col = {0.89f,0.0f,0.0f,1.0f};
		
		float[] spec_col = {1.0f,1.0f,1.0f,1.0f};
		float[] light_col = {1.0f,1.0f,0.0f,1.0f};
		float[] light_pos = {0.0f,5.0f,2.0f,1.0f};
		float[] amb_light_col = {0.5f,0.5f,0.5f,1.0f};
		float[][] properties ={amb_col_coneTree,diff_col_conTree,amb_col_trunk,
				diff_col_trunk,amb_col,diff_col,
				spec_col,light_col,light_pos,amb_light_col};
		String[] str1 = {"amb_col_coneTree","diff_col_conTree","amb_col_trunk",
				"diff_col_trunk","amb_col","diff_col",
				"spec_col","light_col","light_pos","amb_light_col"};

		//Relfective Charactericstics
		float amb_ref_coff = 0.5f;
		float diff_ref_coff = 0.7f;
		float spec_ref_coff = 1.0f;
		float spec_exp = 10.0f;
		float[] characteristic = {amb_ref_coff,diff_ref_coff,spec_ref_coff,spec_exp};
		String[] str2 = {"amb_ref_coff","diff_ref_coff","spec_ref_coff","spec_exp"};

		for(int i=0;i<properties.length;i++)
		{	
			int value = gl3.glGetUniformLocation( program,str1[i]);
			gl3.glUniform4f(value,properties[i][0],properties[i][1],properties[i][2],
					properties[i][3]);
		}
		for(int i=0;i<characteristic.length;i++)
		{	
			int value = gl3.glGetUniformLocation( program,str2[i]);
			gl3.glUniform1f(value,characteristic[i]);
		}
	}
}
