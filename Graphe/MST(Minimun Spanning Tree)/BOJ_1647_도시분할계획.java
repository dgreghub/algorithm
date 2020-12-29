import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//Signed-off-by: Greg <dgreg.shin@gmail.com>
//백준 1647 도시 분할 계획  
public class BOJ_1647_도시분할계획{
	
	static ArrayList <town> townArr;
	static int parents[];
	static int solution,N,M;
	public static void main(String [] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M  =Integer.parseInt(st.nextToken());
		townArr = new ArrayList<town>();
		parents = new int [N+1];
		solution=0;
		for(int cnt=0; cnt<M; cnt++){
			StringTokenizer house = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(house.nextToken());
			int b = Integer.parseInt(house.nextToken());
			int c = Integer.parseInt(house.nextToken());
			townArr.add(new town(a,b,c));
		}
		Collections.sort(townArr);
		MST();
		System.out.println(solution);
	}
	public static void MST(){
		//1. Root 배열 정리
		int latestV=0;
		for(int cnt=1; cnt<N+1; cnt++){
			parents[cnt]=cnt;
		}
		//2.Union-Find
		for(int cnt=0; cnt<townArr.size(); cnt++){
			int aR = find(townArr.get(cnt).a);
			int bR = find(townArr.get(cnt).b);
			
			if(aR==bR){
				continue;
			}
			parents[aR]=bR;
			solution +=townArr.get(cnt).c;
			latestV=townArr.get(cnt).c;
		}
		solution = solution-latestV; 
	}
	
	public static int find(int idx){
		if(parents[idx]==idx){
			return idx;
		}else{
			return parents[idx]=find(parents[idx]);
		}
	}
	public static class town implements Comparable<town>{
		int a;
		int b;
		int c;
		public town(int a, int b, int c){
			this.a=a;
			this.b=b;
			this.c=c;
		}
		public int compareTo(town other) {
			return this.c-other.c;
		}
	}
}