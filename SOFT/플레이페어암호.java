#플레이페어 암호

import java.io.*;
import java.util.*;

public class Main {

    static char[][] graph;
    static HashSet<Character> set;
    static int idx1, idx2;
    static String result = "";
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String mString = sc.next();
        char[] message = mString.toCharArray();
        String kString = sc.next();
        char[] key = kString.toCharArray();
        String alpaString = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        char[] alpa = alpaString.toCharArray();
        graph = new char[5][5];
        set = new HashSet<>();
        //1. 표 그리기
        for(char k : key)
            makeGraph(k);
        for(char a : alpa)
            makeGraph(a);
        //2. 암호화
        int idx = 0;
        while(true) {
            //암호화할 두 글자 고르기
            char a = message[idx++];
            char b;
            if(idx==message.length) {
                b = 'X';
                encrypt(a, b);
                break;
            } else {
                b = message[idx++];
            }
            if(a==b) { //만약 두 글자가 같으면 "X or Q 넣기"
                if(a=='X')
                    encrypt(a, 'Q');
                else
                    encrypt(a, 'X');
                idx--; //b는 다음 턴으로 미룸
            } else { //두 글자 다르면 암호화 진행
                encrypt(a, b);
                if(idx==message.length)
                    break;
            }
        }
        System.out.println(result);
    }

    static void makeGraph(char c) {
        if(!set.contains(c)) {
            set.add(c);
            graph[idx1][idx2] = c;
            idx2++;
            if(idx2==5) {
                idx1++;
                idx2 = 0;
            }
        }
    }

    static void encrypt(char a, char b) {
        int ai = -1, aj = -1, bi = -1, bj = -1;
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                if(graph[i][j]==a) {
                    ai = i;
                    aj = j;
                }
                if(graph[i][j]==b) {
                    bi = i;
                    bj = j;
                }
            }
        }
        if(ai==bi) { //같은 행에 존재하면
            result += Character.toString(graph[ai][(aj+1)%5]);
            result += Character.toString(graph[bi][(bj+1)%5]);
        } else if(aj==bj) { //같은 열에 존재하면
            result += Character.toString(graph[(ai+1)%5][aj]);
            result += Character.toString(graph[(bi+1)%5][bj]);
        } else { //다른 행, 열에 존재하면
            result += Character.toString(graph[ai][bj]);
            result += Character.toString(graph[bi][aj]);
        }
    }
}

/*
[개선할만한 부분들]
1. 표 그리는 단계에서 행과 열 index 정해주는 것과 알파벳 차례대로 순회하는 방법
  - map[idx / 5][idx % 5] = (char) (i + 'A');
2. 두 글자씩 나누는 단계에서 Queue 활용하는 방법
  - 참고: https://velog.io/@ajongs/소프티어-인증평가3차-기출-플레이페어-암호
*/
