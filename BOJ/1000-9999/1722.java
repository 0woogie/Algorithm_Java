#순열의 순서

import java.util.Scanner;

public class B1722 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int num = sc.nextInt();
		boolean[] visited = new boolean[N+1]; //숫자 선택 방문처리 (visited[2]=true <- 2를 순열에 들어가는 숫자로 골랐다는 의미)
		if(num==1) {
			long k = sc.nextLong(); //k는 최대 N!
			int[] result = new int[N];
			int leftNum = N; //남은 숫자 공간 개수
			for(int step=1; step<=N; step++) { //step: 순열의 몇번째 숫자 정하는지
				leftNum--;
				for(int i=1; i<=N; i++) {
					if(!visited[i]) { //아직 안 고른 숫자라면
						if(fac(leftNum)>=k) { //해당 숫자를 골라야 할 때
							visited[i] = true;
							result[step-1] = i;
							break;
						} else {
							k -= fac(leftNum);
						}
					}
				}
			}
			for(int r : result)
				System.out.print(r + " ");
		} else {
			int[] target = new int[N];
			for(int i=0; i<N; i++)
				target[i] = sc.nextInt();
			int leftNum = N; //남은 숫자 공간 개수
			long answer = 0;
			for(int i=0; i<N; i++) {
				leftNum--;
				int now = target[i]; //이번에 선택되는 숫자
				visited[now] = true;
				for(int j=now-1; j>=1; j--) {
					//해당 숫자보다 작은 숫자가 아직 방문처리 안 된 경우 -> 방문처리 안 된 숫자로 시작하는 순열 개수 계산
					if(!visited[j])
						answer += fac(leftNum);
				}
			}
			answer++;
			System.out.println(answer);
		}
	}
	
	static long fac(int x) { //팩토리얼 구하는 함수
		long result = 1;
		for(int i=x; i>1; i--) result *= i;
		return result;
	}
}

//N!은 int형 변수로 담을 수 없다는 것에 주의!! (1 ≤ N ≤ 20)
