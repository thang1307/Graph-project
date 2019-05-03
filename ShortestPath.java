import java.util.*; 
import java.lang.*; 
import java.io.*; 

class ShortestPath { 
	public Writer fileOut;
	int V;
	int [][] Graph;
	public void writeFile()
            {
				try{
					int[] n = dijkstra(Graph,0);
					fileOut.write("s->t:"+n[3]);          
                      }catch(IOException e)
                          {   System.out.println(e); }
			}
	public ShortestPath(String output_name)
            {
                try{
                           fileOut = new FileWriter(output_name); 
                    }catch(IOException e)
                       {    System.out.println(e); }
                   
            }
	
	public void ShortestPath1(String fn){
		BufferedReader br;
		try{
			br = new BufferedReader(new FileReader(fn));
			String s = br.readLine();
			V = Integer.parseInt(s);
			Graph = new int[V][V];
			for (int i = 0; i < V; i++){
				String line = br.readLine();
				String[] strE = line.split(" ");
				for(int j = 0; j < strE.length; j++)
				{	
					Graph[i][j] = Integer.parseInt(strE[j]);
				}
			}								
			br.close();
		}catch(IOException e){};
	} 

	int minDistance(int dist[], Boolean sptSet[]) 
    { 
        int min = Integer.MAX_VALUE, min_index=-1; 
  
        for (int v = 0; v < V; v++) 
            if (sptSet[v] == false && dist[v] <= min) 
            { 
                min = dist[v]; 
                min_index = v; 
            } 
  
        return min_index; 
    } 

    public int[] dijkstra(int graph[][], int src) 
    { 
        int dist[] = new int[V]; 
        Boolean sptSet[] = new Boolean[V]; 

        for (int i = 0; i < V; i++) 
        { 
            dist[i] = Integer.MAX_VALUE; 
            sptSet[i] = false; 
        } 

        dist[src] = 0; 

        for (int count = 0; count < V-1; count++) 
        { 
            int u = minDistance(dist, sptSet); 
            sptSet[u] = true; 
            for (int v = 0; v < V; v++) 
                if (!sptSet[v] && graph[u][v]!=0 && 
                        dist[u] != Integer.MAX_VALUE && 
                        dist[u]+graph[u][v] < dist[v]) 
                    dist[v] = dist[u] + graph[u][v]; 
        } 
		return dist;

	}
	
	public static void main(String[] args) 
	{ 
		String input_name = args[0];
        String output_name = args[1];
		ShortestPath project = new ShortestPath(output_name);
		try{
			 project.ShortestPath1(input_name);
			 project.writeFile();
             project.fileOut.close(); 
            }catch(IOException e)
            {
               System.out.println(e);
            }
	} 
} 

