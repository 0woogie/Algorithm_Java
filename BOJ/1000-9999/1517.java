#버블 소트

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1517 {

	public static int[] arr;
	public static int[] tarr;
	public static long result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		tarr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		msort(0, N-1); //병합 정렬 수행
		System.out.println(result);
	}

	public static void msort(int start, int end) {
		if(start==end) return;
		
    int m = (start+end)/2;
		msort(start, m);
		msort(m+1, end);
    
		for(int i=start; i<=end; i++) tarr[i] = arr[i]; //tarr에 arr 값 저장
		
		//병합 정렬 방식으로 정렬(값 이동하는 만큼 count)
		int left = start;
		int right = m+1;
		int idx = start;
		while(left<m+1 && right<=end) {
			if(tarr[left]<=tarr[right]) { //비교하는 두 수가 같은 값인 경우는 swap이 일어나지 않으므로 else문에 포함되어서는 안 된다. => 부등호는 '<'가 아니라 '<='
				arr[idx++] = tarr[left++];
			} else {
				result += right-idx; //병합 정렬에서 swap이 일어나는 만큼 result에 카운트
				arr[idx++] = tarr[right++];
			}
		}
		while(left<=m) arr[idx++] = tarr[left++];
		while(right<=end) arr[idx++] = tarr[right++];
		
	}
}
