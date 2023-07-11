#파리 퇴치

import java.util.Scanner;

public class S2001 {

	public static int[][] arr;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			arr = new int[n][n];
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++)
					arr[i][j] = sc.nextInt();
			int maxNum = 0;
			for(int i=0; i<=n-m; i++) {
				for(int j=0; j<=n-m; j++) {
					int temp = getNum(i, j, m);
					if(temp > maxNum) maxNum = temp;
				}
			}
			System.out.println("#" + test_case + " " + maxNum);
		}

	}
	
	public static int getNum(int x, int y, int m) {
		int total = 0;
		for(int i=x; i<x+m; i++)
			for(int j=y; j<y+m; j++)
				total += arr[i][j];
		return total;
	}

}

/*
논리는 같지만 4중 for문 형식이 더 깔끔하다.

int maxSum = 0;
for (int i = 0; i <= N - M; i++) {
	for (int j = 0; j <= N - M; j++) {
		int sum = 0;
		for (int x = 0; x < M; x++) {
			for (int y = 0; y < M; y++) {
				sum += map[i + x][j + y];
			}
		}
		maxSum = Math.max(sum, maxSum);
	}
}

*/
