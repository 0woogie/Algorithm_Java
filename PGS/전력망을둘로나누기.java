#전력망을 둘로 나누기

import java.util.*;

class Solution {
    
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static int result;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        list = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
        }
        for(int[] wire : wires) { //양방향 간선
            list[wire[0]].add(wire[1]);
            list[wire[1]].add(wire[0]);
        }
        
        for(int[] target : wires) {
            //모든 간선에 대해 한 번씩 끊어봄
            visited = new boolean[n+1];
            result = 0;
            simul(target[0], target[1], 1); //a, b, now
            answer = Math.min(answer, Math.abs(result-(n-result)));
        }
        
        return answer;
    }
    
    static void simul(int a, int b, int now) {
        
        visited[now] = true;
        result++;
        
        for(int next : list[now]) {
            //a와 b를 잇는 간선은 제거된 상황
            if((now==a&&next==b) || (now==b&&next==a))
                continue;
            if(!visited[next])
                simul(a, b, next);
        }
    }
}
