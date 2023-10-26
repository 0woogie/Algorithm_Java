#최대공약수 하나 빼기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14476 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		int[] gcdLtoR = new int[N];
		int[] gcdRtoL = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		//최대공약수 누적합 구하기 (왼->오 방향)
		gcdLtoR[0] = nums[0];
		for(int i=1; i<N; i++) {
			gcdLtoR[i] = gcd(gcdLtoR[i-1], nums[i]);
		}
		
		//최대공약수 누적합 구하기 (오->왼 방향)
		gcdRtoL[N-1] = nums[N-1];
		for(int i=N-2; i>=0; i--) {
			gcdRtoL[i] = gcd(gcdRtoL[i+1], nums[i]);
		}
		
		int max = 0;
		int maxIndex = 0;
		for(int k=0; k<N; k++) {
			int gcd = 0;
			if(k==0) {
				gcd = gcdRtoL[1];
			} else if(k==N-1) {
				gcd = gcdLtoR[N-2];
			} else {
				gcd = gcd(gcdLtoR[k-1], gcdRtoL[k+1]);
			}
			//max값 갱신
			if(nums[k]%gcd!=0 && gcd>max) {
				max = gcd;
				maxIndex = k;
			}
		}
		
		if(max==0)
			System.out.println(-1);
		else
			System.out.println(max + " " + nums[maxIndex]);
	}
	
	// gcd(a, b) == gcd(b, a%b), a%b==0이 될 때까지
	static int gcd(int a, int b) {
		if(b==0) {
			return a;
		} else {
			return gcd(b, a%b);
		}
	}

}
