
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11053_가장긴증가하는부분수열 {
	public static void main(String []args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int N = Integer.parseInt(br.readLine());
		int arr[] =new int [N+1];
		int dp[] = new int [N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++){
			arr[i]=Integer.parseInt(st.nextToken());
		}
		int max=1;
		for(int i=1; i<N+1; i++){
			dp[i]=1;
			for(int j=1; j<i; j++){
				if(arr[i]>arr[j]&&dp[i]<=dp[j]){
					dp[i]=dp[j]+1;
					if(max<dp[i])max=dp[i];
				}
			}
			
		}
		System.out.println(max);
	}
}