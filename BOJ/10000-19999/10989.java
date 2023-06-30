#수 정렬하기 3

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int n = Integer.parseInt(br.readLine());
        int[] count = new int[10001];
        for (int i = 0; i < n; i++) {
            count[Integer.parseInt(br.readLine())]++;
        }
        for (int i = 1; i < 10001; i++) {
            while (count[i] > 0) {
                sb.append(i + "\n");
                count[i]--;
            }
        }
        System.out.println(sb.toString());
    }
}

/*
StringBuffer와 관련된 내용은 1874.java 참고
*/
