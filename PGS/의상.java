#의상

import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> map = new HashMap<>();
        for(String[] s : clothes) {
            // 의상 종류 별로 몇 개씩 있는지 HashMap에 저장
            if(map.containsKey(s[1])) {
                int value = map.get(s[1]);
                map.put(s[1], value+1); //해당 종류 의상 1 추가
            } else {
                map.put(s[1], 1);
            }
        }
        for(String s : map.keySet()) {
            int value = map.get(s);
            answer *= value+1; //해당 종류의 의상 안 입는 경우도 고려하기 위해 +1
        }
        answer -= 1; //옷을 아무 것도 안 입은 경우 빼기
        return answer;
    }
}
