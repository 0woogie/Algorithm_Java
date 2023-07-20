#최소 스패닝 트리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1197 {

	static int[] parent;
	static PriorityQueue<Edge> queue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		parent = new int[v+1];
		for(int i=1; i<=v; i++)
			parent[i] = i;
		queue = new PriorityQueue<>();
		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			queue.add(new Edge(a, b, c));
		}
		int vNum = 1;
		int result = 0;
		while(vNum<v) {
			Edge now = queue.poll();
			//a와 b가 같으면 무시, 다른 집합이면 union하고 노드 개수, 가중치 cnt
			if(find(now.a)!=find(now.b)) {
				union(now.a, now.b);
				vNum++;
				result += now.c;
			}
		}
		System.out.println(result);
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a>b) parent[a] = b;
		else parent[b] = a;
	}
	
	private static int find(int x) {
		if(parent[x]!=x)
			parent[x] = find(parent[x]);
		return parent[x];
	}

}

class Edge implements Comparable<Edge> {
	int a;
	int b;
	int c;
	Edge(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	@Override
	public int compareTo(Edge o) {
		return this.c - o.c;
	}
}

//가중치를 기준으로 자동 정렬을 위해 PriorityQueue 자료구조 사용
//객체 간 비교를 위해 Comparable 사용
