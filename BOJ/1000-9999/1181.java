#단어 정렬

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class B1181 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String[] arr = new String[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.next();
		}
		Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.length()==o2.length())
					return o1.compareTo(o2);
				else
					return o1.length()-o2.length();
			}
		});
		System.out.println(arr[0]);
		for(int i=1; i<N; i++) {
			if(!arr[i].equals(arr[i-1]))
				System.out.println(arr[i]);
		}
	}
}

//https://velog.io/@tlsdmsgp33/%EB%B0%B1%EC%A4%80-1181%EB%B2%88-%EB%8B%A8%EC%96%B4-%EC%A0%95%EB%A0%AC-java-%ED%92%80%EC%9D%B4
