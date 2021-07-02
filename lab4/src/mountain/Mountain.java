package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;


import javax.print.attribute.standard.Sides;
import java.nio.channels.Pipe;
import java.util.HashMap;
import java.util.Map;

public class Mountain extends Fractal{
    private Point p1;
    private Point p2;
    private Point p3;
    private double dev;
    public Map<Side, Point> MyMap;

    public Mountain(Point p1, Point p2, Point p3, double dev){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.dev = dev;
        this.MyMap = new HashMap<>();
    }


    @Override
    public String getTitle() {
        return "mountain";
    }

    @Override
    public void draw(TurtleGraphics g) {
        fractalLine(g, order, p1, p2, p3,dev);
    }



    public  Point mid(Point p1, Point p2,double dev){
        double vc = RandomUtilities.randFunc(dev);
        Point p = new Point(((p1.getX() + p2.getX())/2), (int)(((p1.getY() + p2.getY())/2) - vc));
        Side s = new Side(p1,p2);
        if(MyMap.containsKey(s)){
            Point temp = MyMap.get(s);
            MyMap.remove(s);
            return temp;
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
            fractalLine(turtle,order-1, a                , ca      ,ab,dev/2 );
            fractalLine(turtle,order-1, bc , b           ,ab       ,dev/2 );
            fractalLine(turtle,order-1, ca , bc          ,c        ,dev/2);
            fractalLine(turtle,order-1, ab , bc          ,ca       ,dev/2);

        }
    }
}
