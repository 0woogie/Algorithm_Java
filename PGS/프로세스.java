#프로세스

import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); //PriorityQueue를 이용한 풀이
		    
        for(int num : priorities) {
		pq.add(num);
	}
	while(!pq.isEmpty()) {
		for(int i=0; i<priorities.length; i++) {
			if(priorities[i] == pq.peek()) {
				pq.poll();
				answer++;
				if(i == location)
					return answer;
			}
		}
	}  
        return answer;
    }
}

//참고 링크 - https://velog.io/@dh1010a/Java-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A442587-%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4
/*
[기존 내 풀이]
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int[] result = new int[priorities.length];
        int cnt = 0;
        
        int[] refer = new int[10]; //우선순위 개수 카운트
        for(int x : priorities)
            refer[x]++;
        int idx = 9;
        while(true) {
            if(refer[idx]!=0) break;
            else idx--;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<priorities.length; i++) {
            queue.add(i);
        }
        
        while(!queue.isEmpty()) {
            int target = queue.poll();
            if(priorities[target]<idx) {
                //아직 우선순위가 안 된 경우
                queue.add(target);
            } else {
                //빼도 되는 경우
                result[cnt++] = target;
                if(cnt==result.length)
                    break;
                refer[idx]--;
                while(true) {
                    if(refer[idx]!=0) break;
                    else idx--;
                }
            }
        }
        for(int i=0; i<result.length; i++) {
            if(result[i]==location) {
                answer = i+1;
                break;
            }
        }
        return answer;
    }
}
*/
