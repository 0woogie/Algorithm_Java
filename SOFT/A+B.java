#A+B

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i=1; i<=T; i++){
            int A = sc.nextInt();
            int B = sc.nextInt();
            int result = A+B;
            System.out.println("Case #"+i+": "+result);
        }
    }
}
