#소트인사이드

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int[] A = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            A[i] = Integer.parseInt(s.substring(i, i + 1));
        }
        for (int i = 0; i < s.length() - 1; i++) {
            int Max = i;
            for (int j = i + 1; j < s.length(); j++) {
                if (A[j] > A[Max])
                    Max = j;
            }
            if (A[i] < A[Max]) {
                int temp = A[i];
                A[i] = A[Max];
                A[Max] = temp;
            }
        }
        for (int i = 0; i < s.length(); i++)
            System.out.print(A[i]);
    }
}

/*
substring() - 문자열의 특정 부분을 잘라내는 데 사용
substring(int startIndex, int endIndex) - startIndex(포함)부터 endIndex(불포함)까지의 문자열을 리턴
ex) "Hello".substring(2,4) -> "ll"
참고 - https://hianna.tistory.com/534
*/
