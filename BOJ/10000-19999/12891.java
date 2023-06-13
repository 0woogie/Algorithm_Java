#DNA 비밀번호

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] checkArr;
    static int[] myArr;
    static int checkSecret;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int result = 0;
        char[] A = new char[s];
        checkArr = new int[4];
        myArr = new int[4];
        checkSecret = 0;
        A = br.readLine().toCharArray();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            checkArr[i] = Integer.parseInt(st.nextToken());
            if (checkArr[i] == 0)
                checkSecret++;
        }
        for (int i = 0; i < p; i++) {
            Add(A[i]);
        }
        if (checkSecret == 4)
            result++;
        for (int i = p; i < s; i++) {
            int j = i - p;
            Add(A[i]);
            Remove(A[j]);
            if (checkSecret == 4)
                result++;
        }
        System.out.println(result);
        br.close();
    }

    private static void Add(char c) {
        switch (c) {
            case 'A':
                myArr[0]++;
                if (myArr[0] == checkArr[0])
                    checkSecret++;
                break;
            case 'C':
                myArr[1]++;
                if (myArr[1] == checkArr[1])
                    checkSecret++;
                break;
            case 'G':
                myArr[2]++;
                if (myArr[2] == checkArr[2])
                    checkSecret++;
                break;
            case 'T':
                myArr[3]++;
                if (myArr[3] == checkArr[3])
                    checkSecret++;
                break;
        }
    }

    private static void Remove(char c) {
        switch (c) {
            case 'A':
                if (myArr[0] == checkArr[0])
                    checkSecret--;
                myArr[0]--;
                break;
            case 'C':
                if (myArr[1] == checkArr[1])
                    checkSecret--;
                myArr[1]--;
                break;
            case 'G':
                if (myArr[2] == checkArr[2])
                    checkSecret--;
                myArr[2]--;
                break;
            case 'T':
                if (myArr[3] == checkArr[3])
                    checkSecret--;
                myArr[3]--;
                break;
        }
    }
}

/*
첫 번째 풀이
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int p = sc.nextInt();
        String dna = sc.next();
        char[] charArray = dna.toCharArray();
        int[] count = new int[4]; //차례대로 a,c,g,t
        int count1 = sc.nextInt();
        int count2 = sc.nextInt();
        int count3 = sc.nextInt();
        int count4 = sc.nextInt();
        int result = 0;
        for (int i = 0; i < p; i++) {
            if (charArray[i] == 'A') {
                count[0]++;
            } else if (charArray[i] == 'C') {
                count[1]++;
            } else if (charArray[i] == 'G') {
                count[2]++;
            } else if (charArray[i] == 'T') {
                count[3]++;
            }
        }
        if (count[0] >= count1 && count[1] >= count2 && count[2] >= count3 && count[3] >= count4) {
            result++;
        }
        int first = 0;
        for (int i = p; i < s; i++) {
            if (charArray[i-p] == 'A') {
                count[0]--;
            } else if (charArray[i-p] == 'C') {
                count[1]--;
            } else if (charArray[i-p] == 'G') {
                count[2]--;
            } else if (charArray[i-p] == 'T') {
                count[3]--;
            }
            if (charArray[i] == 'A') {
                count[0]++;
            } else if (charArray[i] == 'C') {
                count[1]++;
            } else if (charArray[i] == 'G') {
                count[2]++;
            } else if (charArray[i] == 'T') {
                count[3]++;
            }
            if (count[0] >= count1 && count[1] >= count2 && count[2] >= count3 && count[3] >= count4) {
                result++;
            }
        }
        System.out.println(result);
    }
}
*/
