#동전 2

import java.util.Arrays;
import java.util.Scanner;

public class B2294 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] D = new int[k+1];
		Arrays.fill(D, Integer.MAX_VALUE);
		int[] coins = new int[n];
		for(int i=0; i<n; i++) {
			coins[i] = sc.nextInt();
			if(coins[i]<=k)
				D[coins[i]] = 1;
		}
		for(int i=1; i<=k; i++) {
			for(int j=0; j<n; j++) {
				if(i-coins[j]<=0)
					continue;
				if(D[i-coins[j]] != Integer.MAX_VALUE)
					D[i] = Math.min(D[i], D[i-coins[j]]+1);
			}
		}
		if(D[k]==Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(D[k]);
	}
}

//살짝 다른 방식 - https://velog.io/@sungjin0757/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%B0%B1%EC%A4%80-2294%EB%B2%88-%EB%8F%99%EC%A0%842-JAVA
