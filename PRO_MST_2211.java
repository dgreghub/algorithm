package proExam;


	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.ArrayDeque;
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.Comparator;
	import java.util.Deque;
	import java.util.PriorityQueue;
	import java.util.StringTokenizer;


	// 백준 2211 네트워크 복구 
	// Prim을 통한 다익스트라와 유사함.
	public class PRO_MST_2211{
		static int N,M;
		
		static int[] prev;
	    static int[] dist;
		
		static ArrayList<computer> [] nodeList; //방문확인 배열
		static int Solution;
		static ArrayList<computer> sol;
		public static void main(String args[]) throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			prev = new int[N + 1]; //연결된 부모
		    dist = new int[N + 1]; //방문한 것을 표기함.
			nodeList = new ArrayList[N+1]; //각 노드의 연결상태 저장				
			Solution=0;
			sol = new ArrayList<computer>();
			for(int cnt=1; cnt<N+1; cnt++){
				nodeList[cnt] = new ArrayList<computer>();
				 dist[cnt] = Integer.MAX_VALUE;
			}
			
			for(int cnt=0; cnt<M; cnt++){
				StringTokenizer spec = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(spec.nextToken());
				int b = Integer.parseInt(spec.nextToken());
				int c = Integer.parseInt(spec.nextToken());
				//각 노드에 해당하는 연결가능성이 있는 값들을 모두 넣음
				nodeList[a].add(new computer(a,b,c));
				nodeList[b].add(new computer(b,a,c));
			}
			MST();
			System.out.println(Solution);
			for(int cnt=0; cnt<sol.size(); cnt++){
				System.out.println(sol.get(cnt).a+" "+sol.get(cnt).b);
			}
		}
		
		public static void MST(){
			//우선 순위 큐를 활용하기 위하여 Min heap을 구현
			//비용이 가장 작은 간선을 바로 뽑기 위한 우선순위 큐
			PriorityQueue<computer> pq = new PriorityQueue<computer>();
			//정점을 모두 방문하는데 쓸 큐
			
			pq.add(new computer(0,1,0));
			while(!pq.isEmpty()){
				
				computer pc = pq.poll();			
				if (dist[pc.b] != Integer.MAX_VALUE){
					continue;
				}
				dist[pc.b] = pc.c;
				
				if(pc.a != 0 ){
	                prev[pc.b] = pc.a;
	            }
				// 해당 노드 방문 처리해서 한 번방문해서 간선이 연결된 노드는 다시 처리 안함
				for(int cnt=0; cnt<nodeList[pc.b].size() ; cnt++){
					computer next = nodeList[pc.b].get(cnt);
	                if (dist[next.b] != Integer.MAX_VALUE){
	                	continue;
	                }
	                pq.add(new computer(next.a, next.b, next.c + pc.c));
				}
			}
			for (int i = 0; i < prev.length; i++) {
	            if (prev[i] != 0) {
	            	Solution++;
	            	sol.add(new computer(i, prev[i],0));
	            }
	        }
		}
		
		public static class computer implements Comparable<computer>{
			int a;
			int b;
			int c;
			
			public computer(int a, int b, int c){
				super();
				this.a=a;
				this.b=b;
				this.c=c;
			}

			public int compareTo(computer o) {
				return this.c-o.c;
			}

		}

	}

