#최소직사각형

class Solution {
    public int solution(int[][] sizes) {
        
        int ga = 0; //길이가 더 긴 쪽을 가로로 선택
        int se = 0;
        
        for(int[] tmp : sizes) {
            if(tmp[0]>tmp[1]) {
                ga = Math.max(ga, tmp[0]);
                se = Math.max(se, tmp[1]);
            } else {
                ga = Math.max(ga, tmp[1]);
                se = Math.max(se, tmp[0]);
            }
        }
        
        return ga*se;
    }
}
