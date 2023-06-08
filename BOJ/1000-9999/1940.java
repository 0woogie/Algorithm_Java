#주몽

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        Arrays.sort(A);
        int i = 0;
        int j = n-1;
        int count = 0;
        while (i < j) {
            if (A[i] + A[j] == m) {
                count += 1;
                i++;
                j--;
            } else if (A[i] + A[j] < m) {
                i++;
            } else {
                j--;
            }
        }
        System.out.println(count);
    }
}
