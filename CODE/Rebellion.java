#루돌프의 반란 (삼성 SW 역량테스트 2023 하반기 오후 1번 문제)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Rebellion {
	
	static int N, C, D;
	static Santa[] santas;
	static int[][] graph;
	static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1}; //상우하좌 + 대각선
	static int[] dy = {0, 1, 0, -1, -1, 1, -1, 1};
	static int outNum; //탈락 산타 수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());		
		D = Integer.parseInt(st.nextToken());
		
		graph = new int[N+1][N+1];
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		graph[a][b] = -1; //루돌프 위치 표시
		
		//산타들이 순서대로 이동을 해야함 -> Santa 배열 생성
		santas = new Santa[P+1];
		for(int i=0; i<P; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			santas[p] = new Santa(sx, sy, 0, -1, true); //산타에 대한 정보 저장
			graph[sx][sy] = p; //게임판에 산타 번호 표시
		}
		
		for(int m=1; m<=M; m++) { //게임은 M턴간 진행
			
			//0. 만약 P 명의 산타가 모두 게임에서 탈락하게 된다면 그 즉시 게임 종료
			if(outNum==P)
				break;
			
			//1. 루돌프의 이동
			//가장 가까운 산타 찾기 - 산타들과의 거리를 계산해서 list에 담기
			//list에 저장되는 데이터: 산타 넘버, 산타와의 거리, r, c (거리가 같으면 r, c 순으로 우선순위)
			List<Target> list = new ArrayList<>();
			for(int i=1; i<=P; i++) {
				Santa santa = santas[i];
				if(!santa.status) //탈락한 산타인 경우
					continue;
				int dist = getDistance(a, b, santa.x, santa.y);
				list.add(new Target(i, dist, santa.x, santa.y));
			}
			Collections.sort(list);
			Target target = list.get(0); //타켓 산타 결정
			//8방향 중 가장 가까워지는 방향으로 한 칸 돌진
			int tmp = target.dist; //거리
			int na = -1;
			int nb = -1;
			int nd = -1;
			for(int d=0; d<8; d++) {
				int ta = a + dx[d];
				int tb = b + dy[d];
				if(ta<=0 || ta>N || tb<=0 || tb>N)
					continue;
				if(getDistance(target.x, target.y, ta, tb)<tmp) { //8방향 중 가장 가까워지는 방향 찾기
					tmp = getDistance(target.x, target.y, ta, tb); //갱신!!!
					na = ta;
					nb = tb;
					nd = d;
				}
			}
			//결론적으로 (na, nb)가 루돌프의 이동 위치 -> 이로 인한 충돌, 상호작용 있는지 체크
			if(graph[na][nb]!=0) { //충돌 발생
				//충돌에 대한 함수 호출
				crash(nd, graph[na][nb], m, C); //루돌프의 이동 방향, 산타 넘버, 충돌 시점
			}
			graph[a][b] = 0;
			graph[na][nb] = -1; //루돌프 이동
			a = na;
			b = nb;
			
			//2. 산타의 이동
			for(int p=1; p<=P; p++) {
				Santa santa = santas[p];
				if(!santa.status || santa.down+1>=m) //기절했거나 이미 게임에서 탈락한 산타
					continue;
				//4방향 중 가장 가까워지는 방향으로 한 칸 돌진
				int tmp2 = getDistance(a, b, santa.x, santa.y); //거리
				int nx = -1;
				int ny = -1;
				int ndir = -1;
				for(int d=0; d<4; d++) {
					int tx = santa.x + dx[d];
					int ty = santa.y + dy[d];
					if(tx<=0 || tx>N || ty<=0 || ty>N) //게임판 밖인 경우
						continue;
					if(graph[tx][ty]>0) //다른 산타가 있는 칸인 경우
						continue;
					if(getDistance(a, b, tx, ty)<tmp2) { //4방향 중 가장 가까워지는 방향 찾기
						tmp2 = getDistance(a, b, tx, ty); //갱신!!!
						nx = tx;
						ny = ty;
						ndir = d;
					}
				}
				if(ndir != -1) { //산타가 움직이는 경우
					//결론적으로 (nx, ny)가 산타의 이동 위치 -> 이로 인한 충돌, 상호작용 있는지 체크
					if(graph[nx][ny]==-1) { //충돌 발생
						//충돌에 대한 함수 호출 전에 충돌 상황에서의 산타 위치 저장!!!
						graph[santa.x][santa.y] = 0;
						santa.x = nx;
						santa.y = ny;
						//충돌에 대한 함수 호출
						crash((ndir+2)%4, p, m, D); //산타의 이동 방향, 산타 넘버, 충돌 시점
					} else { //산타 이동에 따른 수정
						graph[santa.x][santa.y] = 0;
						graph[nx][ny] = p;
						santas[p].x = nx;
						santas[p].y = ny;
					}
				}
			}
			
			//3. 매 턴 이후 아직 탈락하지 않은 산타들에게는 1점씩을 추가로 부여
			for(int p=1; p<=P; p++) {
				if(santas[p].status)
					santas[p].score++;
			}
		}
		
		//게임이 끝났을 때 각 산타가 얻은 최종 점수 출력
		for(int p=1; p<=P; p++) {
			System.out.print(santas[p].score + " ");
		}
	}
	
	static int getDistance(int r1, int c1, int r2, int c2) {
		return (r1-r2)*(r1-r2) + (c1-c2)*(c1-c2);
	}
	
	static void crash(int d, int num, int m, int X) { //루돌프의 이동 방향, 산타 넘버, 충돌 시점, 획득 점수
		santas[num].score += X;
		santas[num].down = m;
		//이와 동시에 산타는 루돌프가 이동해온 방향으로 X 칸 만큼 밀려나게 됩니다.
		int tx = santas[num].x + dx[d]*X;
		int ty = santas[num].y + dy[d]*X;
		//만약 밀려난 위치가 게임판 밖이라면 산타는 게임에서 탈락됩니다.
		if(tx<=0 || tx>N || ty<=0 || ty>N) {
			santas[num].status = false;
			outNum++;
			return;
		}
		//만약 밀려난 칸에 다른 산타가 있는 경우 상호작용이 발생합니다.
		if(graph[tx][ty]!=0) {
			//상호작용에 대한 함수 호출
			interaction(d, graph[tx][ty]); //산타의 이동 방향, 산타 넘버
		}
		//산타 위치 갱신
		graph[tx][ty] = num;
		santas[num].x = tx;
		santas[num].y = ty;
	}
	
	static void interaction(int d, int num) { //산타의 이동 방향, 산타 넘버
		//산타는 이전 산타가 이동해온 방향으로 1칸 만큼 밀려나게 됩니다.
		int tx = santas[num].x + dx[d];
		int ty = santas[num].y + dy[d];
		//만약 밀려난 위치가 게임판 밖이라면 산타는 게임에서 탈락됩니다.
		if(tx<=0 || tx>N || ty<=0 || ty>N) {
			santas[num].status = false;
			outNum++;
			return;
		}
		//만약 밀려난 칸에 다른 산타가 있는 경우 상호작용이 발생합니다.
		if(graph[tx][ty]!=0) {
			//상호작용에 대한 함수 호출
			interaction(d, graph[tx][ty]); //산타의 이동 방향, 산타 넘버
		}
		//산타 위치 갱신
		graph[tx][ty] = num;
		santas[num].x = tx;
		santas[num].y = ty;
	}
}

class Target implements Comparable<Target> { //가장 가까운 산타 후보들
	int num;
	int dist;
	int x;
	int y;
	
	Target(int num, int dist, int x, int y) {
		this.num = num;
		this.dist = dist;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int compareTo(Target o) {
		if(this.dist == o.dist) {
			if(this.x == o.x) {
				return o.y - this.y;
			}
			return o.x - this.x;
		}
		return this.dist - o.dist;
	}
}

class Santa {
	int x;
	int y;
	int score; //산타가 얻은 점수
	int down; //기절한 시각
	boolean status; //탈락여부
	
	Santa(int x, int y, int score, int down, boolean status) {
		this.x = x;
		this.y = y;
		this.score = score;
		this.down = down;
		this.status = status;
	}
}
