#절댓값 힙

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            int first_abs = Math.abs(o1);
            int second_abs = Math.abs(o2);
            if (first_abs == second_abs)
                return o1 > o2 ? 1 : -1;
            else
                return first_abs - second_abs;
        });
        for (int i = 0; i < n; i++) {
            int request = Integer.parseInt(br.readLine());
            if (request == 0) {
                if (queue.isEmpty())
                    System.out.println("0");
                else
                    System.out.println(queue.poll());
            } else {
                queue.add(request);
            }
        }
    }
}

/*
Java에서 PriorityQueue 사용법
1. 최소힙 (최소값부터 정렬)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
2. 최대힙 (최대값부터 정렬)
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
3. 기준 커스터마이징
참고 - https://velog.io/@robolab1902/Java-Priority-Queue-%EB%A7%A4%EA%B0%9C%EB%B3%80%EC%88%98%EC%97%90-%EB%9E%8C%EB%8B%A4%EC%8B%9D-%EC%93%B0%EB%8A%94-%EC%9D%B4%EC%9C%A0%EA%B0%80-%EB%AD%90%EC%95%BC
*/
