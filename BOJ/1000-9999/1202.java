#보석 도둑

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class B1202 {
	
	public static void main(String[] args) { //보석 개수 N개, 가방 개수 K개 (1<=N,K<=300,000)
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		Jewel[] jewels = new Jewel[N];
		int[] bags = new int[K];
		
		for(int i=0; i<N; i++) {
			jewels[i] = new Jewel(sc.nextInt(), sc.nextInt());
		}
		
		for(int i=0; i<K; i++) {
			bags[i] = sc.nextInt();
		}
		
		//가방 오름차순 정렬 O(NlogN)
		Arrays.sort(bags);
		
		//보석 무게 오름차순 정렬 O(NlogN)
		Arrays.sort(jewels, Comparator.comparingInt(Jewel::getWeight));
		
		//보석 가격 최대힙 (java의 PriorityQueue는 내부적으로 힙 구현)
		PriorityQueue<Jewel> pq = new PriorityQueue<>(Comparator.comparingInt(Jewel::getValue).reversed());
		
		long result = 0;
		int jIndex = 0;
		//1. 남은 가방 중 제일 작은 가방을 선택 O(N)
		for(int i=0; i<bags.length; i++) {
			//2. 선택된 가방에 넣을 수 있는 남은 보석 중 가장 비싼 보석을 선택 O(NlogN)
			 while(jIndex<N && jewels[jIndex].weight<=bags[i]) {
				 pq.add(jewels[jIndex++]);
			 }
			 if(!pq.isEmpty()) {
				 result += pq.poll().value;
			 }
		}
		System.out.println(result);
	}

}

class Jewel {
	int weight;
	int value;
	
	public int getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}
	
	public Jewel (int weight, int value) {
		this.weight = weight;
		this.value = value;
	}
}

/*
<대표적인 힙 유형 문제>
- 그리디처럼 가장 OO한 것 고르는 문제
- 이때 정렬 기준이 여러개일때
=> 위 문제를 해결한 것과 같이 정렬과 pq를 활용한다면 PriorityQueue는 특정 시점에 두 가지 정렬 조건을 만족하는 무언가를 Top에 가지게 됨
*/
