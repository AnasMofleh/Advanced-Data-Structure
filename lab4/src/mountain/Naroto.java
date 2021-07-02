package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;


import javax.print.attribute.standard.Sides;
import java.nio.channels.Pipe;
import java.util.HashMap;
import java.util.Map;

public class Naroto extends Fractal{
    private Point p1;
    private Point p2;
    private Point p3;
    private double dev;
    public Map<Side, Point> MyMap;

    public Naroto(Point p1, Point p2, Point p3,double dev){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.dev = dev;
        this.MyMap = new HashMap<>();
    }


    @Override
    public String getTitle() {
        return "Naroto";
    }
    @Override
    public void draw(TurtleGraphics g) {
        fractalLine(g, order, p1, p2, p3,dev);
    }



    public  Point mid(Point p1, Point p2,double dev){
        double vc = RandomUtilities.randFunc(dev);
        Point p = new Point(((p1.getX() + p2.getX())/2), ((p1.getY() + p2.getY())/2));
        Side s = new Side(p1,p2);
        if(MyMap.containsKey(s)){
            return MyMap.get(s);
        } else {
            MyMap.put(s,p);
            return p;
        }
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
            Point ab = mid(a,b,dev);
            Point bc = mid(b,c,dev);
            Point ca = mid(c,a,dev);
            fractalLine(turtle,order-1, a                ,ab       ,bc   ,dev/2 );
            fractalLine(turtle,order-1, bc               ,b        ,ca    ,dev/2 );
            fractalLine(turtle,order-1, ca               ,ab       ,c     ,dev/2);
            fractalLine(turtle,order-1, ab               ,bc       ,ca    ,dev/2);

        }
    }
}
