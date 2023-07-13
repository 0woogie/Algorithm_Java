#N과 M (1)

import java.util.Scanner;

public class B15649 {
	public static int[] result;
	public static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		result = new int[m];
		visited = new boolean[n];
		dfs(n, m, 0);
	}
	
	static void dfs(int n, int m, int depth) {
		if(depth==m) {
			for(int value : result)
				System.out.print(value + " ");
			System.out.println();
			return;
		}
		for(int i=0; i<n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				result[depth] = i+1;
				dfs(n, m, depth+1);
				visited[i] = false;
			}
		}
	}
}

//참고 - https://st-lab.tistory.com/114
