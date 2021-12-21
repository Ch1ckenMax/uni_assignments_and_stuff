import java.awt.Color;
import java.lang.Math;

/**
 * Tester class for Triangle class. 
 * Tests all the members of the class. The members inherited from the Shape superclass are not explicitly tested.
 * 
 * @author Li Hoi Kit
 */
public class TriangleTester {
    /**
     * Main function of the tester class.
     * 
     * @param args Not used in this application.
     */
    public static void main(String args[]){
        Shape test = new Triangle();

        test.color = new Color(0f,0f,0f); //Black
        test.filled = true;
        test.theta = Math.PI;
        test.xc = 3.5;
        test.yc = 4.6;
        test.xLocal = new double[2];
        test.xLocal[0] = 0.5;
        test.xLocal[1] = 1.4;
        test.yLocal = new double[2];
        test.yLocal[0] = 2.7;
        test.yLocal[1] = 4.3;

        //Testing the instance variables
        System.out.println("Testing the instance variables :\n");
        System.out.println("Color : " + test.color.toString());
        System.out.println("Filled : " + test.filled);
        System.out.println("theta : " + test.theta);
        System.out.println("x coord of center : " + test.xc);
        System.out.println("y coord of center : " + test.yc);
        System.out.println("vertice 1 : x = " + test.xLocal[0] + ", y = " + test.yLocal[0]);
        System.out.println("vertice 2 : x = " + test.xLocal[1] + ", y = " + test.yLocal[1]);
        System.out.println("--------------------------------------------------------------");

        //Testing void setVertices(double d) method
        System.out.println("Testing the method void setVertices(double d) :\n");
        double d = 2;
        System.out.println("d : " + d);
        test.setVertices(d);
        System.out.println("After the function call, ");
        for(int i = 0; i < 3; i++)
            System.out.println("vertice" + (i+1) +" : x = " + test.xLocal[i] + ", y = " + test.yLocal[i]);
        System.out.println("--------------------------------------------------------------");

        //Testing translate
        System.out.println("Testing the method void translate(double dx, double dy) :\n");
        System.out.println("xc = " + test.xc + ", yc = " + test.yc);
        double dx = 3.2;
        double dy = 4.1;
        System.out.println("dx = " + dx + ", dy = " + dy);
        test.translate(dx,dy);
        System.out.println("After the function call, " + "xc = " + test.xc + ", yc = " + test.yc);
        System.out.println("--------------------------------------------------------------");

        //Testing rotate
        System.out.println("Testing the method void rotate(double dt) :\n");
        System.out.println("theta : " + test.theta);
        double dt = 1.0;
        System.out.println("dt : " + dt);
        test.rotate(dt);
        System.out.println("After the function call, " + "theta : " + test.theta);
        System.out.println("--------------------------------------------------------------");

        //Testing getX() and getY()
        System.out.println("Testing the method int[] getX() and int[] getY() :\n");
        System.out.println("xLocal[0] = " + test.xLocal[0] + ", xLocal[1] =  " + test.xLocal[1] + ", xLocal[2] =  " + test.xLocal[2]);
        System.out.println("yLocal[0] = " + test.yLocal[0] + ", yLocal[1] =  " + test.yLocal[1] + ", yLocal[2] =  " + test.yLocal[2]);
        System.out.println("theta : " + test.theta);
        System.out.println("xc = " + test.xc + ", yc = " + test.yc);
        int[] testX = test.getX();
        int[] testY = test.getY();
        System.out.println("After the function call, " + "getX[0] = " + testX[0] + ", getX[1] =  " + testX[1] + ", getX[2] =  " + testX[2]);
        System.out.println("After the function call, " + "getY[0] = " + testY[0] + ", getY[1] =  " + testY[1] + ", getY[2] =  " + testY[2]);
        System.out.println("--------------------------------------------------------------");
    }

}
