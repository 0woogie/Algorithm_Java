#여행 가자

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1976 {

	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		parent = new int[n+1];
		for(int i=1; i<=n; i++) {
			parent[i] = i;
		}
		for(int i=1; i<=n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				int value = Integer.parseInt(st.nextToken());
				if(value==1) union(i,j);
			}
		}
		int[] plan = new int[m];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
		boolean possible = true;
		int norm = find(plan[0]);
		for(int i=1; i<m; i++) {
			if(find(plan[i])!=norm) possible = false;
		}
		if(possible) System.out.println("YES");
		else System.out.println("NO");
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a<b) parent[b] = a;
		else parent[a] = b;
	}

	private static int find(int x) {
		if(parent[x]!=x) 
			parent[x] = find(parent[x]);
		return parent[x];
	}

}
