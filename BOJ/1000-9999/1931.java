#회의실 배정

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1931 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][2];
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		//끝나는 시간 기준으로 오름차순 정렬 (끝나는 시간이 같으면 시작 시간이 빠른 회의가 먼저 와야 한다. ex> (0,2)와 (2,2)가 있는 경우)
		Arrays.sort(arr, (o1, o2) -> {
		    if(o1[1] == o2[1]){
		        return Integer.compare(o1[0], o2[0]);
		    } else {
		        return Integer.compare(o1[1], o2[1]);
		    }
		});
		int cnt = 0;
		int lastIdx = 0;
		for(int i=0; i<n; i++) {
			if(arr[i][0]>=lastIdx) {
				cnt++;
				lastIdx = arr[i][1];
			}
		}
		System.out.println(cnt);
	}
}

//참고 - https://gre-eny.tistory.com/2
