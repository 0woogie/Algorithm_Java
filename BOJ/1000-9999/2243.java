#사탕상자

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2243 {
	
	static int Max = 1000000;
	static int S;
	static int[] tree;
	static int A, B, C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		S = 1;
		while(S<Max) {
			S *= 2;
		}
		tree = new int[2*S];
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			if(A==1) {
				B = Integer.parseInt(st.nextToken());
				int target = query(1, S, 1, B);
				System.out.println(target);
				update(1, S, 1, target, -1);
			} else {
				B = Integer.parseInt(st.nextToken());
				C = Integer.parseInt(st.nextToken());
				update(1, S, 1, B, C);
			}
		}
		
	}

	static int query(int left, int right, int node, int target) {
		if(left == right) { //리프 노드(목적지)에 도달한 경우
			return left;
		} else {
			int mid = (left+right)/2;
			if(tree[node*2]>=target) {
				return query(left, mid, node*2, target);
			} else {
				target -= tree[node*2]; //중요한 부분!!!
				return query(mid+1, right, node*2+1, target);
			}
		}
	}
	
	static void update(int left, int right, int node, int target, int diff) {
		if(target<left || right<target) {
			return;
		} else {
			tree[node] += diff;
			if(left != right) {
				int mid = (left+right)/2;
				update(left, mid, node*2, target, diff);
				update(mid+1, right, node*2+1, target, diff);
			}
		}
	}
}
