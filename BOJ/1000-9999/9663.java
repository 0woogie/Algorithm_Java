#N-Queen

import java.util.Scanner;

public class B9663 {
	
	static int N;
	static int cnt;
	static int[][] arr;
	static boolean[] visited;
	static int[] dx = {-1,-1,1,1};
	static int[] dy = {-1,1,-1,1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N][N];
		visited = new boolean[N];
		dfs(0); //depth
		System.out.println(cnt);
	}
	
	private static void dfs(int depth) {
		if(depth==N) {
			cnt++;
			return;
		}
		for(int i=0; i<N; i++) {
			if(!visited[i]) { //해당되는 열에 아직 다른 퀸이 놓이지 않았을 경우
				if(checkDiagonal(depth, i)) { //대각선 어느 곳에도 다른 퀸이 없을 경우
					visited[i] = true; //해당 열을 점거
					arr[depth][i] = 1; //다른 퀸이 대각선 체크를 하는 것에 사용하기 위해 체스판에도 퀸의 위치를 표시
					dfs(depth+1); //재귀 호출
					arr[depth][i] = 0;
					visited[i] = false;
				}
			}
		}
	}
	
	private static boolean checkDiagonal(int x, int y) {
		//대각선 체크
		for(int d=0; d<4; d++) {
			int nx = x;
			int ny = y;
			while(true) {
				int tx = nx + dx[d];
				int ty = ny + dy[d];
				//범위 밖으로 나가면 break
				if(tx<0 || tx>=N || ty<0 || ty>=N) break;
				//다른 퀸을 만나면 false 리턴
				if(arr[tx][ty]==1) return false;
				nx = tx;
				ny = ty;
			}
		}
		return true; //대각선 어느 곳에도 다른 퀸이 없을 경우
	}
}
