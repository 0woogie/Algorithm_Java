#탑

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Node {
	int value;
	int index;
}

public class B2493 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Node> stack1 = new Stack<>();
		for(int i=0; i<n; i++) {
			Node node = new Node();
			node.index = i+1;
			node.value = Integer.parseInt(st.nextToken());
			stack1.push(node);
		}
		Stack<Node> stack2 = new Stack<>();
		int[] result = new int[n+1];
		while(!stack1.isEmpty()) {
			Node a = stack1.pop();
			while(!stack2.isEmpty()) {
				Node b = stack2.pop();
				if(b.value<a.value) {
					result[b.index] = a.index;
				} else {
					stack2.push(b);
					break;
				}
			}
			stack2.push(a);
		}
		for(int i=1; i<n+1; i++) System.out.print(result[i]+" ");
	}
}

/*
//Stack에 int형 배열로 인덱스와 값을 넣어서 구현한 풀이법
public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Stack<int[]> stack = new Stack<>();
		for(int i=1; i<n+1; i++) {
			int top = Integer.parseInt(st.nextToken());
			while(!stack.isEmpty()) {
				if(stack.peek()[1]>top) {
					System.out.print(stack.peek()[0] + " ");
					break;
				}
				stack.pop();
			}
			if(stack.isEmpty()) 
				System.out.print("0 ");
			stack.push(new int[] {i, top});
		}
	}

참고 - https://moonsbeen.tistory.com/204
*/
