/**
 * This is the tester class of RegularPolygon. Tests all public members.
 * 
 * @author Li Hoi Kit
 */
public class RegularPolygonTester {
    /**
     * main method of this class
     * 
     * @param args Not used in this application
     */
    public static void main(String[] args){
        RegularPolygon test = new RegularPolygon();
        
        //Initialize the variables for testing
        int numOfSides = 6;
        int numOfSidesInvalid = 2;
        double radius = 3.2;
        double radiusInvalid = -4.2;

        //Test the no param constructor, getter and setter
        System.out.println("Testing constructor with no param, the getters and setters methods : ");
        System.out.println("Original numOfSides : " + test.getNumOfSides() + " , original radius : " + test.getRadius());
        test.setNumOfSides(numOfSides);
        test.setRadius(radius);
        System.out.println("Valid numOfSides : " + test.getNumOfSides() + " , valid radius : " + test.getRadius());
        test.setNumOfSides(numOfSidesInvalid);
        System.out.println("Invalid numOfSides : " + test.getNumOfSides());
        test.setRadius(radiusInvalid);
        System.out.println("Invalid setRadius : " + test.getRadius());

        System.out.println("\n");

        //Test the other constructors

        //RegularPolygon(int n)
        System.out.println("Testing constructor with (int n) as paramemter : ");
        RegularPolygon test2 = new RegularPolygon(numOfSides);
        System.out.println("Valid parameter, numOfSides : " + test2.getNumOfSides() + " ,radius : " + test2.getRadius());
        RegularPolygon test3 = new RegularPolygon(numOfSidesInvalid);
        System.out.println("Inalid parameter, numOfSides : " + test3.getNumOfSides() + " ,radius : " + test3.getRadius());
        
        System.out.println("\n");

        //RegularPolygon(int n, double r)
        System.out.println("Testing constructor with (int n, double r) as parameter : ");
        RegularPolygon test4 = new RegularPolygon(numOfSides, radius);
        System.out.println("Valid parameter, numOfSides : " + test4.getNumOfSides() + " , radius : " + test4.getRadius());
        RegularPolygon test5 = new RegularPolygon(numOfSidesInvalid, radiusInvalid);
        System.out.println("Inalid parameter, numOfSides : " + test5.getNumOfSides() + " , radius : " + test5.getRadius());

        System.out.println("\n");

        //Test contain method
        System.out.println("Testing contain method : ");
        RegularPolygon test6 = new RegularPolygon(4,1.0);
        test6.setTheta(Math.PI/4);
        test6.setXc(1.0);
        test6.setYc(2.0);
        double xIn = 1.0, yIn = 2.0;
        double xOut = 0.0, yOut = 0.0;
        System.out.println("The center : " + test6.contains(xIn, yIn));
        System.out.println("The origin : " + test6.contains(xOut, yOut));
                




    }
}
