#N과 M (9)

import java.util.Arrays;
import java.util.Scanner;

public class B15663 {
	
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
		int before = -1; //중복되는 수열 방지 목적
		for(int i=0; i<N; i++) {
			if(!visited[i] && arr[i]!=before) {
				result[count] = arr[i];
				before = arr[i];
				visited[i] = true;
				DFS(count+1);
				visited[i] = false;
			}
		}
	}
}

//다른 풀이 - 저장한 순서대로 출력할 수 있고 중복도 허용하지 않는 LinkedHashSet 사용
//참고 링크1 - https://codingrapper.tistory.com/21
//참고 링크2 - https://stonage.tistory.com/241
