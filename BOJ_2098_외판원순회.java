import java.io.*;
import java.util.StringTokenizer;

public class BOJ_2098_외판원순회{
	
	static int V;
    static int[][] m;
    static int[][] dp;
    static int end;
 
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         
        //V는 최대, 16입니다.
        V = Integer.parseInt(st.nextToken());              
        //노드마다 갈 수 있는 간선의 값을 저장하기위한 배열입니다.
        m = new int[V + 1][V + 1];
        // DP 배열의 크기는 [현재위치][2진수 나타낼수 있는 2의V승 크기] 로 나타냅니다.
        // 비트 연산은 다음과 같습니다 V=4 인 경우,  1<<4 0001 -> 10000 로 크기가 설정이 됩니다.
        dp = new int[V + 1][(1 << V) + 1];
 
        //V 크기만큼 순회하다 V*V 의 데이터 값을 삽입합니다.
        //자기 자신인 경우 0으로 유지하거나 Max 값으로 Setting합니다.
        for (int i = 1; i <= V; i++)
        {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= V; j++) {
                m[i][j] = Integer.parseInt(st.nextToken());
                if (m[i][j] == 0)
                    m[i][j] = 987654321;
            }
        }
 
        // 어디를 시작점으로 하든 사이클을 구하는 것이므로 한 번만 돌면 답을 구할 수 있음
        end = 1;
        //DFS 순환 탐색으로 DP[1][1]에 가장 적합한 값을 누적합니다.
        System.out.println(shortestPath(1, 1));    
    }
 
    //DFS 함수
    static int shortestPath(int here, int visited) {
 
        // 모든 도시를 다 방문했다면 첫 번째 도시로 돌아가고 종료
        // 비트 연산 1<<16 -1 인 경우 1111....11 과 동일하므로 탈출 조건 성립
        if (visited == ((1 << V) - 1))
            return m[here][end];
 
        // 이미 방문했던 곳이면 dp값을 Return
        if (dp[here][visited] != 0)
            return dp[here][visited];
        
        dp[here][visited] = 987654321;
        //DFS 탐색
        for (int next = 1; next <= V; next++) {
            // 해당 도시를 이미 방문했다면 더이상 방문하지 않음
        	// 비트연산 &를 통해 next값을 1~V 까지 순차적으로 움직이며, 해당 값이 존재할 경우 DFS 를 타지 않음
        	// 이유는 이미 조합으로 가지고 있는 경우의 수기 때문에
            if ((visited & (1 << (next - 1))) >= 1)
                continue;  
            // 그렇지 않을 경우
            int nextIdx = 1 << (next - 1);
            dp[here][visited] = Math.min(dp[here][visited], m[here][next] + shortestPath(next, visited|nextIdx ));                  
        }                        
        return dp[here][visited];
    }
}
