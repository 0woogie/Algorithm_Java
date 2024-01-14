#듣보잡

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class BOJ_1764 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		HashSet<String> set = new HashSet<>();
		for(int i=0; i<N; i++)
			set.add(sc.next());
		int cnt = 0;
		List<String> list = new ArrayList<>();
		for(int i=0; i<M; i++) {
			String target = sc.next();
			if(set.contains(target)) {
				list.add(target);
				cnt++;
			}
		}
		Collections.sort(list);
		System.out.println(cnt);
		for(String s : list)
			System.out.println(s);
	}
}
