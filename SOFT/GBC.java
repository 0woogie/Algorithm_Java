#GBC

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        ArrayList<int[]> A = new ArrayList<>();
        ArrayList<int[]> B = new ArrayList<>();
        for(int i=0; i<N; i++) {
            A.add(new int[]{sc.nextInt(), sc.nextInt()});
        }
        for(int i=0; i<M; i++) {
            B.add(new int[]{sc.nextInt(), sc.nextInt()});
        }
        int indexA = 0;
        int indexB = 0;
        int[] target = A.get(indexA);
        int[] now = B.get(indexB);
        int result = 0;
        while(true) {
            if(target[1]<now[1])
                    result = Math.max(result, now[1]-target[1]);
            if(target[0]>now[0]){ //구간 길이가 더 긴 경우 -> 테스트 길이 갱신 필요
                target[0] -= now[0];
                now = B.get(++indexB);
            } else if(target[0]<now[0]){//테스트 길이가 더 긴 경우 -> 구간 길이 갱신 필요
                now[0] -= target[0];
                target = A.get(++indexA);
            } else{ //두 길이 같은 경우 -> 둘 다 갱신 (끝났는지 체크 필요)
                if(indexA+1==A.size())
                    break;
                target = A.get(++indexA);
                now = B.get(++indexB);
            }
        }
        System.out.println(result);
    }
}

//문제에서의 구간이 0m~100m밖에 되지 않기 때문에 배열을 활용하는 방식이 더 간편할 듯
//참고링크 - https://yes-admit.tistory.com/97
