#도영이가 만든 맛있는 음식

import java.util.Scanner;

public class B2961 {

	static int result;
	static int N, M;
	static int[][] arr;
	static int[][] output;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N][2];
		for(int i=0; i<N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}
		result = 999_999_999;
		//재료 n개 중에서 1개 뽑는 경우 ~ n개 뽑는 경우까지
		for(int i=1; i<=N; i++) {
			M = i;
			output = new int[M][2];
			dfs(0, 0); //depth, start
		}
		System.out.println(result);
	}

	static void dfs(int depth, int start) {
		//종료 조건
		if(depth==M) {
			//신맛과 쓴맛 차이 계산 후 result 값 갱신
			int sin = output[0][0];
			int sseun = output[0][1];
			for(int i=1; i<M; i++) {
				sin *= output[i][0];
				sseun += output[i][1];
			}
			int tmp = Math.abs(sin-sseun);
			if(tmp<result) result = tmp;
			return;
		}
		for(int i=start; i<N; i++) {
			output[depth][0] = arr[i][0];
			output[depth][1] = arr[i][1];
			dfs(depth+1, i+1);
		}
	}
}
