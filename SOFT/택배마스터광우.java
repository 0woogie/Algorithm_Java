#택배 마스터 광우

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[] arr;
    static boolean[] visited;
    static int[] result;
    static int answer;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        visited = new boolean[N];
        result = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        answer = Integer.MAX_VALUE;
        DFS(0); //count
        System.out.println(answer);
    }

    static void DFS(int count) {
        if(count==N) {
            //현재 수열 기준으로 K번의 일 시행
            int total = 0;
            int idx = 0;
            for(int i=0; i<K; i++) {
                int m = M;
                while(result[idx]<=m) { //바구니 무게를 초과하지 않는 만큼 담기
                    total += result[idx];
                    m -= result[idx];
                    idx = (idx+1)%N;
                }
            }
            if(total<answer)
                answer = total;
            return;
        }
        for(int i=0; i<N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                result[count] = arr[i];
                DFS(count+1);
                visited[i] = false;
            }
        }
    }
}

//N이 최대 10이기 때문에 완전탐색으로 답 구할 수 있음 (순열 활용)
