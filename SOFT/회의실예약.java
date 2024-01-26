#회의실 예약

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Meeting> list = new ArrayList<>(); //회의실 이름과 visited 배열 인덱스 저장하는 리스트
        boolean[][] visited = new boolean[N][9]; //각 회의의 9~18시 예약 테이블 (visited[x][0]==true: 9~10시 예약 존재함, visited[x][2]==true: 11~12시 예약 존재함)
        for(int i=0; i<N; i++){
            String name = br.readLine();
            list.add(new Meeting(name, i));
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            String now = st.nextToken(); //예약된 회의실 이름
            int s = Integer.parseInt(st.nextToken()); //시작 시각
            int e = Integer.parseInt(st.nextToken()); //종료 시각
            for(Meeting m : list) {
                if(now.equals(m.name)){
                    int target = m.index; //회의실 이름에 매핑된 visited 배열 위치
                    for(int x=s-9; x<e-9; x++)
                        visited[target][x] = true; //회의실 예약!!
                    break;
                }
            }
        }
        Collections.sort(list); //회의실 이름 오름차순 정렬
        //아래부턴 결과 출력 파트
        int horizon = 1; //구분선 출력용 변수
        for(Meeting m : list) {
            System.out.println("Room "+m.name+":");
            int index = m.index; //회의실 이름에 매핑된 visited 배열 위치
            int cnt = 0; //현재 예약 가능한 시간대 몇 개인지 카운트하기 위해
            String[] result = new String[5]; //9~18 중간중간 예약 있으면 -> 예약 가능한 시간대는 최대 5개
            int idx = 0;
            while(true) {
                if(!visited[index][idx]) { //회의실 예약 가능하다면 -> 시간대 result[]에 저장하고 cnt 증가시키기
                    if(idx==0)
                        result[cnt] = "09-";
                    else
                        result[cnt] = idx+9+"-";
                    int end = idx;
                    while(true) {
                        end++;
                        if(end>8 || visited[index][end])
                            break;
                    }
                    result[cnt] += Integer.toString(end+9);
                    idx = end;
                    cnt++;
                } else {
                    idx++;
                }
                //18시까지 다 봤으면 탈출
                if(idx==9)
                    break;
            }
            if(cnt==0){
                System.out.println("Not available");
            } else {
                System.out.println(cnt+" available:");
                for(int i=0; i<cnt; i++)
                    System.out.println(result[i]);
            }
            if(horizon<N)
                System.out.println("-----");
            horizon++;
        }
    }
}

class Meeting implements Comparable<Meeting> {
    String name;
    int index;

    Meeting(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
	public int compareTo(Meeting o) {
		    //return this.name - o.name; 잘못된 코드
        return this.name.compareTo(o.name); //문자열 정렬
	}
}
