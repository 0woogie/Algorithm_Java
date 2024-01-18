#조약돌 꺼내기

import java.util.Scanner;

public class B13251 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		double[] arr = new double[M];
		double total = 0;
		for(int i=0; i<M; i++) {
			arr[i] = sc.nextDouble();
			total += arr[i];
		}
		int K = sc.nextInt();
		double result = 0;
		for(int i=0; i<M; i++) {
			result += combi(arr[i], K);
		}
		System.out.println(result/combi(total, K));
	}
	
	static double combi(double a, double b) {
		if(a<b)
			return 0;
		double x = 1.0;
		double y = 1.0;
		for(double i=a; i>a-b; i--)
			x *= i;
		for(double i=1.0; i<=b; i++)
			y *= i;
		return x/y;
	}
}
