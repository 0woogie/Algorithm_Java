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
