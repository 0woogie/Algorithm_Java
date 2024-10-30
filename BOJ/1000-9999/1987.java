#알파벳

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1987 {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int R, C;
	static int[][] board;
	static boolean[] visited;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		visited = new boolean[26]; //알파벳 방문여부 체크
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) {
				board[i][j] = s.charAt(j) - 'A';
			}
		}
		visited[board[0][0]] = true;
		dfs(0, 0, 1); //x, y, total
		System.out.println(answer);
	}
	
	static void dfs(int x, int y, int total) {
		answer = Math.max(answer, total);
		
		for(int d=0; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(nx<0 || nx>=R || ny<0 || ny>=C) continue;
			if(!visited[board[nx][ny]]) {
				visited[board[nx][ny]] = true;
				dfs(nx, ny, total+1);
				visited[board[nx][ny]] = false;
			}
		}
	}
}

//처음에 BFS & 알파벳 경로를 큐에 담아서 넘기는 식으로 구현 -> 메모리 초과
