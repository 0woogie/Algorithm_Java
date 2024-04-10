#메이즈 러너 (삼성 SW 역량테스트 2023 상반기 오후 1번 문제)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MazeRunner {
	
	static int outNum; //탈출한 인원 수
	static int[] dx = {-1, 1, 0, 0}; //상하좌우
	static int[] dy = {0, 0, -1, 1};
	static int X, Y; //출구 좌표
	static Runner[] runners;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] maze = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				maze[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		runners = new Runner[M];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			runners[i] = new Runner(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), false, 0);
		}
		//출구 위치 저장 (X, Y)
		st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		maze[X][Y] = -1; //출구는 -1로 표시
		
		for(int k=0; k<K; k++) {
			
			//0. 모든 참가자가 미로를 탈출한 경우
			if(outNum==M)
				break;
			
			//1. 참가자 이동
			for(int m=0; m<M; m++) {
				Runner runner = runners[m];
				if(runner.status) //탈출한 참가자인 경우
					continue;
				int now = getDistance(X, Y, runner.x, runner.y); //현재 거리
				for(int d=0; d<4; d++) { //상하좌우 이동해보기 (상하 우선)
					int tx = runner.x + dx[d];
					int ty = runner.y + dy[d];
					if(tx<=0 || tx>N || ty<=0 || ty>N)
						continue;
					if(maze[tx][ty]>0)
						continue;
					if(maze[tx][ty]==-1) { //출구인 경우
						runners[m].distance++;
						runners[m].status = true;
						outNum++;
						break;
					}
					if(getDistance(X, Y, tx, ty)<now) { //움직일 수 있는 경우
						runners[m].distance++;
						runners[m].x = tx;
						runners[m].y = ty;
						break;
					}
				}
			}
			
			//2. 회전
			//2-1. 정사각형 선택하기 - 한 명 이상의 참가자와 출구를 포함한 가장 작은 정사각형. 가장 작은 크기를 갖는 정사각형이 2개 이상 -> r 작은 것 우선 -> c 작은 것 우선 
			//maze 배열 복사본
			int[][] tmp = new int[maze.length][maze[0].length];
			for(int i=0; i<tmp.length; i++) {
				System.arraycopy(maze[i], 0, tmp[i], 0, maze[0].length);
			}
			int[] result = pickSquare(tmp);
			int a = result[0];
			int b = result[1];
			int n = result[2];
			//2-2. 정사각형 회전시키기 - 시계방향으로 90도 회전, 회전된 벽은 내구도가 1씩 깎임. 이때 runners 전체 탐색하면서 해당 공간에 속하면 위치 갱신
			//회전 공간 만큼의 임시 배열 생성
			int[][] temp = new int[n][n];
			//현재 공간 한 행씩 임시 배열에 회전시켜서 옮김 (이때 내구도 깎기)
			for(int i=a; i<a+n; i++) {
				for(int j=b; j<b+n; j++) {
					if(maze[i][j]>0)
						maze[i][j] -= 1;
					if(maze[i][j]==-1) { //출구 위치 갱신
						X = j-b+a;
						Y = n-1-(i-a)+b;
					}
					temp[j-b][n-1-(i-a)] = maze[i][j];
				}
			}
			//옮긴 결과물 다시 본 배열에 옮김
			for(int i=a; i<a+n; i++) {
				for(int j=b; j<b+n; j++) {
					maze[i][j] = temp[i-a][j-b];
				}
			}
			//runners 전체 탐색하면서 해당 공간에 속하면 위치 갱신
			for(int m=0; m<M; m++) {
				Runner runner = runners[m];
				//탈출한 참가자인 경우
				if(runner.status)
					continue;
				if(runner.x>=a && runner.x<a+n && runner.y>=b && runner.y<b+n) { //해당 공간에 속한다면

          //runner.x와 runner.y를 그대로 사용하면 아래 runners[m].x와 runners[m].y를 갱신하는 과정에서 문제 생김!!! (runner는 runners를 가리키는 참조변수임)
					int runnerX = runner.x;
					int runnerY = runner.y;
					
					runners[m].x = runnerY - b + a;
					runners[m].y = n - 1 - (runnerX-a) + b;
				}
			}
		}
		
		//3. 모든 참가자들의 이동 거리 합과 출구 좌표 출력
		int total = 0;
		for(Runner r : runners) {
			total += r.distance;
		}
		System.out.println(total);
		System.out.println(X + " " + Y);
	}
	
	static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
	
	static int[] pickSquare(int[][] maze) { //가장 작은 정사각형 선택하기
		int[] result = new int[3];
		for(int m=0; m<M; m++) {
			Runner runner = runners[m];
			if(runner.status)
				continue;
			maze[runner.x][runner.y] = -2; //탈출 못 한 참가자들 maze에 표시 (-2로 표시)
		}
		//정사각형 n^2개(n은 2부터 시작, 우선순위에 맞게)를 그리고 해당 정사각형 안에 -2가 있는지 체크 -> 결과로 전달해줘야 하는 값은 좌상단 좌표 + 길이 
		for(int n=2; n<=N; n++) {
			//(i,j) <- 좌상단 좌표
			for(int i=X-n+1; i<=X; i++) {
				for (int j=Y-n+1; j<=Y; j++) {
					if(i<=0 || j<=0 || i+n-1>N || j+n-1>N)
						continue;
					if(runnerCheck(maze, i, j, n)) { //만약에 러너가 있으면
						result[0] = i; //좌상단 x 좌표
						result[1] = j; //좌상단 y 좌표
						result[2] = n; //정사각형 길이
						return result;
					}
				}
			}
		}
		return result;
	}
	
	static boolean runnerCheck(int[][] maze, int x, int y, int n) { //maze, 좌상단좌표, 길이
		boolean chk = false;
		for(int i=x; i<x+n; i++) {
			for(int j=y; j<y+n; j++) {
				if(maze[i][j]==-2)
					chk = true;
			}
		}
		return chk;
	}
}

class Runner {
	int x;
	int y;
	boolean status; //탈출했으면 true
	int distance; //이동거리
	
	Runner(int x, int y, boolean status, int distance) {
		this.x = x;
		this.y = y;
		this.status = status;
		this.distance = distance;
	}
}

/*
[핵심 포인트]
1. 2차원 배열의 깊은 복사
  참고 링크: https://seokmimmmmmmmm.tistory.com/entry/%EB%B0%B0%EC%97%B4%EC%9D%98-%EB%B3%B5%EC%82%AC2%EC%B0%A8%EC%9B%90-%EB%B0%B0%EC%97%B4%EC%9D%98-%EB%B3%B5%EC%82%AC
2. 114행 참조변수 관련 이슈 (runner.x와 runner.y를 그대로 사용하면..)
3. 회전 관련 이슈
  i <-> j-b+a
  j <-> n-1-(i-a)+b
*/
