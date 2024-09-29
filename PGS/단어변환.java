#단어 변환

import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int[] cnts = new int[words.length];
        Arrays.fill(cnts, Integer.MAX_VALUE);
        
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<words.length; i++) {
            String word = words[i];
            //begin에서 변환할 수 있는 알파벳들 Queue에 넣기, cnts 반영
            int num=0;
            for(int j=0; j<begin.length(); j++) {
                if(begin.charAt(j) != word.charAt(j)) num++;
            }
            if(num==1) {
                queue.add(i);
                cnts[i] = 1;
            }
        }
        //Queue에서 하나씩 빼면서 변환 가능한 단어들 words에서 찾기
        //Queue에서 뺀 단어가 target이라면 그 즉시 종료
        //Queue가 빌때까지 찾지 못한다면 0 리턴
        int targetIdx = -1; //target을 찾았는지 체크
        while(!queue.isEmpty()) {
            int index = queue.poll();
            String now = words[index];
            if(now.equals(target)) {
                targetIdx = index;
                break;
            }
            for(int i=0; i<words.length; i++) {
                String word = words[i];
                int num=0;
                for(int j=0; j<now.length(); j++) {
                    if(now.charAt(j) != word.charAt(j)) num++;
                }
                if(num==1 && (cnts[index]+1<cnts[i])) {
                    queue.add(i);
                    cnts[i] = cnts[index]+1;
                }
            }
        }
        
        if(targetIdx==-1) return 0;
        else return cnts[targetIdx];
    }
}

//DFS + visited 배열 처리 방식: https://jisunshine.tistory.com/157
