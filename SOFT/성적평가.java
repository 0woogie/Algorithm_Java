#성적 평가

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        Contest[][] c = new Contest[4][N];
        for(int i=0; i<3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                c[i][j] = new Contest(j, Integer.parseInt(st.nextToken())); //참가자 번호, 점수
            }
        }

        for(int i=0; i<N; i++){
            int total = 0;
            for(int j=0; j<3; j++){
                total += c[j][i].score;
            }
            c[3][i] = new Contest(i, total);
        }

        //3개의 대회에 대한 등수, 전체 등수 출력
        //성적 순 내림차순 정렬
        for(Contest[] con : c) {
            Arrays.sort(con, (o1, o2) -> {
                return Integer.compare(o2.score, o1.score);
            });
            /*
            또다른 정렬 방식
            Arrays.sort(con, new Comparator<Contest>(){
                @Override
                public int compare(Contest o1, Contest o2) {
                    return Integer.compare(o2.score, o1.score);
                }
            });
            */
        }

        int[][] r = new int[4][N];
        for(int i=0; i<4; i++){
            int rank = 1;
            int cnt = 1;
            r[i][c[i][0].num] = rank;
            for(int j=1; j<N; j++){
                //점수가 같으면 rank가 같은
                if(c[i][j-1].score == c[i][j].score){
                    r[i][c[i][j].num] = rank;
                    cnt++;
                }
                else {
                    rank += cnt;
                    r[i][c[i][j].num] = rank;
                    cnt = 1;
                }
            }
        }

        for(int i=0; i<4; i++){
            for(int j=0; j<N-1; j++){
                System.out.print(r[i][j] + " ");
            }
            System.out.print(r[i][N-1]);
            System.out.println();
        }
    }
}

class Contest {
    int num, score;
    Contest(int num, int score) {
        this.num = num;
        this.score = score;
    }
}
