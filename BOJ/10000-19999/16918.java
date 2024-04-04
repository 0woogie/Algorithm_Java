#봄버맨

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class tmp {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		int now = 0; //현재 시간
		int[][] graph = new int[R][C];
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) {
				char c = s.charAt(j);
				if(c=='.')
					graph[i][j] = -1; //빈 칸: -1
				else
					graph[i][j] = 0; //0초에 설치된 폭탄: 0
			}
		}
		now++;
		while(now<N) {
			now++;
			//1. 폭탄이 설치되지 않은 칸(빈 칸)에 폭탄 설치하는 Time
			if(now%2==0) {
				for(int i=0; i<R; i++) {
					for(int j=0; j<C; j++) {
						if(graph[i][j]==-1)
							graph[i][j] = now; //now초에 설치된 폭탄: now
					}
				}
			}
			//2. 3초 전 설치된 폭탄이 터지는 Time
			else {
				for(int i=0; i<R; i++) {
					for(int j=0; j<C; j++) {
						if(graph[i][j]==now-3) { //3초 전 설치된 폭탄인 경우
							graph[i][j] = -1;
							for(int d=0; d<4; d++) {
								int nx = i + dx[d];
								int ny = j + dy[d];
								if(nx<0 || nx>=R || ny<0 || ny>=C)
									continue;
								if(graph[nx][ny]==now-3)
									continue; //이 폭탄은 이 폭탄 나름대로 이번 Time에 사방에 피해를 입혀야 함. 터뜨리면 안 됨! (아래 주석 참고)
								graph[nx][ny] = -1;
							}
						}
					}
				}
			}
		}
		//3. 격자판 상태 출력
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(graph[i][j]==-1)
					System.out.print(".");
				else
					System.out.print("O");
			}
			System.out.println();
		}
	}
}

/*
[유의해야 할 점]
연쇄반응으로 주변 폭탄을 터트릴 때 그 주변 폭탄이 이번 Time에 터트려야 할 폭탄이라면 연쇄반응으로 터트리지 않는다.
왜냐하면 이번에 터트려야 할 폭탄을 연쇄반응으로 미리 터트리게 되면 미리 터트린 폭탄의 주변 폭탄을 연쇄시킬 수 없어 문제가 생기게 된다!
[참고 링크]
https://velog.io/@yanghl98/%EB%B0%B1%EC%A4%80-16918-%EB%B4%84%EB%B2%84%EB%A7%A8-JAVA%EC%9E%90%EB%B0%94
*/
