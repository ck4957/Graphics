//
//  textureParams.java
//
//  Created by Joe Geigel on 1/23/13.
//
//  Contributor:  Chirag Kular
//
//  Simple class for setting up the textures for the textures
//  assignment.
//
//  20155 version
//

//
// Reference slides : Texture Mapping(20-textures_TBK_2up)
//Slide : 79,80


import java.io.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;
import com.jogamp.opengl.util.texture.*;


public class textureParams
{

	Texture t_id1, t_id2;
    /**
     * constructor
     */
    public textureParams()
    {
    }

    /**
     * This function loads texture data to the GPU.
     *
     * You will need to write this function, and maintain all of the values
     * needed to be sent to the various shaders.
     *
     * @param gl3 - GL3 object on which all OpenGL calls are to be made
     *
     */
    public void loadTexture( GL3 gl3 )
    {      
    	//Referred slide 70
    	 try {
    		 //Read both the images and assign to texture object
    		 InputStream stream1 = new BufferedInputStream(new FileInputStream("smiley2.png"));
             t_id1 = TextureIO.newTexture(stream1,false, "png");             
           }
            catch (IOException e) {
             e.printStackTrace();
             System.exit(1);
            }
    }

    /**
     * This function sets up the parameters for texture use.
     *
     * You will need to write this function, and maintain all of the values
     * needed to be sent to the various shaders.
     *
     * @param program - The ID of an OpenGL (GLSL) program to which
     *    parameter values are to be sent
     *
     * @param gl3 - GL3 object on which all OpenGL calls are to be made
     *
     */
    public void setUpTexture( int program, GL3 gl3 )
    {
    	//Referred Slide 79,80
    	//Binding texture to texture units
    	gl3.glActiveTexture(gl3.GL_TEXTURE0 + 1);
    	t_id1.bind(gl3);
    	//gl3.glActiveTexture(gl3.GL_TEXTURE0 + 2);
    	//t_id2.bind(gl3);
    	
    	//get locations of images
    	int value1 = gl3.glGetUniformLocation(program, "t_id1");
    	//int value2 = gl3.glGetUniformLocation(program, "t_id2");
    	
    	//Connect images to texture units
    	gl3.glUniform1i(value1,1);
    	//gl3.glUniform1i(value2,2);
    }
}
