#ATM

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        int[] S = new int[n]; //합 배열
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        for (int i = 1; i < n; i++) { //삽입 정렬
            int now = i;
            for (int j = i - 1; j >= 0; j--) {
                if (A[now] < A[j]) {
                    int temp = A[now];
                    A[now] = A[j];
                    A[j] = temp;
                    now = j;
                } else {
                    break;
                }
            }
        }
        S[0] = A[0];
        for (int i = 1; i < n; i++) {
            S[i] = S[i - 1] + A[i];
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += S[i];
        }
        System.out.println(result);
    }
}

/*
교재의 삽입 정렬 코드
for (int i = 1; i < n; i++) {
  int insert_point = i;
  int insert_value = A[i];
  for (int j = i - 1; j >= 0; j--) {
    if (A[j] < A[i]) {
      insert_point = j + 1;
      break;
    }
    if (j == 0) {
      insert_point = 0;
    }
  }
  for (int j = i; j > insert_point; j--) {
    A[j] = A[j - 1];
  }
  A[insert_point] = insert_value;
}
*/
