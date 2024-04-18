#테트로미노

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14500 {
	
	static int N, M;
	static int[][] graph;
	static boolean[][] visited;
	static int result;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, graph[i][j]); //x, y, cnt, sum
				visited[i][j] = false;
				
				combi(i, j, 0, 0, graph[i][j]); //x, y, cnt, start, sum
			}
		}
		System.out.println(result);
	}
	
	static void dfs(int x, int y, int cnt, int sum) {
		if(cnt==4) {
			result = Math.max(result, sum);
			return;
		}
		for(int d=0; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(nx<0 || nx>=N || ny<0 || ny>=M)
				continue;
			if(visited[nx][ny])
				continue;
			visited[nx][ny] = true;
			dfs(nx, ny, cnt+1, sum+graph[nx][ny]);
			visited[nx][ny] = false;
		}
	}
	
	static void combi(int x, int y, int cnt, int start, int sum) { //인접한 4칸 중 3칸 고르기 (ㅗ, ㅓ, ㅏ, ㅜ)
		if(cnt==3) {
			result = Math.max(result, sum);
			return;
		}
		for(int d=start; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(nx<0 || nx>=N || ny<0 || ny>=M)
				continue;
			combi(x, y, cnt+1, d+1, sum+graph[nx][ny]);
		}
	}
}

//참고링크1 - https://developer-u.tistory.com/103
//참고링크2 - https://velog.io/@dlsrjsdl6505/%EB%B0%B1%EC%A4%80-14500-%ED%85%8C%ED%8A%B8%EB%A1%9C%EB%AF%B8%EB%85%B8-%EC%9E%90%EB%B0%94
