#금고털이

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        PriorityQueue<Jewel> queue = new PriorityQueue<Jewel>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            queue.offer(new Jewel(weight, price));
        }
        int result = 0;
        while(!queue.isEmpty()) {
        	Jewel jewel = queue.poll();
            if(W>jewel.weight) {
                result += jewel.weight * jewel.price;
                W -= jewel.weight;
            } else {
                result += W*jewel.price;
                break;
            }
        }
        System.out.println(result);
    }
}

class Jewel implements Comparable<Jewel> {
	int weight;
	int price;
	
	Jewel(int weight, int price) {
		this.weight = weight;
        this.price = price;
	}
	
	@Override
	public int compareTo(Jewel o) {
		return o.price - this.price;
	}
}

//Comparable & 우선순위큐 이용
//Comparable & ArrayList 이용하는 방법도 있음 (BOJ1922 참고)
