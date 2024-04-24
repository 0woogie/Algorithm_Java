#피로도

class Solution {
    
    static boolean[] visited;
    static int answer;
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(k, dungeons, 0); //현재 피로도, 던전 배열, 탐험한 던전 수
        return answer;
    }
    
    static void dfs(int k, int[][] dungeons, int cnt) {
        for(int i=0; i<dungeons.length; i++) {
            if(!visited[i] && k>=dungeons[i][0]) {
                //방문하지 않은 상태 && k가 최소 필요 피로도보다 크거나 같은 경우
                visited[i] = true;
                dfs(k-dungeons[i][1], dungeons, cnt+1);
                visited[i] = false;
            }   
        }
        answer = Math.max(answer, cnt);
    }
}
