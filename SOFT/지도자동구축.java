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


//num의 의미: 정사각형 한 변에 존재하는 점의 개수
/*
N=1: num = 2*2-1 = 3
N=2, num = 3*2-1 = 5
N=3, num = 5*2-1 = 9
...
*/
