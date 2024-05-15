#스택 2

import java.util.Scanner;
import java.util.Stack;

public class B28278 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer sb = new StringBuffer();
		int N = sc.nextInt();
		Stack<Integer> stack = new Stack<>();
		for(int i=0; i<N; i++) {
			int num = sc.nextInt();
			switch(num) {
			case 1:
				int x = sc.nextInt();
				stack.add(x);
				break;
			case 2:
				if(stack.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(stack.pop()).append("\n");
				break;
			case 3:
				sb.append(stack.size()).append("\n");
				break;
			case 4:
				if(stack.isEmpty())
					sb.append(1).append("\n");
				else
					sb.append(0).append("\n");
				break;
			case 5:
				if(stack.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(stack.peek()).append("\n");
				break;
			}
		}
		System.out.println(sb.toString());
	}
}

//참고링크 - https://velog.io/@gayeong39/%EB%B0%B1%EC%A4%80-28278-%EC%8A%A4%ED%83%9D-2
