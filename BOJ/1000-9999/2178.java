#미로 탐색

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B2178 {
	
	static int n, m;
	static int[][] graph;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		graph = new int[n][m];
		for(int i=0; i<n; i++) {
			String s = sc.next();
			for(int j=0; j<m; j++) {
				graph[i][j] = s.charAt(j) - '0';
			}
		}
		BFS(0,0);
		System.out.println(graph[n-1][m-1]);
	}

	private static void BFS(int a, int b) {
		Queue<Location> queue = new LinkedList<>();
		queue.add(new Location(a,b));
		while(!queue.isEmpty()) {
			Location now = queue.poll();
			int x = now.x;
			int y = now.y;
			for(int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				if(graph[nx][ny]==1) {
					graph[nx][ny] += graph[x][y];
					queue.add(new Location(nx,ny));
				}
			}
		}
	}
}

class Location {
	int x;
	int y;
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

/*
문자열 -> 문자 -> 숫자
graph[i][j] = s.charAt(j) - '0';
*/
/*
Queue에 좌표(x,y) 저장하는 또 다른 방법
Queue<int[]> q = new LinkedList<>();
q.add(new int[] {x,y});
*/
