#네트워크 연결

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class B1922 {

	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		parent = new int[N+1]; //Union-Find를 위한 parent 배열
		for(int i=1; i<N+1; i++)
			parent[i] = i;
		int M = Integer.parseInt(br.readLine());
		ArrayList<Edge> list = new ArrayList<>(); //간선 리스트 생성
		StringTokenizer st;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.add(new Edge(a, b, c));
		}
		Collections.sort(list); //간선 정렬 (ArrayList라서 Collections으로 정렬시킴, Edge[]이었다면 Arrays.sort() 사용
		int result = 0;
		int cnt = 0; //연결된 선의 개수 카운트
		for(Edge e : list) {
			//만약 N-1만큼의 간선으로 연결됐다면 MST 완성된 것 -> 탈출
			if(cnt==N-1)
				break;
			if(find(e.a)!=find(e.b)) { //최소 신장 트리에 포함될 수 있는 간선인 경우 (사이클을 만들지 않는 간선인 경우)
				union(e.a, e.b);
				cnt++;
				result += e.c;
			}
		}
		System.out.println(result);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a>b)
			parent[a] = b;
		else
			parent[b] = a;
	}
	
	static int find(int x) {
		if(parent[x]==x)
			return x;
		return parent[x] = find(parent[x]);
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

//최소신장트리 → 크루스칼 알고리즘 or 프림 알고리즘으로 구현 (위 코드는 크루스칼 알고리즘)
