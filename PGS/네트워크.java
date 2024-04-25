#네트워크

class Solution {
    
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        for(int i=0; i<n; i++) {
            if(visited[i]) continue;
            dfs(i, n, computers);
            answer++;
        }
        return answer;
    }
    
    static void dfs(int x, int n, int[][] computers) {
        
        visited[x] = true;
        
        for(int i=0; i<n; i++) {
            if(x==i) continue;
            if(computers[x][i]==1 && !visited[i])
                dfs(i, n, computers);
        }
    }
}
