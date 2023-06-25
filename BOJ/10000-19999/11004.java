#K번째 수

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        quickSort(A, 0, n - 1, k - 1);
        System.out.println(A[k - 1]);
    }

    public static void quickSort(int[] A, int start, int end, int k) {
        if (start < end) {
            int pivot = partition(A, start, end);
            if (pivot == k)
                return;
            else if (k < pivot)
                quickSort(A, start, pivot - 1, k);
            else
                quickSort(A, pivot + 1, end, k);
        }
    }

    public static int partition(int[] A, int start, int end) {
        if (start + 1 == end) {
            if (A[start] > A[end])
                swap(A, start, end);
            return end;
        }
        int mid = (start + end) / 2;
        swap(A, start, mid); //편의를 위해 중앙값을 1번째 요소로 이동하기
        int pivot = A[start];
        int i = start + 1;
        int j = end;
        while (i <= j) {
            while (j >= start + 1 && pivot < A[j]) { //피벗보다 작은 수가 나올 때까지 j--
                j--;
            }
            while (i <= end && pivot > A[i]) { //피벗보다 큰 수가 나올 때까지 i++
                i++;
            }
            if (i <= j) {
                swap(A, i++, j--);
            }
        }
        //피벗 데이터를 나눠진 두 그룹의 경계 index에 저장하기
        A[start] = A[j];
        A[j] = pivot;
        return j;
    }

    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
