#수들의 합 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2003 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n+1]; //OutOfIndex를 방지하기 위해 여유 공간 설정 (arr[n]은 수들의 합에 영향을 주지 않는 값 -> 0)
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		int s = 0, e = 0;
		int total = arr[0];
		int result = 0;
		while(e<n) {
			if(total==m) {
				result++;
				total += arr[++e];
			} else if(total<m) {
				total += arr[++e];
			} else {
				total -= arr[s++];
			}
		}
		System.out.println(result);
	}
}
