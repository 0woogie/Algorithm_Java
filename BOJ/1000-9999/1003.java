#피보나치 함수

import java.util.Scanner;

public class B1003 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=0; t<T; t++) {
			int N = sc.nextInt();
			int[][] arr = new int[41][2];
			arr[0][0] = 1;
			arr[0][1] = 0;
			arr[1][0] = 0;
			arr[1][1] = 1;
			for(int i=2; i<=N; i++) {
				arr[i][0] = arr[i-1][0] + arr[i-2][0];
				arr[i][1] = arr[i-1][1] + arr[i-2][1];
			}
			System.out.println(arr[N][0] + " " + arr[N][1]);
		}
	}
}
