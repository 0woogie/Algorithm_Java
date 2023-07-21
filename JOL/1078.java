#저글링 방사능 오염

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class J1078 {

	static int n, m;
	static int[][] graph;
	static boolean[][] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt(); //열
		m = sc.nextInt(); //행
		graph = new int[m][n];
		visited = new boolean[m][n];
		for(int i=0; i<m; i++) {
			String s = sc.next();
			for(int j=0; j<n; j++) {
				graph[i][j] = s.charAt(j) - '0';
			}
		}
		int a = sc.nextInt(); //열
		int b = sc.nextInt(); //행
		BFS(b-1,a-1); //방사능 확산
		int cnt = 0; //방사능 오염되지 않은 저글링 수 카운트
		int max = 0; //가장 마지막에 오염된 저글링 시간 카운트
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(graph[i][j]==1 && !visited[i][j]) cnt++;
				else if(graph[i][j]>max) max = graph[i][j];
			}
		}
		System.out.println(max+2);
		System.out.println(cnt);
	}
	
	private static void BFS(int a, int b) {
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		Queue<int[]> queue = new LinkedList<int[]>();
		visited[a][b] = true;
		queue.add(new int[] {a,b});
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0];
			int y = now[1];
			for(int d=0; d<4; d++) { //오염된 저글링 상하좌우 방향으로 방사능 확산
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx<0 || nx>=m || ny<0 || ny>=n) continue;
				if(graph[nx][ny]==1 && !visited[nx][ny]) { //오염된 저글링과 인접하고 방사능에 오염되지 않은 학생인 경우
					graph[nx][ny] += graph[x][y];
					visited[nx][ny] = true;
					queue.add(new int[] {nx,ny});
				}
			}
		}
	}

}
