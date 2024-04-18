#뱀

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B3190 {
	
	static int[] dx = {0, 1, 0, -1}; //우 하 좌 상
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] graph = new int[N+1][N+1];
		int K = Integer.parseInt(br.readLine());
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = 2; //사과는 2
		}
		int L = Integer.parseInt(br.readLine());
		ArrayList<int[]> list = new ArrayList<>();
		for(int l=0; l<L; l++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			//리스트에 시간, 방향 저장
			if(c=='D')
				list.add(new int[] {a, 1});
			else
				list.add(new int[] {a, -1});
		}
		int x = 1; //머리 위치
		int y = 1; //머리 위치
		int d = 0; //머리 방향
		int time = 1;
		graph[1][1] = 1;
		Queue<int[]> queue = new LinkedList<>(); //뱀을 의미하는 큐
		queue.add(new int[] {1, 1});
		while(true) {
			//1. 뱀 이동
			int nx = x + dx[d];
			int ny = y + dy[d];
			//2-1. 벽을 만나거나 본인 몸에 부딪히면 끝
			if(nx<=0 || nx>N || ny<=0 || ny>N || graph[nx][ny]==1)
				break;
			//2-1. 빈 칸이면 꼬리를 한칸 땡김 (사과를 만나면 꼬리를 땡길 필요 없음)
			if(graph[nx][ny]==0) {
				int[] tail = queue.poll();
				graph[tail[0]][tail[1]] = 0;
			}
			//2-2. 머리 위치 갱신
			graph[nx][ny] = 1;
			queue.add(new int[] {nx, ny});
			x = nx;
			y = ny;
			//3. 이번 시간에 머리 방향 돌려야 하는지 체크
			if(list.size()>0 && time == list.get(0)[0]) {
				int dir = list.get(0)[1];
				d = (d + dir + 4) % 4;
				list.remove(0);
			}
			time++;
		}
		System.out.println(time);
	}
}

//HashMap을 사용하는 방식 - https://velog.io/@kimmjieun/%EB%B0%B1%EC%A4%80-3190%EB%B2%88-%EB%B1%80-Java-%EC%9E%90%EB%B0%94
