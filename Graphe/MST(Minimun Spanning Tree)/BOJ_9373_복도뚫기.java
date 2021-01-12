import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//백준 복도 뚫기 MST - 크루스칼
public class BOJ_9373_복도뚫기 {
	
	static int W,N, left,right;
	static ArrayList <sensor> sensorArr;
	static PriorityQueue<Edge> edgeQueue;
	static int parents[];
	static double solution;
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // (1 ≤ T ≤ 100) 
		for(int testCse=0; testCse<T; testCse++){
			W = Integer.parseInt(br.readLine()); // 복도의 넓이   (1 ≤ W ≤ 100 000)
			N = Integer.parseInt(br.readLine()); // 센서의 갯수  (1 ≤ N ≤ 100 000)
			sensorArr = new ArrayList<sensor>();
			solution=0;
			//다른 MST의 부모 배열과 다르게 [좌측 벽]과 [우측 벽]을 사용할 예정이므로, N+2의 공감으로 만든다.
			parents = new int [N+2];
			edgeQueue =  new PriorityQueue <Edge>();
			for(int cnt=0; cnt<N; cnt++){
				//부모배열 초기화
				parents[cnt+1]=cnt+1;
				//각 센서의 인자값 입력
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				sensorArr.add(new sensor(x,y,r));
			}
			MST();
			System.out.println(solution);
		}
	}
	
	// 양쪽 벽 노드 연결되는 트리를 찾기 위한 크루스칼
	public static void MST(){
		//left : 좌측벽을 노드로 지정 주소값은 N
		//right : 우측벽을 노드로 지정 주소값은 N+1
		left = N;
		right = N+1;
		//부모배열에도 동일하게 초기화를 시켜줌
		parents[left] = N;
		parents[right] = N+1;
		
		//센서의 값을 좌 우측벽과의 차이값과 센서들간의 차이값으로 간선가중치를 Queue에 저장한다.
		for(int cnt=0; cnt<sensorArr.size(); cnt++){
			sensor from = sensorArr.get(cnt);
			edgeQueue.add(new Edge(cnt, left, Math.max(0, from.x-from.r))); //해당 센서와 좌측 벽 노드간의 간선 가중치 차이 계산
			edgeQueue.add(new Edge(cnt, right, Math.max(0, W-from.x-from.r)));//해당 센서와 우측 벽 노드간의 간선 가중치 차이 계산
			//해당 센서가 다른 센서와의 거리 차이도 간선 가중치로 저장한다.
			for(int cnt2=0; cnt2<cnt; cnt2++){
				sensor to = sensorArr.get(cnt2);
				//한 개의 센서를 기준으로, 모든 센서들과의 절대적인 거리를 구함  센서는 하나의 Node이기때문에 각각의 반지름을 뺀 값으로 Queue에 저장한다.
				edgeQueue.add(new Edge(cnt, cnt2, calDist(from,to)-from.r-to.r));
			}
		}
		//좌 우측 벽 노드도 W라는 간선가중치로 연결이 가능하기 때문에 Queue에 삽입한다.
		edgeQueue.add(new Edge(left, right, W));
		
		//저장된 노드를 순차적으로 꺼내어 MST를 찾는다.
		while(!edgeQueue.isEmpty()){
			//한 개의 객체를 꺼낸다.
			Edge here = edgeQueue.poll();            
            //합쳐졌을때
			if(union(here.s, here.e)){
				//left와 right 부모배열이 일치되었을때, 트리가 완성되었음을 확인할 수 있다.
				if(find(left)==find(right)){
					solution = here.c/2;
					break;
				}
            }
        }
	}
	
	//Disjoint Union
	static boolean union(int s, int e){
		int RootS = find(s);
		int RootE = find(e);
		
		if(RootS==RootE){
			return false;
		}
		parents[RootS]=RootE;
		return true;
	}
	
	// Disjoint Find
	static int find(int idx){
        if(parents[idx]==idx){
        	return idx;
        }else{
        	return parents[idx] = find(parents[idx]);
        } 
    }
	
	//거리 차이 계산 로직 From 과 To 간의 거리 차이 계산 
	public static double calDist(sensor from, sensor to){
		double dist = Math.sqrt(Math.pow(to.y-from.y,2)+Math.pow(to.x-from.x,2));
		return dist;
	}
	
	
	//초기 센서 값 Class
	public static class sensor{
		int x;
		int y;
		int r;
		sensor(int x, int y, int r){
			super();
			this.x=x;
			this.y=y;
			this.r=r;
		}
	}
	
	//MST Node Class
	public static class Edge implements Comparable<Edge>{
		int s; //Node A
		int e; //Node B
		double c; //간선가중치 Node 간의 거리 값
		
		public Edge(int s, int e, double c){
			super();
			this.s=s;
			this.e=e;
			this.c=c;
		}
		
		//동일한 오름차순이나, double 형태는 아래와 같이 표현한다.
		public int compareTo(Edge other) {
			if(this.c>other.c){
				return 1;
			}
			return -1;
		}
	}
}
