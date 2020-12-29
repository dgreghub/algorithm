import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697_숨바꼭질  {

	static int N;
	static int K;
	static boolean [] check = new boolean [100001];  //이미 방문한 지점 체크
	static int [] second = new int [100001]; 		 //누적된 초 정보
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //스타트 숨바꼭질
		K = Integer.parseInt(st.nextToken()); //술래지점
		System.out.println(bfs(N));
	}
	
	static int bfs(int start){
		Queue <Integer> queue = new LinkedList <Integer>();
		int solution=0;
		check [start]= true;
		second[start]= 0;
		queue.add(start);
		while(!queue.isEmpty()){
			int guide = queue.poll();
			int plus = guide+1;
			int minus = guide-1;
			int max = guide*2;
			if(plus<100001 && check[plus]==false){
				check[plus]=true;
				second[plus]= second[guide]+1;
				queue.add(plus);
				if(K==plus){
					solution=second[plus];
					break;
				}
			}
			if(minus>=0 && check[minus]==false){
				check[minus]=true;
				second[minus]= second[guide]+1;
				queue.add(minus);
				if(K==minus){
					solution=second[minus];
					break;
				}
			}
			if(max<100001 && check[max]==false){
				check[max]=true;
				second[max]= second[guide]+1;
				queue.add(max);
				if(K==max){
					solution=second[max];
					break;
				}
			}
		}
		return solution;
	}
}
