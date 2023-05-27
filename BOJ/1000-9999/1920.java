#수 찾기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] A = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            int start = 0;
            int end = n-1;
            int count = 0;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (A[mid] > target) {
                    end = mid - 1;
                } else if (A[mid] < target) {
                    start = mid + 1;
                } else {
                    count++;
                    break;
                }
            }
            System.out.println(count);
        }
    }
}
