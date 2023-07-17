#사칙연산 유효성 검사

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1233 {

	static int N;
	static char[] tree;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int test_case = 1; test_case <= 10; test_case++) {
			N = Integer.parseInt(br.readLine());
			tree = new char[N+1];
			for (int i=1; i<=N; i++) {
				tree[i] = br.readLine().split(" ")[1].charAt(0);
			}
			//preOrder
			cnt=0;
			preOrder(1);
			if(cnt!=0) System.out.println("#"+test_case+" "+0);
			else System.out.println("#"+test_case+" "+1);
		}
	}

	private static void preOrder(int n) { //부모-좌-우
		if(n*2>N) { //자식(L,R)이 없는 경우 -> 해당 노드는 숫자여야 함
			if(tree[n]=='+' || tree[n]=='-' || tree[n]=='*' || tree[n]=='/') cnt++;
		} else { //자식이 있는 경우 -> 해당 노드는 연산자여야 함, 자식 마저 탐색
			if(tree[n]!='+' && tree[n]!='-' && tree[n]!='*' && tree[n]!='/') cnt++;
			if(n*2<=N) preOrder(n*2);
			if(n*2+1<=N) preOrder(n*2+1);
		}
	}
}
