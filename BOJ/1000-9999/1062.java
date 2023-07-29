#가르침

import java.util.Scanner;

public class B1062 {

	static int n, k;
	static boolean[] visited;
	static String[] words;
	static int result;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		if(k<5) {
			System.out.println(0);
			return;
		}
		words = new String[n];
		for(int i=0; i<n; i++)
			words[i] = sc.next();
		visited = new boolean[26]; //각 알파벳(a~z)을 배웠는지 체크
		//a, c, i, n, t 가르치기
		visited['a'-'a'] = true;
		visited['c'-'a'] = true;
		visited['i'-'a'] = true;
		visited['n'-'a'] = true;
		visited['t'-'a'] = true;
		k -= 5;
		result = 0;
		DFS(1, 0); //start, t (t: 가르친 알파벳 개수 의미)
		System.out.println(result);
	}
	
	static void DFS(int start, int t) {
		if(t==k) { //k개 가르친 경우
			//읽을 수 있는 단어 개수 카운트, result 갱신
			int cnt = 0;
			for(String word : words) {
				boolean chk = false;
				char[] charArray = word.toCharArray();
				for(char c : charArray) {
					if(!visited[c-'a']) { //안 배운 글자가 있는 경우
						chk = true;
						break;
					}
				}
				if(!chk) cnt++;
			}
			if(cnt>result) result = cnt;
			return;
		}
		for(int i=start; i<26; i++) {
			if(!visited[i]) {
				visited[i] = true;
				DFS(i+1, t+1);
				visited[i] = false;
			}
		}
	}

}
