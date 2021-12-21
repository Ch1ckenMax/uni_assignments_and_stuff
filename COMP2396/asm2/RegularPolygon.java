/** 
* This class is used to model regular n-sided polygons. It is a subclass of Shape.
* 
* @author Li Hoi Kit
*/
public class RegularPolygon extends Shape{
    //Variables

    private int numOfSides; //Number of sides of the polygon
    private double radius; //The radius of the polygon

    //Constructors

    /** 
     * Builds a n-sided polygon with radius r
     * @param n number of sides (sets to 3 if this argument is less than 3)
     * @param r radius (sets to 0.0 if this argument is less than 0.0)
    */
    public RegularPolygon(int n, double r){
        if(r >= 0.0)
            this.radius = r;
        else
            this.radius = 0.0;
        
        if(n >= 3)
            this.numOfSides = n;
        else
            this.numOfSides = 3;

        this.setVertices();
    }

    /** 
     * Builds a n-sided polygon with radius 1.0
     * @param n number of sides (sets to 3 if this argument is less than 3)
    */
    public RegularPolygon(int n){
        this.radius = 1.0;

        if(n >= 3)
            this.numOfSides = n;
        else
            this.numOfSides = 3;

        this.setVertices();
    }

    /** 
     * Builds a 3-sided polygon with radius 1.0
    */
    public RegularPolygon(){
        this.radius = 1.0;
        this.numOfSides = 3;
        this.setVertices();
    }

    //Methods
    
    /** 
     * Getter method for retrieving the number of sides of the polygon
     * @return int Number of sides of the polygon
     */

    public int getNumOfSides(){
        return this.numOfSides;
    }

    
    /** 
     * Getter method for retrieving the radius of the polygon
     * @return double Radius of the polygon
     */
    public double getRadius(){
        return this.radius;
    }

    
    /** 
     * Setter method for setting the number of sides of the polygon
     * @param n number of sides (sets to 3 if this argument is less than 3)
     */
    public void setNumOfSides(int n){
        if(n < 3)
            this.numOfSides = 3;
        else
            this.numOfSides = n;

        this.setVertices(); //Reset the local coordinates
    }

    
    /** 
     * Setter method for setting the radius of the polygon
     * @param r radius of the polygon
     */
    public void setRadius(double r){
        if(r < 0.0)
            this.radius = 0.0;
        else
            this.radius = r;

        this.setVertices(); //Reset the local coordinates
    }

    private void setVertices(){
        //Allocate memory in heap for the coordinates arrays
        double[] xLocal = new double[this.numOfSides];
        double[] yLocal = new double[this.numOfSides];

        //Determine value of a
        double a;
        if(this.numOfSides % 2 == 0) //Number of sides is even
            a = Math.PI/this.numOfSides;
        else //Number of sides is odd
            a = 0;

        //Calculates the coordinates of the vertex and assign them to the arrays
        for(int i = 0; i < this.numOfSides; i++){
            xLocal[i] = this.radius * Math.cos(a - (i * (2 * Math.PI)) / this.numOfSides );
            yLocal[i] = this.radius * Math.sin(a - (i * (2 * Math.PI)) / this.numOfSides );
        }

        //Use the setters to assign the vertices to the private xLocal yLocal references inherited from the superclass
        this.setXLocal(xLocal);
        this.setYLocal(yLocal);
    } 

    
    /** 
     * Determines whether a point in screen coordinate system is contained (i.e. lies inside or on sides/vertices) by the polygon.
     * @param x x-coordinate of the point in screen coordinate system
     * @param y y-coordinate of the point in screen coordinate system
     * @return boolean the truth value of whether the point is in the polygon
     */
    public boolean contains(double x, double y){

        //Retrieve the required information from the getter methods inherited from the super class
        double pointX = (x - this.getXc()) * Math.cos(-this.getTheta()) - (y - this.getYc()) * Math.sin(-this.getTheta());
        double pointY = (x - this.getXc()) * Math.sin(-this.getTheta()) + (y - this.getYc()) * Math.cos(-this.getTheta());
        double[] xLocal = this.getXLocal();

        //The vertex with minimum value of X is at the exactly opposite side of the first vertex for even number of sides(which is the n/2th vertex), or at the (n-1)/2th vertex for odd number of sides (-1 is omitted as Java automatically take away decimal places for integer division)
        double minX = xLocal[this.getNumOfSides()/2];
        
        //Compare the x coordinate of the point with the x coordinate of the left most side of the polygon(i.e. with minimum x-value). Return false if point is at the left of the side 
        if(minX > pointX)
            return false;
            
        for(int i = 0; i < this.numOfSides - 1; i++){
            //Rotate
            double originalX = pointX;
            double originalY = pointY;
            pointX = originalX * Math.cos((2 * Math.PI)/this.numOfSides) - originalY * Math.sin((2 * Math.PI)/this.numOfSides);
            pointY = originalX * Math.sin((2 * Math.PI)/this.numOfSides) + originalY * Math.cos((2 * Math.PI)/this.numOfSides);

            //Check
            if(minX > pointX)
            return false;
        }

        return true; //Point isnt outside of the shape, return true
    }
}
