#숫자의 합

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int total = 0;
        String stringArray = sc.next();
        char[] charArray = stringArray.toCharArray();
        for (int i = 0; i < n; i++) {
            total += charArray[i] - '0';
        }
        System.out.println(total);
    }
}
