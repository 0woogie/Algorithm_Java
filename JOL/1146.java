#선택정렬

import java.util.Scanner;

public class J1146 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
        	arr[i] = sc.nextInt();
        for(int i=0; i<n-1; i++) {
        	int minNum = arr[i];
        	int targetIdx = i;
            for(int j=i+1; j<n; j++) {
            	if(arr[j]<minNum) {
            		minNum = arr[j];
            		targetIdx = j;
            	}
            }
            int temp = arr[i];
            arr[i] = arr[targetIdx];
            arr[targetIdx] = temp;
            for(int k=0; k<n; k++) System.out.print(arr[k]+" ");
            System.out.println();
        }
	}
}
