#문자열 반복

import java.util.Scanner;

public class B2675 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=0; t<T; t++) {
			int R = sc.nextInt();
      String S = sc.next();
      for(int i=0; i<S.length(); i++) {
        for(int j=0; j<R; j++) {
          System.out.print(S.charAt(i));
        }
      }
			System.out.println();
		}
	}
}

//nextLine() - 엔터값을 입력받을 때까지 기준으로 한 줄을 읽어버린다. -> 공백 포함해서 읽어버림
//next() - 공백을 기준으로 하나의 문자열만 읽어들인다.
