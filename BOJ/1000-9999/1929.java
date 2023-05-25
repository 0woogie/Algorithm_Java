#소수 구하기

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] A = new int[n + 1];
        A[1] = 1;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (A[i] == 1) {
                continue;
            }
            for (int j = i + i; j <= n; j += i) {
                A[j] = 1;
            }
        }
        
        for (int i = m; i < n + 1; i++) {
            if (A[i] != 1) {
                System.out.println(i);
            }
        }
    }
}
