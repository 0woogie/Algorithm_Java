#지도 자동 구축

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int num = 2;
        for(int i=0; i<N; i++) {
            num = num*2 - 1;
        }
        System.out.println(num*num);
    }
}

/*
n=1: 2*2-1 = 3
n=2, 3*2-1 = 5
n=3, 5*2-1 = 9
...
*/
