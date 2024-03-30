#퇴사 2

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N+1][2];
		int[] D = new int[N+2];
		
		StringTokenizer st;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=N; i>0; i--) {
			int next = i + arr[i][0]; //다음 날짜
			if(next > N+1) { //일할 수 있는 날짜를 넘어가는 경우
				//일을 하지 못하므로 이전 DP 값을 사용
				D[i] = D[i+1];
			} else { //일할 수 있는 날짜인 경우
				//1. 일하지 않았을 때 (D[i+1])
				//2. 일했을 때 (arr[i][1] + D[next])
				//위 두 경우 중 더 큰 값을 선택
				D[i] = Math.max(D[i+1], arr[i][1] + D[next]);
			}
		}
		
		System.out.println(D[1]);
	}
}

//유사문제 - 백준 14501
//14501번과 달리 N의 크기가 커서 DFS로는 풀 수 없음
