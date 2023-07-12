#삽입정렬 횟수 세기

import java.util.Scanner;

public class J1814 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
        	arr[i] = sc.nextInt();
        int cnt = 0;
        for(int i=1; i<n; i++) {
        	int insertIdx = i;
        	for(int j=0; j<=i-1; j++) {
        		if(arr[j]>arr[i]) {
        			insertIdx = j;
        			break;
        		}
        	}
        	int temp = arr[i];
        	for(int j=i-1; j>=insertIdx; j--) {
        		arr[j+1] = arr[j];
        		cnt++;
        	}
        	arr[insertIdx] = temp;
        }
        System.out.println(cnt);
	}
}
