package game;


//vi kan ha public final istället
public class Tuple {
    private final int x;
    private final int y;
    private final int z;

    public Tuple(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}

