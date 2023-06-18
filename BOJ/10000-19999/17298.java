#오큰수

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        int[] R = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < n; i++) {
            //스택이 비어 있지 않고 현재 값이 스택 top 인덱스가 가리키는 값보다 큰 경우
            while (!stack.isEmpty() && A[stack.peek()] < A[i]) {
                R[stack.pop()] = A[i]; //정답 배열에 오큰수 저장
            }
            stack.push(i); //현재 값에 대한 오큰수 갱신 끝났으면 해당 값의 인덱스를 stack에 push
        }
        while (!stack.isEmpty()) {
            R[stack.pop()] = -1;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i=0; i<n; i++)
            bw.write(R[i] + " ");
        bw.write("\n");
        bw.flush();
    }
}
