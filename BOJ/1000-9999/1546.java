#평균

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        long maxNum = 0;
        long total = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] > maxNum)
                maxNum = A[i];
            total += A[i];
        }
        System.out.println(total*100.0/maxNum/n);
    }
}
