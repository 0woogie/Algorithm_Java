#달팽이 숫자

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int[][] arr = new int[N][N];
			//n*n 배열 만든 뒤 d = [R,D,L,U] 순서로 n^2 만큼의 수 채우기
			int[] dx = {0, 1, 0, -1};
			int[] dy = {1, 0, -1, 0};
			int direction = 0;
			int x=0, y=0;
			int nx=0, ny=0;
			arr[x][y] = 1;
			for(int i=2; i<=N*N; i++) {
				//벽에 맞으면 방향 바꾸면서 진행
				nx = x + dx[direction];
				ny = y + dy[direction];
				if(nx>=N || nx<0 || ny>=N || ny<0 || arr[nx][ny]!=0) {
					direction = (direction+1)%4;
					nx = x + dx[direction];
					ny = y + dy[direction];
				}
				x = nx;
				y = ny;
				arr[x][y] = i;
			}
			System.out.println("#"+test_case);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println("");
			}
		}
	}

}
