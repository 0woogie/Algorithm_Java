#여행경로

import java.util.*;

class Solution {
    
    static boolean[] visited;
    static ArrayList<String> routes;
    
    public String[] solution(String[][] tickets) {
        
        visited = new boolean[tickets.length]; //항공권 방문처리
        routes = new ArrayList<>(); //가능한 경로들 담는 리스트
        
        dfs("ICN", "ICN", tickets, 0);
        
        Collections.sort(routes);
        return routes.get(0).split(" ");
    }
    
    static void dfs(String start, String route, String[][] tickets, int cnt) {
        if(cnt==tickets.length) {
            routes.add(route);
            return;
        }
        for(int i=0; i<tickets.length; i++) {
            if(start.equals(tickets[i][0]) && !visited[i]) {
                visited[i] = true;
                dfs(tickets[i][1], route+" "+tickets[i][1], tickets, cnt+1);
                visited[i] = false;
            }
        }
    }
}
