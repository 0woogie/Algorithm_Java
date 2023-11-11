#싸이버개강총회

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_19583 {
	public static void main(String[] args) throws IOException {
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String S = st.nextToken();
        String E = st.nextToken();
        String Q = st.nextToken();

        Set<String> before = new HashSet<>(); //개강총회 이전에 입장한 학생 담는 Set
        Set<String> after = new HashSet<>(); //개강총회 이후에 퇴장한 학생 담는 Set
        
        String str = null;
        while((str = br.readLine()) != null) { //종종 써먹는 syntax
            String[] s = str.split(" ");
            String time = s[0];
            String name = s[1];

            if(S.compareTo(time) >= 0) { //개강총회가 시작한 시간 이전에 대화를 한 적이 있는 학회원
                before.add(name);
            }else if(E.compareTo(time) <= 0 && Q.compareTo(time) >= 0) { //개강총회가 끝나고 스트리밍이 끝날 때까지 대화를 한 적이 있는 학회원
                after.add(name);
            }
        }

        int result = 0;
        for(String name : before) { //개강총회 전에 입장한 학생들만 확인하면 됨
            if(after.contains(name)) {
                result += 1;
            }
        }
        System.out.println(result);
	}
}

//모든 시간의 입력은 HH:MM형식으로 동일하기 때문에 compareTo를 사용한 시간의 비교가 가능함
//str1.compareTo(str2): str1 문자의 ASCII 코드값에서 str2 문자의 ASCII 코드값을 뺀 차이값을 반환
//참고 - https://m.blog.naver.com/hyoun1202/222113488861
