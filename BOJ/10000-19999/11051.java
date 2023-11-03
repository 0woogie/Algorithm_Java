#이항 계수 2

import java.util.Scanner;

public class B11051 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        	int N = sc.nextInt();
        	int K = sc.nextInt();
        
	        int[][] dp = new int[N + 1][N + 1]; //dp[n][k] = nCk 값
	        for (int i = 1; i <= N; i++) {
	        	dp[i][0] = 1;
	        	dp[i][i] = 1;
	        }
	        for (int i = 1; i <= N; i++) {
	            for (int j = 1; j < i; j++) {
	            	dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % 10007; //조합의 성질(파스칼의 삼각형)
	            }
	        }
	        System.out.println(dp[N][K]);
	}
}

//바텀업 방식의 DP 이용
/*
DP의 중요 포인트 세 가지
1. DP 배열을 어떻게 정의할 것인지 (11행 주석 참고)
2. 관계식 (18행)
3. 기저 세팅 (12~15행)
*/
