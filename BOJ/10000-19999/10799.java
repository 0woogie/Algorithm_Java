#쇠막대기

import java.util.Scanner;

public class B10799 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] arr = sc.next().toCharArray();
		int cnt = 0;
		long total = 0;
		int idx = 0;
		while(idx<arr.length) {
			if(arr[idx]=='(') {
				if(idx+1<arr.length && arr[idx+1]==')') { //1. 레이저인 경우
					total += cnt;
					idx++; //다음 괄호 문자까지 확인했으니까
				} else { //2. 막대기 하나 시작
					cnt++;
					total++;
				}
			} else { //3. 막대기 하나 끝
				cnt--;
			}
			idx++;
		}
		System.out.println(total);
	}
}

//참고 링크 - https://steady-coding.tistory.com/10
