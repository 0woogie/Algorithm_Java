#탈출

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B3055 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		char[][] graph = new char[r][c];
		Queue<Point> queue = new LinkedList<>();
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
    		int[][] visited = new int[r][c];
		
		Point p = null;
		for(int i=0; i<r; i++) {
			String str = br.readLine();
			for(int j=0; j<c; j++) {
				graph[i][j] = str.charAt(j);
				if(graph[i][j]=='*')
					queue.add(new Point('*',i,j));
				else if(graph[i][j]=='S')
					p = new Point('S',i,j);
			}
		}
		queue.add(p); //큐에 고슴도치를 물보다 나중에 넣기
    
		boolean possible = false;
		while(!queue.isEmpty()) {
			//1. 큐에서 꺼내옴
			Point point = queue.poll();
			char type = point.type;
			int x = point.x;
			int y = point.y;
			//2. 목적지인가?
			if(type=='D') {
				System.out.println(visited[x][y]);
				possible = true;
				break;
			}
			//3. 연결된 곳을 순회 -> 상하좌우
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				//4. 갈 수 있는가?(공통)
				if(nx<0 || nx>=r || ny<0 || ny>=c) continue;
				if(type=='*') { //4-1. 갈 수 있는가?(물 -> . 혹은 S로 이동 가능)
					if(graph[nx][ny]=='.' || graph[nx][ny]=='S') {
						//5. 체크인 -> 지도에 * 표기
						graph[nx][ny] = '*';
						//6. 큐에 넣음
						queue.add(new Point('*',nx,ny));
					}
				} else { //4-2. 갈 수 있는가?(고슴도치 -> . 혹은 D로 이동 가능)
					if((graph[nx][ny]=='.' || graph[nx][ny]=='D') && visited[nx][ny]==0) {
						//5. 체크인 -> visited에 거리 기록
						visited[nx][ny] = visited[x][y] + 1;
						//6. 큐에 넣음
						queue.add(new Point(graph[nx][ny],nx,ny));
					}
				}
			}
		}
		if(!possible) System.out.println("KAKTUS");
	}
}

class Point {
	char type;
	int x;
	int y;
	
	public Point(char type, int x, int y) {
		super();
		this.type = type;
		this.x = x;
		this.y = y;
	}
}
