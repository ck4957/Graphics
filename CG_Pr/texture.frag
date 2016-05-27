#version 150

// Texture mapping fragment shader
//
// Contributor:  CHIRAG KULAR
// Reference slides : Texture Mapping(20-textures_TBK_2up)
//Slide : 83


// INCOMING DATA
in vec2 texCoord;

// OUTGOING DATA

// final fragment color
out vec4 fragmentColor;

//For 1st image (smiley.png)
uniform sampler2D t_id1;


void main()
{
   vec4 front_part = texture2D(t_id1, texCoord);
   fragmentColor = front_part;    
}
