package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;


import javax.print.attribute.standard.Sides;
import java.nio.channels.Pipe;

public class Bergfraktal extends Fractal{
    private Point p1;
    private Point p2;
    private Point p3;
    private double dev;

    public Bergfraktal(Point p1, Point p2, Point p3,double dev){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.dev = dev;
    }


    @Override
    public String getTitle() {
        return "Bergfraktal";
    }

    @Override
    public void draw(TurtleGraphics g) {
        fractalLine(g, order, p1, p2, p3,dev);
    }



    public  Point mid(Point p1, Point p2){
        Point p = new Point(((p1.getX() + p2.getX())/2), ((p1.getY() + p2.getY())/2));
        return p;
    }


    //
    private void fractalLine(TurtleGraphics turtle, int order,Point a ,Point b , Point c, double dev) {

        if (order == 0) {
            turtle.moveTo(a.getX() , a.getY());
            turtle.penDown();
            turtle.forwardTo(b.getX() , b.getY());
            turtle.forwardTo(c.getX() , c.getY());
            turtle.forwardTo(a.getX() , a.getY());
        } else {
            Side ab = new Side(a , b);
            Side bc = new Side(b , c);
            Side ca = new Side(c , a);
            fractalLine(turtle,order-1, a                , mid(c,a)       ,mid(a,b),dev/2 );
            fractalLine(turtle,order-1, mid(b,c) , b                      ,mid(a,b),dev/2 );
            fractalLine(turtle,order-1, mid(c,a) , mid(b,c)       ,c               ,dev/2);
            fractalLine(turtle,order-1, mid(a,b) ,mid(b,c)        ,mid(c,a),dev/2);

        }
    }
}
