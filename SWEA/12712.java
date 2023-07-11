#파리퇴치3

import java.util.Scanner;

public class S12712 {
  
	public static int[][] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			arr = new int[n][n];
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++)
					arr[i][j] = sc.nextInt();
			int result = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					int tmp1 = type1(i, j, n, m);
					int tmp2 = type2(i, j, n, m);
					if(tmp1 > result) result = tmp1;
					if(tmp2 > result) result = tmp2;
				}
			}
			System.out.println("#" + test_case + " " + result);
		}
	}
	
	public static int type1(int x, int y, int n, int m) {
		int total = 0;
		total += arr[x][y];
		int[] dx = {0,1,0,-1};
		int[] dy = {1,0,-1,0};
		for(int i=0; i<4; i++) {
			for(int j=1; j<m; j++) {
				int nx = x + dx[i]*j;
				int ny = y + dy[i]*j;
				if(nx>=0 && nx<n && ny>=0 && ny<n) {
					total += arr[nx][ny];
				}
			}
		}
		return total;
	}
	
	public static int type2(int x, int y, int n, int m) {
		int total = 0;
		total += arr[x][y];
		int[] dx = {-1,1,1,-1};
		int[] dy = {1,1,-1,-1};
		for(int i=0; i<4; i++) {
			for(int j=1; j<m; j++) {
				int nx = x + dx[i]*j;
				int ny = y + dy[i]*j;
				if(nx>=0 && nx<n && ny>=0 && ny<n) {
					total += arr[nx][ny];
				}
			}
		}
		return total;
	}

}
