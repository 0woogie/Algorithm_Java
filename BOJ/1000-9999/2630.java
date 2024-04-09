#색종이 만들기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2630 {
	
	static int whiteCnt;
	static int blueCnt;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		target(0, N-1, 0, N-1, N); //세로 시작, 세로 끝, 가로 시작, 가로 끝, 한 변의 길이
		System.out.println(whiteCnt);
		System.out.println(blueCnt);
	}
	
	static int target(int ss, int se, int gs, int ge, int size) { //세로 시작, 세로 끝, 가로 시작, 가로 끝, 한 변의 길이
		
		if(size!=1) {
			int resultA = target(ss, (ss+se)/2, gs, (gs+ge)/2, size/2);
			int resultB = target(ss, (ss+se)/2, (gs+ge)/2+1, ge, size/2);
			int resultC = target((ss+se)/2+1, se, gs, (gs+ge)/2, size/2);
			int resultD = target((ss+se)/2+1, se, (gs+ge)/2+1, ge, size/2);
			if(resultA >=0 &&resultA==resultB && resultA==resultC && resultA==resultD) { //다 같은 경우
				if(resultA==0) {
					whiteCnt -= 3;
				} else {
					blueCnt -= 3;
				}
				return resultA;
			} else {
				return -1;
			}
		} else { //더이상 나눌 수 없는 경우
			if(arr[ss][gs]==0) {
				whiteCnt++;
			} else {
				blueCnt++;
			}
			//하얀색으로 칠해진 칸은 0, 파란색으로 칠해진 칸은 1
			return arr[ss][gs];
		}
	}
}

//동일한 대상을 두 번 확인하지 않기 위해 더 이상 자를 수 없는 사이즈(1)까지 자른 상태를 시작점으로 '다른 대상들과 합칠 수 있는가'를 확인하면서 재귀를 거슬러 올라옴
//다른 풀이 방식 - https://st-lab.tistory.com/227 (만약에 특정 구역이 모두 동일 색상이 아닌 것으로 확인되면 그 때 4조각 내는 방식)
