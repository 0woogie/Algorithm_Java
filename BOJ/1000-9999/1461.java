#도서관

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1461 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int result = 0;
		int idx1 = 0;
		//1. 음수 M개씩 묶어서 계산
		while(true) {
			int cnt = 0;
			int tmp = 0;
			for(int i=0; i<M; i++) {
				if(idx1>=N) break;
				if(arr[idx1]<0) {
					tmp = Math.max(tmp, Math.abs(arr[idx1]));
					cnt++;
					idx1++;
				} else {
					break;
				}
			}
			result += tmp*2;
			if(cnt!=M) break;
		}
		int idx2 = N-1;
		//2. 양수 M개씩 묶어서 계산
		while(true) {
			int cnt = 0;
			int tmp = 0;
			for(int i=0; i<M; i++) {
				if(idx2<idx1) break;
				if(arr[idx2]>0) {
					tmp = Math.max(tmp, Math.abs(arr[idx2]));
					cnt++;
					idx2--;
				} else {
					break;
				}
			}
			result += tmp*2;
			if(cnt!=M) break;
		}
		//3. 음수, 양수 중 절댓값 큰 쪽 편도만 계산하기
		int num = Math.max(Math.abs(arr[0]), Math.abs(arr[N-1]));
		result -= num;
		System.out.println(result);
	}
}

//다른풀이(PriorityQueue 활용) - https://steady-coding.tistory.com/279
