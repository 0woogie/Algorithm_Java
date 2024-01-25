#근무 시간

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int result = 0;
        StringTokenizer st;
        for(int i=0; i<5; i++){
            st = new StringTokenizer(br.readLine());
            String[] sArr = st.nextToken().split(":");
            int sTime = Integer.parseInt(sArr[0])*60 + Integer.parseInt(sArr[1]);
            String[] eArr = st.nextToken().split(":");
            int eTime = Integer.parseInt(eArr[0])*60 + Integer.parseInt(eArr[1]);
            result += eTime - sTime;
        }
        System.out.println(result);
    }
}
