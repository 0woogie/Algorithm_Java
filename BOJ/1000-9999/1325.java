#효율적인 해킹

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1325 {
	
	static int n;
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		list = new ArrayList[n+1];
		for(int i=0; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			//a가 b를 신뢰한다 -> b를 통해 a를 해킹할 수 있다.
			list[s].add(e);
		}
		//1. 1~N번 노드까지 각각 신뢰하는 노드를 탐색
		//bfs 탐색하면서 첫 방문시 탐색노드 위치 빈도 수 배열(룩업테이블) ++
		//노드수 및 연결이 많을 때 dfs 탐색시 => 재귀호출제한 또는 메모리초과
		result = new int[n+1];
		for(int i=1; i<=n; i++) {
			visited = new boolean[n+1];
			visited[i] = true;
			BFS(i);
		}
		//2. 빈도수 최대값 찾기
		int maxNum = 0;
		for(int i=1; i<=n; i++) {
			maxNum = Math.max(maxNum, result[i]);
		}
		//3. 최대값을 갖는 노드 오름차순으로 출력
		for(int i=1; i<=n; i++) {
			if(result[i]==maxNum) System.out.print(i+" ");
		}
	}
	
	private static void BFS(int s) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int v : list[now]) {
				if(!visited[v]) {
					result[v] ++;
					visited[v] = true;
					queue.add(v);
				}
			}
		}
	}
}

//시간 초과 엄청 났던 문제..리뷰 필요...
