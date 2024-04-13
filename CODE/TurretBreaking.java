#포탑 부수기 (삼성 SW 역량테스트 2023 상반기 오전 1번 문제)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class TurretBreaking {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] graph = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] memo = new int[N][M]; //0. 공격한 시점 적어둘 배열 필요
		int[] dx = {0, 1, 0, -1, -1, -1, 1, 1}; //우 하 좌 상 + 대각선
		int[] dy = {1, 0, -1, 0, -1, 1, -1, 1};
		ArrayList<Turret> list;
		for(int k=1; k<=K; k++) { //k턴 동안 진행됨
			boolean[][] visited = new boolean[N][M]; //공격과 무관한 것들 판별하기 위한 배열
			
			//1. 공격자 선정
			list = new ArrayList<>();
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(graph[i][j]!=0)
						list.add(new Turret(i, j, memo[i][j], graph[i][j]));
				}
			}
			if(list.size()==1) //만약 부서지지 않은 포탑 1개 -> 종료
				break;
			Collections.sort(list); //맨 앞이 공격자, 맨 뒤가 공격대상
			Turret attacker = list.get(0);
			graph[attacker.x][attacker.y] += N+M; //공격력 증가
			attacker.power += N+M;
			memo[attacker.x][attacker.y] = k; //공격 시점 갱신
			visited[attacker.x][attacker.y] = true;
			
			//2. 공격자 공격 -> 3. 포탑 부서짐
			Turret target = list.get(list.size()-1);
			//2-1. 레이저 공격
			boolean chk = false; //레이저 공격 성공 여부
			boolean[][] tmp = new boolean[N][M]; //이번 탐색에서의 방문처리
			String route = ""; //최단경로 (target 지점은 제외한 경로)
			Queue<Route> queue = new LinkedList<>();
			queue.add(new Route(attacker.x, attacker.y, ""));
			tmp[attacker.x][attacker.y]= true; 
			while(!queue.isEmpty()) {
				Route now = queue.poll();
				for(int d=0; d<4; d++) {
					//가장자리에서 반대방향으로 나올 수 있음
					int nx = (now.x + dx[d] + N) % N;
					int ny = (now.y + dy[d] + M) % M;
					//부서진 포탑 위치거나 이미 방문한 경우
					if(graph[nx][ny]==0 || tmp[nx][ny])
						continue;
					if(nx==target.x && ny==target.y) { //도착한 경우
						chk = true;
						route = now.route;
						break;
					} else { //경로 중에 있는 경우
						tmp[nx][ny] = true;
						queue.add(new Route(nx, ny, now.route+d));
					}
				}
				if(chk) break; //공격 경로 찾아내면 탈출
			}
			if(chk) {
				//레이저 공격 실행
				graph[target.x][target.y] -= attacker.power; 
				visited[target.x][target.y]= true; 
				if(graph[target.x][target.y]<=0)
					graph[target.x][target.y]= 0;
				int nx = attacker.x;
				int ny = attacker.y;
				for(int i=0; i<route.length(); i++) {
					int dir = route.charAt(i) - '0';
					nx = (nx + dx[dir] + N) % N;
					ny = (ny + dy[dir] + M) % M;
					graph[nx][ny] -= attacker.power/2;
					visited[nx][ny] = true;
					if(graph[nx][ny]<=0)
						graph[nx][ny]= 0;
				}
			} else {
				//2-2. 포탄 공격
				graph[target.x][target.y] -= attacker.power; 
				visited[target.x][target.y]= true;
				if(graph[target.x][target.y]<=0)
					graph[target.x][target.y]= 0;
				for(int d=0; d<8; d++) {
					int nx = (target.x + dx[d] + N) % N;
					int ny = (target.y + dy[d] + M) % M;
					if(nx==attacker.x && ny==attacker.y)
						continue; //공격자는 영향 x
					if(graph[nx][ny]==0)
						continue; //부서진 탑은 x
					graph[nx][ny] -= attacker.power/2;
					visited[nx][ny] = true;
					if(graph[nx][ny]<=0)
						graph[nx][ny]= 0;
				}
			}
			
			//4. 포탑 정비
			//공격과 무관했던 것들 공격력 1씩 상승 (공격자, 피해자, 부서진 탑 X)
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(graph[i][j]!=0 && !visited[i][j])
						graph[i][j]++;
				}
			}
		}
		
		//5. 남은 포탑 중 가장 강한 포탑 공격력 출력
		int result = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(graph[i][j]>result)
					result = graph[i][j];
			}
		}
		System.out.println(result);
	}
}

class Route {
	int x;
	int y;
	String route;
	
	Route(int x, int y, String route) {
		this.x = x;
		this.y = y;
		this.route = route;
	}
}

class Turret implements Comparable<Turret> {
	int x;
	int y;
	int time; //공격한 시점
	int power;
	
	Turret(int x, int y, int time, int power) {
		this.x = x;
		this.y = y;
		this.time = time;
		this.power = power;
	}

	@Override
	public int compareTo(Turret o) {
		if(this.power==o.power) {
			if(this.time==o.time ) {
				if(this.x+this.y==o.x+o.y) {
					return o.y - this.y;
				}
				return (o.x+o.y) - (this.x+this.y);
			}
			return o.time - this.time;
		}
		return this.power - o.power;
	}
}
