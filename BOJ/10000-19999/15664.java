#N과 M (10)

import java.util.Arrays;
import java.util.Scanner;

public class B15664 {
	
	static int N, M;
	static int[] arr;
	static boolean[] visited;
	static int[] result;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		visited = new boolean[N];
		result = new int[M];
		for(int i=0; i<N; i++)
			arr[i] = sc.nextInt();
		Arrays.sort(arr);
		DFS(0); //count
	}
	
	static void DFS(int count) {
		if(count==M) {
			for(int r : result)
				System.out.print(r + " ");
			System.out.println();
			return;
		}
		int before = -1;
		for(int i=0; i<N; i++) {
			if(!visited[i] && arr[i]!=before) {
				if(count>=1 && result[count-1]>arr[i])
					continue;
				result[count] = arr[i];
				before = arr[i];
				visited[i] = true;
				DFS(count+1);
				visited[i] = false;
			}
		}
	}
}

//풀이1 - 위 코드와 같이 15663번 문제 풀었을 때 방식에서 조건문 하나 추가(36행)해주는 방법
//풀이2 - DFS() 호출할 때 start 변수를 파라미터로 가지고 다니면서 반복문 시작을 start부터 하는 방식
