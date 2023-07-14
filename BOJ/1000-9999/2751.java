#수 정렬하기 2
//병합 정렬을 구현해서 정렬

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2751 {

	static int[] arr;
	static int[] tarr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		tarr = new int[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		msort(0, N-1); //0~N-1 까지 오름차순 정렬
		StringBuilder ans = new StringBuilder();
		for(int n: arr) ans.append(n).append("\n");
		System.out.println(ans);
	}
	
	public static void msort(int left, int right) {
		//0.종료조건 체크 => 종료
		if(left==right) return;
		//1.절반을 나눠서, 양쪽을 정렬(msort)
		int m = (left+right)/2;
		msort(left, m);		//왼쪽정렬
		msort(m+1, right);	//오른쪽정렬
		//2.합치기
		//임시 tarr에 원본데이터 복사(지금 병합할 부분만!)
		for(int i=left; i<=right; i++) tarr[i]=arr[i];
		int l=left, r=m+1, i=left;
		//3.둘 중 하나 소진될때까지 작은값부터 붙임  
		while(l<=m && r<=right) {	//둘 중 작은값 붙이기
			if (tarr[l]<tarr[r])	arr[i++] = tarr[l++];
			else					arr[i++] = tarr[r++];
		}
		//4.남은 배열의 값 뒤에 붙이기(둘 중 하나만 남아있음)
		while(l<=m) 		arr[i++] = tarr[l++];
		while(r<=right)	arr[i++] = tarr[r++];
	}

}
