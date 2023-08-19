#후보 추천하기

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class B1713 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		ArrayList<Person> list = new ArrayList<>();
		for(int i=0; i<m; i++) {
			int x = sc.nextInt();
			boolean check = false;
			for(int j=0; j<list.size(); j++) {
				if(list.get(j).number==x) { //현재 사진이 게시되어 있는 경우
					Person p = list.get(j);
					p.count++; //추천받은 횟수만 증가시키기
					list.set(j, p);
					check = true;
					break;
				}
			}
			if(!check) { //새롭게 추천받은 학생의 사진 게시
				if(list.size()<n) { //그냥 추가하면 됨
					list.add(new Person(x,1,i));
				} else { //기존 사진 하나 제거
					list.remove(list.size()-1);
					list.add(new Person(x,1,i));
				}
			}
			Collections.sort(list);
		}
		Collections.sort(list, (o1,o2) -> o1.number - o2.number);
		for(Person p : list) {
			System.out.print(p.number+" ");
		}
	}
}

class Person implements Comparable<Person> {
	int number;
	int count;
	int time;
	
	public Person(int number, int count, int time) {
		super();
		this.number = number;
		this.count = count;
		this.time = time;
	}

	@Override
	public int compareTo(Person o2) {
		if(count==o2.count)
			return o2.time - time;
		else
			return o2.count - count;
	}
}

//Comparable과 Comparator을 적절히 이용해야 하는 문제
/*
  삭제 대상 1순위를 ArrayList의 맨 앞에 두면 remove()할 때마다 나머지 요소들이 앞으로 한 칸씩 이동해야 함
  -> 삭제 대상 1순위를 ArrayList 맨 뒤에 두게끔 정렬 기준 적용하기
*/
