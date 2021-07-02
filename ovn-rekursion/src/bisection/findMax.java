package bisection;

import java.util.Arrays;

public class findMax {

    public static int findMax(int[] v, int n) {

        if (n == 1) {
            return v[0];
        } else {
            int vectorwithoutlast = findMax(v, n-1);
            if (v[n-1] > vectorwithoutlast) {
                return v[n-1];
            } else {
                return vectorwithoutlast;
            }
        }
    }
      /*if we replace vectorwithoutlast with findMax(v, n-1)
       * the method will be ineffective */




    public static void main(String[] args) {
        int[] vector = new int[1000];
        Arrays.fill(vector,1,1000,50);
        int max = 32;
        long t0 = System.nanoTime();
        int x = findMax(vector, max);
        long t1 = System.nanoTime();
        System.out.println(x);
        System.out.println("tid: " + (t1 - t0) / 1000000000.0 + " ms");

    }
}
