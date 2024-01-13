#사전

import java.util.Scanner;

public class B1256 {
	
	static final int INF = 1000000001;
	static final int MAX = 101;
	static int[][] dp = new int[MAX][MAX]; //dp[x][y] = x개의 a와 y개의 z로 만들 수 있는 문자열 수
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		
		if(getDP(N, M)<K) {
			System.out.println(-1);
		} else {
			System.out.println(getWord(N, M, K));
		}
	}
	
	static int getDP(int n, int m) {
		//기저에 도착하면 1 리턴
		if(n==0 || m==0)
			return 1;
		//이미 구해놨던 값이라면
		if(dp[n][m]!=0)
			return dp[n][m];
		
		return dp[n][m] = Math.min(getDP(n-1,m)+getDP(n,m-1), INF); //dp 배열 터지는 것 방지하기 위해 INF 값 설정
	}
	
	static String getWord(int N, int M, int K) {
		String ret = "";
		if(N==0) {
			for(int i=0; i<M; i++)
				ret += 'z';
			return ret;
		}
		if(M==0) {
			for(int i=0; i<N; i++)
				ret += 'a';
			return ret;
		}
		//a가 맨 앞에 붙고 나머지 N-1, M로 만들 수 있는 경우의 수
		int aFrontCnt = getDP(N-1, M);
		//맨 앞에 a를 붙여야 하는 경우
		if(K <= aFrontCnt) {
			ret = 'a' + getWord(N-1, M, K);
		}
		//맨 앞에 z를 붙여야 하는 경우
		else {
			ret = 'z' + getWord(N, M-1, K-aFrontCnt);
		}
		return ret;
	}
}

/*
1. DP 배열 정의: DP[n][m] - "a" n개, "z" m개를 사용할 때 경우의 수
2. 점화식: DP[n][m] = DP[n-1][m] + DP[n][m-1]
	- 만약 "a" n개와 "z" m개로 만들 수 있는 문자열에 대해 살펴본다고 했을 때 (DP[n][m])
 	- Case 1: 맨 앞 글자가 a로 시작 + "a" n-1개와 "z" m개 (DP[n-1][m])
 	- Case 2: 맨 앞 글자가 z로 시작 + "a" n개와 "z" m-1개 (DP[n][m-1])
3. 기저 세팅: DP[0][m] = 1, DP[n][0] = 1
*/
