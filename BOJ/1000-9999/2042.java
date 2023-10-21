#구간 합 구하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2042 {

	static long[] nums;
	static long[] tree;
	static int N, S;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		nums = new long[N];
		for (int i=0; i<N; i++) {
            		nums[i] = Long.parseLong(br.readLine());
        	}
		
		//Indexed Tree 사용
		S = 1;
		while(S<N) {
			S *= 2;
		}
		
		tree = new long[2*S];
		
		init(); //Init (Bottom-Up)
		
		//M+K번 만큼 연산 수행
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if(a==1) { //Update (Top-Down)
				update(1, S, 1, b, c-tree[S+b-1]);
			} else { //Query (Top-Down)
				System.out.println(query(1, S, 1, b, (int) c));
			}
		}
	}
	
	static void init() {
		//리프노드 초기화
		for(int i=0; i<N; i++) {
			tree[S+i] = nums[i];
		}
		//내부노드 초기화
		for(int i=S-1; i>=1; i--) {
			tree[i] = tree[i*2] + tree[i*2+1];
		}
	}
	
	static void update(int left, int right, int node, int target, long diff) {
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
	
	static long query(int left, int right, int node, int queryLeft, int queryRight) {
		if (queryRight<left || right<queryLeft) {
            		return 0;
        	} else if(queryLeft<=left && right<=queryRight) {
        		return tree[node];
        	} else {
        		int mid = (left+right)/2;
        		return query(left, mid, node*2, queryLeft, queryRight) + query(mid+1, right, node*2+1, queryLeft, queryRight);
        	}
	}

}

//int, long으로 인한 런타임 에러(NumberFormat) 주의!!!
