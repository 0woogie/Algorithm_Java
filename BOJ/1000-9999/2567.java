#색종이 - 2

import java.util.Scanner;

public class B2567 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] arr = new int[101][101];
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		
		for(int t=0; t<n; t++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			for(int i=x; i<x+10; i++)
				for(int j=y; j<y+10; j++)
					arr[i][j] = 1;
		}
		
		int result = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(arr[i][j]==1) { //검은색 색종이 영역인 경우
					for(int k=0; k<4; k++) { //주변(상,하,좌,우)의 0 개수 세기
						if(arr[i+dx[k]][j+dy[k]]==0) result++;
					}
				}
			}
		}
		System.out.println(result);

	}

}
