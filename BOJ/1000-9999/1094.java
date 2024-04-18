#막대기

import java.util.Scanner;

public class B1094 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int X  = sc.nextInt();
		int bar = 64;
		int result = 0;
		while(X>0) {
			if(bar > X) {
				bar /= 2;
			} else {
				X -= bar;
				result++;
			}
		}
		System.out.println(result);
	}
}
