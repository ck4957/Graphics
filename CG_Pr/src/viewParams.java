//
//  viewParams.java
//
//  Class for setting up the view parameters.
//

import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;

public class viewParams
{
    // current values for transformations
    private float rotateDefault[];
    private float translateDefault[];
    private float scaleDefault[];

    // current view values
    private float eyeDefault[];
    private float lookDefault[];
    private float upDefault[];

    // clipping window boundaries
    private float left = -1.0f;
    private float right = 1.0f;
    private float top = 1.0f;
    private float bottom = -1.0f;
    private float near =3.0f;
    private float far = 100.5f;

    /**
     * constructor
     */
    public viewParams()
    {
        rotateDefault = new float[3];
        rotateDefault[0] = 0.0f;
        rotateDefault[1] = 50.0f;
        rotateDefault[2] = 90.0f;

        translateDefault = new float[3];
        translateDefault[0] = 1.0f;
        translateDefault[1] = 0.0f;
        translateDefault[2] = -1.0f;

        scaleDefault = new float[3];
        scaleDefault[0] = 1.0f;
        scaleDefault[1] = 4.0f;
        scaleDefault[2] = 1.0f;

        eyeDefault = new float[3];
        eyeDefault[0] = 0.0f;
        eyeDefault[1] = 3.0f;
        eyeDefault[2] = 3.0f;

        lookDefault = new float[3];
        lookDefault[0] = 1.0f;
        lookDefault[1] = 0.0f;
        lookDefault[2] = 0.0f;

        upDefault = new float[3];
        upDefault[0] = 0.0f;
        upDefault[1] = 1.0f;
        upDefault[2] = 0.0f;
    }


    /**
     * This function sets up the view and projection parameter for a frustum
     * projection of the scene. See the assignment description for the values
     * for the projection parameters.
     *
     * You will need to write this function, and maintain all of the values
     * needed to be sent to the vertex shader.
     *
     * @param program - The ID of an OpenGL (GLSL) shader program to which
     *    parameter values are to be sent
     * @param gl3 - GL3 object on which all OpenGL calls are to be made
     *
     */
    public void setUpFrustum( int program, GL3 gl3 )
    {
        int leftLoc = gl3.glGetUniformLocation( program , "left" );
        int rightLoc = gl3.glGetUniformLocation( program , "right" );
        int topLoc = gl3.glGetUniformLocation( program , "top" );
        int bottomLoc = gl3.glGetUniformLocation( program , "bottom" );
        int nearLoc = gl3.glGetUniformLocation( program , "near" );
        int farLoc = gl3.glGetUniformLocation( program , "far" );

        gl3.glUniform1f( leftLoc, left );
        gl3.glUniform1f( rightLoc, right );
        gl3.glUniform1f( topLoc, top );
        gl3.glUniform1f( bottomLoc, bottom );
        gl3.glUniform1f( nearLoc, near );
        gl3.glUniform1f( farLoc, far );
    }


    /**
     * This function clears any transformations, setting the values to the
     * defaults: no scaling (scale factor of 1), no rotation (degree of
     * rotation = 0), and no translation (0 translation in each direction).
     *
     * You will need to write this function, and maintain all of the values
     * which must be sent to the vertex shader.
     *
     * @param program - The ID of an OpenGL (GLSL) shader program to which
     *    parameter values are to be sent
     * @param gl3 - GL3 object on which all OpenGL calls are to be made
     */
    public void clearTransforms( int program, GL3 gl3 )
    {
        int thetaLoc = gl3.glGetUniformLocation( program , "theta" );
        int transLoc = gl3.glGetUniformLocation( program , "trans" );
        int scaleLoc = gl3.glGetUniformLocation( program , "scale" );

        gl3.glUniform3fv( thetaLoc, 1, rotateDefault, 0 );
        gl3.glUniform3fv( transLoc, 1, translateDefault, 0 );
        gl3.glUniform3fv( scaleLoc, 1, scaleDefault, 0 );
    }


