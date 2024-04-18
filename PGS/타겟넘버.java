#타겟 넘버

class Solution {
    
    static int answer;
    
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0); //numbers, target, depth, sum
        return answer;
    }
    
    static void dfs(int[] numbers, int target, int depth, int sum) {
        if(depth==numbers.length) {
            if(sum==target)
                answer++;
            return;
        }
        //현재 숫자 더하기 하는 경우
        dfs(numbers, target, depth+1, sum+numbers[depth]);
        //현재 숫자 빼기 하는 경우
        dfs(numbers, target, depth+1, sum-numbers[depth]);
    }
}
