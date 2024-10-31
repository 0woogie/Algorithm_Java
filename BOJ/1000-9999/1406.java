#에디터

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B1406 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int N = s.length();
		char[] charArr = s.toCharArray();
		Stack<Character> stack1 = new Stack<>(); //커서 왼쪽 문자
		Stack<Character> stack2 = new Stack<>(); //커서 오른쪽 문자
		for(char c : charArr)
			stack1.push(c);
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			char op = st.nextToken().charAt(0);
			if(op=='L') {
				if(!stack1.isEmpty())
					stack2.push(stack1.pop());
			} else if(op=='D') {
				if(!stack2.isEmpty())
					stack1.push(stack2.pop());
			} else if(op=='B') {
				if(!stack1.isEmpty())
					stack1.pop();
			} else {
				stack1.push(st.nextToken().charAt(0));
			}
		}
		while(!stack1.empty())
			stack2.push(stack1.pop());
        StringBuilder sb = new StringBuilder();
        while (!stack2.empty())
        	sb.append(stack2.pop());
        System.out.println(sb.toString());
	}
}
