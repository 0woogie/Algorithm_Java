#햄버거 다이어트

import java.util.Scanner;

public class S5215 {

	static int result;
	static int N, L;
	static int[][] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt(); //재료의 수
			L = sc.nextInt(); //제한 칼로리
			arr = new int[N][2];
			for(int i=0; i<N; i++) {
				arr[i][0] = sc.nextInt(); //재료 i의 점수
				arr[i][1] = sc.nextInt(); //재료 i의 칼로리
			}
			result = 0;
			dfs(0, 0, 0); //n, score, cal
			System.out.println("#"+test_case+" " + result);
		}
	}

	private static void dfs(int n, int score, int cal) {
		if(cal>L) return;
		if(n==N) {
			if(score>result) result = score;
			return;
		}
		
		//선택하는 경우
		dfs(n+1, score+arr[n][0], cal+arr[n][1]);
		//선택하지 않는 경우
		dfs(n+1, score, cal);
	}

}

/*
//조합을 풀 때는 위의 방식(이진 트리)으로 구현하는 것이 더 적합함. 아래의 방식은 N이 대략 15를 넘어가면 과부하가 온다.
import java.util.Scanner;

public class S5215 {

	static int result;
	static int N, M;
	static int L;
	static int[][] arr;
	static int[][] output;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt(); //재료의 수
			L = sc.nextInt(); //제한 칼로리
			arr = new int[N][2];
			for(int i=0; i<N; i++) {
				arr[i][0] = sc.nextInt(); //재료 i의 점수
				arr[i][1] = sc.nextInt(); //재료 i의 칼로리
			}
			result = 0;
			for(int i=1; i<=N; i++) { //재료 N개 중에서 1개 뽑는 경우 ~ N개 뽑는 경우까지
				M = i;
				output = new int[M][2]; //뽑은 재료 M개의 점수와 칼로리 저장을 위한 배열 output
				dfs(0, 0); //depth, start
			}
			System.out.println("#"+test_case+" " + result);
		}
	}

	static void dfs(int depth, int start) {
		if(depth==M) { //종료 조건(M개를 뽑았을 때)
			//재료의 점수 합과 칼로리 합 계산
			int score = 0;
			int cal = 0;
			for(int i=0; i<M; i++) {
				score += output[i][0];
				cal += output[i][1];
			}
			//제한 칼로리 이하면서 현재 저장된 최고 점수보다 높으면 갱신
			if(cal<=L && score>result) result = score;
			return;
		}
		for(int i=start; i<N; i++) {
			output[depth][0] = arr[i][0];
			output[depth][1] = arr[i][1];
			dfs(depth+1, i+1);
		}
	}

}
*/
