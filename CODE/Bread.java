#코드트리 빵 (삼성 SW 역량테스트 2022 하반기 오후 1번 문제)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bread {
	
	static int[] dx = {-1, 0, 0, 1}; //위 왼 오 아래
	static int[] dy = {0, -1, 1, 0};
	static int[][] graph;
	static boolean[][] visited;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Peon[] peons = new Peon[M+1];
		for(int i=1; i<=M; i++) { //편의점 정보 받기
			st = new StringTokenizer(br.readLine());
			peons[i] = new Peon(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Person[] persons = new Person[M+1];
		for(int i=1; i<=M; i++) {
			persons[i] = new Person(-1, -1); //격자 밖에 있으면 (-1, -1)
		}
		
		int outNum = 0;
		int T = 0;
		ArrayList<int[]> list;
		
		while(true) {
			//0. 모든 사람이 편의점에 도착한다면
			if(outNum==M)
				break;
			
			T++; //시간 경과
			
			list = new ArrayList<>(); //이번 턴 끝날 때 막아둬야 할 곳들 list에 담기 (편의점들)
			
			//1. 격자 위 사람들의 이동
			for(int m=1; m<=M; m++) {
				Person person = persons[m];
				if(person.x==-1 && person.y==-1) //격자 밖 사람인 경우
					continue;
				//목적지 편의점까지의 최단 거리 계산 (이때 제일 처음 움직여야하는 방향 기억해두기)
				int d = getDirection(person.x, person.y, peons[m].x, peons[m].y); //d: 최단 거리를 위해 움직여야하는 방향
				//이동하기, 만약 편의점 도착한다면 편의점 위치 list에 담기
				person.x += dx[d];
				person.y += dy[d];
				if(person.x==peons[m].x && person.y==peons[m].y) {
					list.add(new int[] {person.x, person.y});
					person.x = -1;
					person.y = -1;
					outNum++;
				}
			}
			//모든 사람들의 이동 끝나면 list에 있는 위치들 폐쇄하기
			for(int[] target : list) {
				graph[target[0]][target[1]] = -1;
			}
			
			//2. 격자 밖 사람의 이동
			if(T<=M) { //격자 밖에 아직 사람이 있는 경우 (베이스캠프로 가야하는 인원)
				//T번 편의점
				Peon peon = peons[T]; //현재 목적지가 되는 편의점
				//해당 목적지 기준으로 베이스 캠프 결정
				int[] tmp = getBasecamp(peon.x, peon.y);
				//결정된 베이스 캠프에 T번 사람 이동시키고 해당 위치 막아두기
				persons[T].x = tmp[0];
				persons[T].y = tmp[1];
				graph[tmp[0]][tmp[1]] = -1;
			}
		}
		
		//3. 결과 출력
		System.out.println(T);
	}
	
	static int[] getBasecamp(int a, int b) { //(a, b) 편의점에서 최단거리인 베이스캠프의 위치 리턴
		int[] result = new int[2];
		Queue<int[]> queue = new LinkedList<>();
		visited = new boolean[N+1][N+1];
		visited[a][b] = true; //시작점 방문처리
		queue.add(new int[] {a, b});
		while(!queue.isEmpty()) {
			int length = queue.size();
			boolean chk = false;
			ArrayList<Result> list = new ArrayList<>();
			for(int len=0; len<length; len++) {
				int[] now = queue.poll();
				int x = now[0];
				int y = now[1];
				for(int d=0; d<4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if(nx<=0 || nx>N || ny<=0 || ny>N)
						continue;
					if(visited[nx][ny] || graph[nx][ny]==-1)
						continue;
					if(graph[nx][ny]==1) { //베이스캠프 발견
						chk = true;
						list.add(new Result(nx, ny, null));
					} else {
						visited[nx][ny] = true;
						queue.add(new int[] {nx, ny});
					}
				}
			}
			//만약 이번 턴에 최소 거리 찾았다면
			if(chk) {
				//가장 우선순위 높은 베이스캠프 선택
				Collections.sort(list);
				Result r = list.get(0);
				result[0] = r.x;
				result[1] = r.y;
				return result;
			}
		}
		return result;
	}
	
	static int getDirection(int a, int b, int targetX, int targetY) { //시작점(사람): a, b, 도착점(편의점): targetX, targetY
		int result = -1;
		Queue<Result> queue = new LinkedList<>();
		visited = new boolean[N+1][N+1];
		visited[a][b] = true; //시작점 방문처리
		queue.add(new Result(a, b, ""));
		while(!queue.isEmpty()) {
			Result now = queue.poll();
			int x = now.x;
			int y = now.y;
			String route = now.route;
			for(int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx<=0 || nx>N || ny<=0 || ny>N)
					continue;
				if(visited[nx][ny] || graph[nx][ny]==-1)
					continue;
				if(nx==targetX && ny==targetY) { //편의점 발견
					route += d; //방향 저장
					char c = route.charAt(0);
					return c-'0';
				} else {
					visited[nx][ny] = true;
					queue.add(new Result(nx, ny, route+d));
				}
			}
		}
		return result;
	}
}

class Peon {
	int x;
	int y;
	
	Peon(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Person {
	int x;
	int y;
	
	Person(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Result implements Comparable<Result> {
	int x;
	int y;
	String route;
	
	Result(int x, int y, String route) {
		this.x = x;
		this.y = y;
		this.route = route;
	}

	@Override
	public int compareTo(Result o) {
		if(this.x==o.x) {
			return this.y - o.y;
		}
		return this.x - o.x;
	}
}
