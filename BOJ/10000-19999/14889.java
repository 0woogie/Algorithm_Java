#스타트와 링크

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14889 {
	
	static int N;
	static int[][] arr;
	static boolean[] visited;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
		dfs(0, 0); //n, cnt
		System.out.println(result);
	}

	private static void dfs(int n, int cnt) {
		if(n==N) {
			if(cnt==N/2) {
				//각 팀마다 총 능력치 계산하고 두 팀의 능력치 차이와 result를 비교하고 갱신
				calculateGap();
			}
			return;
		}
		//선택하는 경우
		visited[n] = true;
		dfs(n+1, cnt+1);
		visited[n] = false;
		//선택하지 않는 경우
		dfs(n+1, cnt);
	}
	
	private static void calculateGap() {
		int start = 0;
		int link = 0;
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				if(visited[i]==true && visited[j]==true) {
					start += arr[i][j] + arr[j][i];
				}
				else if(visited[i]==false && visited[j]==false) {
					link += arr[i][j] + arr[j][i];
				}
			}
		}
		int gap = Math.abs(start-link);
		if(gap<result) result = gap;
	}
}
