
public class BOJ_1197_최소스패닝트리 {
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.Collections;
	import java.util.StringTokenizer;


	//백준 1197 최소 스패닝 트리
	public class Main{
		
		static ArrayList<spannig> spannigArr;
		static int parents[], rank[];
		static int N,M;
		static int solution=0;
		public static void main(String []args) throws NumberFormatException, IOException{
			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			N =  Integer.parseInt(st1.nextToken()); // V(1 ≤ V ≤ 10,000)
			M = Integer.parseInt(st1.nextToken()); //E(1 ≤ E ≤ 100,000)
			spannigArr = new ArrayList<spannig>();
			for (int cnt=0; cnt<M; cnt++){
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); 
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				spannigArr.add(new spannig(a,b,c));
			}
			parents = new int [N+1];
			rank = new int [N+1];
			//0. 간선가중치 정렬
			Collections.sort(spannigArr);
			//1. MST 준비
			MST();
			//2. 결과출력
			System.out.println(solution);
		}
		public static class spannig implements Comparable<spannig>{
			int a;
			int b;
			int c;
			public spannig(int a, int b, int c){
				this.a=a;
				this.b=b;
				this.c=c;
			}
			public int compareTo(spannig other) {	
				return this.c-other.c;
			}
		} 
		
		public static void MST(){
			//1. Root와 Rank의 값을 초기화 세팅
			for(int cnt=1; cnt<N+1; cnt++){
				parents[cnt]=cnt;
				rank[cnt]=1;
			}
			//2.Union-Find를 통하여, 해당 하는 MST를 최적화를 찾는다.
			for(int cnt=0; cnt<spannigArr.size(); cnt++){
				int rootA = find(spannigArr.get(cnt).a);
				int rootB = find(spannigArr.get(cnt).b);
				
				if(rootA == rootB){
					continue;
				}
				// "union-by-rank 최적화"
				// 항상 높이가 더 낮은 트리를 높이가 높은 트리 밑에 넣는다. 즉, 높이가 더 높은 쪽을 root로 삼음
				if(rank[rootA] < rank[rootB]) {
					parents[rootA] = rootB; //A의 root를 B로 변경
				}else{
					parents[rootB] = rootA; //B의 root를 A로 변경
					if(rank[rootA]==rank[rootB]){
						rank[rootA]++;
					}
				}
				solution+=spannigArr.get(cnt).c;
			}
		}
		
		// Find 메소드
		public static int find(int idx){
			if(parents[idx]==idx){
				return idx;
			}else{
				return parents[idx]=find(parents[idx]);
			}
		}
	}
