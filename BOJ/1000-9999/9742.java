#순열

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B9742 {

	static char[] charArray;
	static char[] result;
	static boolean[] visited;
	static String answer;
	static int now, pos;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while((line=br.readLine())!=null) {
			StringTokenizer st = new StringTokenizer(line);
			String str = st.nextToken();
			charArray = str.toCharArray();
			int l = charArray.length;
			pos = Integer.parseInt(st.nextToken());
			now = 0;
			result = new char[l];
			visited = new boolean[l];
			dfs(l, 0);
			if(now<pos) answer = "No permutation";
			System.out.println(str + " " + pos + " = " + answer);
		}
	}
	
	public static void dfs(int l, int depth) {
		if(l==depth) {
			now++;
			if(now==pos)
				answer = new String(result);
			return;
		}
		for(int i=0; i<l; i++) {
			if(!visited[i]) {
				visited[i] = true;
				result[depth] = charArray[i];
				dfs(l, depth+1);
				visited[i] = false;
			}
		}
	}
}

/*
while((line=br.readLine())!=null) : 개수가 정해지지 않은 문자열 입력 케이스 대응
참고 - https://yeoncoding.tistory.com/461
*/
