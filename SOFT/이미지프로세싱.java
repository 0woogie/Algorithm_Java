#이미지 프로세싱

import java.io.*;
import java.util.*;

public class Main {

    static int H, W;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        graph = new int[H+1][W+1];
        for(int i=1; i<=H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=W; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int Q = Integer.parseInt(br.readLine());
        for(int i=0; i<Q; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            BFS(a, b, c);
        }
        for(int i=1; i<=H; i++){
            for(int j=1; j<W; j++){
                System.out.print(graph[i][j]+" ");
            }
            System.out.print(graph[i][W]);
            System.out.println();
        }
    }

    static void BFS(int a, int b, int c) {
        visited = new boolean[H+1][W+1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {a, b});
        visited[a][b] = true;
        while(!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];
            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx<1 || nx>H || ny<1 || ny>W)
                    continue;
                //가로세로 연결되어 있고 같은 색깔인 픽셀들 선택해서 큐에 넣음
                if(!visited[nx][ny] && graph[nx][ny]==graph[x][y]) {
                    queue.offer(new int[] {nx, ny});
                    visited[nx][ny] = true;
                }
            }
            //주변 픽셀들 다 판단했으면 현재 픽셀 c로 바꿈
            graph[x][y] = c;
        }
    }
}
