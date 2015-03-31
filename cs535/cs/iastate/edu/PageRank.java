package pa3.cs535.cs.iastate.edu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class PageRank {
	
	private HashMap<Integer,Vertex> graph;
	private String graphFileName;
	
	public PageRank(String graph){
		this.graphFileName = graph;
	}
	
	public float pageRankOf(String vertex){
		Vertex v = graph.get(vertex.hashCode());
		return v.getRank();
	}
	public int outDegreeOf(String vertex){
		Vertex v = graph.get(vertex.hashCode());
		return v.getOutDegree();
	}
	public int inDegreeOf(String vertex){
		Vertex v = graph.get(vertex.hashCode());
		return v.getInDegree();
	}
	
	public int NumEdges(){
		Vertex v;
		int edgeCount = 0;
		for(Integer i:this.graph.keySet()){
			v = graph.get(i);
			edgeCount = edgeCount + v.getNextVertices().size();
		}
		return edgeCount;
	}
	
	public void topKInDegree(int k){
		Vertex v;
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		for(Integer i:this.graph.keySet()){
			v = graph.get(i);
			vertices.add(v);
		}
		Collections.sort(vertices,Vertex.inDegreeComparator);
		for(int i=0;i<k;i++){
			System.out.println(vertices.get(i));
		}
	}
}
