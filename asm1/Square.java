/**
 * This class is used to model squares.
 * 
 * @author Li Hoi Kit
 */
public class Square extends Shape {
    
    /** 
     * Setting the local coordinates of the 4 vertices of the square according to the parameter d. Overrides the method in Shape superclass.
     * The origin is at the center of the shape.
     * 
     * @param d Length of half of a side of the square
     */
    public void setVertices(double d){
        //Allocating memory in heap
        this.xLocal = new double[4];
        this.yLocal = new double[4];

        //Assigning the vertices vaules
        this.xLocal[0] = d;
        this.xLocal[1] = d;
        this.xLocal[2] = -d;
        this.xLocal[3] = -d;

        this.yLocal[0] = d;
        this.yLocal[1] = -d;
        this.yLocal[2] = -d;
        this.yLocal[3] = d;
        
    }

}
