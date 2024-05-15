#그림

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1926 {
	
	static int n, m;
	static int[][] graph;
	static boolean[][] visited;
	static int result; //가장 넓은 그림의 넒이
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[n][m];
		visited = new boolean[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(!visited[i][j] && graph[i][j]==1) {
					bfs(i, j);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
		System.out.println(result);
	}
	
	static void bfs(int a, int b) {
		visited[a][b] = true;
		int total = 1;
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {a, b});
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int x = tmp[0];
			int y = tmp[1];
			for(int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx<0 || nx>=n || ny<0 || ny>=m)
					continue;
				if(!visited[nx][ny] && graph[nx][ny]==1) {
					visited[nx][ny] = true;
					total++;
					queue.add(new int[] {nx, ny});
				}
			}
		}
		if(total>result)
			result = total;
	}
}

//유사한 문제: BOJ_2667
