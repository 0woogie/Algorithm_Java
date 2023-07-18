#최소 생산 비용

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5209 {

	static int[][] arr;
	static boolean[] visited;
	static int N;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visited = new boolean[N];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = Integer.MAX_VALUE;
			dfs(0, 0); //depth, sum
			System.out.println("#"+test_case+" "+result);
		}
	}
	
	private static void dfs(int depth, int sum) {
		if(sum>result) return; //지금까지의 생산 비용이 result보다 큰 경우 재귀를 계속해봐야 최소 생산 비용이 나오는 경우가 아님 -> 불필요한 경로 차단
		if(depth==N) {
			if(sum<result) result = sum;
			return;
		}
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(depth+1, sum+arr[depth][i]);
				visited[i] = false;
			}
		}
	}
}

/*
위에서 주석 단 부분 없으면 시간 초과 발생 (N<=15지만 전체 경우를 순열 탐색하므로 N=15면 15^15개의 경우 발생)
백트래킹 알고리즘 - 가능한 모든 경우를 탐색하면서, "불필요한 경로를 조기에 차단"하여 경우의 수를 줄여 효율적으로 탐색하는 알고리즘
*/
