#두 배열의 합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class B2143 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		int n = Integer.parseInt(br.readLine());
		long[] inputA = new long[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			inputA[i] = Long.parseLong(st.nextToken());
		}
		
		int m = Integer.parseInt(br.readLine());
		long[] inputB = new long[m];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			inputB[i] = Long.parseLong(st.nextToken());
		}
		
		//1. subA, subB 만들기
		List<Long> subA = new ArrayList<>();
		long sum = 0;
		for(int i=0; i<n; i++) {
			sum = 0;
			for(int j=i; j<n; j++) {
				sum += inputA[j];
				subA.add(sum);
			}
		}
		
		List<Long> subB = new ArrayList<>();
		sum = 0;
		for(int i=0; i<m; i++) {
			sum = 0;
			for(int j=i; j<m; j++) {
				sum += inputB[j];
				subB.add(sum);
			}
		}
		
		//2. sub 정렬
		Collections.sort(subA);
		Collections.sort(subB, Comparator.reverseOrder());
		
		//3. 2 Pointers
		long result = 0;
		int ptA = 0;
		int ptB = 0;
		while(true) {
			long currentA = subA.get(ptA);
			long currentB = subB.get(ptB);
			sum = currentA + currentB;
			if(sum==t) {
				long countA = 0;
				while(ptA<subA.size() && currentA==subA.get(ptA)) {
					countA++;
					ptA++;
				}
				long countB = 0;
				while(ptB<subB.size() && currentB==subB.get(ptB)) {
					countB++;
					ptB++;
				}
				result += countA*countB;
			} else if(sum<t) {
				ptA++;
			} else { //sum>t
				ptB++;
			}
			
			if(ptA==subA.size() || ptB==subB.size())
				break;
		}
		System.out.println(result);
	}

}
