#왕실의 기사 대결 (삼성 SW 역량테스트 2023 하반기 오전 1번 문제)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KnightBattle {
	
	static int[] dx = {-1, 0, 1, 0}; //위 오 아 왼
	static int[] dy = {0, 1, 0, -1};
	static Knight[] knights;
	static int L, N;
	static int[][] graph, nawabari;
	static boolean[][] target;
	static boolean[] marking;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		graph = new int[L+1][L+1];
		nawabari = new int[L+1][L+1]; //기사 영역 표시를 위한 그래프
		for(int i=1; i<=L; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=L; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		knights = new Knight[N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			knights[i] = new Knight(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			//영역표시
			for(int a=knights[i].r; a<knights[i].r+knights[i].h; a++) {
				for(int b=knights[i].c; b<knights[i].c+knights[i].w; b++) {
					nawabari[a][b] = i;
				}
			}
		}
		
		for(int q=0; q<Q; q++) { //Q개의 명령 수행
			
			//각 기사들마다 현재 본인이 밀어야 하는 기사들 번호를 저장해두고 나중에 밀기!!!
			target = new boolean[N+1][N+1]; //target[a][b]: a가 b를 나중에 밀기
			marking = new boolean[N+1]; //해당 턴에 move했으면 체크해두기 (중복 명령 수행 방지)
			
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); //기사 번호
			int d = Integer.parseInt(st.nextToken()); //이동 방향	
			Knight knight = knights[num];
			
			if(knight.now<=0) //체스판에서 사라진 기사인 경우
				continue;
			
			//1. 명령 이행 가능한지 확인
			boolean chk = true;
			for(int i=knight.r; i<knight.r+knight.h; i++) {
				for(int j=knight.c; j<knight.c+knight.w; j++) {
					//현재 관심사는 graph[i][j]
					int tx = i + dx[d];
					int ty = j + dy[d];
					if(tx<=0 || tx>L || ty<=0 || ty>L) { //영역을 벗어나는 경우
						chk = false;
						break;
					}
					if(graph[tx][ty]==2) { //벽에 부딪힌 경우
						chk = false;
						break;
					}
					if(nawabari[tx][ty]!=0 && nawabari[tx][ty]!=num) { //다른 기사의 영역인 경우
						//상대 기사 밀어내기 시도 -> true or false 반환
						boolean tmp = crash(d, nawabari[tx][ty]); //미는 방향, 밀리는 기사 번호
						if(!tmp) {
							chk = false;
							break;
						} else {
							target[num][nawabari[tx][ty]] = true; //나중에 밀 기사 저장!!!
						}
					}
				}
				if(!chk) break;
			}
			
			//2. 명령 이행
			if(chk) { //이동해도 된다면
				//target 배열에 저장해뒀던 기사들부터 밀기
				for(int i=1; i<=N; i++) {
					if(target[num][i])
						move(d, i); //이동 방향, 이동할 기사 번호
				}
				//기존 영역 지우기
				for(int i=knight.r; i<knight.r+knight.h; i++) {
					for(int j=knight.c; j<knight.c+knight.w; j++) {
						nawabari[i][j] = 0;
					}
				}
				//현재 영역 표시하기
				knights[num].r = knight.r +dx[d];
				knights[num].c = knight.c + dy[d];
				for(int i=knights[num].r; i<knights[num].r+knight.h; i++) {
					for(int j=knights[num].c; j<knights[num].c+knight.w; j++) {
						nawabari[i][j] = num;
					}
				}
			}
		}
		
		int result = 0;
		//생존한 기사들이 총 받은 대미지의 합을 출력
		for(int i=1; i<=N; i++) {
			Knight knight = knights[i];
			if(knight.now <= 0)
				continue;
			result += knight.k - knight.now;
		}
		System.out.println(result);
	}
	
	static boolean crash(int d, int num) { //미는 방향, 밀리는 기사 번호
		//연쇄적으로 밀리는 기사 있는지 확인 -> 연쇄적인 crash() 모두 확인한 뒤, true or false 결정
		
		Knight knight = knights[num];
		
		boolean chk = true;
		for(int i=knight.r; i<knight.r+knight.h; i++) {
			for(int j=knight.c; j<knight.c+knight.w; j++) {
				//현재 관심사는 graph[i][j]
				int tx = i + dx[d];
				int ty = j + dy[d];
				if(tx<=0 || tx>L || ty<=0 || ty>L) { //영역을 벗어나는 경우
					chk = false;
					continue;
				}
				if(graph[tx][ty]==2) { //벽에 부딪힌 경우
					chk = false;
					continue;
				}
				if(nawabari[tx][ty]!=0 && nawabari[tx][ty]!=num) { //다른 기사의 영역인 경우
					//상대 기사 밀어내기 시도 -> true or false 반환
					boolean tmp = crash(d, nawabari[tx][ty]); //미는 방향, 밀리는 기사 번호
					if(!tmp) {
						chk = false;
					} else {
						target[num][nawabari[tx][ty]] = true; //나중에 밀 기사 저장
					}
				}
			}
		}
		
		//이동 가능 여부 리턴
		if(chk)
			return true;
		else
			return false;
	}
	
	static void move(int d, int num) { //이동 방향, 이동할 기사 번호
		
		if(marking[num])
			return; //이미 이동한 기사인 경우
		
		Knight knight = knights[num];
		
		//해당 기사가 밀 기사부터 밀고 나서 이동하기!!!
		for(int i=1; i<=N; i++) {
			if(target[num][i])
				move(d, i); //이동 방향, 이동할 기사 번호
		}
		//기존 영역 지우기
		for(int i=knight.r; i<knight.r+knight.h; i++) {
			for(int j=knight.c; j<knight.c+knight.w; j++) {
				nawabari[i][j] = 0;
			}
		}
		//현재 영역 표시하기 + 함정 개수 카운트
		int cnt = 0;
		knights[num].r = knight.r +dx[d];
		knights[num].c = knight.c + dy[d];
		for(int i=knights[num].r; i<knights[num].r+knight.h; i++) {
			for(int j=knights[num].c; j<knights[num].c+knight.w; j++) {
				nawabari[i][j] = num;
				if(graph[i][j]==1)
					cnt++;
			}
		}
		//함정 개수만큼 데미지 입음
		knights[num].now -= cnt;
		if(knights[num].now <= 0) { //체스판에서 사라지는 기사
			//영역 지우기
			for(int i=knights[num].r; i<knights[num].r+knight.h; i++) {
				for(int j=knights[num].c; j<knights[num].c+knight.w; j++) {
					nawabari[i][j] = 0;
				}
			}
		}
		//이동 완료 후에는 꼭 표시를 해주자
		marking[num] = true;
	}
}

class Knight {
	int r;
	int c;
	int h;
	int w;
	int k;
	int now;
	
	Knight(int r, int c, int h, int w, int k) {
		this.r = r;
		this.c = c;
		this.h = h;
		this.w = w;
		this.k = k;
		this.now = k;
	}
}

/*
아래 테스트케이스에서 에러가 발생해서 boolean[] marking을 추가함

4 5 13
1 1 0 0
1 0 0 0
1 1 1 1
1 1 0 0
4 1 1 1 5
1 1 1 3 7
2 2 1 1 10
2 3 1 2 5
3 2 1 3 13
2 2
1 2
5 2
1 2
5 3
1 2
5 3
5 0
4 2
2 2
3 1
1 2
1 0
*/
