#부분합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1806 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int[] arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		int result = Integer.MAX_VALUE;
		int start = 0, end = 0;
		int total = arr[0];
		while(end < n) {
			if(total>=s) {
				if(end-start+1 < result) result = end-start+1;
				total -= arr[start++];
			} else {
				total += arr[++end];
			}
		}
		if(result==Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(result);
	}
}

//2003번과 유사, 조금 더 어려움
