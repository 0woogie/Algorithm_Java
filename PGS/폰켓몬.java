#폰켓몬

import java.util.HashSet;

public class Solution {
    public int solution(int[] nums) {
        int n = nums.length; //연구실 폰켓몬 수
        HashSet<Integer> s = new HashSet<>();
        for(int num : nums) s.add(num); //집합에 넣기 -> 같은 종류 폰켓몬 중복 제거
        if(n/2<s.size())
            return n/2;
        else
            return s.size();
    }
}
