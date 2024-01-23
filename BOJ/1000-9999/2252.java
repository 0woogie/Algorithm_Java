#줄 세우기

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B2252 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] edgeCount = new int[N+1]; //위상정렬에 사용할 진입차수 저장 배열
		ArrayList<Integer>[] list = new ArrayList[N+1]; //인접 리스트
		for(int i=1; i<N+1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		for(int i=0; i<M; i++) {
			//A가 B보다 앞에 서야 함
			int A = sc.nextInt();
			int B = sc.nextInt();
			//인접 리스트에 A->B 표시하기
			list[A].add(B);
			//B의 진입차수 1 증가
			edgeCount[B]++;
		}
		Queue<Integer> queue = new LinkedList<>();
		//진입차수가 0인 정점들 큐에 넣기 (자신의 앞에 올 학생이 없는 학생들 선별)
		for(int i=1; i<N+1; i++) {
			if(edgeCount[i]==0) {
				queue.add(i);
				edgeCount[i] = -1; //방문처리
			}
		}
		//큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			int now = queue.poll();
			//현재 정점 출력하기
			System.out.print(now + " ");
			//현재 정점이 갈 수 있는 목적지 정점들 모두 진입차수 1 감소 && 진입차수 0이 된 정점들 큐에 넣기
			for(int x : list[now]) {
				edgeCount[x]--;
				if(edgeCount[x]==0)
					queue.add(x);
			}
		}
	}
}
