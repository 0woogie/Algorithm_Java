#전자카트

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5189 {

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
			dfs(1, 0, 0); //n, sum, from
			System.out.println("#"+test_case+" "+result);
		}
	}
	
	private static void dfs(int n, int sum, int from) {
		if(sum>result) return;
		if(n==N) {
			sum += arr[from][0]; //관리 구역을 다 돌고 나서 사무실로 돌아옴
			if(sum<result) result = sum;
			return;
		}
		for(int to=1; to<N; to++) {
			if(!visited[to]) {
				visited[to] = true;
				dfs(n+1, sum+arr[from][to], to);
				visited[to] = false;
			}
		}
	}
}
