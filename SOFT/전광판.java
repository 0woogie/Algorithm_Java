#전광판

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //0부터 9까지의 전구 표시 방식 - 켜졌으면 1, 꺼졌으면 0
        Map<Character, String> rule = new HashMap<>();
        rule.put('0', "1111110");
        rule.put('1', "0011000");
        rule.put('2', "0110111");
        rule.put('3', "0111101");
        rule.put('4', "1011001");
        rule.put('5', "1101101");
        rule.put('6', "1101111");
        rule.put('7', "1111000");
        rule.put('8', "1111111");
        rule.put('9', "1111101");
        rule.put(' ', "0000000"); //아무것도 켜져있지 않은 상태

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t=0; t<T; t++) {
            st = new StringTokenizer(br.readLine());
            String a = String.format("%5s", st.nextToken()); //5자리보다 작을 경우 앞에 공백 추가
            String b = String.format("%5s", st.nextToken());
            char[] A = a.toCharArray();
            char[] B = b.toCharArray();

            int result = 0;
            for(int i=0; i<5; i++) {
                result += diff(rule.get(A[i]), rule.get(B[i]));
            }
            System.out.println(result);
        }
    }

    static int diff(String x, String y) {
        int cnt = 0;
        char[] X = x.toCharArray();
        char[] Y = y.toCharArray();
        for(int i=0; i<X.length; i++) {
            if(X[i]!=Y[i])
                cnt++;
        }
        return cnt;
    }
}

//자리수가 가변적이라서(A와 B는 한 자리 이상 다섯 자리 이하의 자연수) 고생했는데..
//String.format("%5s", st.nextToken()) <- 5자리보다 작을 경우 앞에 공백 추가
