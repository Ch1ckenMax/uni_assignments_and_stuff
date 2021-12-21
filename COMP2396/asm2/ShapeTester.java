import java.awt.Color;
/**
 * Tester class for the shape class. Tests all public members.
 * 
 * @author Li Hoi Kit
 */
public class ShapeTester {
    /**
     * Main method of the tester class.
     * 
     * @param args Not used in this application.
     */
    public static void main(String args[]){
        Shape test = new Shape();

        //Testing getters and setters

        //Initialize variables for testing
        Color color = new Color(30,20,50);
        boolean filled = true;
        double theta = 2.31;
        double xc = 1.5;
        double yc = 2.4;
        double[] xLocal = {0.2, 1.4};
        double[] yLocal = {4.21, 7.42};

        //Use the setters
        test.setColor(color);
        test.setFilled(filled);
        test.setTheta(theta);
        test.setXc(xc);
        test.setYc(yc);
        test.setXLocal(xLocal);
        test.setYLocal(yLocal);

        //Try the getters
        System.out.println("Testing the getter and setters: ");
        System.out.println("Color : " + test.getColor());
        System.out.println("Filled : " + test.getFilled());
        System.out.println("Theta : " + test.getTheta());
        System.out.println("xc : " + test.getXc());
        System.out.println("yc : " + test.getYc());
        System.out.println("xLocal length : " + test.getXLocal().length + ", yLocal length : " + test.getYLocal().length);
        System.out.println("xLocal[0] : " + test.getXLocal()[0] + ", xLocal[1] : " + test.getXLocal()[1]);
        System.out.println("yLocal[0] : " + test.getYLocal()[0] + ", yLocal[1] : " + test.getYLocal()[1]);

        System.out.println("\n");

        //Test the methods

        //Test translate
        System.out.println("Translate method :");
        double dx = 2.4;
        double dy = 4.1;
        System.out.println("Before translation, xc : " + test.getXc() + ", yc : " + test.getYc());
        test.translate(dx, dy);
        System.out.println("After translation, xc : " + test.getXc() + ", yc : " + test.getYc());

        System.out.println("\n");

        //Test rotate
        System.out.println("Rotate method :");
        double dt = 7.14;
        System.out.println("Before rotate, theta : " + test.getTheta());
        test.rotate(dt);
        System.out.println("After rotate, theta : " + test.getTheta());

        System.out.println("\n");

        //Test getX and getY
        System.out.println("getX and getY methods :");
        int[] getX =  test.getX();
        int[] getY = test.getY();
        System.out.println("getX length : " + getX.length + ", getY length : " + getY.length);
        System.out.println("getX[0] : " + getX[0] + ", getX[1] : " + getX[1]);
        System.out.println("getY[0] : " + getY[0] + ", getY[1] : " + getY[1]);
    }
}
