#N과 M (2)

import java.util.Scanner;

public class B15650 {
	
	public static int[] result;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		result = new int[m];
		dfs(n, m, 0, 0);
	}
	
	static void dfs(int n, int m, int depth, int start) {
		if(depth==m) {
			for(int value : result)
				System.out.print(value + " ");
			System.out.println();
			return;
		}
		for(int i=start; i<n; i++) {
			result[depth] = i+1;
			dfs(n, m, depth+1, i+1);
		}
	}
}

//순열 문제(15649번)에서 달라진 부분은 dfs를 재귀 호출할 때마다 다음 시작 지점(변수 start)을 넘겨주는 것 밖에 없음
