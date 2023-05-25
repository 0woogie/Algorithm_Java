#잃어버린 괄호

import java.util.Scanner;

public class Main {
    static int answer = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String example = sc.nextLine();
        String[] str = example.split("-");
        for (int i = 0; i < str.length; i++) {
            int result = mySum(str[i]);
            if (i==0)
                answer += result;
            else
                answer -= result;
        }
        System.out.println(answer);
    }

    static int mySum(String a) {
        int total = 0;
        String[] temp = a.split("[+]"); //'+'의 경우 특별한 의미로 사용되기도 해서 '[]'를 붙여줘야 함
        for (int i = 0; i < temp.length; i++) {
            total += Integer.parseInt(temp[i]);
        }
        return total;
    }
}
