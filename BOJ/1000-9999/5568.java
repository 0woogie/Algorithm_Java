#카드 놓기

import java.util.HashSet;
import java.util.Scanner;

public class B5568 {
	
	static HashSet<String> set = new HashSet<>();
	static String[] cards;
	static boolean[] visited;
	static int n, k;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		cards = new String[n];
		visited = new boolean[n];
		for(int i=0; i<n; i++) {
			cards[i] = sc.next();
		}
		DFS(0, ""); //count
		System.out.println(set.size());
	}
	
	static void DFS(int count, String result) {
		if(count==k) {
			set.add(result);
			return;
		}
		for(int i=0; i<n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				DFS(count+1, result+cards[i]);
				visited[i] = false;
			}
		}
	}
}

//백트래킹 + HashSet 사용
