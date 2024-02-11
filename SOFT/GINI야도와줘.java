#GINI야 도와줘

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        //세차장(W) -> 집(H)
        //태범이는 소나기와 강을 지나지 못함
        //소나기는 강과 집에 옮겨지지 않음
        int[][] graph = new int[R][C];
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0; i<R; i++){
            String s = sc.next();
            for(int j=0; j<C; j++) {
                char c = s.charAt(j);
                if(c=='H') {
                    graph[i][j] = -3; //집: -3
                } else if(c=='W') {
                    graph[i][j] = 1; //태범: 1부터 시작
                    queue.add(new int[] {i,j}); //소나기보다 queue에 먼저 넣어야 함
                } else if(c=='X') {
                    graph[i][j] = -1; //강: -1
                } else if(c=='*') {
                    graph[i][j] = -2; //소나기: -2
                } else {
                    graph[i][j] = 0; //비어있는 칸: 0
                }
            }
        }
        for(int a=0; a<R; a++) {
            for(int b=0; b<C; b++) {
                if(graph[a][b]==-2)
                    queue.add(new int[] {a,b}); //소나기 queue에 넣기
            }
        }
        int result = 0;
        boolean chk = false;
        while(true) {
            if(chk)
                break;
            int num = queue.size();
            if(num==0)
                break;
            for(int t=0; t<num; t++) {
                int[] tmp = queue.poll();
                int x = tmp[0];
                int y = tmp[1];
                if(graph[x][y]==-2) { //소나기라면 -> 강과 집에 옮겨지지 않음
                    for(int d=0; d<4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        if(nx<0 || nx>=R || ny<0 || ny>=C)
                            continue;
                        if(graph[nx][ny]>=0) {
                            graph[nx][ny] = -2;
                            queue.add(new int[] {nx,ny});
                        }
                    }
                } else { //태범이라면 -> 소나기와 강을 지나지 못함
                    for(int d=0; d<4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        if(nx<0 || nx>=R || ny<0 || ny>=C)
                            continue;
                        if(graph[nx][ny]==0) {
                            graph[nx][ny] = graph[x][y] + 1;
                            queue.add(new int[] {nx,ny});
                        } else if(graph[nx][ny]==-3) {
                            result = graph[x][y] + 1;
                            chk = true;
                            break;
                        }
                    }
                }
                if(chk) break;
            }
        }
        if(result==0)
            System.out.println("FAIL");
        else
            System.out.println(result-1);
    }
}

/*
[처음에 틀렸던 이유]
- queue에 태범이보다 소나기를 먼저 넣었음
- 태범이는 상하좌우로 이동할 때 “graph[nx][ny] = graph[x][y] + 1”로 동작하는데
- 같은 시점이더라도 소나기가 먼저 확산하면 graph[x][y]에 소나기 값(-2)이 담겨버림
*/
