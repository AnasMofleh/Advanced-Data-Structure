package mountain;

import java.util.HashMap;
import java.util.Map;

public class Side {
    private Point firstPoint;
    private Point secondPoint;
    private double side;

    public Side(Point firstPoint, Point secondPoint){
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.side = Math.hypot((secondPoint.getX() - firstPoint.getX()), (secondPoint.getY() - firstPoint.getY()));
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Side) {
            Side s = (Side) obj;
            return     (firstPoint.equals(s.firstPoint)  && secondPoint.equals(s.secondPoint))
                    || (firstPoint.equals(s.secondPoint) && secondPoint.equals(s.firstPoint));
        } else {
            return false;
        }
    }

    public int hashCode() {
        return firstPoint.hashCode() + secondPoint.hashCode();
    }

    public static void main(String[] args){
        Side x1 = new Side(new Point(100,400),new Point(200,700));
        Side x2 = new Side(new Point(200,700),new Point(100,400));


    }

}
