package pa3.cs535.cs.iastate.edu;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class WikiRanker {
	public static void main(String args[]){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String graphFilePath;
		System.out.println("Please input the graph file path:");
		try {
			graphFilePath = br.readLine();
//			File file = new File(filePath);
//			String graphFileName = "D:\\Dropbox\\Courses\\CS535x\\ProgrammingAssignment\\p3\\PavanWikiTennis.txt";;
			Graph graph = new Graph(graphFilePath);
//			graph.printGraph();
			graph.topKInDegree(100);
			System.out.println();
			graph.topKOutDegree(100);
			
			PageRank pr = new PageRank(graph);
			pr.pageRank((float)0.85, (float)0.1, graph);
			pr.topKPageRank(100);
			
			pr.pageRank((float)0.85, (float)0.05, graph);
			pr.topKPageRank(100);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
