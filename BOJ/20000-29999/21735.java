#눈덩이 굴리기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B21735 {

	static int N, M;
	static int[] arr;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		result = 0;
		dfs(0, 1, 0); //현재 굴린 시간(m), 현재 덩어리 크기(size), 현재 위치(now)
		System.out.println(result);
	}
	
	private static void dfs(int m, int size, int now) {
		if(m==M) { //M초 동안 굴렸으면 종료 (종료조건1)
			if(size>result) result = size;
			return;
		}
		if(now+2>N) { //M초가 되지 않았더라도 경로가 끝이 나면 종료 (종료조건2)
			if(size>result) result = size;
			if(now+1==N) {
				if(size+arr[now+1]>result) result = size+arr[now+1];
			}
			return;
		}
		//밀기
		dfs(m+1, size+arr[now+1], now+1);
		//던지기
		dfs(m+1, size/2+arr[now+2], now+2);
	}
}
