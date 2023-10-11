#게임

import java.util.Scanner;

public class B1072 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long x = sc.nextInt();
		long y = sc.nextInt();
		long z = y*100/x;
		
		if(z>=99) {
			System.out.println(-1);
		} else {
			long s = 1;
			long e = x;
			long result = -1;
			while(s<=e) {
				long mid = (s+e)/2;
				long tmp = (y+mid)*100/(x+mid);
				if(tmp>z) {
					result = mid;
					e = mid - 1;
				} else {
					s = mid + 1;
				}
			}
			System.out.println(result);
		}
	}
}

//승률이 99 이상인 경우 더 이상 승률이 증가하지 않음
//파라메트릭 서치 문제, 승률이 98 -> 99 바뀌는 구체적인 예시를 생각해보면 e를 x로 두게 됨 
