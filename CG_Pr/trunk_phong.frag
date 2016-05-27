#version 150

// Phong fragment shader
//
// Contributor:  CHIRAG KULAR
// Reference slides : Lighting, Materials, and Shading(19-shading_tbk_2up)
// Formulas referred from the mentioned slides

// INCOMING DATA
//All important vectors
varying vec3 N,L,R,V;

//Material Properties
uniform vec4 amb_col_trunk;
uniform vec4 diff_col_trunk;
uniform vec4 spec_col;
uniform vec4 light_col;
uniform vec4 light_pos;
uniform vec4 amb_light_col;

//Relative Characteristics
uniform float amb_ref_coff;
uniform float diff_ref_coff;
uniform float spec_ref_coff;
uniform float spec_exp;


// OUTGOING DATA

// final fragment color
out vec4 fragmentColor;


void main()
{
	fragmentColor = vec4( 1.0, 1.0, 1.0, 1.0 );
		
	float cos_theta = dot(N,L); 
	float  cos_alpha = dot(V,R);

	vec4 A, D, S;
	A = amb_col_trunk * amb_ref_coff *  amb_light_col;
	D = light_col * diff_col_trunk * diff_ref_coff * max(0,cos_theta);

	if(cos_theta<=0||cos_alpha<=0)
		S = vec4(0,0,0,0);
	else
		S = light_col * spec_col * spec_ref_coff * pow(max(0,cos_alpha),spec_exp);
	
    fragmentColor = A + S + D;
}