#부분수열의 합

import java.util.Scanner;

public class B1182 {
	static int N, S;
	static int[] arr;
	static int result;
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		N = sc.nextInt();
		S = sc.nextInt();
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		result = 0;
		dfs(0, 0, 0); //n, sum
		System.out.println(result);
	}

	private static void dfs(int n, int sum, int cnt) { //크기가 양수인 부분수열이므로 변수 cnt를 이용해서 check
		if(n==N) {
			if(sum==S && cnt!=0) result++;
			return;
		}
		//선택하는 경우
		dfs(n+1, sum+arr[n], cnt+1);
		//선택하지 않는 경우
		dfs(n+1, sum, cnt);
	}
}
