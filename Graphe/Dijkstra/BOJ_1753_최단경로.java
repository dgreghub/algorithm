
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753_최단경로 {
	static int N;//정점의 갯수
	static int M;//간선의 갯수
	static int vertax[];//최단 Vertax
	static int visit[];//visit
	static ArrayList<ArrayList<vertex>> add;
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());//시작노드
		add = new ArrayList<ArrayList<vertex>>();
		
		vertax = new int [N];
		visit = new int [N];
		for(int i=0; i<N; i++){
			vertax[i]=Integer.MAX_VALUE;
			visit[i]=0;
			add.add(new ArrayList<vertex>());
		}
		
		for(int i=0; i<M; i++){
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st1.nextToken());
			int y=Integer.parseInt(st1.nextToken());
			int z=Integer.parseInt(st1.nextToken());
			add.get(x-1).add(new vertex(y-1,z));//해당 경로 최소값만 삽입 각 노드의 최소값 삽입
		}
		
		
		dijkstra(start-1);
		for(int i=0; i<N; i++){
			System.out.println((vertax[i]==Integer.MAX_VALUE)?"INF":vertax[i]);
		}
	}
	
	//다익스트라로 최단 경로 산출
		static void dijkstra(int start){
			PriorityQueue <vertex>queue = new PriorityQueue<vertex>();
			//맨처음 거리는 0으로 Default 세팅한다.
			vertax[start]=0;
			queue.offer(new vertex(start, vertax[start])); // 초기값 생성
			while(!queue.isEmpty()){
				int nowVertex = queue.peek().getIndex();
				int cost = queue.peek().getWeight();
				queue.poll(); //사용된 큐는 삭제
				if(visit[nowVertex]==1)continue;
				if(cost>vertax[nowVertex])continue;  //최소거리인지 확인 하고서 무시
				visit[nowVertex]=1;
				
				for(int i=0; i<add.get(nowVertex).size(); i++){ //해당 정접에 인접한 리스트 사이즈 만큼 검색
					 if(visit[add.get(nowVertex).get(i).getIndex()]==0){
						 int e = add.get(nowVertex).get(i).getIndex();
						 int w = add.get(nowVertex).get(i).getWeight();
						 
						 vertax[e] = Math.min(vertax[e],vertax[nowVertex]+w);
						 queue.offer(new vertex(e,vertax[e]));
					 }
				}
				/*for(int i=0; i<=N; i++) {
					if(add.get(nowVertex).get(i).weight != 11 && vertax[i] > vertax[nowVertex] + add.get(nowVertex).get(i).weight) { //신규최단거리 생성
						vertax[i] = vertax[nowVertex] + add.get(nowVertex).get(i).weight;
						queue.offer(new vertex(i, vertax[i]));
					}
				}*/
			}
		}
		static class vertex implements Comparable<vertex>{
			private int index;
			private int weight;
			public vertex (int index, int weight){
				this.index = index;
				this.weight  =weight;
			}
			public int getIndex() {
				return index;
			}
			public int getWeight() {
				return weight;
			}
			public int compareTo(vertex another) {
				return this.weight - another.weight;
			}
		}
}
