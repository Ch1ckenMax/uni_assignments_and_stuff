/**
 * This class is used to model triangles.
 * 
 * @author Li Hoi Kit
 */
public class Triangle extends Shape {
    
    /** 
     * Setting the local coordinates of the 3 vertices of the triangle according to the parameter d. Overrides the method in Shape superclass.
     * By default (without any rotation), one of the vertices lies on positive x-axis.
     * The origin is at the center of the shape.
     * 
     * @param d The distance of the vertices from its center.
     */
    public void setVertices(double d){
        //Allocating memory in heap
        this.xLocal = new double[3];
        this.yLocal = new double[3];

        //Assigning the vertices vaules
        this.xLocal[0] = d;
        this.xLocal[1] = -d * Math.cos(Math.PI / 3);
        this.xLocal[2] = -d * Math.cos(Math.PI / 3);

        this.yLocal[0] = 0;
        this.yLocal[1] = -d * Math.sin(Math.PI / 3);
        this.yLocal[2] = d * Math.sin(Math.PI / 3);
        
    }
}
