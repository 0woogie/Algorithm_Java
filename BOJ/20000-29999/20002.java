#사과나무

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

 public class B20002 {
	public static void main(String[] args) throws Exception {
		//1. 구간합 구하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] A = new int[N+1][N+1];
		int[][] S = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				S[i][j] = S[i][j-1] + S[i-1][j] - S[i-1][j-1] + A[i][j];
			}
		}
		int result = Integer.MIN_VALUE;
		//2. K를 바꿔가며 최대 구간합 갱신 (K: 1~N)
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N-k+1; i++) {
				for(int j=1; j<=N-k+1; j++) {
					int r = i + k - 1;
					int c = j + k - 1;
					result = Math.max(result, S[r][c] - S[r-k][c] - S[r][c-k] + S[r-k][c-k]);
				}
			}
		}
		System.out.println(result);
	}
}

//2차원배열 구간합 구하기, 유사문제: BOJ-11660
