#장훈이의 높은 선반

import java.util.Scanner;

public class S1486 {

	static int[] arr;
	static int N, B;
	static int result;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			B = sc.nextInt();
			arr = new int[N];
			for(int i=0; i<N; i++) {
				arr[i] = sc.nextInt();
			}
			result = Integer.MAX_VALUE;
			dfs(0, 0); //n, sum
			System.out.println("#"+test_case+" "+(result-B));
		}
	}
	
	private static void dfs(int n, int sum) {
		if(n==N) {
			if(sum>=B) {
				if(sum<result) result = sum;
			}
			return;
		}
		//선택하는 경우
		dfs(n+1, sum+arr[n]);
		//선택하지 않는 경우
		dfs(n+1, sum);
	}
}
