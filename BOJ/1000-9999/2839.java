#설탕 배달

import java.util.Scanner;

public class B2839 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int cnt = 0;
		while(n>0) {
			if(n%5==0) {
				cnt += n/5;
				n = 0;
			} else {
				n -= 3;
				cnt++;
			}
		}
		if(n==0) System.out.println(cnt);
		else System.out.println(-1);
	}
}

//BOJ-14916번과 유사한 문제
/*
[다른 풀이 - DP]
public class B2839 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		if(N==3 || N==5)
			System.out.println(1);
		else if(N==4)
			System.out.println(-1);
		else {
			int[] D = new int[N+1];
			Arrays.fill(D, Integer.MAX_VALUE);
			D[3] = 1;
			D[5] = 1;
			for(int i=6; i<=N; i++) {
				if(D[i-5]!=Integer.MAX_VALUE)
					D[i] = D[i-5] + 1;
				if(D[i-3]!=Integer.MAX_VALUE)
					D[i] = Math.min(D[i], D[i-3]+1);
			}
			if(D[N]==Integer.MAX_VALUE)
				System.out.println(-1);
			else
				System.out.println(D[N]);
		}
	}
}
*/
