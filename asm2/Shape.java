import java.awt.Color;

/** 
* This class is used to model general shapes. This class should be inherited by other subclasses to be functional.
* 
* @author Li Hoi Kit
*/
public class Shape {
    //Private variables
    
    private Color color; //Color of the shape
    private boolean filled; //Fill-type of the shape
    private double theta; //orientation of the shape (in radians)
    private double xc; //x coordinate of center
    private double yc; //y coordinate of center
    private double[] xLocal; //x coordinates of the vertices
    private double[] yLocal; //y coordinates of the vertices

    

    //Getter methods

    /** 
     * Getter method for retrieving the color of the shape
     * @return Color the color
     */
    public Color getColor(){
        return this.color;
    }

    
    /** 
     * Getter method for retrieving the fill-type of the shape
     * @return boolean the fill-type
     */
    public boolean getFilled(){
        return this.filled;
    }

    
    /** 
     * Getter method for retrieving the orientation (in radians) of the shape in screen coordinate system
     * @return double orientation (in radians)
     */
    public double getTheta(){
        return this.theta;
    }

    
    /** 
     * Getter method for retrieving the x-coordinate of the center of the shape in the screen coordinate system
     * @return double the x-coordinate of the center
     */
    public double getXc(){
        return this.xc;
    }

    
    /** 
     * Getter method for retrieving the y-coordinate of the center of the shape in the screen coordinate system
     * @return double the y-coordinate of the center
     */
    public double getYc(){
        return this.yc;
    }

    
    /** 
     * Getter method for retrieving the x-coordinates of the vertices (in counter-clockwise order) of the shape in its local coordinate system
     * @return double[] an array that contains the x-coordinates of the vertices
     */
    public double[] getXLocal(){
        return this.xLocal;
    }

    
    /** 
     * Getter method for retrieving the y-coordinates of the vertices (in counter-clockwise order) of the shape in its local coordinate system
     * @return double[] an array that contains the y-coordinates of the vertices
     */
    public double[] getYLocal(){
        return this.yLocal;
    }

    //Setter Methods

    /** 
     * Setter method for setting the color of the shape
     * @param color The color
     */

    public void setColor(Color color){
        this.color = color;
    }

    
    /** 
     * Setter method for setting the fill-type of the shape
     * @param filled The fill-type
     */
    public void setFilled(boolean filled){
        this.filled = filled;
    }

    
    /** 
     * Setter method for setting the orientation of the shape (in radians)
     * @param theta the orientation (in radians)
     */
    public void setTheta(double theta){
        this.theta = theta;
    }

    
    /** 
     * Setter method for setting the x-coordinate of the center of the shape in the screen coordinate system
     * @param xc x-coordinate of the center
     */
    public void setXc(double xc){
        this.xc = xc;
    }

    
    /** 
     * Setter method for setting the y-coordinate of the center of the shape in the screen coordinate system
     * @param yc y-coordinate of the center
     */
    public void setYc(double yc){
        this.yc = yc;
    }

    
    /** 
     * Setter method for setting the x-coordinates of the vertices (in counter-clockwise order) of the shape in its local coordinate system
     * @param xLocal an array of x-coordinates of the vertices (in couter-clockwise order)
     */
    public void setXLocal(double[] xLocal){
        this.xLocal = xLocal;
    }

    
    /** 
     * Setter method for setting the y-coordinates of the vertices (in counter-clockwise order) of the shape in its local coordinate system
     * @param yLocal an array of y-coordinates of the vertices (in couter-clockwise order)
     */
    public void setYLocal(double[] yLocal){
        this.yLocal = yLocal;
    }

    //Other methods

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
