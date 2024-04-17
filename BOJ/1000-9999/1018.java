#체스판 다시 칠하기

import java.util.Scanner;

public class B1018 {
	public static void main(String[] args) {
		//0. 전체 보드판 입력 받기
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		char[][] board = new char[N][M];
		for(int i=0; i<N; i++) {
			String s = sc.next();
			for(int j=0; j<M; j++)
				board[i][j] = s.charAt(j);
		}
		int result = 65;
		//1. 주어진 전체 보드판에서 8*8 크기를 선택한다.
		for(int i=0; i<=N-8; i++) {
			for(int j=0; j<=M-8; j++) {
				//(i,j)가 좌상단인 8*8 체스판
				//2. 타입1 체스판(좌상단 W로 시작 -> a+b 짝수면 W)과 비교했을 때 바꿔야하는 개수 카운트
				int cnt = 0;
				for(int a=0; a<8; a++) {
					for(int b=0; b<8; b++) {
						char x = board[i+a][j+b];
						if((a+b)%2==0) { //W여야 함
							if(x!='W') cnt++;
						} else { //B여야 함
							if(x!='B') cnt++;
						}
					}
				}
				if(cnt<result)
					result = cnt;
				//3. 타입2 체스판(좌상단 B로 시작 -> a+b 짝수면 B)과 비교했을 때 바꿔야하는 개수 카운트
				cnt = 0;
				for(int a=0; a<8; a++) {
					for(int b=0; b<8; b++) {
						char x = board[i+a][j+b];
						if((a+b)%2==0) { //B여야 함
							if(x!='B') cnt++;
						} else { //W여야 함
							if(x!='W') cnt++;
						}
					}
				}
				if(cnt<result)
					result = cnt;
			}
		}
		//4. 갱신된 최소 개수 출력
		System.out.println(result);
	}
}

//다른 풀이 - https://st-lab.tistory.com/101 (boolean[][]을 이용, 8*8 체스판에 대한 반복문을 돌때 T/F를 교차하며 판별)
