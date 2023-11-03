#다리 놓기

import java.util.Scanner;

public class B1010 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=0; t<T; t++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			//M개에서 N개 뽑는 조합 문제
			int[][] dp = new int[M+1][M+1];
			for(int i=1; i<=M; i++) {
				dp[i][0] = 1;
				dp[i][i] = 1;
			}
			for(int i=1; i<=M; i++) {
				for(int j=1; j<i; j++) {
					dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
				}
			}
			System.out.println(dp[M][N]);
		}
	}
}
