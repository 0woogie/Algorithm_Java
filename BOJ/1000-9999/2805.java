#나무 자르기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2805 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] tree = new int[n];
		st = new StringTokenizer(br.readLine());
		int low = 0, high = 0;
		int result = 0;
		for(int i=0; i<n; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			if(tree[i]>high) high = tree[i];
		}
		while(low<=high) {
			int h = (low+high)/2;
			long total = 0;
			for(int i=0; i<n; i++) {
				if(tree[i]>h) total += tree[i] - h;
			}
			if(total<m) {
				high = h-1;
			} else {
				result = h;
				low = h+1;
			}
		}
		System.out.println(result);
	}
}

//대표적인 파라메트릭 서치(Parametric Search) 문제
