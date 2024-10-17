#구명보트

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int start = 0; //가장 몸무게 적은 사람
        int end = people.length-1; //가장 몸무게 많은 사람
        while(start<=end) {
            if(start==end) { //1명 남은 경우
                answer++;
                break;
            }
            if(people[start]+people[end] <= limit) { //2명 태워보낼 수 있는 경우
                answer++;
                if(start+1==end) break;
                start++;
                end--;
            } else { //혼자 타야하는 경우
                answer++;
                end--;
            }
        }
        return answer;
    }
}

//https://easybrother0103.tistory.com/126