    /**
     * This function sets up the transformation parameters for the vertices
     * of the shapes.  The order of application is specified in the driver
     * program.
     *
     * You will need to write this function, and maintain all of the values
     * which must be sent to the vertex shader.
     *
     * @param program - The ID of an OpenGL (GLSL) shader program to which
     *    parameter values are to be sent
     * @param gl3 - GL3 object on which all OpenGL calls are to be made
     * @param scaleX - amount of scaling along the x-axis
     * @param scaleY - amount of scaling along the y-axis
     * @param scaleZ - amount of scaling along the z-axis
     * @param rotateX - angle of rotation around the x-axis, in degrees
     * @param rotateY - angle of rotation around the y-axis, in degrees
     * @param rotateZ - angle of rotation around the z-axis, in degrees
     * @param translateX - amount of translation along the x axis
     * @param translateY - amount of translation along the y axis
     * @param translateZ - amount of translation along the z axis
     */
    public void setUpTransforms( int program, GL3 gl3,
        float scaleX, float scaleY, float scaleZ,
        float rotateX, float rotateY, float rotateZ,
        float translateX, float translateY, float translateZ )
    {
        float scaleVec[] = { scaleX, scaleY, scaleZ };
        float rotateVec[] = { rotateX, rotateY, rotateZ };
        float translateVec[] = { translateX, translateY, translateZ };

        int thetaLoc = gl3.glGetUniformLocation( program , "theta" );
        int transLoc = gl3.glGetUniformLocation( program , "trans" );
        int scaleLoc = gl3.glGetUniformLocation( program , "scale" );

        // send down to the shader
        gl3.glUniform3fv( thetaLoc, 1, rotateVec, 0 );
        gl3.glUniform3fv( transLoc, 1, translateVec, 0 );
        gl3.glUniform3fv( scaleLoc, 1, scaleVec, 0 );
    }


    /**
     * This function clears any changes made to camera parameters, setting the
     * values to the defaults: eyepoint (0.0,0.0,0.0), lookat (0,0,0.0,-1.0),
     * and up vector (0.0,1.0,0.0).
     *
     * You will need to write this function, and maintain all of the values
     * which must be sent to the vertex shader.
     *
     * @param program - The ID of an OpenGL (GLSL) shader program to which
     *    parameter values are to be sent
     * @param gl3 - GL3 object on which all OpenGL calls are to be made
     */
    void clearCamera( int program, GL3 gl3 )
    {
        int posLoc = gl3.glGetUniformLocation( program, "cPosition" );
        int lookLoc = gl3.glGetUniformLocation( program, "cLookAt" );
        int upVecLoc = gl3.glGetUniformLocation( program, "cUp" );

        gl3.glUniform3fv( posLoc, 1, eyeDefault, 0 );
        gl3.glUniform3fv( lookLoc, 1, lookDefault, 0 );
        gl3.glUniform3fv( upVecLoc, 1, upDefault, 0 );
    }


    /**
     * This function sets up the camera parameters controlling the viewing
     * transformation.
     *
     * You will need to write this function, and maintain all of the values
     * which must be sent to the vertex shader.
     *
     * @param program - The ID of an OpenGL (GLSL) shader program to which
     *    parameter values are to be sent
     * @param gl3 - GL3 object on which all OpenGL calls are to be made
     * @param eyepointX - x coordinate of the camera location
     * @param eyepointY - y coordinate of the camera location
     * @param eyepointZ - z coordinate of the camera location
     * @param lookatX - x coordinate of the lookat point
     * @param lookatY - y coordinate of the lookat point
     * @param lookatZ - z coordinate of the lookat point
     * @param upX - x coordinate of the up vector
     * @param upY - y coordinate of the up vector
     * @param upZ - z coordinate of the up vector
     */
    void setUpCamera( int program, GL3 gl3,
        float eyepointX, float eyepointY, float eyepointZ,
        float lookatX, float lookatY, float lookatZ,
        float upX, float upY, float upZ )
    {
        float eyeVec[] = { eyepointX, eyepointY, eyepointZ };
        float lookatVec[] = { lookatX, lookatY, lookatZ };
        float upVec[] = { upX, upY, upZ };

        int posLoc = gl3.glGetUniformLocation( program, "cPosition" );
        int lookLoc = gl3.glGetUniformLocation( program, "cLookAt" );
        int upVecLoc = gl3.glGetUniformLocation( program, "cUp" );

        // send down to the shader
        gl3.glUniform3fv( posLoc, 1, eyeVec, 0 );
        gl3.glUniform3fv( lookLoc, 1, lookatVec, 0 );
        gl3.glUniform3fv( upVecLoc, 1, upVec, 0 );
    }

}
