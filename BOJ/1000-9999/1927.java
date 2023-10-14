#최소 힙

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class B1927 {
	
	static List<Integer> list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		list = new ArrayList<>();
		list.add(0);
		for(int i=0; i<n; i++) {
			int x = sc.nextInt();
			if(x==0) {
				if(list.size()==1) {
					System.out.println(0);
				} else {
					System.out.println(delete());
				}
			} else {
				insert(x);
			}
		}
	}
	
	static void insert(int x) {
		//1. 트리 마지막에 값 삽입
		list.add(x);
		
		int current = list.size() - 1;
		int parent = current / 2;
		while(true) {
			//2. root에 도달했거나 이미 최소힙 상태면 반복문 탈출
			if(parent==0 || list.get(parent)<=list.get(current))
				break;
			//3. swipe 후 반복문 다시 진행
			int tmp = list.get(parent);
			list.set(parent, list.get(current));
			list.set(current, tmp);
			current = parent;
			parent = parent / 2;
		}
	}
	
	static int delete() {
		//1. root 값 빼기
		if(list.size()==2) {
			return list.remove(1);
		}
		int top = list.get(1);
		//2. 가장 마지막 값을 root로 올리기
		list.set(1, list.remove(list.size()-1));
		
		int currentPos = 1;
		while(true) {
			//리프에 도달했으면 반복문 탈출
			int leftPos = currentPos*2;
			int rightPos = currentPos*2+1;
			if(leftPos>=list.size()) {
				break;
			}
			
			//자식(최대 2개) 중 값이 작은 노드 선택
			int minValue = list.get(leftPos);
			int minPos = leftPos;
			
			if(rightPos<list.size() && minValue > list.get(rightPos)) {
				minValue = list.get(rightPos);
				minPos = rightPos;
			}
			
			//최소힙 상태가 아니면 swipe 후 반복문 다시 진행
			if(list.get(currentPos) > minValue) {
				int tmp = list.get(minPos);
				list.set(minPos, list.get(currentPos));
				list.set(currentPos, tmp);
				currentPos = minPos;
			} else {
				break;
			}
		}
		return top;
	}

}
