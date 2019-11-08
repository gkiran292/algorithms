import java.lang.*;
import java.io.*;

public class CakeWalk
{
    private boolean cakeWalkHelper(long num, long target){
        if(target == num) return true;
        if(num > target) return false;

        boolean tens = cakeWalkHelper(num * 10, target );
        boolean twenty = cakeWalkHelper(num * 20, target);

        return tens || twenty;

    }

    public static void main (String[] args) throws java.lang.Exception
    {
        CakeWalk cf = new CakeWalk();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        long[] arr = new long[size];
        int count =0;

        while(size-- > 0){
            arr[count] = Long.parseLong(br.readLine());
            count++;
        }

        for (long l : arr) {
            if (l == 1) {
                System.out.println("Yes");
                continue;
            }
            if (cf.cakeWalkHelper(1, l)) System.out.println("Yes");
            else System.out.println("No");
        }
    }
}