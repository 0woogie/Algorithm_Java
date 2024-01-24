#실습용 로봇

class Solution {
    public int[] solution(String command) {
        int[] answer = new int[2]; //최종 위치의 좌푯값 x, y
        int[] dx = {0, 1, 0, -1}; //상, 우, 하, 좌
        int[] dy = {1, 0, -1, 0};
        int direction = 0; //현재 보는 방향
        int x = 0, y = 0;
        char[] charArray = command.toCharArray();
        for(char c : charArray) {
            if(c=='R') {
                direction = Math.floorMod(direction+1, 4);
            } else if(c=='L') {
                direction = Math.floorMod(direction-1, 4);
            } else if(c=='G') {
                x += dx[direction];
                y += dy[direction];
            } else {
                x -= dx[direction];
                y -= dy[direction];
            }
        }
        answer[0] = x;
        answer[1] = y;
        return answer;
    }
}

//자바에서의 음수 모듈러 연산 -> Math.floorMod() 이용하기
//관련 링크 - https://www.acmicpc.net/board/view/72426
