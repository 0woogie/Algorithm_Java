#강의실 배정

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		//끝나는 시간 기준으로 오름차순 정렬
		Arrays.sort(arr, (o1, o2) -> {
		    if(o1[1] == o2[1]){
		        return Integer.compare(o1[0], o2[0]);
		    } else {
		        return Integer.compare(o1[1], o2[1]);
		    }
		});
        int cnt = 0;
        int lastIdx = 0;
        for(int i=0; i<N; i++) {
            if(arr[i][0]>=lastIdx) {
                cnt++;
                lastIdx = arr[i][1];
            }
        }
		System.out.println(cnt);
    }
}

//BOJ 1931번과 동일
