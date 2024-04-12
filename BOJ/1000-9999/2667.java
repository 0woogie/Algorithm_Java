#단지번호붙이기

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class B2667 {
	
	static int n;
	static int cnt;
	static int[][] graph;
	static boolean[][] visited;
	static List<Integer> result;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		graph = new int[n][n];
		visited = new boolean[n][n];
		result = new ArrayList<>();
		for(int i=0; i<n; i++) {
			String s = sc.next();
			for(int j=0; j<n; j++) {
				graph[i][j] = s.charAt(j)-'0';
			}
		}
		int danji = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(graph[i][j]!=0 && !visited[i][j]) {
					cnt = 0;
					DFS(i,j);
					result.add(cnt);
					danji++;
				}
			}
		}
		Collections.sort(result);
		System.out.println(danji);
		for(int i=0; i<danji; i++) {
			System.out.println(result.get(i));
		}
	}
	
	private static void DFS(int x, int y) {
		if(x<0 || x>=n || y<0 || y>=n) return;
		if(graph[x][y]==0 || visited[x][y]) return;
		visited[x][y] = true;
		cnt++;
		DFS(x+1,y);
		DFS(x,y+1);
		DFS(x-1,y);
		DFS(x,y-1);
	}
}

//dx, dy 배열을 이용한 방식으로도 구현할 수 있음
/*
static void DFS(int x, int y) {
	visited[x][y] = true;
	cnt++;
	for(int d=0; d<4; d++) {
		int nx = x + dx[d];
		int ny = y + dy[d];
		if(nx<0 || nx>=N || ny<0 || ny>=N)
			continue;
		if(!visited[nx][ny] && graph[nx][ny]==1)
			dfs(nx, ny);
	}
}
*/
