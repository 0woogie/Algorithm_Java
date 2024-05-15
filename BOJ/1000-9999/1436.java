#영화감독 숌

import java.util.Scanner;

public class B1436 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int num = 666;
		int cnt = 1;
		while(cnt != N) { //num이 "종말의 수"인지 판단 -> 맞다면 카운트
			num++;
			if(String.valueOf(num).contains("666"))
				cnt++;
		}
		System.out.println(num);
	}
}
