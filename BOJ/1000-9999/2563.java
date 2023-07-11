#색종이

import java.util.Scanner;

public class B2563 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] arr = new int[100][100];
		
		int result = 0;
		for(int t=0; t<n; t++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			for(int i=x; i<x+10; i++) {
				for(int j=y; j<y+10; j++) {
					if(arr[i][j]==0) {
						result++;
						arr[i][j] = 1;
					}
				}
			}
		}	
		System.out.println(result);
	}
}
