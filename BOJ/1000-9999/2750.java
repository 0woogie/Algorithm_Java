#수 정렬하기
//퀵 정렬을 구현해서 정렬

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2750 {

	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		qsort(0, N-1); //0~N-1 까지 오름차순 정렬
		StringBuilder ans = new StringBuilder();
		for(int n: arr) ans.append(n).append("\n");
		System.out.println(ans);
	}
	
	public static void qsort(int l, int r) {
		//재귀함수는 시작부분에서 종료조건 체크 및 종료
		if(l>=r) return;
		int p = partition(l, r); //p기준 양쪽 정렬
		qsort(l, p-1);	//왼쪽정렬
		qsort(p+1, r);	//오른쪽정렬
	}
	
	public static int partition(int l, int r) {
		int p = l++; //p, l, r 초기값 설정
		while(l<r) { // l, r 자리잡고 교환
			while(l<r && arr[p]>arr[l]) l++;
			while(l<r && arr[p]<arr[r]) r--;
			//l<r 조건을 만족하지 않아서 탈출했는지 체크필요
			if(l<r) { //자리잡았다면 교환!
				int t=arr[l]; arr[l]=arr[r]; arr[r]=t;
			}
		}
		//while문 탈출 == (l==r), p만 자리잡고 교환!
		if(arr[p] <= arr[l]) l--;
		int t=arr[p]; arr[p]=arr[l]; arr[l]=t;
		return l;
	}

}

/*
//버블 정렬을 구현해서 정렬
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        //sort() 함수를 이용할 수도 있지만 아래와 같이 직접 정렬을 구현하는 방식으로 해결
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (A[j] > A[j + 1]) {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(A[i]);
        }
    }
}
*/
