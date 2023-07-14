#오목

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] arr = new int[19][19];
		for(int i=0; i<19; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<19; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//바둑알이 움직일 수 있는 가로, 세로, 대각선 방향으로 이동하기 위해 배열 dx1, dy1, dx2, dy2 생성
		int[] dx1 = {0,-1,1,-1};
		int[] dy1 = {-1,0,-1,-1};
		int[] dx2 = {0,1,-1,1};
		int[] dy2 = {1,0,1,1};
		int check = 0; //승부가 났는지 체크하기 위한 변수
		for(int j=0; j<19; j++) { //!!!좌측 우선으로 순회!!!
			for(int i=0; i<19; i++) {
				int now = arr[i][j]; //현재 바둑알의 위치
				if(now==0) continue; //아직 놓이지 않은 자리인 경우 검사하지 않음
				for(int c=0; c<4; c++) { //총 네 방향에서 바둑알이 몇 개 연속 되는지 확인하기
					int cnt = 1;
					int nx = i, ny = j;
					int tx = 0, ty = 0;
					while(true) {
						tx = nx + dx1[c];
						ty = ny + dy1[c];
						if(tx<0 || tx>=19 || ty<0 || ty>=19 || arr[tx][ty]!=now) break;
						cnt++;
						nx = tx;
						ny = ty;
					}
					nx = i;
					ny = j;
					while(true) {
						tx = nx + dx2[c];
						ty = ny + dy2[c];
						if(tx<0 || tx>=19 || ty<0 || ty>=19 || arr[tx][ty]!=now) break;
						cnt++;
						nx = tx;
						ny = ty;
					}
					if(cnt==5) { //바둑알 5개가 연속된 경우
						System.out.println(now);
						System.out.println((i+1) + " " + (j+1));
						check++; //승부가 났으므로 check 값 변경
						return;
					}
				}
			}
		}
		if(check==0) System.out.println(0); //승부가 나지 않은 경우
	}
}

/*
다섯 개의 바둑알 중에서 가장 왼쪽에 있는 바둑알의 위치를 출력해야 하므로 바둑판을 좌측 우선으로 순회해야 함
dx, dy 배열을 2개씩 두는 것보다(위와 같이) dx[i]*(-1)와 같은 방식이 더 깔끔
*/
