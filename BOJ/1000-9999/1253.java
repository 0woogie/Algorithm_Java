#좋다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] A = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(A);
        int result = 0;
        for (int k = 0; k < n; k++) {
            long target = A[k];
            int i = 0;
            int j = n-1;
            while (i < j) {
                if (i == k) {
                    i++;
                } else if (j == k) {
                    j--;
                }else if (A[i] + A[j] > target) {
                    j--;
                } else if (A[i] + A[j] < target) {
                    i++;
                } else {
                    result += 1;
                    break;
                }
            }
        }
        System.out.println(result);
    }
}
