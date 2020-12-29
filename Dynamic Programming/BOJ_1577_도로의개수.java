import java.util.*;
import java.io.*;


public class BOJ_1577_도로의개수{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String args[]) throws IOException	{	
		int N, M, x1, x2, y1, y2, i, j;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//초기값 설정
		long array[][] = new long[N + 1][M + 1];
		//1은 아래쪽 방향
		//0은 오른쪽 방향
		int check[][][] = new int[N + 1][M + 1][2];
		array[0][0] = 1;
		int K = Integer.parseInt(br.readLine());
		//막힌 도로 분기 설정
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			if (y1 == y2) {
				//1. (1) 아래쪽 방향에 못가는 곳을 체크함
				if (x1 > x2)
					check[x2][y1][0] = -1;
				else
					check[x1][y1][0] = -1;
			} else {
				//2. (0) 오른쪽으로 못가는 곳을 체크함
				if (y1 > y2)
					check[x1][y2][1] = -1;
				else
					check[x1][y1][1] = -1;
			}
		}
		//N*M 만큼 순회하며 DP[N][M]의 값을 찾음
		//점화식  : DP[N][M] = DP[N-1][M] + DP[N][M-1]
		//항상 도로를 정확하게 N + M개 거치기 때문에 방향은 무조건 오른쪽과 아래쪽 방향이다.
		for (i = 0; i <= N; i++) {
			for (j = 0; j <= M; j++) {
				//1. DP[0][0]은 이미 Default 값 지정이 되어있으니 Pass
				if (i == 0 && j == 0)
					continue;
				//2. Y축이 0인 경우, 
				else if (i != 0 && j == 0) {
					if (check[i - 1][j][0] != -1)
						array[i][j] = array[i - 1][j];
				//3. X축이 0인 경우
				} else if (i == 0 && j != 0) {
					if (check[i][j - 1][1] != -1)
						array[i][j] = array[i][j - 1];
				//4. 좌측과 위에서 내려오는 값이 각기 올 수있는지 체크하여 
				//   점화식을 수행함
				} else {
					// 좌측에서 온 값 OK?
					if (check[i - 1][j][0] != -1)
						array[i][j] += array[i - 1][j];
					// 위에서 온 값 OK?
					if (check[i][j - 1][1] != -1)
						array[i][j] += array[i][j - 1];
				}
			}
		}
		System.out.println(array[N][M]);
	}
}