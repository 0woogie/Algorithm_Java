#삽입정렬

import java.util.Scanner;

public class J1158 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
        	arr[i] = sc.nextInt();
        for(int i=1; i<n; i++) {
        	int insertIdx = i;
        	for(int j=0; j<=i-1; j++) {
        		if(arr[j]>arr[i]) {
        			insertIdx = j;
        			break;
        		}
        	}
        	int temp = arr[i];
        	for(int j=i-1; j>=insertIdx; j--)
        		arr[j+1] = arr[j];
        	arr[insertIdx] = temp;
            for(int k=0; k<n; k++) System.out.print(arr[k]+" ");
            System.out.println();
        }
	}
}
