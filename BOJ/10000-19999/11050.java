#이항 계수 1

import java.util.Scanner;

public class B11050 {

	static int[][] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		dp = new int[N+1][K+1]; //dp[n][k] = nCk 값
		System.out.println(Combi(N, K));
	}
	
	static int Combi(int n, int k) {
		if(k==0 || n==k)
			return 1;
		if(dp[n][k]!=0)
			return dp[n][k];
		return dp[n][k] = Combi(n-1, k-1) + Combi(n-1, k); //조합의 성질(파스칼의 삼각형)
	}

}

//탑다운 방식의 DP 이용
