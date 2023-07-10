#Ladder1

import java.util.Scanner;

public class S1210 {
	
	public static int[][] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int n = sc.nextInt();
			arr = new int[100][100];
			for(int i=0; i<100; i++) {
				for(int j=0; j<100; j++) {
					arr[i][j] = sc.nextInt(); //1이 사다리, 2가 목적지
				}
			}
			for(int i=0; i<100; i++) {
				if(arr[0][i]==1) { //출발점 발견
					boolean result = start(i);
					if(result) {
						System.out.println("#"+test_case + " " + i);
						break;
					}
				}
			}
		}
	}

	public static boolean start(int s) {
		boolean result = false;
		int x=0, y=s;
		while(true) {
			//일단 밑으로 한 칸 내려가기
			x++;
			//만약 x가 100이 된다면 반복문 탈출하기
			if(x==100) break;
			//만약 목적지(2)라면 result를 true로 바꾸고 탈출하기
			if(arr[x][y]==2) {
				result = true;
				break;
			}
			//범위에 벗어나지 않는 양 옆을 둘러보고 옆으로 이동할 수 있는지 확인
			//옆으로 갈 수 있다면 이동 (끝까지)
			if(y-1>=0 && arr[x][y-1]==1) {
				while(y-1>=0 && arr[x][y-1]==1)
					y = y-1;
			} else if(y+1<100 && arr[x][y+1]==1) {
				while(y+1<100 && arr[x][y+1]==1)
					y = y+1;
			}
		}
		return result;
	}
}

/*
다른 사람 풀이 - 도착점에서 역으로 올라가면서 출발점 찾기 (좌/우/상 방향 이동을 위한 dx,dy 배열 이용)
*/
