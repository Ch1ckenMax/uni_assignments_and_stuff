import java.awt.Color;
import java.lang.Math;

/** 
* This class is used to model general shapes. This class should be inherited by other subclasses to be functional.
* 
* @author Li Hoi Kit
*/
public class Shape {
    
    //Instance Variables
    /** Specifies the color of the shape.*/
    public Color color;

    /** Specifies whether the shape is filled or not.*/
    public boolean filled;

    /** Specifies the orientation(in radians) of the shape in screen coordinate system.*/
    public double theta;

    /** Specifies the x-coordinate of the center of the shape in the screen coordinate system.*/
    public double xc;

    /** Specifies the y-coordinate of the center of the shape in the screen coordinate system.*/
    public double yc;

    /** An array of double values specifying the x-coordinates of the vertices (in counter-clockwise order) of the shape in its local coordinate system.*/
    public double[] xLocal;

    /** An array of double values specifying the y-coordinates of the vertices (in counter-clockwise order) of the shape in its local coordinate system.*/
    public double[] yLocal;

    
    /** 
     * Set the local coordinates of the vertices of a shape. A dummy method to be overriden by the subclasses. Prints a dummmy message.
     * @param d This parameter has no usage here as it belongs to a dummy method here.
     */
    //Methods
    public void setVertices(double d){
        System.out.println("Dummy setVertices method of Shape class is called. The parameter d is: " + d);
    }

    
    /** 
     * Translates the center of the shape by the parameters dx and dy respectively, along the x and y directions of the screen coordinate system. In other words, add parameters dx and dy respectively to instance variables xc and yc. 
     * @param dx distance to be shifted in x direction
     * @param dy distance to be shifted in y direction
     */
    public void translate(double dx, double dy){
        this.xc += dx;
        this.yc += dy;
    }

    
    /** 
     * Rotates the shape about its center by the parameter dt (in radians). In other words, add parameter dt to instance variable theta.
     * @param dt angle to rotate (in radians)
     */
    public void rotate(double dt){
        this.theta += dt;
    }

    
    /** 
     * Returns the x-coordinates of the vertices (in counter-clockwise order) of the shape in the screen coordinate system.
     * @return int[] the array of x-coordinates
     */
    public int[] getX(){
        int[] xVerticesScreen = new int[this.xLocal.length]; //Allocating memory in heap
        for(int i = 0; i < this.xLocal.length; i++) //Computes and saves in the new array
            xVerticesScreen[i] = (int) Math.round( this.xLocal[i]*Math.cos(this.theta) - this.yLocal[i]*Math.sin(this.theta) + this.xc );
        return xVerticesScreen;
    }

    
    /** 
     * Returns the y-coordinates of the vertices (in counter-clockwise order) of the shape in the screen coordinate system.
     * @return int[] the array of y-coordinates
     */
    public int[] getY(){
        int[] yVerticesScreen = new int[this.yLocal.length]; //Allocating memory in heap
        for(int i = 0; i < this.yLocal.length; i++) //Computes and saves in the new array
            yVerticesScreen[i] = (int) Math.round( this.xLocal[i]*Math.sin(this.theta) + this.yLocal[i]*Math.cos(this.theta) + this.yc );
        return yVerticesScreen;
    }
}
