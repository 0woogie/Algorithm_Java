#1학년

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B5557 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N]; //입력 숫자 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		int lastNum = Integer.parseInt(st.nextToken()); //마지막 숫자
		
		//dp 배열 정의: dp[i][x] - i번째 숫자까지의 연산 결과로 x라는 숫자를 만드는 경우의 수
		long[][] dp = new long[N][21];
		//기저 세팅 - 1번째 숫자를 이용해서 1번째 숫자를 만드는 경우의 수 => 1가지
		dp[1][arr[1]] = 1;
		
		for(int i=1; i<=N-2; i++) {
			//0~20까지의 연산 결과를 만들었는지 확인
			for(int j=0; j<=20; j++) {
				//만든 경우가 있을 때
				if(dp[i][j]!=0) {
					//다음 수를 더했을 때 범위(0~20)에 속하면 경우의 수 반영
					if(j+arr[i+1]<=20)
						dp[i+1][j+arr[i+1]] += dp[i][j];
					//다음 수를 뺐을 때 범위(0~20)에 속하면 경우의 수 반영
					if(j-arr[i+1]>=0)
						dp[i+1][j-arr[i+1]] += dp[i][j];
				}
			}
		}
		
		System.out.println(dp[N-1][lastNum]); //N-1번째 숫자까지의 연산 결과로 lastNum을 만드는 경우의 수 출력
	}

}

//DFS로 접근하면 N의 크기가 3 이상 100 이하이므로 최대 2^98의 시간이 걸려 초과됨
// 만약 0~20이라는 조건이 없었다면 위와 같은 dp 정의는 불가능했을 것임

//참고1 - https://dingdingmin-back-end-developer.tistory.com/entry/%EB%B0%B1%EC%A4%80-5557%EC%9E%90%EB%B0%94-java-1%ED%95%99%EB%85%84
//참고2 - https://velog.io/@yoonuk/%EB%B0%B1%EC%A4%80-5557-1%ED%95%99%EB%85%84-Java

/*
 //처음에 시도했던 풀이 - 조합을 DFS로 푸는 방식, 시간초과
 public class Main {

	static int N;
	static int[] arr;
	static int answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = sc.nextInt();
		DFS(1, arr[0]); //현재몇개연산했는지, 결과값
		System.out.println(answer);
	}
	
	static void DFS(int cnt, int result) {
		//만약 중간에 나오는 수가 음수거나 20을 넘는다면
		if(result<0 || result>20)
			return;
		//만약 왼쪽에서 N-1개의 수를 계산했다면
		if(cnt==N-1) {
			if(result==arr[cnt])
				answer++;
			return;
		}
		//다음 수를 더하는 경우
		DFS(cnt+1, result+arr[cnt]);
		//다음 수를 빼는 경우
		DFS(cnt+1, result-arr[cnt]);
	}
}
 */
