#우물 안 개구리

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1]; //회원들이 들 수 있는 무게 배열
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        boolean[] visited = new boolean[N+1]; //본인이 최고라고 생각하는 사람들은 최종적으로 visited 배열 false
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(arr[a]==arr[b]){ //친분 관계가 있는 사람과 칠 수 있는 무게 같음 == 둘 다 나가리
                visited[a] = true;
                visited[b] = true;
            } else if(arr[a]<arr[b]) { //친분 관계 있는 사람보다 무게 못 치면 탈락
                visited[a] = true;
            } else {
                visited[b] = true;
            }
        }
        int cnt = 0;
        for(int i=1; i<=N; i++) { //본인이 끝까지 최고라고 생각하는 사람들 몇명인지 카운트
            if(!visited[i])
                cnt++;
        }
        System.out.println(cnt);
    }
}
