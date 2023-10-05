#막대기

import java.util.Scanner;
import java.util.Stack;

public class B17608 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Stack<Integer> stack = new Stack<>();
		for(int i=0; i<n; i++) {
			stack.push(sc.nextInt());
		}
		int maxNum = 0;
		int cnt = 0;
		while(!stack.isEmpty()) {
			int now = stack.pop();
			if(now > maxNum) {
				maxNum = now;
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}

//굳이 Stack 쓸 필요가 없긴 하다.
