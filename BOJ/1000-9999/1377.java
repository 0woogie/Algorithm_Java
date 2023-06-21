#버블 소트

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        mData[] A = new mData[n];
        for (int i = 0; i < n; i++) {
            A[i] = new mData(Integer.parseInt(br.readLine()), i);
        }
        Arrays.sort(A); //여기서 compareTo() 내부적으로 사용
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (max < A[i].index - i)
                max = A[i].index - i;
        }
        System.out.println(max + 1);
    }
}

class mData implements Comparable<mData> {
    int value;
    int index;

    public mData(int value, int index) {
        super();
        this.value = value;
        this.index = index;
    }

    @Override
    public int compareTo(mData o) {
        return this.value - o.value;
    }
}

/*
Comparable<T> 인터페이스
- Comparable 인터페이스는 객체를 정렬하는 데 사용되는 compareTo() 메서드를 정의함
- 자바에서는 같은 타입의 인스턴스를 서로 비교해야만 하는 클래스들은 모두 Comparable 인터페이스를 구현하고 있음
참고- http://www.tcpschool.com/java/java_collectionFramework_comparable
*/
