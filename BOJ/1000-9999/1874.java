#스택 수열

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        Stack<Integer> stack = new Stack<>();
        StringBuffer bf = new StringBuffer();
        int num = 1;
        boolean result = true;
        for (int i = 0; i < A.length; i++) {
            int now = A[i];
            if (now >= num) {
                while (now >= num) {
                    stack.push(num++);
                    bf.append("+\n");
                }
                stack.pop();
                bf.append("-\n");
            } else {
                int target = stack.pop();
                if (target > now) {
                    System.out.println("NO");
                    result = false;
                    break;
                } else {
                    bf.append("-\n");
                }
            }
        }
        if (result) {
            System.out.println(bf.toString());
        }
    }
}

/*
String: 문자열 연산 적고, 멀티쓰레드 환경인 경우
StringBuffer: 문자열 연산 많고, 멀티쓰레드 환경인 경우
StringBuilder: 문자열 연산 많고, 단일쓰레드 환경이거나 동기화 고려 안 해도 되는 경우
참고 - https://dev-jwblog.tistory.com/108
*/
