import java.lang.Math;

/**
 * This class is used to model circles.
 * 
 * @author Li Hoi Kit
 */
public class Circle extends Shape {
    
    /** 
     * Setting the local coordinates of the 2 vertices (read Detail for more details) of the triangle according to the parameter d. Overrides the method in Shape superclass.
     * The 2 vertices are the upper left and lower right vertices of an axis-aligned bounding square box of a circle, respectively.
     * The origin is at the center of the shape.
     * 
     * @param d The radius of the circle.
     */
    public void setVertices(double d){
        //Allocating memory in heap
        this.xLocal = new double[2];
        this.yLocal = new double[2];

        //Assigning the vertices vaules
        this.xLocal[0] = -d;
        this.xLocal[1] = d;

        this.yLocal[0] = -d;
        this.yLocal[1] = d;
        
    }

    
    /** 
     * Returns the x-coordinates of the 2 vertices (read Detail for more details) of the shape in the screen coordinate system.
     * The 2 vertices are the upper left and lower right vertices of an axis-aligned bounding square box of a circle, respectively.
     * 
     * @return int[] the array of x-coordinates
     */
    public int[] getX(){
        int[] xVerticesScreen = new int[2]; //Allocating memory in heap
        xVerticesScreen[0] = (int) Math.round( this.xLocal[0] + this.xc ); //Computes and saves in the new array
        xVerticesScreen[1] = (int) Math.round( this.xLocal[1] + this.xc );
        return xVerticesScreen;
    }

    
    /** 
     * Returns the y-coordinates of the 2 vertices (read Detail for more details) of the shape in the screen coordinate system.
     * The 2 vertices are the upper left and lower right vertices of an axis-aligned bounding square box of a circle, respectively.
     * 
     * @return int[] the array of y-coordinates
     */
    public int[] getY(){
        int[] yVerticesScreen = new int[2]; //Allocating memory in heap
        yVerticesScreen[0] = (int) Math.round( this.yLocal[0] + this.yc ); //Computes and saves in the new array
        yVerticesScreen[1] = (int) Math.round( this.yLocal[1] + this.yc );
        return yVerticesScreen;
    }
}
