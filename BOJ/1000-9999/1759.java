#암호 만들기

import java.util.Arrays;
import java.util.Scanner;

public class B1759 {

	static char[] charArray;
	static char[] output;
	static int L, C;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		L = sc.nextInt();
		C = sc.nextInt();
		output = new char[L];
		charArray = new char[C];
		for(int i=0; i<C; i++)
			charArray[i] = sc.next().charAt(0);
		Arrays.sort(charArray);
		dfs(0, 0); //depth, start
	}

	static void dfs(int depth, int start) {
		//종료 조건
		if(depth==L) {
			//모음 한 개, 자음 두 개 이상인 경우만 정답
			int moCnt=0, jaCnt=0;
			for(char c : output) {
				if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u') moCnt++;
				else jaCnt++;
			}
			if(moCnt>=1 && jaCnt>=2)
				System.out.println(new String(output));
			return;
		}
		for(int i=start; i<C; i++) {
			output[depth] = charArray[i];
			dfs(depth+1, i+1);
		}
	}
}

//관련 문제 - BOJ 15650
